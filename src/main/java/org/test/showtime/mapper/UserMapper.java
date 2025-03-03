package org.test.showtime.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.test.showtime.dto.UserDTO;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userLocation", ignore = true)
    User getUserEntity(UserDTO userDto);

    @Mapping(target = "location", source = "userLocation", qualifiedByName = "locationTypeToId")
    UserDTO getUser(User user);

    @Named("locationTypeToId")
    public static Long locationTypeToId(Location location) {
        return location.getId();
    }

//    @Named("locationIdToType")
//    public static Long locationIdToType(Long location) {
//        return
//    }
}