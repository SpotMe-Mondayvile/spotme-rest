package com.mts.spotmerest.configs.starter_data;

import com.mts.spotmerest.mappers.GymDAO;
import com.mts.spotmerest.models.Gym;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GymConfig {

    @Bean
    CommandLineRunner commandLineRunner2(GymDAO gymDAO){
        return args -> {
            Gym gym1 = new Gym("2384075nv982374n059",
                    "Magus's Muscle Shack",
                    "1287 Brick Wall dr",
                    "Houston",
                    "Earf",
                    "75070"

            );

            Gym gym2 = new Gym("0-w48nwfcw",
                    "No Guns, No Huns",
                    "1286 No Motion Pkwy",
                    "Houston",
                    "Earf",
                    "75070"
            );

            Gym gym3 = new Gym("09831y0b9238740",
                    "Joe's Jym",
                    "2345 Thumb Built Way",
                    "Houston",
                    "Earf",
                    "75070"
            );
            gymDAO.saveAll(
                    List.of(gym1,gym2,gym3));
        };
    }
}
