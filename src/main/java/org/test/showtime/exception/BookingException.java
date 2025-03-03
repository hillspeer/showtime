package org.test.showtime.exception;

public class BookingException extends RuntimeException {

    public BookingException(String message) {
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
