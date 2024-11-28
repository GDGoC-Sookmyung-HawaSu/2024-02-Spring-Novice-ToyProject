package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private Long authorId;
    @Builder
    public PostsSaveRequestDto(String title, String content, Long authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public Posts toEntity(User author) {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
