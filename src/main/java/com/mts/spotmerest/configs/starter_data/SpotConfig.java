package com.mts.spotmerest.configs.starter_data;

import com.mts.spotmerest.mappers.SpotDAO;
import com.mts.spotmerest.models.Spot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpotConfig {

    @Bean
    CommandLineRunner commandLineRunnerSpot(SpotDAO spotDAO){
        return args -> {
            Spot spot1= new Spot(1L,"2384075nv982374n059","First Workout","Headed to the gym for the first time in a Month");
            Spot spot2= new Spot(2L,"2384075nv982374n059","Chest Workout","My homies said Im fat");
            Spot spot3= new Spot(1L,"2384075nv982374n059","Back Day, Looking for homies", "Lets get ripped");
            spotDAO.saveAll(
                    List.of(spot1,spot2,spot3));
        };
    }

}
