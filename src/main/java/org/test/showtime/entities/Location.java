package org.test.showtime.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Table(name = "location", schema = "location")
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Getter
    private Long id;

    @JsonProperty("region")
    @Column(nullable = false)
    @Getter
    private String region;

    @JsonProperty("city")
    @Column(nullable = false)
    private String city;

    @JsonProperty("state")
    @Column(nullable = false)
    private String state;

    @JsonProperty("pincode")
    @Column(nullable = false)
    private Integer pincode;

//    @OneToMany(mappedBy = "location")
//    @JoinColumn(name ="location")
//    @Getter
//    private List<Show> shows;
//
//    @OneToMany(mappedBy = "user_location")
//    @JoinColumn(name ="user_location")
//    @Getter
//    private List<User> users;
    @Column(nullable = false, columnDefinition = "integer default 0")
    @Version
    private long version;

}
