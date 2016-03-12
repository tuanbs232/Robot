package org.dragon.robot.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainClass extends JPanel {

//	public void paint(Graphics g) {
//		Dimension d = this.getPreferredSize();
//		int fontSize = 20;
//
//		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
//
//		g.setColor(Color.red);
//
//		g.drawString("www.java2s.com", 10, 20);
//		repaint();
//	}
	
	public MainClass(int size, int status){
	}

	public void drawText(int x, int y) {
		Graphics g = getGraphics();
		Graphics2D s = (Graphics2D) g;
		int fontSize = 20;
		System.out.println("test: x = " + x + ", y = " + y);

		s.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		s.setStroke(new BasicStroke(5));
		s.setColor(Color.red);
		s.drawLine(0, 0, this.getWidth(), this.getHeight());
//		revalidate();
//		repaint();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		MainClass pane = new MainClass(1, 1);
		frame.getContentPane().add(pane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 350);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		int x = 10;
		int y = 20;
		for (int i = 0; i < 5; i++) {
			y += 10;
			pane.drawText(x, y);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		frame.revalidate();
		frame.repaint();
	}
}