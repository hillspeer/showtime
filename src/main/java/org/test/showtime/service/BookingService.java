package org.test.showtime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.test.showtime.entities.*;
import org.test.showtime.exception.BookingException;
import org.test.showtime.model.Status;
import org.test.showtime.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService {

    Status status;
    @Autowired
    LocationRepository locationrepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> listAll(long userId){
        User user = userRepo.getById(userId);
        List<Booking> bookings = bookingRepository.findAllByUser(user).get();
        return bookings;
    }

    public List<Seat> listAvailableSeats(long showId){
        Show show = showRepository.getById(showId);
        List<ShowSeatAvailability> showseats = show.getShowSeat().stream().filter( ss-> ss.getReservationStatus().equals("Available")).collect(Collectors.toList());
        List<Long> seatIDs = showseats.stream().mapToLong(showseat -> showseat.getSeatId().getId()).boxed().collect(Collectors.toList());

        List<Seat> seats = seatRepository.getSeatsByIdIsIn(seatIDs);

        return seats;
    }

    public Long save(Booking booking) throws BookingException{
        Status status;
        //List<Long> seatIds = booking.getSeats().stream().mapToLong(seat-> seat.getId()).boxed().collect(Collectors.toUnmodifiableList());

        Show show = showRepository.findById(booking.getShowId()).get();
        booking.setShowId(show.getId());
        List<Seat> seats = seatRepository.getSeatsByIdIsIn(Arrays.stream(booking.getSeatIDs().split(",")).mapToLong(Long::valueOf).boxed().collect(Collectors.toList()));

        List<ShowSeatAvailability> ssa = showSeatRepository.findByShowIdAndReservationStatusAndSeatIdIn(show, Status.AVAILABLE.toString(), seats);

        if(ssa.size() < seats.size()){
            throw new BookingException("1 or more seats not available");
        }

        if(null== booking.getPaymentStatus() ||
                booking.getPaymentStatus().equalsIgnoreCase("Pending")) {
            status = Status.RESERVED;
            booking.setStatus(Status.RESERVED.toString());
        }
//       else if(booking.getStatus().equalsIgnoreCase("Paid")){
//            status = Status.CONFIRMED;
        else {
            status = Status.AVAILABLE;
        }

        if(show.getStart_time().getHours()>12 && show.getStart_time().getHours()<15 ) {
            booking.setFare( show.getFare() - (show.getFare() * .2) );
        }

        ssa.stream().forEach(ssa1 -> {
            ssa1.setReservationStatus(status.toString());
        });
        //showRepository.save(show);
        showSeatRepository.saveAll(ssa);
        bookingRepository.save(booking);
        return booking.getId();
    }

    public void cancel(Show show){
        //List<Long> seatIds = booking.getSeats().stream().mapToLong(seat-> seat.getId()).boxed().collect(Collectors.toUnmodifiableList());

        List<Booking> bookings = bookingRepository.getBookingByShowId(show.getId());

        for (Booking booking : bookings) {
            booking.setStatus("CANCELLED");
            List<ShowSeatAvailability> ssa = showSeatRepository.findByShowId(show);
            ssa.stream().forEach(ssa1 -> {ssa1.setReservationStatus("Available");});
            showSeatRepository.saveAll(ssa);
        }


        bookingRepository.saveAll(bookings);
    }
}
