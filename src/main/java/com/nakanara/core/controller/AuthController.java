package com.nakanara.core.controller;


import com.nakanara.core.service.MemberService;
import com.nakanara.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    private MemberService memberService;

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/signup")
    public String signup(){

        return "/signup";
    }


    @PostMapping("/signup")
    public String signup(User user) {
        memberService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping("/myinfo")
    public String myinfo(){

        return "/myinfo";
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}
