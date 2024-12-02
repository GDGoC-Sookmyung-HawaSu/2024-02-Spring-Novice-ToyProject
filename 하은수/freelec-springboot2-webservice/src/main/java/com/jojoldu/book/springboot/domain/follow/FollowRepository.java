package com.jojoldu.book.springboot.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollowerId(Long followerId);
    List<Follow> findByFolloweeId(Long followeeId);
    Optional<Follow> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
    boolean existsByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
}