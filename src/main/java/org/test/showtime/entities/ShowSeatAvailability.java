package org.test.showtime.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;
import java.util.Date;
//uniqueConstraints = { @UniqueConstraint(columnNames = { "personNumber", "isActive" }) }
@Table(name = "show_seat_availability", schema = "theatre_partner", uniqueConstraints = {@UniqueConstraint(columnNames = {"seat_id","show_id"})} )
@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class ShowSeatAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @JsonProperty("showId")
    @ManyToOne
    @JoinColumn(name = "showId")
    @Getter
    private Show showId;

    @JsonProperty("seatId")
    @ManyToOne
    @JoinColumn(name = "seatId")
    @Getter
    private Seat seatId;

    @Column(nullable = false)
    private String reservationStatus;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "theatre")
//    private Theatre theatre;

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private long version;
}