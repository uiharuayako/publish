package ch10Network;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

class ServerSide
{
	public static void main(String[] args) throws Exception
	{
		PrintStream ps=null;
DataInputStream dis=null;
String username;
ServerSocket serverSocket=null;
Socket clientSocket=null;
try { serverSocket=new 
                      ServerSocket(1111);
}catch (IOException e)
{ System.out.println("error"+e);
System.exit(1);}

try { clientSocket=serverSocket.accept();
}catch (IOException e){
   System.out.println("Accept failed.");System.exit(1);}

ps=new PrintStream(clientSocket.getOutputStream());
dis=new  
       DataInputStream(clientSocket.getInputStream()); 

 ps.println("login:"); ps.flush();

if ((username=dis.readLine())==null)
{	System.out.println("readLine returned null");
	System.exit(1); }
System.out.println("Username:"+username);

ps.println("login sucessful"); ps.flush();
System.out.println(username+" has logged off");}

	}

