package com.sigamfe.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExceptionHandler {

	@AfterThrowing(pointcut = "execution(public * com.sigamfe.*.*(..))", throwing = "exception")
	public void handler(JoinPoint joinPoint, Throwable exception) {
		System.out.println("Foi lançada uma exceção!");
		System.out.println(exception.getStackTrace());
	}
}
