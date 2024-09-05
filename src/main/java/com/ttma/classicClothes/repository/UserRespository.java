package com.ttma.classicClothes.repository;

import com.ttma.classicClothes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
   Optional<User> findByEmail(String email);
   Optional<User> findByUsername(String username);
}
