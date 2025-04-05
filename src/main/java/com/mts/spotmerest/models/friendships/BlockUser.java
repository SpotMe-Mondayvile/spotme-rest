package com.mts.spotmerest.models.friendships;

import jakarta.persistence.*;

@Entity
@Table(name = "block_users")
public class BlockUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long userId;
    private Long targetUserId;

    public BlockUser(Long targetUserId, Long userId) {
        this.targetUserId = targetUserId;
        this.userId = userId;
    }

    public BlockUser() {
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId(){
        return this.id;
    }

}