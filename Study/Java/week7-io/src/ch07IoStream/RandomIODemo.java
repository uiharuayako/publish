package ch07IoStream;
   import java.io.*;
   public class RandomIODemo{
     public static void main(String args[]) throws IOException{
      RandomAccessFile raf=new RandomAccessFile("random.txt","rw");
      raf.writeBoolean(true);
      raf.writeInt(123456);
      raf.writeChar('j');
      raf.writeDouble(1234.56);
      raf.seek(1);
      System.out.println(raf.readInt());
      System.out.println(raf.readChar());
      System.out.println(raf.readDouble());
      raf.seek(0);
      System.out.println(raf.readBoolean());
      raf.close();
     }
   }
