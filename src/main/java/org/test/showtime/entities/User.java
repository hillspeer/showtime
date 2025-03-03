package org.test.showtime.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Table(name = "profile", schema = "customer")
@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @JsonProperty("fullName")
    @Column(nullable = false)
    private String fullName;

    @JsonProperty("email")
    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @JsonProperty("password")
    @Getter
    @Column(nullable = false)
    private String password;

    @Column(name = "prefered_genre")
    private String prefered_genre;

    @Column( name = "address_line1")
    private String address_line1;

    @Column( name = "address_line2")
    private String address_line2;

    @ManyToOne
    @JoinColumn(name = "user_location")
    private Location userLocation;

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private long version;
}