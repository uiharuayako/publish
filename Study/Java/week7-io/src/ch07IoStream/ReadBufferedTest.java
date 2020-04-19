package ch07IoStream;
import java.io.*;
public class ReadBufferedTest{
public static void main(String args[]){
      double sum = 0;
      try{
         long start = System.currentTimeMillis();
         FileInputStream fs_in = new FileInputStream ("sample.dat");
         BufferedInputStream bfs_in = new BufferedInputStream(fs_in);
         DataInputStream in = new DataInputStream(bfs_in);
         for (int i = 0; i < 100000; i++)
            sum += in.readDouble();
         in.close();
         long stop  = System.currentTimeMillis();
         System.out.println("Average: " + (sum / 100000));
         System.out.println("Time passed: " + (stop - start));
      }catch(IOException ioe) {
         System.out.println(ioe);
      }
   }
}
