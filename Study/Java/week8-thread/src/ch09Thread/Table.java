package ch09Thread;

import java.awt.*;
import java.awt.event.*;

public class Table extends Frame implements ActionListener
{  private Button quit  = new Button("Quit");
   private Button start = new Button("Start");
   private Button stop  = new Button("Stop");
   private Marble marbles[]   = new Marble[20];
   Image bird= Toolkit.getDefaultToolkit().getImage("bird.jpg");
   public Table()
   {  super("Table with Marbles");
      setLayout(new FlowLayout());
      add(quit);  quit.addActionListener(this);
      add(start); start.addActionListener(this);
      add(stop);  stop.addActionListener(this);
      validate(); setSize(300,300);
      setVisible(true);
      for (int i = 0; i < marbles.length; i++)
      {  int x = (int)(getSize().width*Math.random());
         int y = (int)(getSize().height*Math.random());
         marbles[i] = new Marble(this, x, y);
      }
   }
   public void actionPerformed(ActionEvent ae)
   {  if (ae.getSource() == stop)
         for (int i = 0; i < marbles.length; i++)
            marbles[i].halt();
      if (ae.getSource() == start)
         for (int i = 0; i < marbles.length; i++)
         {  marbles[i].halt();
            marbles[i] = new Marble(this, marbles[i].x, marbles[i].y);
         }
      if (ae.getSource() == quit)
         System.exit(0);
   }



   public void paint(Graphics g)
   {  for (int i = 0; i < marbles.length; i++)
         if (marbles[i] != null)
            marbles[i].draw(g);
    }

    public static void main(String args[] )
    { Table table = new Table();  }
}
