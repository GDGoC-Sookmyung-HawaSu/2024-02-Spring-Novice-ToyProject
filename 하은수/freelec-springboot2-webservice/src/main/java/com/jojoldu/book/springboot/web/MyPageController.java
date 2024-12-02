package com.jojoldu.book.springboot.web;


import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MyPageController {
    @GetMapping("/mypage")
    public String myPage(Model model, @LoginUser SessionUser user) {
        model.addAttribute("userName", user.getName());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userPicture", user.getPicture());
        return "my-page";
    }

}
