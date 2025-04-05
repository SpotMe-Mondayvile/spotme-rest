package com.mts.spotmerest.controllers;


import com.mts.spotmerest.auth.DataFilter;
import com.mts.spotmerest.models.Routine;
import com.mts.spotmerest.models.Routine;
import com.mts.spotmerest.services.RoutineService;
import com.mts.spotmerest.services.RoutineService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
@SecurityRequirement(name = "JWT")
@RequestMapping(path = "api/v1/routine")
public class RoutineController {
    private final DataFilter dataFilter;
    private final RoutineService routineService;

    @Autowired
    public RoutineController(RoutineService routineService,DataFilter dataFilter){
        this.routineService= routineService;
        this.dataFilter=dataFilter;
    }

    @GetMapping
    public String printHello(){
        return "Routine Web Controller";
    }

    @GetMapping(path="all")
    public List<Optional<Routine>> getRoutines(Principal principal){
        return routineService.getAllRoutines(dataFilter.getPrincipalId(principal));
    }

    @GetMapping(path= "id/{routineId}")
    public Optional<Routine> getRoutineByID(@PathVariable("routineId") Long id, Principal principal){
        Optional<Routine> validatedRoutine = Optional.empty();
        Optional<Routine> requestedRoutine = routineService.getRoutine(id);
        Long owner = requestedRoutine.orElseThrow().getUserId();
        if(dataFilter.isUser(principal,owner)){
            validatedRoutine=routineService.getRoutine(id);
        }
        return validatedRoutine ;
    }

    @PostMapping(path="/add")
    public void newRoutine(@RequestBody Routine routine, Principal principal){
        System.out.println(routine);
        if(dataFilter.isUser(principal,routine.getUserId())){
            routineService.addNewRoutine(routine);
        }
    }

    @DeleteMapping(path= "/{routine_id}")
    public void deleteRoutine(@PathVariable("routine_id") Long id,Principal principal){
        Optional<Routine> newRoutine = Optional.empty();
        Long owner = routineService.getRoutine(id).orElseThrow().getUserId();
        if(dataFilter.isUser(principal,owner)){
            routineService.deleteRoutine(id);
        }
    }

}