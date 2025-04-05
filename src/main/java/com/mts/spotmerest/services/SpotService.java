package com.mts.spotmerest.services;

import com.mts.spotmerest.mappers.SpotDAO;
import com.mts.spotmerest.mappers.UserDAO;
import com.mts.spotmerest.models.Match;
import com.mts.spotmerest.models.Spot;
import com.mts.spotmerest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SpotService {

    private final SpotDAO spotDAO;
    private final UserDAO userDAO;

    @Autowired
    public SpotService(SpotDAO spotDAO, UserDAO userDAO){
        this.spotDAO = spotDAO;
        this.userDAO= userDAO;
    }

    public List<Spot> getSpots(){

        return spotDAO.findAll();
    }
    public Optional<Spot> getSpot(Long id){

        return spotDAO.findById(id);
    }
//
//    public List<Match> getSpotMatchesByID(Long userId){
//        Optional<Spot> spot= spotDAO.findSpotByUserId(userId);
//        return spot.orElseThrow().getMatches();
//    }

    public List<Match> getSpotMatchesByUserID(Long spotID){
        Optional<Spot> spot= spotDAO.findById(spotID);
        return spot.orElseThrow().getMatches();
    }
    public List<Optional<Spot>> getSpotMatchesByUserEmail(String email){
        Optional<User> user = userDAO.findUserByUserEmail(email);
        List<Optional<Spot>> spots= spotDAO.findSpotByUserId(user.orElseThrow().getId());
        return spots;
    }

    public void updateSpot(Long id, Spot spotIn){
        Optional<Spot> spot = spotDAO.findById(id);
        Spot updated = spot.orElseThrow().updateAttributes(spotIn);
        spotDAO.save(updated);
    }


    public void addNewSpot(Spot spot) {
        Optional<Spot> spotById= spotDAO
                .findSpotById(spot.getId());
        if(spotById.isPresent()){
            throw new IllegalStateException("Spot Already exists");
        }else{
            spotDAO.save(spot);
            System.out.println("Spot Created");
        }
        spotDAO.save(spot);
    }

    public void deleteSpot(Long id) {
        boolean exists = spotDAO.existsById(id);
        if(!exists){
            throw new IllegalStateException("Spot with id "+ id+ "does not exist");
        }else{
            spotDAO.deleteById(id);
        }
    }

}