/**
 * 
 */
package com.cw.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author  xueyongfei
 * @date 2020年8月11日
 */
@Aspect
@Component
@Slf4j
public class SysLogAop {

	@Pointcut("execution ( public * com.cw.controller..*.*(..))*")
	public void weblog() {
		
	}
	
	@Before("weblog()")
	public void doBefore(JoinPoint joinPoint) {
		log.info("start");
		RequestContextHolder.getRequestAttributes().setAttribute("start", System.currentTimeMillis(), RequestAttributes.SCOPE_REQUEST);
	}
	
	@AfterReturning(returning="ret",pointcut="weblog()")
	public void doAfterReturning(Object ret) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String url = request.getRequestURI();
		log.info("end");
		long end = System.currentTimeMillis();
		long start =  (Long)RequestContextHolder.currentRequestAttributes().getAttribute("start", RequestAttributes.SCOPE_REQUEST);
		
		log.info("API:"+url+"-耗时："+(end-start));
	}
}
