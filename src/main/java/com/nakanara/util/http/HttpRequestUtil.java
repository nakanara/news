package com.nakanara.util.http;

import com.nakanara.core.service.MemberService;
import com.nakanara.user.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpRequestUtil {
    /**
     * 사용자 정보 돌려주기
     * @param res
     * @return
     */
    public static UserEntity getUser(HttpServletRequest res) {
        HttpSession session = res.getSession();
        return getUser(session);
    }

    /**
     * 사용자 정보 돌려주기
     * @param session
     * @return
     */
    public static UserEntity getUser(HttpSession session) {
        UserEntity userEntity = (UserEntity)session.getAttribute(MemberService.SESSION_USER);

        return userEntity;
    }
}
