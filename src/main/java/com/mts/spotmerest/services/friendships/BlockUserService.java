package com.mts.spotmerest.services.friendships;


import com.mts.spotmerest.mappers.friendships.UserBlockedDAO;
import com.mts.spotmerest.mappers.UserDAO;
import com.mts.spotmerest.models.User;
import com.mts.spotmerest.models.friendships.BlockUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BlockUserService {

    @Autowired
    private final UserDAO userDAO;

    private final UserBlockedDAO userBlockedDAO;

    @Autowired
    public BlockUserService(UserDAO userDAO, UserBlockedDAO userBlockedDAO) {
        this.userDAO = userDAO;
        this.userBlockedDAO = userBlockedDAO;
    }

    //all blocked users for a user
    public Set<Optional<BlockUser>> allBlockedUsers(Long userId){
        return userBlockedDAO.findAllById(userId);
    }

    //all users that have blocked the current user
    public Set<Optional<BlockUser>> allUsersBlockedBy(Long userId){
        return userBlockedDAO.findAllByTargetUserId(userId);
    }
    //add a blocked user
    public void createBlockUser(BlockUser b) {
        userBlockedDAO.save(b);
    }


    //remove a blocked user
    public void deleteBlockUser(Long id) {
        userBlockedDAO.deleteById(id);
    }


    public Optional<BlockUser> getBlockRelationship(Long userId, Long targetUserId) {
        return userBlockedDAO.getBlockUserByUserAndTargetId(userId, targetUserId);
    }
}
