package com.chartTmSearch.quickstart;

public class TestRunnable {

	public static void main(String[] args) {
		String url = "http://www.bifengxia.com/";
		String url1 = "http://www.bifengxia.com/commodity/list/0";
//		System.out.println(url.length());
		String u1 = url1.replace(url, "");
		System.out.println(u1);
	}

}
