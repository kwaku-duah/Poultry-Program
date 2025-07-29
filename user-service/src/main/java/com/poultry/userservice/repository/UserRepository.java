package com.poultry.userservice.repository;

import com.poultry.userservice.UserResponse;
import com.poultry.userservice.entity.Role;
import com.poultry.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findAllByRoles(Set<Role> roles);
}
