package com.nakanara.support.api.service;

import com.nakanara.core.service.MemberService;
import com.nakanara.user.entity.SNSUserEntity;
import com.nakanara.user.entity.UserEntity;
import com.nakanara.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class OAuthController {


    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/oauth/naverLogin")
    public String naverLogin(Model model, HttpServletRequest res){

        log.info("{}", res);
        return "/oauth/naverLogin";

    }

    @GetMapping("/api/oauth/naver")
    public String naverLoginCallback(Model model, HttpServletRequest res){

        log.info("callback={}", res);
        return "/oauth/naverLoginCallback";

    }

    @PostMapping("/sns/login/naver")
    public String naverLoginPost(Model model, @ModelAttribute("user") UserEntity userEntity, @ModelAttribute("snsUser") SNSUserEntity snsUserEntity) {


        log.info("userEntity {}", userEntity.toString());
        log.info("snsUserEntity {}", snsUserEntity.toString());

        memberService.saveSnsUser(userEntity, snsUserEntity);

        return "redirect:/";
    }
}
