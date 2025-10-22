package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import com.itwillbs.service.BoardServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
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
		//return "redirect:/board/listALL";
		return "redirect:/board/listCri";
	}
	
	// 게시판 리스트 출력 - GET
	// 수행하려는 동작이 입력받거나 조회의 동작이면, GET
	// 입력받은 데이터를 처리, 저장, 수정, 삭제하는 경우, POST
	// http://localhost:8088/board/listALL
	@RequestMapping(value="/listALL", method=RequestMethod.GET)
	public void listALLGET(Model model, HttpSession session, Criteria cri) throws Exception{
		logger.debug(" /board/listALL -> listALLGET() 실행!");
		
		// 서비스 -> DAO : (DB에 저장된 모든 게시판 글정보를 가져오기)
		// List<BoardVO> boardList = bService.getBoardList();
		
//		Criteria cri = new Criteria(); 매개변수로 옮김으로써 new가 되어진다.
//		cri.setPage(2);
//		cri.setPageSize(10);
		List<BoardVO> boardList = bService.getBoardListPage(cri);
		
		//logger.debug(" list : " + boardList);
		logger.debug(" list : " + boardList.size());
		
		// DB에서 가져온 정보를 연결된 뷰페이지에 전달 => Model
		model.addAttribute("boardList", boardList);
		//model.addAttribute(boardList); // => boardVOList 이름
		
		// 세션영역에 조회수증가 여부를 판단하는 상태값을 생성
		session.setAttribute("incrementStatus", true); // 조회수 증가 가능
		
		// 리턴 void -> 주소이름과 같은 주소를 view 페이지로 설정
		logger.debug("/views/board/listALL.jsp 페이지 이동");
	}
	
	// http://localhost:8088/board/listCri?page=2&pageSize=5
	@RequestMapping(value="/listCri", method=RequestMethod.GET)
	public void listCriGET(Model model, HttpSession session, Criteria cri) throws Exception{
		logger.debug(" /board/listCri -> listCriGET() 실행!");
		
		// 페이징 처리 객체 PageVO 생성
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		//pageVO.setTotalCount(2560); // 개수 + 계산된 정보
		pageVO.setTotalCount(bService.getTotalCount()); // 개수 + 계산된 정보
		
		
		List<BoardVO> boardList = bService.getBoardListPage(cri);
		
		//logger.debug(" list : " + boardList);
		logger.debug(" list : " + boardList.size());
		
		// DB에서 가져온 정보를 연결된 뷰페이지에 전달 => Model
		model.addAttribute(pageVO);
		model.addAttribute("boardList", boardList);
		//model.addAttribute(boardList); // => boardVOList 이름
		
		// 세션영역에 조회수증가 여부를 판단하는 상태값을 생성
		session.setAttribute("incrementStatus", true); // 조회수 증가 가능
		
		// 리턴 void -> 주소이름과 같은 주소를 view 페이지로 설정
		logger.debug("/views/board/listCri.jsp 페이지 이동");
	}
	
	
	// http://localhost:8088/board/read/10 : path variable rest에서 배웁니당.
	// http://localhost:8088/board/read?bno=10
	// 게시판 글 읽기 (본문 내용확인) - GET
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String readGET(Model model, 
						  HttpSession session,
						  @RequestParam(value="bno", defaultValue = "1") int bno,
						  @ModelAttribute("page") int page) throws Exception{
						  // request.getParameter(); => String 타입인데
						  // @RequestParam => 자동 형변환을 포함(문자, 숫자, 날짜...)
						  // 파라미터 없을 경우 /read만 부를 시 400에러가 뜬다. defaultValue로 1을 설정한다.
		logger.debug(" /board/read -> readGET() 실행! ");
		// 전달된 정보(파라메터) 저장
		logger.debug(" bno : " + bno);
		
		// 세션 영역에 저장된 조회수 변경가능 상태정보를 출력
		boolean incrementStatus = (boolean) session.getAttribute("incrementStatus");
		logger.debug(" incrementStatus : " + incrementStatus);
		
		if(incrementStatus) {
			// 서비스 -> DAO : 특정 번호(bno)에 해당하는 글 조회수를 1증가
			bService.increaseViewCnt(bno);
			logger.debug(" 조회수 1 증가! ");
			// 상태 변경 true => false
			session.setAttribute("incrementStatus", !incrementStatus);
		}
		
		
		// 서비스 -> DAO : 특정 번호(bno)에 해당하는 글정보만 가져오기
		BoardVO resultVO = bService.getBoard(bno);
		logger.debug(" resultVO : {}", resultVO);
		logger.debug(" 게시글 불러오기 완료 ");

		// DB에서 받아
		model.addAttribute(resultVO); //객체를 보낸다.
		
		// 연결된 뷰 페이지에 출력
		return "/board/read";
	}

	//http://localhost:8088/board/modify?bno=10
	//http://localhost:8088/board/modify?bno=10&page=2
	// 게시판 글 수정 - GET
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(Model model,
						@ModelAttribute("bno") int bno,
						@ModelAttribute("page") int page) throws Exception{
		logger.debug("/board/modify -> modifyGET() 실행");
		// 전달된 정보(파라메터) 저장
		logger.debug("bno : " + bno);
		
		// 서비스 -> DAO 
		// 기존에 DB에 저장된 글정보를 가져와서 화면에 출력
		BoardVO resultVO = bService.getBoard(bno);
		logger.debug(" result : {}", resultVO);
		
		// 연결된 뷰페이지로 전달 => model 객체
		model.addAttribute(resultVO);
		//model.addAttribute(bService.getBoard(bno));
		
		logger.debug(" 게시글 불러오기 완료");
		
		logger.debug(" views/board/modify.jsp 페이지 실행");
	}
	// 게시판 글 수정 - POST
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO vo,
							@ModelAttribute("page") int page,
							RedirectAttributes rttr	) throws Exception{
		logger.debug("/board/modify.jsp 폼태그 submit() -> modifyPOST() 실행 ");
		// 전달된 정보 저장
		logger.debug("vo : {}", vo);
		
		// 서비스 -> DAO : 전달된 정보를 사용해서 정보 수정
		bService.boardModify(vo);
		
		rttr.addFlashAttribute("result", "modifyOK");
		
		// 다시 리스트 페이지로 이동
		//return "redirect:/board/listALL";
		return "redirect:/board/listCri?page="+page;
		
		// 다시 read 페이지로 이동 (bno 가지고 이동)
		// 1)
		// rttr.addAttribute("bno", vo.getBno());
		// 여러번 사용, URI에 표시 O
		
		// rttr.addFlashAttribute("bno", vo.getBno());
		// 1번만 사용, URI에 표시 X (세션 영역 저장)
		
		// return "redirect:/board/read";
		
		// 2)
		// return "redirect:/board/read"+vo.getBno();
		
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public String removePOST(@ModelAttribute("bno") int bno,
							RedirectAttributes rttr,
							@ModelAttribute("page") int page) throws Exception {
		logger.debug(" /board/remove => removePOST() 실행");
		
		// 전달된 정보(파라메터 bno) 저장
		logger.debug(" bno : " + bno);
		
		// 서비스 -> DAO : 특정 게시판글(bno)에 해당하는 글 정보를 삭제
		int result = bService.boardRemove(bno);
		if(result == 1) {
			logger.debug(" 게시판"+ bno +"번 글 삭제 성공! ");
		}
		rttr.addFlashAttribute("result", "removeOK");
		// 페이지 이동
		//return "redirect:/board/listALL";
		return "redirect:/board/listCri?page="+page;
	}
}

