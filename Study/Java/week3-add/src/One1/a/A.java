/*
 * @(#)A.java 1.0 04/05/04
 *
 * You can modify the template of this file in the
 * directory ..\JCreator\Templates\Template_1\Project_Name.java
 *
 * You can also create your own project template by making a new
 * folder in the directory ..\JCreator\Template\. Use the other
 * templates as examples.
 *
 */
package myprojects.a;

import java.awt.*;
import java.awt.event.*;

import Latin.*;
import Greek.*;


class A extends Frame {

	public A() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}

	public static void main(String args[]) {
		System.out.println("Starting A...");
		A mainFrame = new A();
		mainFrame.setSize(400, 400);
		mainFrame.setTitle("A");
		mainFrame.setVisible(true);
		Delta d = new Delta();

	}
}
