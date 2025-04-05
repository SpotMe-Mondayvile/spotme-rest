package com.mts.spotmerest.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mts.spotmerest.models.Routine;
import com.mts.spotmerest.mappers.RoutineDAO;

import java.util.List;
import java.util.Optional;


@Service
public class RoutineService {
//methods/functionalities to create add, delete, edit, favorite

    private final RoutineDAO routineDAO;

    @Autowired
    public RoutineService(RoutineDAO routineDAO) {
        this.routineDAO = routineDAO;
    }

    //For creation, you're passing in a new model b/c you need a model of what you're adding, comes with own parameters
    public void addNewRoutine(Routine routine) {

        routineDAO.save(routine);
    }

    //delete user, select by uniqueID


    public void editRoutine(Routine routine) {

        Optional<Routine> original = routineDAO.findByRoutineName(routine.getRoutineName());
        Routine newRoutine = original.orElseThrow().editRoutine(routine);
        Routine originalRoutine = original.get();
        routineDAO.deleteById(original.orElseThrow().getRoutineId());
        routine = newRoutine; //Added after office hours with Magus
        routineDAO.save(originalRoutine);
    }

    public void deleteRoutine(Routine routine) {
        // Check if the routine exists, need to change to delete by ID because names can eb edited

        Optional<Routine> optionalRoutine = routineDAO.findByRoutineName(routine.getRoutineName());

        if (optionalRoutine.isPresent()) {
            // if Routine exists, delete BY NAME
            routineDAO.deleteByRoutineName(routine.getRoutineName());
        } else {
            // Handle the case where the routine does not exist, throw an exception, log, or handle accordingly
            throw new IllegalArgumentException("Routine not found!");
        }
    }//modify to be able to delete by list of ID's so user doesn't have to select one at a time. Can wait for now


    public void deleteRoutine(Long id) {
        // Check if the routine exists, need to change to delete by ID because names can eb edited

        Optional<Routine> optionalRoutine = routineDAO.findById(id);

        if (optionalRoutine.isPresent()) {
            // if Routine exists, delete BY NAME
            routineDAO.deleteById(id);
        } else {
            // Handle the case where the routine does not exist, throw an exception, log, or handle accordingly
            throw new IllegalArgumentException("Routine not found!");
        }
    }//modify to be able to delete by list of ID's so user doesn't have to select one at a time. Can wait for now


    public Optional<Routine> getRoutine(Long id) {
        Optional<Routine> optionalRoutine = routineDAO.findById(id);
        return optionalRoutine;
    }

    public List<Optional<Routine>> getAllRoutines(Long userId){

        return routineDAO.findAllByUserId(userId);
    }
}