package com.mts.spotmerest.mappers;

import com.mts.spotmerest.models.Routine;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoutineDAO
        extends JpaRepository<Routine, Long>//gives CRUD capabilities to routine
{     //when pulling from DB. assuming we would
    ///for routineDAO, consider whether we need to specify by userID number


    void deleteByRoutineName(String routineName);
    void deleteById(Long id);

    Optional<Routine> findByRoutineName(String routineName);
    Optional<Routine> findById(Long id);

    List<Optional<Routine>> findAllByUserId(Long userId);
}