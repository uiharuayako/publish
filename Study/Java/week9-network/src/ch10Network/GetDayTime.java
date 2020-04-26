package ch10Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class GetDayTime {
	public static void main(String args[]) {
		String host = args[0];
		try {
			Socket connection = new Socket(host, 13);
			System.out.println("Connection established:");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String daytime = in.readLine();
			System.out.println("DayTime received: " + daytime);
			connection.close();
		} catch (UnknownHostException uhe) {
			System.err.println("Host not found: " + uhe);
		} catch (IOException ioe) {
			System.err.println("Error: " + ioe);
		}
	}
}
