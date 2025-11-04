package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

                                       // /boards/~~~ (REST 컨트롤러)
									   //	/board/~~~ (일반 컨트롤러)	
@RestController
//@RequestMapping(value = "/rest/*")
@RequestMapping(value = "/boards/*")   
public class BoardRestController {

	//mylog
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

	// 사용할 서비스 객체를 주입받기
	@Inject
	private BoardService bService;
	
	//@Autowired
	//private MemberService mService;
	
	
	
	//게시판 글쓰기 / POST(추가,생성-Create) / [ /rest/regist + 데이터 ]
    //										=> [ /boards + 데이터 ]
	//@RequestMapping(value = "/regist",method = RequestMethod.POST)
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResponseEntity<String> restCreate(@RequestBody BoardVO vo) throws Exception{
		
		logger.info("  글쓰기 동작 실행! ");
		logger.info(" 1. 전달된 정보를 받아오기 ");
		logger.info("vo : "+vo);

		logger.info(" 2. 정보를 서비스로 전달 ");
		bService.regist(vo);
		logger.info(" 글쓰기 완료!(REST) ");
		
		return new ResponseEntity<String>("createOK",HttpStatus.OK);
	}
	
	
	
	
	
	
}
