package com.codecool.giftservice.repository;

import com.codecool.giftservice.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
//    @Query("SELECT gift FROM Gift gift WHERE gift.id = ?1")
//    Gift getGiftById(Long id);

//    @Query("DELETE FROM Gift gift WHERE gift.id = ?1")
    void deleteById(Long id);
}
