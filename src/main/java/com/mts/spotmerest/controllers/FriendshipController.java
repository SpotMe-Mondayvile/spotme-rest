package com.mts.spotmerest.controllers;

import com.mts.spotmerest.auth.DataFilter;
import com.mts.spotmerest.models.friendships.Friend;
import com.mts.spotmerest.models.friendships.FriendRequest;
import com.mts.spotmerest.services.friendships.FriendRequestService;
import com.mts.spotmerest.services.friendships.FriendsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
@SecurityRequirement(name = "JWT")
@RequestMapping(path = "api/v1/friends")
public class FriendshipController {
    private final DataFilter dataFilter;
    private final FriendsService friendsService;
    private final FriendRequestService friendRequestService;


    @Autowired
    public FriendshipController(DataFilter dataFilter, FriendsService friendsService, FriendRequestService friendRequestService) {
        this.friendsService = friendsService;
        this.dataFilter= dataFilter;
        this.friendRequestService= friendRequestService;
    }

    @PostMapping(value = "/add")
    public void userFriendRequest(@RequestBody FriendRequest friendRequest, Principal principal) {
        Long userId = dataFilter.getPrincipalId(principal);
        if(friendRequest.getStatus().equals("CONFIRMED")) {
            friendsService.addUserFriends(userId, friendRequest.getCreator());
        }
    }

    @PostMapping(value = "/send_request")
    public void sendUserFriendRequest(@RequestBody FriendRequest friendRequest, Principal principal) {
        Long userId = dataFilter.getPrincipalId(principal);
        friendRequest.setCreator(userId);
        friendRequestService.createRequestFromObject(friendRequest);
    }


    @PostMapping(value = "/accept/{id}")
    public void acceptFriendRequest(@PathVariable("id") Long id, Principal principal) {
        Long userId = dataFilter.getPrincipalId(principal);
        FriendRequest friendRequest = friendRequestService.getFriendRequest(id).orElseThrow();
        friendRequest.setStatus("CONFIRMED");
        friendRequestService.updateFriendRequestFromObject(friendRequest);
        if(friendRequest.getStatus().equals("CONFIRMED")) {
            friendsService.addUserFriends(userId, friendRequest.getCreator());
        }
    }

    @PostMapping(value = "/deny/{id}")
    public void denyFriendRequest(@PathVariable("id") Long id, Principal principal) {
        Long userId = dataFilter.getPrincipalId(principal);
        FriendRequest friendRequest = friendRequestService.getFriendRequest(id).orElseThrow();
        friendRequest.setStatus("DENIED");
        friendRequestService.updateFriendRequestFromObject(friendRequest);
        if(friendRequest.getStatus().equals("DENIED")) {
            friendRequestService.deleteRequest(friendRequest.getId());
        }
    }


    @DeleteMapping(value = "/delete/{id}")
    public void userFriendRequest(@PathVariable("id") Long id, Principal principal) {
        Long userId = dataFilter.getPrincipalId(principal);
        friendsService.removeFriend(id,userId);
    }

    @GetMapping(path="/getUserFriendList")
    public Set<Optional<Friend>> getUserFriendList(Principal principal){
        Long userId = dataFilter.getPrincipalId(principal);
        return this.friendsService.getUserFriendsList(userId);
    }

    @GetMapping(path="/getFriend/{id}")
    public Optional<Friend> getFriend(@PathVariable(value="id") Long id, Principal principal){
        Long userId = dataFilter.getPrincipalId(principal);
        return this.friendsService.getFriend(id,userId);
    }

    @GetMapping(path="/getFriendRequests")
    public List<Optional<FriendRequest>> getUserFriendRequests(Principal principal){
        Long userId = dataFilter.getPrincipalId(principal);
        return friendRequestService.getFriendRequests(userId);
    }


//    @GetMapping(path="/getUserFriendList")
//    public ResponseEntity<Map<String, Object>> getUserFriendList(@RequestBody UserFriendsListRequestEntity userFriendsListRequestEntity) {
//        return this.friendsService.getUserFriendsList(userFriendsListRequestEntity);
//    }
//
//    @GetMapping(path= "/getCommonUserFriends")
//    public ResponseEntity<Map<String, Object>> getCommonUserFriends(@RequestBody UserFriendsRequestEntity userFriendsRequestEntity) {
//        return this.friendsService.getCommonUserFriends(userFriendsRequestEntity);
//    }
//
//    @PostMapping(path = "/subscribeUserRequest")
//    public ResponseEntity<Map<String, Object>> subscribeUserRequest(@RequestBody SubscribeUserRequestEntity subscribeUserRequestEntity) {
//        return this.subscribeUserService.addSubscribeUser(subscribeUserRequestEntity);
//    }
//
//    @PostMapping(path = "/blockUserRequest")
//    public ResponseEntity<Map<String, Object>> blockUserRequest(@RequestBody BlockUserRequestEntity blockUserRequestEntity) {
//        return this.blockUserService.addBlockUser(blockUserRequestEntity);
}
