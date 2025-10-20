package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DBConnectTest {
	
	private static final Logger logger = LoggerFactory.getLogger(DBConnectTest.class);

	@Inject
	private DataSource ds;
	
	@Inject
	private SqlSession sqlSession;
	
	@Inject
	private BoardDAO bDAO;
	
	//@Test
	public void testDS() {
		logger.info(" testDS() 테스트 실행 ! ");
		logger.info(" dataSource : " + ds);
		logger.info(" testDS() 테스트 끝 ! ");
	}
	
	//@Test
	public void testSqlSession() {
		logger.info(" testSqlSession() 테스트 실행 ! ");
		logger.info(" sqlSession : " + sqlSession);
		logger.info(" testSqlSession() 테스트 끝 ! ");
	}
	
	@Test
	public void test_페이징처리된_게시판목록() throws Exception {
		logger.info(" test_페이징처리된 게시판 목록() 시작");
		
		Criteria cri = new Criteria();
		cri.setPage(1);
		cri.setPageSize(20);
		
		//List<BoardVO> boardList = bDAO.selectBoardListPage(3);
		List<BoardVO> boardList = bDAO.selectBoardListPage(cri);
		logger.info("리스트 개수 : " + boardList.size() + "개");
		
		for(BoardVO vo : boardList ) {
			logger.info(" ------------------------------- ");
			logger.info(vo.getBno() + "/" + vo.getTitle());
			logger.info(" ------------------------------- ");
		}
		
		logger.info(" test_페이징처리된 게시판 목록() 끝");
	}
}
