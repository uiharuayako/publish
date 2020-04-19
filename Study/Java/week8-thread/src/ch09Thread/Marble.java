package ch09Thread;

import java.awt.*;
import java.awt.event.*;
public class Marble extends Thread
{  private int xdir = 2*(1-2*(int)Math.round(Math.random()));
   private int ydir = 2*(1-2*(int)Math.round(Math.random()));
   private boolean running = false;
   private Table table = null;
   protected int x,y;
   Image bird= Toolkit.getDefaultToolkit().getImage("bird.jpg");
   public Marble(Table _table, int _x, int _y)
   { table = _table;
     x = _x;
     y = _y;
     start();
   }

   public void start()
   { running = true;
     super.start();
   }
  
   public void halt()
   {
     running = false;
   }
    
   public void run()
   {
    while (running)
     {
       move();
       try 
       { sleep(200);}
       catch (InterruptedException ie)
       { System.err.println("Thread interrupted");}
       table.repaint();
       //this.draw();
      }
    }

    private void move()
    {
      x+=xdir;
      y+=ydir;
      if (x > table.getSize().width) 
         { x = table.getSize().width;
           xdir *= (-1);
         }
      if  (x < 0)
          xdir *= (-1);

      if (y > table.getSize().height)
         {
          y =table.getSize().height;
          ydir *= (-1);
         }   
         if (y < 0)
         ydir *= (-1);
     }

     public void draw(Graphics g)
     {
       //g.setColor(Color.red);
       g.drawImage(bird,x,y,30,40,table);
       //g.fillOval(x, y, 30, 30);
     }
  
}
 

