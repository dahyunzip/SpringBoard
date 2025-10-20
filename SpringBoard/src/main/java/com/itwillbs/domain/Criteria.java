package com.itwillbs.domain;

/* *
 * 페이징 처리할때 정보를 저장하는 객체
 * - 페이지 번호 page
 * - 페이지 크기 pageSize
 */

public class Criteria {
	
	private int page; 							// 페이지 정보
	private int pageSize;						// 페이지 크기(페이지 개수)
	//private int pageStart;						// mapper에서 시작하는 인덱스 정보
	
	public Criteria(){ // 0페이지, size 0 은 없기 때문에 기본값 설정함.
		this.page = 1;
		this.pageSize = 10;
	}
	
	// alt + shift +s  +  r
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return; // 함수의 종료
		}
		this.page = page;
	}
	
	public void setPageSize(int pageSize) {
		if(pageSize <= 0 || pageSize >= 100) {
			this.pageSize = 10;
			return;
		}
		this.pageSize = pageSize;
	}
	
	///////////////////////////////////////////////////////////////////////
	
	public int getPage() {
		return page;
	}
	
	// ★★★★★ 객체안에 getXXXX() 메서드는 mapper에서 #{xXXX}으로 호출 가능하다.
	// (굳이 변수가 필요 없음!)
	
	// 페이지 정보를 받아서 mapper에서 사용할 인덱스로 전환
	public int getPageStart() {
		return (this.page-1) * this.pageSize;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	//alt shift s + s
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", pageSize=" + pageSize + "]";
	}

	
	
}
