//package org.dragon.robot.test;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.EventQueue;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Point;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//import javax.swing.border.Border;
//import javax.swing.border.MatteBorder;
//
//public class TestGrid02 {
//
//    public static void main(String[] args) {
//        new TestGrid02();
//    }
//
//    public TestGrid02() {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//                }
//
//                JFrame frame = new JFrame("Testing");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setLayout(new BorderLayout());
//                
//
//
//        		List<Point> list = new ArrayList<>();
//        		list.add(new Point(2, 2));
//        		list.add(new Point(4, 2));
//        		list.add(new Point(0, 4));
//        		list.add(new Point(4, 6));
//        		Robot robot = new Robot(8, list);
//                
//
//
////        		List<Point> list = new ArrayList<>();
////        		list.add(new Point(2, 2));
////        		list.add(new Point(3, 2));
////        		list.add(new Point(2, 3));
////        		list.add(new Point(3, 3));
////        		
////        		list.add(new Point(2, 4));
////        		list.add(new Point(2, 5));
////        		list.add(new Point(3, 4));
////        		list.add(new Point(3, 5));
////        		
////        		list.add(new Point(0, 8));
////        		list.add(new Point(0, 9));
////        		list.add(new Point(1, 8));
////        		list.add(new Point(1, 9));
////        		
////        		list.add(new Point(6, 0));
////        		list.add(new Point(7, 0));
////        		list.add(new Point(6, 1));
////        		list.add(new Point(7, 1));
////        		
////        		list.add(new Point(8, 4));
////        		list.add(new Point(8, 5));
////        		list.add(new Point(9, 4));
////        		list.add(new Point(9, 5));
////        		
////        		list.add(new Point(8, 6));
////        		list.add(new Point(6, 6));
////        		list.add(new Point(4, 10));
////        		list.add(new Point(10, 8));
////        		list.add(new Point(10, 10));
////        		Robot robot = new Robot(12, list);
//        		
//                frame.add(robot);
//                frame.pack();
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);
//            }
//        });
//    }
//
//    public class TestPane extends JPanel implements ActionListener{
//        private Timer timer, timer1;
//        private final int DELAY = 500;
//    	
//    	private List<CellPane> listCells;
//
//        /**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//
//		public TestPane() {
//            setLayout(new GridBagLayout());
//
//            GridBagConstraints gbc = new GridBagConstraints();
//            listCells = new ArrayList<>();
//            for (int row = 0; row < 12; row++) {
//                for (int col = 0; col < 12; col++) {
//                    gbc.gridx = col;
//                    gbc.gridy = row;
//
//                    CellPane cellPane = new CellPane(0);
//                    listCells.add(cellPane);
//                    Border border = null;
//                    if (row < 4) {
//                        if (col < 4) {
//                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
//                        } else {
//                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
//                        }
//                    } else {
//                        if (col < 4) {
//                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
//                        } else {
//                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
//                        }
//                    }
//                    cellPane.setBorder(border);
//                    add(cellPane, gbc);
//                }
//            }
//
//            timer = new Timer(DELAY, this);
//            timer.start();
//
//            timer1 = new Timer(2000, this);
//            timer1.start();
//        }
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//
//	    	double r = Math.random();
//	    	int rand = (int) Math.round(r * 140);
//	    	
//	    	listCells.get(rand).resetBackGround(-1);
//		}
//    }
//
//    public class CellPane extends JPanel {
//    	
//    	private int status;
//
//        /**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//
//        public CellPane() {
//        }
//        
//        public CellPane(int status){
//        	this.status = status;
//        	
//        	if(status == 0){
//        		setBackground(Color.WHITE);
//        	} else if(status == 2){
//        		setBackground(Color.BLACK);
//        	}
//        }
//
//        @Override
//        public Dimension getPreferredSize() {
//            return new Dimension(50, 50);
//        }
//        
//        public void resetBackGround(int status){
//        	if(status == -1){
//        		setBackground(Color.DARK_GRAY);
//        	}
//        }
//
//		public int getStatus() {
//			return status;
//		}
//
//		public void setStatus(int status) {
//			this.status = status;
//		}
//    }
//}