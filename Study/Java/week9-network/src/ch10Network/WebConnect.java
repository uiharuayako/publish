package ch10Network;
import java.net.*;
import java.io.*;

public class WebConnect
{  public static void main(String args[])
   {  try
      {  Socket connection = new Socket(args[0], 80);
         System.out.println("Connection established:");
         System.out.println("Local Connection Information:");
         System.out.println("\t address:" +connection.getLocalAddress());
         System.out.println("\t port:" + connection.getLocalPort());
         System.out.println("Remote Connection Information:");
         System.out.println("\t address:" +connection.getInetAddress());
         System.out.println("\t port:" + connection.getPort());
         connection.close();
      }
      catch(UnknownHostException uhe)
      {  System.err.println("Unknown host: " + args[0]); } 
      catch(IOException ioe)
      {  System.err.println("IOException: " + ioe); }
   }
}
