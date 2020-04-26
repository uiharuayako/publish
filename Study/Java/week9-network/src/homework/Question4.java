package homework;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Question4 {
    public static void main(String arg[]) {
        try {
            URL rsURL = new URL("http://www.baidu.com");
            //URL rsURL = new URL("http://rsgis.whu.edu.cn/");
            //上面这个链接我这报错 ERR_CONNECTION_TIMED_OUT
            URLConnection connect=rsURL.openConnection();
            InputStream conInput=connect.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(conInput,"utf-8"));
            StringBuilder resultStr = new StringBuilder();
            String line;
            while ((line = bReader.readLine()) != null) {
                resultStr.append(line + "\r\n");
            }
            BufferedWriter bWriter = new BufferedWriter(new FileWriter("RSHomepage.html"));
            bWriter.write(resultStr.toString());
            bWriter.close();
        }catch (Exception e){
            System.out.println("发生未知错误");
        }
    }
}
