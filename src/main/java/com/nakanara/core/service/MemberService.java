package com.nakanara.core.service;

import com.nakanara.core.config.Role;
import com.nakanara.log.entity.LoginLog;
import com.nakanara.user.entity.SNSUserEntity;
import com.nakanara.user.entity.UserEntity;
import com.nakanara.user.repository.SNSUserRepository;
import com.nakanara.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@Slf4j
public class MemberService implements UserDetailsService  {

    private UserRepository userRepository;

    private SNSUserRepository snsUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(userId);

        return User.builder().username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(Role.USER.getValue())
                .build();

    }

    /**
     * Login 처리
     * @param session
     * @param  userEntity
     */
    public void login(HttpServletRequest request, HttpSession session, UserEntity userEntity) {

        userEntity.setPassword(userEntity.getUsername());

        UserDetails ud = User.builder().username(userEntity.getUsername())
                .password(userEntity.getUsername())
                .authorities(Role.USER.getValue())
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(ud, "", ud.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        session.setAttribute("user", userEntity); // 패스워드 없는 객체


        new LoginLog(request, userEntity.getUsername());

    }

    public UserEntity saveUser(UserEntity userEntity){

        return userRepository.save(userEntity);
    }

    public UserEntity saveSnsUser(UserEntity userEntity, SNSUserEntity snsUserEntity) {

        UserEntity selUserEntity = userRepository.findByUsername(userEntity.getUsername());

        if( selUserEntity == null) {
            userEntity.setRole(Role.SOCIAL);
            userEntity = userRepository.save(userEntity);
            snsUserEntity.setUser(userEntity);

            snsUserRepository.save(snsUserEntity);
            selUserEntity = userEntity;

        }

        return selUserEntity;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSnsUserRepository(SNSUserRepository snsUserRepository) {
        this.snsUserRepository = snsUserRepository;
    }
}
