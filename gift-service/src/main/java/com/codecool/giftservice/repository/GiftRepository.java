package com.codecool.giftservice.repository;

import com.codecool.giftservice.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {

    void deleteById(Long id);

    Gift getById(Long id);
}
