package org.test.showtime.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Table(name = "theatre", schema = "theatre_partner")
@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @JsonProperty("name")
    @Column(nullable = false)
    private String name;

    @Column( name = "address_line1")
    private String address_line1;

    @Column( name = "address_line2")
    private String address_line2;

    @JsonProperty("locationId")
    @Transient
    private Long locationId;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;

//    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Show> shows;

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private long version;
}