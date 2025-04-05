package com.mts.spotmerest.mappers;

import com.mts.spotmerest.models.Spot;
import com.mts.spotmerest.models.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStatsDAO extends JpaRepository<UserStats, Long> {

    Optional<UserStats> findUserStatsById(Long id);
    Optional<UserStats> findUserStatsByUserId(Long userId);

    @Query("UPDATE User u SET u.name = :name WHERE u.id = :id")
    void updateNameById(String name, Long id);
}
