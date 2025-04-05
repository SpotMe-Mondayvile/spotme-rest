package com.mts.spotmerest.mappers.friendships;

import com.mts.spotmerest.models.friendships.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRequestDAO extends JpaRepository<FriendRequest,Long> {
    List<Optional<FriendRequest>> findAllByTargetUser(Long targetId);

    Optional<FriendRequest> findAllById(Long id);
}
