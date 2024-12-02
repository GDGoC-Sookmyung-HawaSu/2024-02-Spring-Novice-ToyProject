package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.FollowService;
import com.jojoldu.book.springboot.web.dto.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/follow")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{followerId}/follow/{followeeId}")
    public ResponseEntity<String> follow(@PathVariable Long followerId, @PathVariable Long followeeId) {
        try {
            followService.follow(followerId, followeeId);
            return ResponseEntity.ok("Followed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error following user");
        }
    }

    @DeleteMapping("/{followerId}/unfollow/{followeeId}")
    public ResponseEntity<String> unfollow(@PathVariable Long followerId, @PathVariable Long followeeId) {
        try {
            followService.unfollow(followerId, followeeId);
            return ResponseEntity.ok("Unfollowed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error unfollowing user");
        }
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<FollowDto>> getFollowing(@PathVariable Long userId) {
        List<FollowDto> following = followService.getFollowing(userId);
        return ResponseEntity.ok(following);
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<FollowDto>> getFollowers(@PathVariable Long userId) {
        List<FollowDto> followers = followService.getFollowers(userId);
        return ResponseEntity.ok(followers);
    }
}