package org.test.showtime.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "partner", schema = "theatre_partner")
@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Partner {
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

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private long version;

}