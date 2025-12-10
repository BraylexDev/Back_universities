package com.universities.universities.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universities.universities.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    public Optional<User> findByUserName(String userName);

    public User findByUserNameAndPassword(String userName, String Password);
    
    public Optional<User> findByUserNameOrEmail(String userName, String email);

    public boolean existsByUserName(String userName);
    public boolean existsByEmail(String email);
}
