package com.mts.spotmerest.models.friendships;

import com.mts.spotmerest.models.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name="friend_requests")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long creator;

    private Long targetUser;

    private String status;

    public FriendRequest(Long id, Long requester, Long targetUser, String status) {
        this.id = id;
        this.creator = requester;
        this.targetUser = targetUser;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long requester) {
        this.creator = requester;
    }

    public Long getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(Long targetUser) {
        this.targetUser = targetUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
