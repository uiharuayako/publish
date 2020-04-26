package homework;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Question3Server{
    public static void main(String args[]) {
        try {
            ServerSocket server = new ServerSocket(6666);
            System.out.println("建立时间服务器于端口：" + server.getLocalPort());
            while (true) {
                Socket connection = server.accept();
                System.out.println("\n建立新线程完成，将与客户端连接\n");
                GetNetworkTime handler = new GetNetworkTime(connection);
            }
        } catch (IOException ioe) {
            System.err.println("Server：发生io异常");
        }
    }
}
