package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private Long authorId;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;
    private boolean following; // 로그인 유저가 해당 사용자를 팔로잉 하고 있는지 여부
    private boolean isAuthor; // 로그인 유저가 작성자인지

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.authorId = entity.getAuthor().getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor().getName();
        this.modifiedDate = entity.getModifiedDate();
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public void setIsAuthor(boolean isAuthor) {
        this.isAuthor = isAuthor;
    }
}
