package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * 공통의 예외를 처리하는 클래스
 * *ControllerAdvice : 컨트롤러에서 발생하는 예외처리를 주로 전문적으로 처리하는 객체
 * Exception.class 실행시 이 클래스를 실행시킨다.Exception이 최상위 클래스이므로 해당 클래스를 사용.
 * */

@ControllerAdvice
public class CommonExceptionAdvice {
	// mylog
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

	// 보조기능
	// @ExceptionHandler(Exception.class) : 특정 예외가 발생시 해당 메서드를 실행
	
	
	// @ExceptionHandler(NullPointerException.class)
	@ExceptionHandler(Exception.class)
	public String Common(Exception e, Model model) {
		logger.debug(" Common() 실행 ");
		logger.debug(e+"");
		// => 주기적으로 로그정보가 파일로 생성
		logger.debug(" 개발자가 주기적으로 로그파일을 확인해서 개선 ");
		
		model.addAttribute("e", e);
		
		//ModelAndView => 모델정보 + 뷰페이지 (사용X)
		
		
		return "error"; //리턴된 문자.jsp 페이지로 연결
	}
}
