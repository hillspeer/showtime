package org.test.showtime.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Table(name = "show", schema = "theatre_partner")
@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @JsonProperty("title")
    @Column(nullable = false)
    private String title;

    @JsonProperty("description")
    @Column(nullable = false)
    private String description;

    @JsonProperty("genre")
    @Column(nullable = false)
    private String genre;

    @Column( name = "start_time")
    private Date start_time;

    @Column( name = "duration")
    private Duration duration;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "fare")
    private Double fare;

    @JsonProperty("locationId")
    @Transient
    private Long locationId;

    @JsonProperty("theatreId")
    @Transient
    private Long theatreId;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;

    @OneToMany
    @JoinColumn(name = "show_seat")
    private List<ShowSeatAvailability> showSeat;

    @Column
    private Long screenId;

    public Show(Long id, String title, String description, String genre, Date start_time, Duration duration, Long rating, Double fare, Long locationId, Long theatreId, Long screenId, Location location, List<ShowSeatAvailability> showSeat) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.start_time = start_time;
        this.duration = duration;
        this.rating = rating;
        this.fare = fare;
        this.locationId = locationId;
        this.theatreId = theatreId;
        this.location = location;
        this.showSeat = showSeat;
        this.screenId = screenId;
    }

    public Show() {

    }

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "theatre")
//    private Theatre theatre;

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private long version;
}