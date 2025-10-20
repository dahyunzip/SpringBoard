package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Inject
	private BoardDAO bDao;
	
	@Override
	public void regist(BoardVO vo) throws Exception {
		bDao.insertBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList() throws Exception {
		logger.debug(" getBoardList() 실행 시작");
		// DB에 처리하는 메서드를 호출
		
		List<BoardVO> boardList = bDao.selectBoardListALL();
		
		logger.debug(" getBoardList() 실행 끝");
		return boardList;
		// => 전달받은 정보를 수정할수도 있다.
		
		// return bDao.selectBoardListALL();
		// => 전달받은 정보를 있는 그대로 전달한다.
	}
	

	@Override
	public List<BoardVO> getBoardListPage(Criteria cri) throws Exception {
		return bDao.selectBoardListPage(cri);
	}

	@Override
	public void increaseViewCnt(int bno) throws Exception {
		logger.debug(" increaseViewCnt(int bno) 시작");
		// DAO 동작(조회수를 1 증가하는 동작)을 호출
		bDao.updateViewCnt(bno);
		
		logger.debug(" increaseViewCnt(int bno) 끝");
	}

	@Override
	public BoardVO getBoard(int bno) throws Exception {
		logger.debug(" getBoard(int bno) 시작");
		// DAO 처리 동작을 호출
		logger.debug(" getBoard(int bno) 종료");
		return bDao.selectBoard(bno);
	}

	@Override
	public void boardModify(BoardVO uvo) throws Exception {
		logger.debug(" boardModify(BoardVO uvo) 시작 ");
		// DAO - 게시판 글정보를 수정하는 동작 
		bDao.updateBoard(uvo);
		logger.debug(" boardModify(BoardVO uvo) 끝 ");
		
	}

	@Override
	public Integer boardRemove(int bno) throws Exception {
		logger.debug(" boardRemove(int bno) 시작");
		// DAO - 게시판 글정보를 삭제하는 동작
		int result = bDao.deleteBoard(bno);
		logger.debug(" boardRemove(int bno) 종료");
		return result;
	}

}
