package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final BoardController boardController;

	//mylog
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

	// 사용할 서비스 객체를 주입받기
	@Inject
	private BoardService bService;

    BoardRestController(BoardController boardController) {
        this.boardController = boardController;
    }
	
	//@Autowired
	//private MemberService mService;
	
	
	
	//게시판 글쓰기 / POST(추가,생성-Create) / [ /rest/regist + 데이터 ]
    //										=> [ /boards + 데이터 ]
	//@RequestMapping(value = "/regist",method = RequestMethod.POST)
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> restCreate(@RequestBody BoardVO vo) throws Exception{
		
		logger.info("  글쓰기 동작 실행! ");
		logger.info(" 1. 전달된 정보를 받아오기 ");
		logger.info("vo : "+vo);

		logger.info(" 2. 정보를 서비스로 전달 ");
		bService.regist(vo);
		logger.info(" 글쓰기 완료!(REST) ");
		
		return new ResponseEntity<String>("createOK",HttpStatus.OK);
	}
	
	//게시판 조회(bno)	/ GET(조회 - Read)  /  [ /boards/{bno} ] (bno)
	@RequestMapping(value="/{bno}", method=RequestMethod.GET)
	public ResponseEntity<BoardVO> readBoard(@PathVariable("bno") int bno) throws Exception{
		logger.info(" readBoard() 실행");
		
		logger.info(" bno : " + bno);
		
		// 서비스 - bno에 해당하는 게시판 정보를 가져오는 기능
		BoardVO resultVO = bService.getBoard(bno);
		logger.info(" resultVO : " + resultVO);
		logger.info(" 서비스 동작 완료! ");
		
		// ResponseEntity 객체를 사용해서 데이터(결과) + HTTP 상태코드 전달
		
		return new ResponseEntity<BoardVO>(resultVO, HttpStatus.OK);
	}
	
	// 게시판 조회(list) / GET(조회 - Read)  /  [ /boards ] (list)
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> restBoardList() throws Exception{
		logger.info(" restBoardList() 실행 ! ");
		
		// 게시판DB에 저장된 최신글 5개만 조회하는 동작 실행
		List <BoardVO> boardList5 = bService.getBoardList5();
		logger.info(" boardList5 : " + boardList5);
		
		return new ResponseEntity<List<BoardVO>>(boardList5, HttpStatus.OK);
	}
	
	
	
	
	
}
