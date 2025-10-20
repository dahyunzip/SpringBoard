package com.itwillbs.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcTest {
	
	/*
	 * // proxyCalc 객체 주입
	 * 
	 * @Inject private Calculator proxyCalc;
	 */
	
	public static void main(String[] args) {
		Calculator cal1 = new Calculator();
		cal1.add(100, 200);
		cal1.sub(100, 200);
		cal1.mul(100, 200);
		cal1.div(100, 200);
		System.out.println("----------------------------------");
		
		//System.out.println("proxyCalc : " + proxyCalc);
		
		ApplicationContext appCTX = new ClassPathXmlApplicationContext("aop-context.xml");
		
		Calculator proxyCalc = (Calculator) appCTX.getBean("proxyCalc");
		System.out.println("proxyCalc : " + proxyCalc);
		
		proxyCalc.add(100, 200);
	}
}
