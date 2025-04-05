package com.mts.spotmerest.models.friendships;


import jakarta.persistence.*;

@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long friendId;
    private Long userId;
    private Boolean isActive;

    public Friend(Long friendId, Long userId, Boolean isActive) {
        this.id = id;
        this.friendId = friendId;
        this.userId = userId;
        this.isActive = isActive;
    }

    public Friend() {
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Long getUser() {
        return userId;
    }

    public void setUser(Long user) {
        this.userId = user;
    }

    public Long getId() {
        return id;
    }


    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}