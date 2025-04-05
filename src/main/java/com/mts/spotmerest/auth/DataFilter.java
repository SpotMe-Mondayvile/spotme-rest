package com.mts.spotmerest.auth;

import com.mts.spotmerest.models.User;
import com.mts.spotmerest.services.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Data
@Service
@NoArgsConstructor(force = true)
public class DataFilter {

    @Autowired
    private final UserService userService;

   public DataFilter(UserService userService){
       this.userService=userService;
   }




    public boolean isUser(Principal principal,Long id){
        Optional<User> out= Optional.empty();
        Optional<User> requestedUser= userService.getUserByID(id);
        String rEmail= requestedUser.orElseThrow().getEmail();
        if(!Objects.equals(rEmail, principal.getName())){
            System.out.println("Not valid");
        }else if(Objects.equals(rEmail, principal.getName())){
            System.out.println("User validated");
        }
        System.out.println("Principal : "+principal.toString());

        return Objects.equals(rEmail, principal.getName());
    }

    public boolean isUser(Principal principal, UserService userService,Long id){
        Optional<User> out= Optional.empty();
        Optional<User> requestedUser= userService.getUserByID(id);
        String rEmail= requestedUser.orElseThrow().getEmail();

        if(!Objects.equals(rEmail, principal.getName())){
            System.out.println("Not valid");
        }else if(Objects.equals(rEmail, principal.getName()))
            out= requestedUser;
        System.out.println("Principal : "+principal.toString());

        return Objects.equals(rEmail, principal.getName());
    }

    public Long getPrincipalId(Principal principal){
        return Objects.requireNonNull(userService).getUserByEmail(principal.getName()).orElseThrow().getId();
    }


}
