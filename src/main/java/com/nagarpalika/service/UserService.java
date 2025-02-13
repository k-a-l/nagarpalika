package com.nagarpalika.service;

import com.nagarpalika.dto.UserDto;
import com.nagarpalika.dto.UserRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto registerUser(UserRequestDto userRequestDto); // ✅ Register user

    Optional<UserDto> getUserById(Long id); // ✅ Prevents null pointer exceptions

    Optional<UserDto> getUserByEmail(String email); // ✅ Safer than returning null

    List<UserDto> getAllUsers(); // ✅ Get list of all users

    UserDto updateUser(Long id, UserRequestDto userRequestDto); // ✅ Update existing user

    boolean deleteUser(Long id); // ✅ Return success/failure
}
