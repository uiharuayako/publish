package Network;

import java.io.*;
import java.net.Socket;

public class Sever implements Runnable {
    // 建立连接
    Socket s = null;

    // 文件相关
    File file = new File("./服务器/AutoSave.png");
    long file_size;// 文件大小
    FileOutputStream fos = null;
    InputStream is = null;
    byte[] buffer = new byte[4096 * 2];// 缓存
    String comm = null;// 接受指令
    // 用户相关
    String ID;
    String name;
    Sever(Socket mySS) {
        s = mySS;
    }

    public void run() {
        while (true) {
            // 读取命令
            try {
                InputStreamReader isr = new InputStreamReader(s.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                comm = br.readLine();
            } catch (IOException e) {
                System.out.println("服务器与客户端断开连接");
            }

            // 解析协议
            int index = comm.indexOf("$");
            String protocol = comm.substring(0, index);
            // 解析id
            comm = comm.substring(index + 1);
            index = comm.indexOf("$");
            ID = comm.substring(0, index).trim();
            if (protocol.equals("sync")) {
                try {
                    // 解析长度
                    comm = comm.substring(index + 1);
                    index = comm.indexOf("$");
                    String filesize = comm.substring(0, index).trim();
                    String toolname = comm.substring(index + 1);
                    // 输出流
                    fos = new FileOutputStream(file);
                    file_size = Long.parseLong(filesize);
                    is = s.getInputStream();
                    *//**size为每次接收数据包的长度*//*
                    int size = 0;
                    long count = 0;
                    size = is.read(buffer);
                    *//**使用while循环接收数据包*//*
                    do{
                        fos.write(buffer, 0, size);
                        fos.flush();
                        System.out.println("服务器端接收到数据包，大小为" + size);
                    }while((size = is.read(buffer)) != -1);
                } catch (FileNotFoundException e) {
                    System.out.println("服务器写文件失败");
                } catch (IOException e) {
                    System.out.println("服务器：客户端断开连接");
                } finally {
                    try {
                        if (fos != null)
                            fos.close();
                        System.out.println("end");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            // 现在开始，要向所有线程发送
                buffer = null;
                try {
                    FileInputStream myFIS = new FileInputStream(file);
                    for(int i = 0; i < SeverApp.mySevers.size(); i++) {
                        Sever thisSever = SeverApp.mySevers.get(i);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if (protocol.equals("join")) {
                name = comm.substring(index + 1);// 获取姓名
            }
        }
    }
    void sendImage() {
        buffer = new byte[4096 * 10];
        try {
            FileInputStream myFIS = new FileInputStream(file);
            OutputStream os = s.getOutputStream();
            int size = 0;
            // 循环读取
            while((size = myFIS.read(buffer)) != -1){
                os.write(buffer, 0, size);
                os.flush();
                myFIS.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
