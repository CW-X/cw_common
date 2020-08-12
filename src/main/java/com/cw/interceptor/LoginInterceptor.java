/**
 * 
 */
package com.cw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cw.annotation.Login;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author  xueyongfei
 * @date 2020年8月7日
 */
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
    public	boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("LoginInterceptor");
		HandlerMethod m = (HandlerMethod)handler;
		Login l = m.getMethodAnnotation(Login.class);
		if(l !=null) {
			log.info("需要登录：{}",request.getHeader("User_Session"));
		}
		return true;
	}
}
