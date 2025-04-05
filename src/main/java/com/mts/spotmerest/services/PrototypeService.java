package com.mts.spotmerest.services;

import com.mts.spotmerest.mappers.PrototypeDAO;
import com.mts.spotmerest.models.Prototype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrototypeService {

    private final PrototypeDAO prototypeDAO;

    @Autowired
    public PrototypeService(PrototypeDAO prototypeDAO){
        this.prototypeDAO = prototypeDAO;
    }

    public List<Prototype> getPrototypes(){

        return prototypeDAO.findAll();
    }

    public void addNewPrototype(Prototype prototype) {
        Optional<Prototype> prototypeByPrototypeName= prototypeDAO
                .findById(prototype.getId());
        if(prototypeByPrototypeName.isPresent()){
            throw new IllegalStateException("Prototype already made");
        }
        prototypeDAO.save(prototype);
    }

    public void deletePrototype(Long id) {
     boolean exists = prototypeDAO.existsById(id);
     if(!exists){
         throw new IllegalStateException("Prototype with id "+ id+ "does not exist");
     }else{
         prototypeDAO.deleteById(id);
        }
     }

}
