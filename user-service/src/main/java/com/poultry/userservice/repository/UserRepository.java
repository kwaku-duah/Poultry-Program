package com.poultry.userservice.repository;

import com.poultry.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
