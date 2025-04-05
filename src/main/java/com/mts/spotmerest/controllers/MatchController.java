package com.mts.spotmerest.controllers;

import com.mts.spotmerest.auth.DataFilter;
import com.mts.spotmerest.models.Match;
import com.mts.spotmerest.models.Spot;
import com.mts.spotmerest.services.MatchService;
import com.mts.spotmerest.services.SpotService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
@SecurityRequirement(name = "JWT")
@RequestMapping(path = "api/v1/match")
public class MatchController {
    private final MatchService matchService;
    private final DataFilter dataFilter;
    private final SpotService spotService;
   @Autowired
    public MatchController(MatchService matchService, SpotService spotService, DataFilter dataFilter){
        this.matchService= matchService;
       this.spotService = spotService;
       this.dataFilter=dataFilter;
   }

    @GetMapping
    public String printHello(){
       return "User Web Controller";
    }

    @GetMapping(path="/all")
    public List<Optional<Match>> getUsers(Principal principal){
        List<Optional<Match>> validatedMathches = new ArrayList<Optional<Match>>();
        Long owner = dataFilter.getPrincipalId(principal);
        if (dataFilter.isUser(principal,owner)) {
            validatedMathches = matchService.getMatchesByAuthorId(owner);
        }
        return  validatedMathches;
    }

    @GetMapping(path= "by/author/{authorId}")
    public List<Optional<Match>> getSpotByAuthorID(@PathVariable("authorId") Long id, Principal principal) {
        List<Optional<Match>> validatedMathches = new ArrayList<Optional<Match>>();
        if (dataFilter.isUser(principal, id)) {
            validatedMathches = matchService.getMatchesByAuthorId(id);
        }
        return  validatedMathches;
    }

    @GetMapping(path= "by/spot/{spotId}")
    public List<Optional<Match>> getMatchesBySpotId(@PathVariable("spotId") Long id, Principal principal) throws Exception {
        List<Optional<Match>> validatedMathches = new ArrayList<Optional<Match>>();
        Optional<Spot> spot =spotService.getSpot(id);
        if (dataFilter.isUser(principal, spot.orElseThrow().getUserId())) {
            validatedMathches = matchService.getMatchesBySpotId(id);
        }
        if(validatedMathches.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cause description here");
        }
        return validatedMathches;
    }

    @GetMapping(path = "id/{matchId}")
    public Optional<Match> getMatchByID(@PathVariable("matchId")Long id, Principal principal){
        Optional<Match> validatedMatch = Optional.empty();
        Optional<Match> requestedMatch = matchService.getMatchById(id);
        Long owner =  requestedMatch.orElseThrow().getAttendeeId();
        if(dataFilter.isUser(principal,owner)){
            validatedMatch=matchService.getMatchById(id);
        }
       return validatedMatch;
    }

    @PostMapping(path="/add")
    public void newMatch(@RequestBody Match match, Principal principal){
       System.out.println(match);
        if(dataFilter.isUser(principal,match.getAuthorId())){
            matchService.addNewMatch(match);
        }
       matchService.addNewMatch(match);
    }

    @DeleteMapping(path= "/{match_id}")
    public void deleteMatch(@PathVariable("match_id") Long id, Principal principal){
       Optional<Match> validatedMatch = Optional.empty();
       Optional<Match> requestedMatch = matchService.getMatchById(id);
       Long owner =  requestedMatch.orElseThrow().getSpot().getUserId();
        if(dataFilter.isUser(principal,owner)){
            validatedMatch=matchService.getMatchById(id);
        }
       matchService.deleteMatch(id);
    }
}
