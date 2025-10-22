package com.itwillbs.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service(value = "mailService")
public class MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
    private final BoardServiceImpl boardServiceImpl;
	
	// 메일 전송에 필요한 정보를 저장한 객체
	@Autowired
	private JavaMailSender mailSender;

    MailService(BoardServiceImpl boardServiceImpl) {
        this.boardServiceImpl = boardServiceImpl;
    }
	// private JavaMailSenderImpl mailSender;
	// 객체를 주입 받아 쓰려면 객체 간 약한 결합의 관계를 유지해야한다.
	// Impl의 부모의 경우 관계가 한 단계 더 멀어지기 때문에, Interface를 주입 받는다.
	
	// 메일 전송하는 기능 (비동기)
    // @Async : 비동기 방식으로 처리되어야 하는 동작(메서드)
    // Async 사용시 root-context.xml 설정 필요함. 
    // xmlns:task="http://www.springframework.org/schema/task" 추가
    // xsi:schemaLocation= "http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task-4.1.xsd"
    @Async
	public void sendMail(String subject,
						 String content,
						 String email) throws MessagingException {
		// 메일 전송에 필요한 정보를 mail-context.xml에서 가져와서 생성
		MimeMessage msg = mailSender.createMimeMessage();
		// Mime 타입 : 확장자를 표시해주는 형태
		// MimeMessage : 메세지를 보낼때도 Mime 타입을 붙여서 보낸다.
		
		// msg 설정 정보에 대해 처리한다.
		MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "UTF-8");
		
		// 이메일 전송에 필요한 내용을 작성
		// msgHelper.setSubject("[잡고] 이메일 인증 번호 테스트입니다."); // 메일 제목
		// msgHelper.setTo("bydhyun@naver.com"); // 수신자(받는사람) 이메일 주소
		// msgHelper.setText("안녕하세요");
		
		msgHelper.setCc("gnlaud07@gmail.com");
		msgHelper.setSubject(subject);
		msgHelper.setText(content);
		msgHelper.setTo(email);
		// msgHelper.setFrom(null); // 발신자(보내는 사람) : 사용 X => 보내는 사람 이메일 주소로 고정
		mailSender.send(msg);
		logger.debug(" 메일 전송 성공 @@@@@@@@@@@@@@@@@@@");
	}
}
