package org.test.showtime.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;

import java.util.List;

@Table(name = "booking", schema = "customer")
@Entity
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Getter
    private Long id;

    @Column
    @JsonProperty("showId")
    @Getter
    @Setter
    private Long showId;

    @JsonProperty("seatIDs")
    @Getter
    @Setter
    private String seatIDs;

    @Column(nullable = false)
    @Getter
    @Setter
    private String status;

    @Column
    @Getter
    @Setter
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(nullable = false, columnDefinition = "integer default 0")
    @Getter
    @Setter
    private Double fare;

    @Column(nullable = false, columnDefinition = "integer default 0")
    @Version
    private long version;
}
