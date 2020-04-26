package ch10Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
	static public void main(String args[]) {
		try {
			DatagramSocket receiveSocket = new DatagramSocket(5678);
			byte buf[] = new byte[100];
			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			System.out.println("开始接收数据包");
			while (true) {
				receiveSocket.receive(receivePacket);
				String hostname = receivePacket.getAddress().toString();
				System.out.println("来自主机：" + hostname + "端口：" + receivePacket.getPort());
				String s = new String(receivePacket.getData());
				// String s=new String(receivePacket.getData(),0,receivePacket.getLength());
				System.out.println("接收到的数据为: " + s);
			}
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			System.out.println("网络通信出现错误，问题在" + e.toString());
		}
	}
}
