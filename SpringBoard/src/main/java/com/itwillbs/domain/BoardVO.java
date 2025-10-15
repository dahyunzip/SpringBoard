package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

/*
 * tbl_board 테이블의 정보를 저장하기 위한 객체
 * - lombok 설정 후 @Data 사용 후 반드시 클래스이름에 F4 눌러서 
 *   get/set 메서드 인식되는지 체크
 * */
@Data
public class BoardVO {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private int viewcnt;
	private Timestamp regdate;
}
