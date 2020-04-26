package ch10Network;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class GetContentTest {
	public static void main(String[] args) {
		try {
			String urlStr = "http://www.baidu.com";
			URL url = new URL(urlStr); // 获取该URL地址下的内容
			Object content = url.getContent();
			String text;
			Image img;
			System.out.print("Content:" + content);
			if (content instanceof String) {
				text = (String) content;
				System.out.print("String:" + text);
			} else if (content instanceof Image) {
				img = (Image) content;
				System.out.print("hello" + content);
			}

		} catch (MalformedURLException me) {
			System.out.println("MalformedURLException: " + me);
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe);
		}
	}
}
