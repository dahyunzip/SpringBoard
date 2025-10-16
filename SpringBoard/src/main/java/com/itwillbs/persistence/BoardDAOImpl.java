package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);

	// sqlSession 객체정보를 주입받아서 사용
	// (디비연결, SQL실행...)
	@Inject
	private SqlSession sqlSession;
	
	// 게시판 mapper namespace
	private final static String NAMESPACE ="com.itwillbs.mapper.BoardMapper.";
	
	@Override
	public void insertBoard(BoardVO vo) throws Exception{
		logger.debug(" insertBoard(BoardVO bo) 실행 ");
		sqlSession.insert(NAMESPACE+"insertBoard", vo);
		logger.debug(" SQL 실행 완료! ");
		logger.debug(" insertBoard(BoardVO bo) 완료! ");
	}

	@Override
	public List<BoardVO> selectBoardListALL() throws Exception {
		logger.debug(" selectBoardListALL() 실행 ");
		logger.debug(" DB 연결, SQL 실행 준비 => SqlSession 객체");
		
		// SQL 실행
		// => mapper에서 VO 형태로 리턴
		//	  mybatis가 List 형태로 변환
		List<BoardVO> boardList = sqlSession.selectList(NAMESPACE+"selectBoardListALL");
		logger.debug(" SQL 실행 완료, 게시판 개수 : " + boardList.size());
		return boardList;
	}

	@Override
	public void updateViewCnt(int bno) throws Exception {
		logger.debug(" updateViewCnt(int bno) 실행!");
		
		// DB 연결, SQL 실행
		sqlSession.update(NAMESPACE+"updateViewCnt", bno);
		logger.debug(" SQL 실행 성공! 조회수 1 증가");
		
		logger.debug(" updateViewCnt(int bno) 완료");
	}

	@Override
	public BoardVO selectBoard(int bno) throws Exception {
		logger.debug(" selectBoard(int bno) 실행!");
		// 디비연결, SQL 실행 => sqlSession 객체
		
		sqlSession.selectOne(NAMESPACE+"selectBoard", bno);
		BoardVO resultVO = sqlSession.selectOne(NAMESPACE+"selectBoard", bno);
		
		logger.debug(" selectBoard(int bno) 종료!");
		return resultVO;
	}

	
	
}
