package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

/*
 * 컨트롤러에서 호출할 동작을 구현 
 * */

public interface BoardService {
	
	// 게시판 글쓰기
	public void regist(BoardVO vo) throws Exception;
	
	// 게시판 모든 글정보를 가져오기 (리스트)
	public List<BoardVO> getBoardList() throws Exception;
	// 제네릭 안쓰면 모든 타입의 데이터를 저장할 수 있음
	
	// 게시판 특정 글 조회수를 1증가
	public void increaseViewCnt(int bno) throws Exception;
	
	// 게시판 특정 글 정보를 조회 (본문읽기)
	public BoardVO getBoard(int bno) throws Exception;
}
