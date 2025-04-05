package com.mts.spotmerest.mappers.friendships;

import com.mts.spotmerest.models.friendships.BlockUser;
import com.mts.spotmerest.models.friendships.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserBlockedDAO extends JpaRepository<BlockUser, Long> {

    @Query(value = "SELECT b FROM BlockUser b WHERE (b.userId = :userId and b.targetUserId= :targetUserId) or (b.userId = :userId and b.targetUserId= :targetUserId)")
    Optional<BlockUser> getBlockUserByUserAndTargetId(Long userId, Long targetUserId);

    Set<Optional<BlockUser>> findAllById(Long userId);

    Set<Optional<BlockUser>> findAllByTargetUserId(Long targetUserId);
}
