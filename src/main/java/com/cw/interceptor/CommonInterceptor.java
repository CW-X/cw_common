/**
 * 
 */
package com.cw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author  xueyongfei
 * @date 2020年8月7日
 */
@Slf4j
public class CommonInterceptor extends HandlerInterceptorAdapter{

	
	
	@Override
    public	boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("CommonInterceptor");
//		HandlerMethod m = (HandlerMethod)handler;
//		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return true;
	}

}
