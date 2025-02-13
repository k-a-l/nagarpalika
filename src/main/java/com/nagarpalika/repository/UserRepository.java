package com.nagarpalika.repository;

import com.nagarpalika.model.Role;
import com.nagarpalika.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
    User findByPhone(String phone);
    User findByAddress(String address);

}
