package com.mts.spotmerest.configs.starter_data;


import com.mts.spotmerest.mappers.RoutineDAO;
import com.mts.spotmerest.models.Routine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class RoutineConfig {

    @Bean
    CommandLineRunner commandLineRunnerRoutine(RoutineDAO routineDAO) {
        return args -> {
            List<String> upperBody = Arrays.asList("Bench Press", "DB Fly's", "Dumbbell Curls", "Incline Press");
            List<String> lowerBody = Arrays.asList("Squats Till Failure", "Leg Press Till Failure", "Calf Raises Till Failure", "Quads Till Failure", "Hammies Till Failure");
            List<String> backDay = Arrays.asList("Pull Ups", "Seated Rows", "Military Press", "Back Extensions");
            List<String> stretch = Arrays.asList("Legs", "Arms/Shoulders", "Core");
            List<String> cardio = Arrays.asList("30 Mile Run", "2 Hour Stair Climber", "200 Suicides", "100 Burpees");

            Routine routine1 = new Routine(
                    "Help The Bear",
                    1L,
                    1L,
                    "Hardcore",
                    upperBody,
                    "Upper Body"

            );  //I need a wheelchair, I can't feel my legs
            Routine routine2 = new Routine(
                    "Mom, I Can't Walk(Paraplegic Conversion)",
                    2L,
                    2L,
                    "Know Pain",
                    lowerBody,
                    "Lower Body"

            );
            Routine routine3 = new Routine(
                    "Back Breaker",
                    3L,
                    3L,
                    "Medium",
                    backDay,
                    "Back"

            );
            Routine routine4 = new Routine(
                    "Stretch Sesh",
                    4L,
                    4L,
                    "Intense",
                    stretch,
                    "Stretching"

            );  //Ready For The Apocalypse
            Routine routine5 = new Routine(
                    "Who's gonna carry the logs, and the boats?",
                    5L,
                    5L,
                    "Know Pain",
                    cardio,
                    "Cardio"

            );

            routineDAO.saveAll(
                    List.of(routine1, routine2, routine3, routine4, routine5));
        };
    }
}