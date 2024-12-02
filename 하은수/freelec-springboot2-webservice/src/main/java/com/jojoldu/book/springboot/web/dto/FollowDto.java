package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.follow.Follow;
import lombok.Getter;

@Getter
public class FollowDto {
    private Long id;
    private Long followerId;
    private Long followeeId;

    public FollowDto(Follow follow) {
        this.id = follow.getId();
        this.followerId = follow.getFollower().getId();
        this.followeeId = follow.getFollowee().getId();
    }
}