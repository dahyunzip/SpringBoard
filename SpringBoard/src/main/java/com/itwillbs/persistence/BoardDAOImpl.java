package com.itwillbs.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

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
	public List<BoardVO> selectBoardListPage(int page) throws Exception {
		logger.debug(" selectBoardListPage(int page) 시작");
		
		// 전달된 page정보를 index 정보로 변경해서 mapper로 전달
		// 1p -> 0, 2p->10, 3p-> 20, 4p->30, ...
		
		if(page <= 0) page = 1;
		page = (page - 1) * 10;
		
		List<BoardVO> boardList = sqlSession.selectList(NAMESPACE+"selectBoardListPage", page);
		logger.debug(" selectBoardListPage(int page) 끝");
		return boardList;
	}

	// 이건 안쓸거임
	@Override
	public List<BoardVO> selectBoardListPage(int page, int pageSize) throws Exception {
		logger.debug(" selectBoardListPage(int page, int pageSize) 시작");
		// 전달된 page정보를 index 정보로 변경해서 mapper로 전달
		// 1p -> 0, 2p->10, 3p-> 20, 4p->30, ...
		if( page <= 0) page = 1;
		page = (page - 1) * 10;
		
		// 두 가지 정보를 보낼때 객체를 사용하거나, map 을 사용한다.
		// 여기서는 같은 카테고리를 나타내기 때문에 객체를 사용할 수 있다.
		// 이 정보들이 카테고리로 묶였을때 편한가. 아닌가로 기준을 나눈다.

		List<BoardVO> boardList = null; 
				// sqlSession.selectList(NAMESPACE+"selectBoardListPage", page, pageSize);
		logger.debug(" selectBoardListPage(int page, int pageSize) 끝");
		return boardList;
	}
	// 이건 안쓸거임
	
	@Override
	public List<BoardVO> selectBoardListPage(Criteria cri) throws Exception {
		logger.debug(" selectBoardListPage(Criteria cri) 시작! ");
		/* Criteria 객체에서 계산!
		 * if( page <= 0) page = 1; page = (page - 1) * 10;
		 */
		
		List<BoardVO> boardList =  sqlSession.selectList(NAMESPACE+"selectBoardListPage", cri);
		logger.debug(" selectBoardListPage(Criteria cri) 종료! ");
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

	@Override
	public void updateBoard(BoardVO uvo) throws Exception {
		logger.debug(" updateBoard(BoardVO uvo) 실행! ");
		int result = sqlSession.update(NAMESPACE+"updateBoard", uvo);
		
		if(result > 0) logger.debug("게시판 글 수정 완료!");
		
		logger.debug(" updateBoard(BoardVO uvo) 종료! ");
		
	}

	@Override
	public Integer deleteBoard(int bno) throws Exception {
		logger.debug(" deleteBoard(int bno) 시작! ");
		
		// DB연결, SQL 실행
		int result = sqlSession.delete(NAMESPACE+"deleteBoard", bno);
		logger.debug(" deleteBoard(int bno) 끝! ");
		return result;
	}
	
}
