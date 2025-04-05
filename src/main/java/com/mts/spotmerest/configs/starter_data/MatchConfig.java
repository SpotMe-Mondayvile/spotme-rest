package com.mts.spotmerest.configs.starter_data;

import com.mts.spotmerest.mappers.MatchDAO;
import com.mts.spotmerest.mappers.SpotDAO;
import com.mts.spotmerest.models.Match;
import com.mts.spotmerest.models.Spot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MatchConfig {

    @Bean
    CommandLineRunner commandLineRunnerMatch(MatchDAO matchDAO,
                                             SpotDAO spotDAO){
        return args -> {
            Spot spot1 =new Spot(1L,"asdlkfja2131","Early Morning","About to go run");
            Spot spot2 =new Spot(2L,"asdlkfja2131","Lunch Workout","About to go lift");
            Match m1 = new Match(1L,2L,1L);
            Match m2 = new Match(2L,3L,1L);
            Match m3 = new Match(3L,1L,2L);
            Match m4 = new Match(4L,4L,1L);
            Match m5 = new Match(5L,3L,2L);
            m1.setSpot(spot1);
            m2.setSpot(spot1);
            m3.setSpot(spot1);
            m4.setSpot(spot2);
            m5.setSpot(spot2);
            spotDAO.saveAll(List.of(spot1,spot2));
            matchDAO.saveAll(
                    List.of(m1,m2,m3,m4,m5));
        };
    }
}
