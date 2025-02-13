package com.nagarpalika.mapper;

import com.nagarpalika.dto.UserDto;
import com.nagarpalika.dto.UserRequestDto;
import com.nagarpalika.model.Role;
import com.nagarpalika.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // ✅ Convert User entity → UserDto (Response)
    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getRole(),
                user.getIsActive()
        );
    }

    // ✅ Convert UserRequestDto → User entity (For Creating a New User)
    public static User fromRequestDto(UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // Encode password in service before saving
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setRole(Role.valueOf(dto.getRole().toUpperCase())); // Convert String to Enum
        user.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true); // Default true
        return user;
    }

    // ✅ Convert UserDto → User entity (For Updating an Existing User)
    public static User fromDto(UserDto userDto) {
        User user = new User();
        user.setId(userDto.id());
        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setPhone(userDto.phone());
        user.setAddress(userDto.address());
        user.setRole(userDto.role());
        user.setIsActive(userDto.isActive());
        return user;
    }
}
