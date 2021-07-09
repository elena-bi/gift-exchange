package com.codecool.userservice.repositories;

import com.codecool.userservice.model.Gift;
import com.codecool.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    User findUserByGiftsContains(Gift gift);
}
