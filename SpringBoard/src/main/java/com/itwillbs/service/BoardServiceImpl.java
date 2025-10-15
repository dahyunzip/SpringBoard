package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
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

}
