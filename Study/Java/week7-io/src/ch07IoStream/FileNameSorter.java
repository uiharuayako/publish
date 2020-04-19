package ch07IoStream;
import java.util.*;
import java.io.File;
public class FileNameSorter implements Comparator
{  public int compare(Object o1, Object o2)
   {  File f1 = (File)o1; File f2 = (File)o2;
      if (f1.isDirectory())
      {  if (f2.isDirectory())
            return f1.getName().compareTo(f2.getName());
         else
            return -1;
      }
      else
      {  if (f2.isDirectory())
            return 1;
         else
            return f1.getName().compareTo(f2.getName());
      }
   }
   public boolean equals(Object o1, Object o2)
   {  return ((File)o1).getName().equals(((File)o2).getName()); }
}

