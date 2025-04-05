package com.mts.spotmerest.mappers;

import com.mts.spotmerest.models.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpotDAO extends JpaRepository<Spot, Long> {

    Optional<Spot> findSpotById(Long id);
    List<Optional<Spot>> findSpotByUserId(Long userId);
//    Optional<Spot> findSpotByUserId(Long userId);
}
