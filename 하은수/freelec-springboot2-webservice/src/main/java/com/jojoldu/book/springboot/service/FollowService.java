package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.follow.Follow;
import com.jojoldu.book.springboot.domain.follow.FollowRepository;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import com.jojoldu.book.springboot.web.dto.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    // 팔로잉 여부 확인
    public boolean isFollowing(Long followerId, Long followeeId) {
        return followRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId);
    }

    @Transactional
    public void follow(Long followerId, Long followeeId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid follower ID: " + followerId));
        User followee = userRepository.findById(followeeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid followee ID: " + followeeId));

        Follow follow = Follow.builder()
                .follower(follower)
                .followee(followee)
                .build();
        followRepository.save(follow);
    }

    @Transactional
    public void unfollow(Long followerId, Long followeeId) {
        Follow follow = followRepository.findByFollowerIdAndFolloweeId(followerId, followeeId)
                .orElseThrow(() -> new IllegalArgumentException("Follow relationship not found"));
        followRepository.delete(follow);
    }

    @Transactional(readOnly = true)
    public List<FollowDto> getFollowing(Long userId) {
        return followRepository.findByFollowerId(userId).stream()
                .map(FollowDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FollowDto> getFollowers(Long userId) {
        return followRepository.findByFolloweeId(userId).stream()
                .map(FollowDto::new)
                .collect(Collectors.toList());
    }
}
