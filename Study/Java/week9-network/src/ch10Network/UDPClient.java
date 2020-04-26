package ch10Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
	public static void main(String args[]) {
		try {
			DatagramSocket sendSocket = new DatagramSocket(3456);
			String string = "Hello, BaiJing 2010!!!!!!!!!!!!!!";
			byte[] databyte = new byte[100];
			databyte = string.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(databyte, string.length(),
					InetAddress.getByName("127.0.0.1"), 5678);
			sendSocket.send(sendPacket);
			System.out.println("发送数据：");
			System.out.println(string);
		} catch (SocketException e) {
			System.out.println("不能打开Datagram Socket，或Datagram Socket无法与指定端口连接!");
		} catch (IOException ioe) {
			System.out.println("网络通信出现错误，问题在" + ioe.toString());
		}
	}
}
