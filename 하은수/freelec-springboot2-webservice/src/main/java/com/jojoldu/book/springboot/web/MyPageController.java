package com.jojoldu.book.springboot.web;


import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@RequiredArgsConstructor
@Controller
public class MyPageController {

    private final PostsService postsService;

    @GetMapping("/mypage")
    public String myPage(Model model, @LoginUser SessionUser user) {
        model.addAttribute("userName", user.getName());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userPicture", user.getPicture());

        model.addAttribute("posts", postsService.findPostsByUser(user.getEmail()));
        return "my-page";
    }

}
