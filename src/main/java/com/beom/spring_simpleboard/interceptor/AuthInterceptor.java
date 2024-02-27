package com.beom.spring_simpleboard.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
public class AuthInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("request.getRequestURI() = {}", requestURI);

        HttpSession session = request.getSession();

        if(session == null || session.getAttribute("member") == null){
            log.info("로그인이 필요한 요청입니다");

            //로그인하도록 redirect
            response.sendRedirect("/member/login");
            return false;
        }

        return true;
    }
}
