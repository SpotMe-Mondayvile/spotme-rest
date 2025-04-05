package com.mts.spotmerest.controllers;

import com.mts.spotmerest.models.Gym;
import com.mts.spotmerest.services.GymService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
@SecurityRequirement(name = "JWT")
@RequestMapping(path = "api/v1/gym")
public class GymController {
    private final GymService gymService;
    @Autowired
    public GymController(GymService gymService){
        this.gymService= gymService;
    }

    @GetMapping
    public String printHello(){
        return "Gym Web Controller";
    }
    @GetMapping(path="/all")
    public List<Gym> getGyms(){
        return gymService.getGyms();
    }

    @PostMapping(path="/add")
    public void newGym(@RequestBody Gym gym){
        System.out.println(gym);
        gymService.addNewGym(gym);
    }

    @DeleteMapping(path= "/{GymId}")
    public void deleteGym(@PathVariable("GymId") Long id){
        gymService.deleteGym(id);
    }
}

