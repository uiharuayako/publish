package ch07IoStream;
import java.io.*; 
class FileInput{
    public static void main(String  args[]){ 
        byte buffer[]=new byte[2056];
        try{
            FileInputStream fileInput
                =new FileInputStream("d:\\javaz\\ch6\\input.java");
            int bytes=fileInput.read(buffer,0,2056);
            String str=new String(buffer);
            System.out.println(str);
        }catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
