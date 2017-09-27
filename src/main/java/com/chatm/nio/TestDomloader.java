package com.chatm.nio;

import java.io.IOException;

public class TestDomloader {

	public static void main(String[] args) {
		
		String url = "http://201606mp4.11bubu.com/20160807/heyzo-1195/1/xml/91_1cdd56d0ad384503ae10daaee92ae878.mp4";
		
		String savePath = "d:/downloader";
		try {
			Downloader domn = new Downloader(url, savePath);
			domn.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
