package com.junior.projeto1.repository;

import com.junior.projeto1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}