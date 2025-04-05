package com.mts.spotmerest.mappers;

import com.mts.spotmerest.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MatchDAO extends JpaRepository<Match, Long> {
    Optional<Match> findMatchById(Long id);
    List<Optional<Match>> findMatchByAuthorId(Long id);

//    @Query("from matches u where u.spot.id = :spot_id")
//    List<Optional<Match>> findMatchBySpotId(@Param("spot_id")Long spot_id);
    List<Optional<Match>> findAllBySpotId(Long spot_id);
}
