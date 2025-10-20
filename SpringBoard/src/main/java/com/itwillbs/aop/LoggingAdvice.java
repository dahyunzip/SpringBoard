package com.itwillbs.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/*
 * 출력 객체 - advice
 * 보조기능 구현
 * 
 * */

public class LoggingAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// 보조기능 aspect
		// => 주기능이 실행 + 보조기능 실행
		
		// 보조기능 실행
		System.out.println(" 보조기능 출력! (주기능 실행 전) ");
		
		// 주기능 실행
		Object obj = invocation.proceed();
		
		// 보조기능 실행
		System.out.println(" 보조기능 출력! (주기능 실행 후) ");
		
		return obj;
	}
	
}
