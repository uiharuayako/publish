package ch10Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class getLocalHostTest {
	public static void main(String args[]) {
		InetAddress myIP = null;
		try {
			myIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
		}
		System.out.println(myIP);
	}
}
