package homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class GetNetworkTime implements Runnable{
    private Socket connection;
    public GetNetworkTime(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            DataInputStream in = new DataInputStream(connection.getInputStream());
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String wayToShow = new String("date");
            String result;
            while (!wayToShow.equalsIgnoreCase("QUIT")) {
                wayToShow = in.readUTF();
                if(wayToShow.equalsIgnoreCase("QUIT")){
                    System.out.println("收到客户端的退出信号");
                    break;
                }
                result = getBaiduTime(wayToShow);
                System.out.println("将向客户端返回时间: " + result);
                out.writeUTF(result);
            }
            in.close();
            out.close();
            connection.close();
            System.out.println("已经断开与某一线程上的客户端的连接!\n");
        } catch (IOException ioe) {
            System.out.println("Thread：发生io异常！");
        }
    }
    public static String getBaiduTime(String way) {
        try {
            URL url=new URL("http://www.baidu.com");
            URLConnection conn=url.openConnection();
            conn.connect();
            long dateL=conn.getDate();
            Date date=new Date(dateL);
            if(way.equals("date")){
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.format(date);
            }
            if(way.equals("time")){
                SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
                return dateFormat.format(date);
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "未知错误！";
    }
}
