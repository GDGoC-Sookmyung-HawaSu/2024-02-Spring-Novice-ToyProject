package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.authorId = entity.getAuthor().getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor().getName();
    }
}
