package com.nakanara.core.service;

import com.nakanara.user.entity.SNSUserEntity;
import com.nakanara.user.entity.UserEntity;
import com.nakanara.user.repository.SNSUserRepository;
import com.nakanara.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                .authorities("USER")
                .build();

    }

    public UserEntity saveUser(UserEntity userEntity){

        return userRepository.save(userEntity);
    }

    public UserEntity saveSnsUser(UserEntity userEntity, SNSUserEntity snsUserEntity) {

        userEntity = userRepository.save(userEntity);
        snsUserEntity.setUser(userEntity);

        snsUserRepository.save(snsUserEntity);

        return userEntity;
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
