package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.service.MailService;

// @EnableAsync : 특정 메서드가 비동기 방식으로 처리될 경우 사용 가능하도록 설정

@Controller
@EnableAsync
public class MailController {
	//mylog
	private static final Logger logger = LoggerFactory.getLogger(MailController.class);

	@Autowired
	private MailService mailService;
	
	// http://localhost:8088/send
	@RequestMapping(value="/send", method=RequestMethod.GET)
	public String sendMailGET() throws Exception{
		logger.debug(" /send -> sendMailGET() 실행 ");
		String subject="Test 제목";
		String content="Test 내용";
		String email="bydhyun@naver.com";
		mailService.sendMail(subject, content, email);
		logger.debug(" 메일전송 완료 !!!!!!!!!!!!!!!!!!");
		// 서비스 -> 메일 전송 실행

		return "redirect:/board/listCri";
	}
}
