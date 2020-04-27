package homework;


import javax.security.sasl.SaslException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Question3Server{
    public static void main(String args[]) {
        try {
            ServerSocket server = new ServerSocket(6666);
            System.out.println("建立时间服务器于端口：" + server.getLocalPort());
            ExecutorService pool= Executors.newFixedThreadPool(3);
            while (true) {
                Socket connection = server.accept();
                System.out.println("\n建立新线程完成，将与客户端连接\n");
                pool.execute(new GetNetworkTime(connection));
            }
        } catch (IOException ioe) {
            System.err.println("Server：发生io异常");
        }
    }
}
