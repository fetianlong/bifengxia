package com.chartTmSearch.quickstart;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorTest {

	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
		final HashMap<String, String> hashMap = new HashMap<String, String>(2);
//		while (true) {
		for (int i = 0; i < 100000; i++) {
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						hashMap.put(UUID.randomUUID().toString(), "");
//						Thread.sleep(1000);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		fixedThreadPool.shutdown();
	}

}
