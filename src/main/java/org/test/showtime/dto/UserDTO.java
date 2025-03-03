package org.test.showtime.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@Getter
@Setter
public class UserDTO {

    public UserDTO(String fullName, String email, String password, String prefered_genre, String address_line1, String address_line2, Long location) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.prefered_genre = prefered_genre;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.location = location;
    }

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    @Getter
    @Setter
    private String password;

    private String prefered_genre;

    private String address_line1;

    private String address_line2;

    private Long location;

}