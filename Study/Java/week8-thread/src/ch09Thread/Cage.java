package ch09Thread;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cage extends Frame implements ActionListener {
	private Button quit = new Button("Quit");
	private Button start = new Button("Start");
	private Button stop = new Button("Stop");
	private Bird birds[] = new Bird[20];
	Image bird = Toolkit.getDefaultToolkit().getImage("bird.jpg");

	public Cage() {
		super("Cage with Birds");
		setLayout(new FlowLayout());
		add(quit);
		quit.addActionListener(this);
		add(start);
		start.addActionListener(this);
		add(stop);
		stop.addActionListener(this);
		validate();
		setSize(300, 300);
		setVisible(true);
		for (int i = 0; i < birds.length; i++) {
			int x = (int) (getSize().width * Math.random());
			int y = (int) (getSize().height * Math.random());
			birds[i] = new Bird(this, x, y);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == stop)
			for (int i = 0; i < birds.length; i++)
				birds[i].halt();
		if (ae.getSource() == start)
			for (int i = 0; i < birds.length; i++) {
				birds[i].halt();
				birds[i] = new Bird(this, birds[i].x, birds[i].y);
			}
		if (ae.getSource() == quit)
			System.exit(0);
	}

	public void paint(Graphics g) {
		for (int i = 0; i < birds.length; i++)
			if (birds[i] != null)
				birds[i].draw(g);
	}

	public static void main(String args[]) {
		Cage table = new Cage();
	}
}
