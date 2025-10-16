package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

/*
 * 서비스의 요청을 받아서 Mybatis 사용해서 SQL구문을 처리
 * */

public interface BoardDAO {
	
	// 게시판 글쓰기
	public void insertBoard(BoardVO vo) throws Exception;
	
	// 게시판 전체 글목록 조회
	public List<BoardVO> selectBoardListALL() throws Exception;
	
	// 게시판 특정 글 조회수를 1 증가
	public void updateViewCnt(int bno) throws Exception;
	
	// 게시판 특정 글 읽기
	public BoardVO selectBoard(int bno) throws Exception;
}
