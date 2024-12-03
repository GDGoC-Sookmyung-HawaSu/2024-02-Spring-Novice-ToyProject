package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.follow.Follow;
import lombok.Getter;

@Getter
public class FollowDto {
    private Long id;
    private Long followerId;
    private Long followeeId;
    private String followeeName;
    private String followerName;
    private String followeeEmail;
    private String followerEmail;

    public FollowDto(Follow follow) {
        this.id = follow.getId();
        this.followerId = follow.getFollower().getId();
        this.followeeId = follow.getFollowee().getId();
        this.followeeName = follow.getFollowee().getName();
        this.followerName = follow.getFollower().getName();
        this.followeeEmail = follow.getFollowee().getEmail();
        this.followerEmail = follow.getFollower().getEmail();
    }
}