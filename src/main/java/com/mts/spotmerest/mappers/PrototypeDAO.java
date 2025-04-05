package com.mts.spotmerest.mappers;

import com.mts.spotmerest.models.Prototype;
import com.mts.spotmerest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrototypeDAO
        extends JpaRepository<Prototype,Long> {

        @Query(value = "SELECT s FROM User s WHERE s.username = :name")
        Optional<Prototype> findUserByUserName(@Param("name") String username);
}

