package com.mts.spotmerest.configs.starter_data;

import com.mts.spotmerest.mappers.MatchDAO;
import com.mts.spotmerest.mappers.SpotDAO;
import com.mts.spotmerest.mappers.UserDAO;
import com.mts.spotmerest.mappers.friendships.FriendRequestDAO;
import com.mts.spotmerest.mappers.friendships.UserFriendsDAO;
import com.mts.spotmerest.models.Match;
import com.mts.spotmerest.models.Spot;
import com.mts.spotmerest.models.friendships.Friend;
import com.mts.spotmerest.services.friendships.FriendsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FriendConfig {

    @Bean
    CommandLineRunner commandLineRunnerFriend(UserFriendsDAO friendDAO, FriendsService friendsService,
                                             UserDAO userDAO){
        return args -> {
//            friendsService.addUserFriends(1L,2L);
//            friendsService.addUserFriends(1L,3L);
//            friendsService.addUserFriends(1L,4L);
//            friendsService.addUserFriends(2L,3L);
//            friendsService.addUserFriends(2L,4L);
//            friendsService.addUserFriends(3L,4L);
        };
    }
}
