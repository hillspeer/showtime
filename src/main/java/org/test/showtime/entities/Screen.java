package org.test.showtime.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Table(name = "screen", schema = "theatre_partner")
@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @JsonProperty("status")
    @Column(nullable = false)
    private String status;

    @Column(name = "screen_num")
    private Long screen_num;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "show")
    private List<Show> shows;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seats")
    private List<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "theatreId")
    private Theatre theatre;

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private long version;
}
