package homework;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Question3Client {
    public static void main(String args[]) {
        try {
            Socket connection = new Socket("localhost", 6666);
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss 时区：z");
            Date date = new Date(System.currentTimeMillis());
            System.out.println("连接已建立于本地时间："+formatter.format(date));
            DataInputStream in = new DataInputStream(connection.getInputStream());
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String way = new String("date");
            while (!way.equalsIgnoreCase("QUIT")) {
                System.out.println("\n\n=====从百度获取时间=====");
                System.out.print("\ndate：回应当前YYYY-MM-DD形式日期\ntime：服务器回应当前hh-mm-ss形式时刻\nquit：退出\n输入获取时间的方式: ");
                BufferedReader wayToRead = new BufferedReader(new InputStreamReader(System.in));
                try {
                    way = wayToRead.readLine();
                    if(way.equalsIgnoreCase("quit")){
                        System.out.println("\t正在向服务器发送信息");
                        out.writeUTF(way);
                        break;
                    }
                } catch (IOException e) {
                    System.out.println("发生io错误");
                    System.exit(-1);
                }
                System.out.println("\t正在向服务器发送信息");
                out.writeUTF(way);
                System.out.println("\t等待服务器响应...");
                String curTime = in.readUTF();
                System.out.println("依照格式返回的当前时间: " + curTime);
                System.out.println("获取时间完成！\n\n");
            }
            System.out.println("将要退出，关闭输入输出流！");
            in.close();
            out.close();
            connection.close();
        } catch (UnknownHostException uhe) {
            System.err.println("这个错误不太可能发生...因为已经指定了确定的host");
        } catch (IOException ioe) {
            System.err.println("Client：io异常");
        }
    }
}
