package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.FollowService;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final FollowService followService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        List<PostsListResponseDto> posts = postsService.findAllDesc();

        if (user != null) {
            Long userId = user.getId();
            model.addAttribute("userName", user.getName());
            model.addAttribute("userId", userId); // 추가
            model.addAttribute("loggedIn", true);

            for (PostsListResponseDto post : posts) {
                boolean isFollowing = followService.isFollowing(userId, post.getAuthorId());
                post.setFollowing(isFollowing);

                boolean isAuthor = userId.equals(post.getAuthorId());
                post.setIsAuthor(isAuthor);
            }
        } else {
            model.addAttribute("loggedIn", false);
        }

        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable("id") Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
