package Network;

import FXStage.MyStatus;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever extends Thread{
    // 建立连接
    ServerSocket ss = null;
    Socket s = null;

    // 文件相关
    File file = new File("./我的作品/AutoSave.png");
    FileOutputStream fos = null;
    InputStream is = null;
    byte[] buffer = new byte[4096 * 5];// 缓存
    String comm = null;// 接受指令
    public void run(){
        while (true){
            try {
                ss = new ServerSocket(4004);
                s = ss.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            String getID = comm.substring(0, index).trim();
            if(protocol.equals("sync")){
                if(!getID.equals(MyStatus.id)) {
                    try {
                        // 解析长度
                        comm = comm.substring(index + 1);
                        index = comm.indexOf("$");
                        String filesize = comm.substring(0, index).trim();
                        String toolname = comm.substring(index + 1);
                        // 输出流
                        fos = new FileOutputStream(file);
                        long file_size = Long.parseLong(filesize);
                        is = s.getInputStream();
                        /**size为每次接收数据包的长度*/
                        int size = 0;
                        /**count用来记录已接收到文件的长度*/
                        long count = 0;

                        /**使用while循环接收数据包*/
                        while (count < file_size) {
                            /**从输入流中读取一个数据包*/
                            size = is.read(buffer);

                            /**将刚刚读取的数据包写到本地文件中去*/
                            fos.write(buffer, 0, size);
                            fos.flush();

                            /**将已接收到文件的长度+size*/
                            count += size;
                            System.out.println("服务器端接收到数据包，大小为" + size);
                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("服务器写文件失败");
                    } catch (IOException e) {
                        System.out.println("服务器：客户端断开连接");
                    } finally {
                        /**
                         * 将打开的文件关闭
                         * 如有需要，也可以在此关闭socket连接
                         * */
                        try {
                            if (fos != null)
                                fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }//catch (IOException e)
                    }//finally
                }
            }


        }
    }
}
