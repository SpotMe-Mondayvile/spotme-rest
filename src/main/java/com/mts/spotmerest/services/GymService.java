package com.mts.spotmerest.services;

import com.mts.spotmerest.mappers.GymDAO;
import com.mts.spotmerest.mappers.UserDAO;
import com.mts.spotmerest.models.Gym;
import com.mts.spotmerest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymService {

    private final GymDAO gymDAO;

    @Autowired
    public GymService(GymDAO gymDAO){
        this.gymDAO = gymDAO;
    }

    public  Optional<Gym> getGymById(Long id){

        return gymDAO.findGymById(id);
    }


    public void addNewGym(Gym gym) {
        Optional<Gym> userByUserName= gymDAO
                .findGymById(gym.getId());
        if(userByUserName.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        gymDAO.save(gym);
    }

    public void deleteGym(Long id) {
     boolean exists = gymDAO.existsById(id);
     if(!exists){
         throw new IllegalStateException("Gym with id "+ id+ "does not exist");
     }else{
         gymDAO.deleteById(id);
        }
     }

    public List<Gym> getGyms() {
        return gymDAO.findAll();
    }
}
