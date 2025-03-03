package org.test.showtime.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Table(name = "show_details", schema = "theatre_partner")
@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class ShowDetails {
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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "theatre")
//    private Theatre theatre;

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private long version;
}