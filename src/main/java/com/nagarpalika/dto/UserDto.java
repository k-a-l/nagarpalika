package com.nagarpalika.dto;

import com.nagarpalika.model.Role;


public record UserDto (
        Long id,
        String username,
        String email,
        String phone,
        String address,
        Role role,
        Boolean isActive
){}
