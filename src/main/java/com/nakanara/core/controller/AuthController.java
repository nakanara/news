package com.nakanara.core.controller;


import com.nakanara.core.service.MemberService;
import com.nakanara.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@Slf4j
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
    public String signup(UserEntity userEntity) {
        memberService.saveUser(userEntity);

        return "redirect:/login";
    }

    @GetMapping("/myinfo")
    public String myinfo(Locale locale, Model model, Authentication auth){
        User vo = (User) auth.getPrincipal();

        log.info("Welcome checkAuth! Authentication is {}.", auth);
        log.info("UserDetailsVO == {}.", vo);

        model.addAttribute("auth", auth );
        model.addAttribute("vo", vo );

        return "/myinfo";
    }


    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}
