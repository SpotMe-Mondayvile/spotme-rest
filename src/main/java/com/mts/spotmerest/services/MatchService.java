package com.mts.spotmerest.services;

import com.mts.spotmerest.mappers.MatchDAO;
import com.mts.spotmerest.models.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchDAO matchDAO;

    @Autowired
    public MatchService(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> getMatches() {
        return matchDAO.findAll();
    }

    public void addNewMatch(Match match) {
        Optional<Match> matchById = matchDAO
                .findMatchById(match.getId());
        if (matchById.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        matchDAO.save(match);
    }

    public void deleteMatch(Long id) {
        boolean exists = matchDAO.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Match with id " + id + "does not exist");
        } else {
            matchDAO.deleteById(id);
        }
    }

    public Optional<Match> getMatchById(Long id) {
        return matchDAO.findById(id);
    }
    public List<Optional<Match>> getMatchesByAuthorId(Long id) {
        return matchDAO.findMatchByAuthorId(id);
    }

    public List<Optional<Match>> getMatchesBySpotId(Long id) {
        return matchDAO.findAllBySpotId(id);
    }
}



