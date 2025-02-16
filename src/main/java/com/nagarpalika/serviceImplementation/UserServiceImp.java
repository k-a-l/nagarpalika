package com.nagarpalika.serviceImplementation;

import com.nagarpalika.dto.UserDto;
import com.nagarpalika.dto.UserRequestDto;
import com.nagarpalika.mapper.UserMapper;
import com.nagarpalika.model.User;
import com.nagarpalika.repository.UserRepository;
import com.nagarpalika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserRequestDto userRequestDto) {
        User user = UserMapper.fromRequestDto(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user=userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::toUserDto);
    }

    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::toUserDto);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserRequestDto userRequestDto) {
        User existingUser = userRepository.findById(id).orElseThrow( ()->new IllegalStateException("User not found"));
        existingUser.setUsername(userRequestDto.getUsername());
        existingUser.setEmail(userRequestDto.getEmail());
        existingUser.setPhone(userRequestDto.getPhone());
        existingUser.setAddress(userRequestDto.getAddress());
        existingUser.setIsActive(userRequestDto.getIsActive());
        if (userRequestDto.getPassword() != null && !userRequestDto.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        }
        existingUser=userRepository.save(existingUser);

        return UserMapper.toUserDto(existingUser);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
}

