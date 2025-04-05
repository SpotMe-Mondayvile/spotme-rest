package com.mts.spotmerest.services;

import com.mts.spotmerest.mappers.UserDAO;
import com.mts.spotmerest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserDAO userDAO;


    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public List<User> getUsers(Authentication auth) throws Exception{
        List<User> users= new ArrayList<>();
        if(hasRole("ADMIN",auth)){
            users = userDAO.findAll();
        }
        return users;
    }

    public Optional<User> getUserByEmail(String usrEmail){
        return userDAO.findUserByUserEmail(usrEmail);
    }

    public Optional<User> getUserByID(Long id){
        String idS = id.toString();
        return userDAO.findById(id);
    }

    public List<String> getEmails(Authentication auth) throws Exception {
        List<String> emails = new ArrayList<>();
        List<User> user = getUsers(auth);
        for(int i=0; i<emails.size();i++){
            String temp = user.get(i).getEmail();
            emails.add(temp);
        }
        return emails;
    }

    public void addNewUser(User user) {
        Optional<User> userByUserName= userDAO
                .findUserByUserName(user.getUsername());
        if(userByUserName.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        userDAO.save(user);
    }

    public void deleteUser(Long id) {
     boolean exists =userDAO.existsById(id);
     if(!exists){
         throw new IllegalStateException("User with id "+ id+ "does not exist");
     }else{
         userDAO.deleteById(id);
        }
     }

    public String getEmail(String email) {
        Optional<User> userByUserEmail= userDAO
                .findUserByUserEmail(email);
        if(userByUserEmail.isPresent()){
            System.out.println("Email found");
        }else{
            System.out.println("Could not find email");
        }
        return userByUserEmail.orElseThrow().getEmail();
    }

    public String getPhoneNumber(String email){
        return getUserByEmail(email).orElseThrow().getPhoneNumber();
    }
    public String getFirstName(String email){
        return getUserByEmail(email).orElseThrow().getPhoneNumber();
    }
    public Boolean getStatus(String email){
        return getUserByEmail(email).orElseThrow().isEnabled();
    }


    private boolean hasRole(String role, Authentication authentication) {

        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));
        return hasUserRole;
    }


}
