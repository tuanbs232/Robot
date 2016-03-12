package org.dragon.robot.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestGrid01 {
	
	public static void main(String[] args) {
//		new TestGrid01();
		List<String> list = new ArrayList<>();
		list.add("test");
		
	}

	public TestGrid01() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}

				JFrame frame = new JFrame("Testing");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				TestPane test = new TestPane();
				frame.add(test);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public class TestPane extends JPanel implements ActionListener {


		/**
		 * 
		 */
		private static final long serialVersionUID = 2484474944765559935L;
		private int columnCount = 15;
		private int rowCount = 15;
		private List<Rectangle> cells;
		private List<Boolean> cellColors;

		public TestPane() {
			cells = new ArrayList<>(columnCount * rowCount);
			cellColors = new ArrayList<>(columnCount * rowCount);
			int width = 400;
			int height = 400;

			int cellWidth = width / columnCount;
			int cellHeight = height / rowCount;

			int xOffset = (width - (columnCount * cellWidth)) / 2;
			int yOffset = (height - (rowCount * cellHeight)) / 2;

			if (cells.isEmpty()) {
				for (int row = 0; row < rowCount; row++) {
					for (int col = 0; col < columnCount; col++) {
						Rectangle cell = new Rectangle(xOffset + (col * cellWidth), yOffset + (row * cellHeight),
								cellWidth, cellHeight);
						cells.add(cell);
						cellColors.add(false);
					}
				}
				cellColors.set(13*columnCount, true);
			}
		}
		
		public int moveCell(int x, int y){
			int currentIndex = x + y*columnCount;
			fillCell(currentIndex);
			
			if(x >= 0 && y - 1 >= 0){
				int index = (x) + (y - 1) * columnCount;
				if(!cellColors.get(index)){
					System.out.println("up");
					fillCell(index);
					
					return 1;
				}
			}
			
			if(x -1 >= 0 && y >= 0){
				int index = (x-1) + (y) * columnCount;
				if(!cellColors.get(index)){
					System.out.println("left");
					fillCell(index);
					
					return 2;
				}
			}


			if(x >= 0 && y+1 <= (columnCount - 1)){
				int index = (x) + (y+1) * columnCount;
				if(!cellColors.get(index)){
					System.out.println("down");
					fillCell(index);
					
					return 3;
				}
			}


			if(x +1 <= (columnCount - 1) && y >= 0){
				int index = (x+1) + (y) * columnCount;
				if(!cellColors.get(index)){
					System.out.println("right");
					fillCell(index);
					
					return 4;
				}
			}
			
			return -1;
		}

		public void fillCell(int index) {
			cellColors.set(index, true);

			repaint();
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(400, 400);
		}

		@Override
		public void invalidate() {
			super.invalidate();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();

			for (int i = 0; i < cells.size(); i++) {
				Rectangle cell = cells.get(i);
				if (cellColors.get(i)) {
					g2d.setColor(Color.BLUE);
					g2d.fill(cell);
				} else {
					g2d.setColor(Color.GRAY);
				}
				g2d.draw(cell);
			}

			g2d.dispose();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			fillCell(0);
		}
	}
}