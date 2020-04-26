package ch10Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

class OpenStreamTest {
	public static void main(String[] args) {
		try {
			URL baidu = new URL("http://www.baidu.com");

			String inputLine;
			BufferedReader d = new BufferedReader(new InputStreamReader(baidu.openStream()));

			while ((inputLine = d.readLine()) != null) {
				System.out.println(inputLine);
			}
			d.close();
		} catch (MalformedURLException me) {
			System.out.println("MalformedURLException:" + me);
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe);
		}
	}
}
