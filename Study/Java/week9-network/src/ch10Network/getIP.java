package ch10Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class getIP {
	public static void main(String args[]) {
		InetAddress whu = null;
		try {
			whu = InetAddress.getByName("www.whu.edu.cn");
		} catch (UnknownHostException e) {
		}
		System.out.println(whu);
	}
}
