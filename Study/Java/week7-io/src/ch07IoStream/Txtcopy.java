package ch07IoStream;
import java.io.*; 
class Txtcopy 
{   public static void main (String args[]) 
  {  byte[] b1=new byte[255];
     byte[] b2=new byte[255];
     byte[] b3=new byte[2056];
     byte[] b4=new byte[2056];
     try 
      { System.out.println("请输入源文件名称：\n");
        System.in.read(b1,0,255);
        System.out.println("\n请输入目的文件名称：\n");
        System.in.read(b2,0,255);
 String sourceName=new String(b1);
//if (!sourceName.trim().equals(new String("F"))){System.out.println(sourceName);};
 String desName=new String(b2);
 FileInputStream fileInput=new FileInputStream(sourceName.trim());
 int bytes1=fileInput.read(b3);
 String sourceFile=new String(b3);
 
 FileOutputStream fileOutput=new FileOutputStream(desName.trim());
 fileOutput.write(b3,0,bytes1);
 fileInput=new FileInputStream(desName.trim());
 int bytes2=fileInput.read(b4,0,2056);
 String desFile=new String(b4);
 System.out.println("\n源文件内容为：\n") ;
 System.out.println(sourceFile);
 System.out.println("\n目的文件内容为：\n");
 System.out.println(desFile);
 } 
catch(Exception e)
   { System.out.println(e.toString());}
}
}



