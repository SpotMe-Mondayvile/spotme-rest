package com.mts.spotmerest.services.friendships;

import com.mts.spotmerest.mappers.UserDAO;
import com.mts.spotmerest.mappers.friendships.UserFriendsDAO;
import com.mts.spotmerest.models.friendships.Friend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FriendsService {
    private static final Logger log = LoggerFactory.getLogger(FriendsService.class);
    private final UserFriendsDAO userFriendsDAO;
    private final UserDAO userDAO;

    @Autowired
    public FriendsService(UserFriendsDAO userFriendsDAO, UserDAO userDAO){
        this.userDAO= userDAO;
        this.userFriendsDAO = userFriendsDAO;
    }

    public void addUserFriends(Long userId,Long friendId) {
        Friend newFriendship = new Friend();
        newFriendship.setUser(userId);
        newFriendship.setFriendId(friendId);
        if(!isFriends(friendId,userId)){
            newFriendship.setActive(true);
            userFriendsDAO.save(newFriendship);
        }

    }

    public void removeFriend(Long friendId,Long userId){
        Optional<Friend> friendShip= userFriendsDAO.findFriendByUserAndFriendId(friendId,userId);
        if(isFriends(friendId,userId)){
            userFriendsDAO.deleteById(friendShip.orElseThrow().getId());
        }else{
            log.error("Could not remove friend");
        }
    }


    public Set<Optional<Friend>> getUserFriendsList(Long userId) {
        return userFriendsDAO.findAllFriends(userId);
    }

    public Optional<Friend> getFriend(Long userId,Long friendId) {
        Optional<Friend> friend=null;
        if(isFriends(friendId,userId)){
            friend = userFriendsDAO.findFriendByUserAndFriendId(friendId,userId);
        }else{
         log.debug("User is not Friends");
        }
        return friend;
    }

    public Boolean isFriends(Long friendId, Long userId){
        return userFriendsDAO.findFriendByUserAndFriendId(friendId,userId).isPresent();
    }
}
