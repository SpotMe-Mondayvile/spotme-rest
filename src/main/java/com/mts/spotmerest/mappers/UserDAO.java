package com.mts.spotmerest.mappers;

import com.mts.spotmerest.models.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO
        extends JpaRepository<User,Long> {

        @Query(value = "SELECT s FROM User s WHERE s.username = :name")
        Optional<User> findUserByUserName(@Param("name") String username);

        @Query(value = "SELECT s FROM User s WHERE s.email = :email")
        Optional<User> findUserByUserEmail(@Param("email") String email);

        Optional<User> findByEmail(String email);

        @NonNull
        Optional<User> findById(Long id);
}

