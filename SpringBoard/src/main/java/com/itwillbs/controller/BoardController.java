package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

/**
 * 컨트롤러에서 해야하는 동작
 * - 공통 URI 설정(/member/*) 
 * - 각 기능별 URI 설정 = 메서드 설정
 * 	 (각 기능별 호출 방법 GET/POST)
 * - 메서드 리턴타입 결정
 * - 예외처리 try-catch-finally / throws
 * 	 >> dao, daoImpl에서 exception 처리 후, service, serviceImpl에서도 exception 던져준다
 * 	 >> 그 뒤에 컨트롤러에서 try-catch 해야하는데 여기서도 throws Exception
 *   => 스프링이 처리함(AOP : 관점 지향 프로그래밍)
 */

@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	// mylog (출력로그)
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService bService;
	
	// http://localhost:8088/
	
	// 게시판 글쓰기 - GET
	// http://localhost:8088/board/regist
	@RequestMapping(value="/regist", method = RequestMethod.GET)
	//@GetMapping(value="/regist")
	public void registGET() throws Exception{
		logger.debug(" /board/regist -> registGET() 실행! ");
		logger.debug(" /views/board/regist.jsp ");
	}
	
	// 게시판 글쓰기 - POST
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String registPOST(BoardVO vo,
							RedirectAttributes rttr)  throws Exception{
		logger.debug(" /board/regist -> registPOST() 실행! ");
		logger.debug(" regist.jsp 페이지에서 submit => registPOST() 실행 ");
		// 폼태그 정보를 post 방식으로 전달할 때 = 한글처리 인코딩 (web.xml 필터)
		// 전달된 파라메터 정보 저장 -> 이게 1번임!
		logger.debug(" BoardVO = " + vo);
		
		// 서비스 -> DAO : 글쓰기 (DB에 정보 입력 insert)
		bService.regist(vo);
		
		// 글쓰기 처리 완료 정보를 전달
		rttr.addFlashAttribute("result", "createOK");
			 //session영역에 데이터를 담아서 넘기고, 한번만 사용하고 사라짐.
		
		// 페이지 이동 (게시판 리스트)
		logger.debug(" 글쓰기 완료 후 페이지 이동(listALL) ");
		return "redirect:/board/listALL";
	}
	
	// 게시판 리스트 출력 - GET
	// 수행하려는 동작이 입력받거나 조회의 동작이면, GET
	// 입력받은 데이터를 처리, 저장, 수정, 삭제하는 경우, POST
	@RequestMapping(value="/listALL", method=RequestMethod.GET)
	public void listALLGET(Model model) throws Exception{
		logger.debug(" /board/listALL -> listALLGET() 실행!");
		
		// 서비스 -> DAO : (DB에 저장된 모든 게시판 글정보를 가져오기)
		List<BoardVO> boardList = bService.getBoardList();
		
		//logger.debug(" list : " + boardList);
		logger.debug(" list : " + boardList.size());
		
		// DB에서 가져온 정보를 연결된 뷰페이지에 전달 => Model
		model.addAttribute("boardList", boardList);
		//model.addAttribute(boardList); // => boardVOList 이름
		
		// 리턴 void -> 주소이름과 같은 주소를 view 페이지로 설정
		logger.debug("/views/board/listALL.jsp 페이지 이동");
	}
	
	// http://localhost:8088/board/read/10 : path variable rest에서 배웁니당.
	// http://localhost:8088/board/read?bno=10
	// 게시판 글 읽기 (본문 내용확인) - GET
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String readGET(BoardVO vo, Model model) throws Exception{
		logger.debug(" /board/read -> readGET() 실행! ");
		
		// 전달된 정보(파라메터) 저장
		
		// 서비스 -> DAO : 특정 번호(bno)에 해당하는 글정보만 가져오기
		
		// 연결된 뷰 페이지에 출력
		
		return "/board/read";
	}

}
