
package com.mts.spotmerest.services;

import com.mts.spotmerest.mappers.UserStatsDAO;
import com.mts.spotmerest.mappers.UserDAO;
import com.mts.spotmerest.models.*;
import com.mts.spotmerest.models.UserStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserStatsService {

    private final UserStatsDAO userStatsDAO;
    private final UserDAO userDAO;

    @Autowired
    public UserStatsService(UserStatsDAO userStatsDAO, UserDAO userDAO){
        this.userStatsDAO = userStatsDAO;
        this.userDAO= userDAO;
    }

//    public List<UserStats> getAllUserStats(Long requesterId){
//
//        return userStatsDAO.findAll();
//    }
    public Optional<UserStats> getUserStats(Long statId){

        return userStatsDAO.findById(statId);
    }
//
//    public List<Match> getUserStatsMatchesByID(Long userId){
//        Optional<UserStats> spot= spotDAO.findUserStatsByUserId(userId);
//        return spot.orElseThrow().getMatches();
//    }

    public Optional<UserStats> getUserStatsByUserID(Long userId){

        return userStatsDAO.findUserStatsByUserId(userId);
    }
    public Optional<UserStats> getUserStatsMatchesByUserEmail(String email){
        Optional<User> user = userDAO.findUserByUserEmail(email);
        Optional<UserStats> uStats= userStatsDAO.findUserStatsByUserId(user.orElseThrow().getId());
        return uStats;
    }

    public void updateUserStats(Long id, UserStats statsIn){
        Optional<UserStats> uStats = userStatsDAO.findById(id);
        UserStats updated = uStats.orElseThrow().cloneStatsObject();
        updated.setRating(statsIn.getRating());
        updated.setPrivate(statsIn.getPrivate());
        updated.setDescription(statsIn.getDescription());
        updated.setStatus(statsIn.getStatus());
        userStatsDAO.save(updated);
    }

    public Optional<UserStats> getUserStatsByUserEmail(String email){
        Optional<User> user = userDAO.findUserByUserEmail(email);
        Optional<UserStats> stats= userStatsDAO.findUserStatsByUserId(user.orElseThrow().getId());
        return stats;
    }

    public void addNewUserStats(UserStats stats) {
        Optional<UserStats> userStatsById= userStatsDAO
                .findUserStatsById(stats.getId());
        if(userStatsById.isPresent()){
            throw new IllegalStateException("UserStats Already exists");
        }else{
            userStatsDAO.save(stats);
            System.out.println("UserStats Created");
        }
        userStatsDAO.save(stats);
    }

    public void updateUserStats(UserStats stats) {
        Optional<UserStats> userStatsById= userStatsDAO
                .findUserStatsById(stats.getId());
        if(userStatsById.isPresent()){
            throw new IllegalStateException("UserStats Already exists");
        }else{
            userStatsDAO.save(stats);
            System.out.println("UserStats Created");
        }
        userStatsDAO.save(stats);
    }

    public void updateUserStat(String stat, Object value, Long userId) throws Exception {
        Optional<UserStats> userStatsById= userStatsDAO
                .findUserStatsById(userId);
        if(userStatsById.isPresent()){
            userStatsById.orElseThrow().updateProperty(stat,value);
        }else{
            System.out.println("UserStats Created");
        }
        userStatsDAO.save(userStatsById.orElseThrow());
    }

    public void deleteUserStats(Long id) {
        boolean exists = userStatsDAO.existsById(id);
        if(!exists){
            throw new IllegalStateException("UserStats with id "+ id+ "does not exist");
        }else{
            userStatsDAO.deleteById(id);
        }
    }

}