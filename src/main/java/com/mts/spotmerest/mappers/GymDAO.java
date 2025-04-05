package com.mts.spotmerest.mappers;

import com.mts.spotmerest.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymDAO
        extends JpaRepository<Gym,Long> {

//        @Query(value = "SELECT s FROM _gym s WHERE s.gym_id = :id")
//        Optional<Gym> findGymByGymId(@Param("id") String id);

        Optional<Gym> findGymById(Long id);
}

