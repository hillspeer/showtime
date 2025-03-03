package org.test.showtime.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ManyToAny;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Table(name = "seat", schema = "theatre_partner")
@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @JsonProperty("status")
    @Column(nullable = false)
    private String status;

    @Column(name = "row_num")
    private Long row_num;

    @Column(name = "seat_num")
    private Long seat_num;

    @Column
    @Getter
    @Setter
    private Long screenId;

    @OneToMany
    @JoinColumn(name = "show_seat")
    private List<ShowSeatAvailability> showSeat;

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private long version;
}
