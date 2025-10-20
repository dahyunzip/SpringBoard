package com.itwillbs.aop;

/*
 * 계산기 객체 - Target
 * 주기능으로 사칙연산 계산
 * 
 * */

public class Calculator {
	public void add(int x, int y) {
		System.out.println(" ADD 결과 : " +(x+y));
	}
	public void sub(int x, int y) {
		System.out.println(" SUB 결과 : " +(x-y));
	}
	public void mul(int x, int y) {
		System.out.println(" MUL 결과 : " +(x*y));
	}
	public void div(int x, int y) {
		System.out.println(" DIV 결과 : " +(x/y));
	}
}
