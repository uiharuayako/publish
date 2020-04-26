package ch10Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class ConnectionTest {
	public static void main(String[] args) {
		try {
			URL baidu = new URL("http://www.baidu.com/");
			// 建立连接
			URLConnection baiduConnection = baidu.openConnection();

			String inputLine;
			// 此次是读取
			BufferedReader d = new BufferedReader(new InputStreamReader(baiduConnection.getInputStream()));
			while ((inputLine = d.readLine()) != null) {
				System.out.println(inputLine);
			}
			d.close();
		} catch (MalformedURLException me) {
			System.out.println("MalformedURLException: " + me);
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe);
		}
	}
}
