//package org.dragon.robot.gui;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.awt.Point;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.JFrame;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//
//import org.dragon.robot.test.Robot;
//import org.dragon.robot.test.Robot2;
//
//public class MapPane {
//	public static void main(String[] args) {
//		new MapPane();
//	}
//
//	public MapPane() {
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
//                frame.setLayout(null);
//                
//                frame.setSize(800, 650);
//                
//
//
//        		List<Point> list = new ArrayList<>();
//        		list.add(new Point(2, 2));
//        		list.add(new Point(3, 2));
//        		list.add(new Point(2, 3));
//        		list.add(new Point(3, 3));
//        		
//        		list.add(new Point(2, 4));
//        		list.add(new Point(2, 5));
//        		list.add(new Point(3, 4));
//        		list.add(new Point(3, 5));
//        		
////        		list.add(new Point(0, 8));
////        		list.add(new Point(0, 9));
////        		list.add(new Point(1, 8));
////        		list.add(new Point(1, 9));
//        		
//        		list.add(new Point(6, 0));
//        		list.add(new Point(7, 0));
//        		list.add(new Point(6, 1));
//        		list.add(new Point(7, 1));
//        		
//        		list.add(new Point(8, 4));
//        		list.add(new Point(8, 5));
//        		list.add(new Point(9, 4));
//        		list.add(new Point(9, 5));
//        		
//        		list.add(new Point(8, 6));
//        		list.add(new Point(8, 7));
//        		list.add(new Point(9, 6));
//        		list.add(new Point(9, 7));
//        		
//        		list.add(new Point(6, 6));
//        		list.add(new Point(6, 7));
//        		list.add(new Point(6, 6));
//        		list.add(new Point(7, 7));
//        		
//        		list.add(new Point(4, 10));
//        		list.add(new Point(4, 11));
//        		list.add(new Point(5, 10));
//        		list.add(new Point(5, 11));
//        		
//        		list.add(new Point(10, 8));
//        		list.add(new Point(10, 9));
//        		list.add(new Point(11, 8));
//        		list.add(new Point(11, 9));
//        		
//        		list.add(new Point(10, 10));
//        		Robot robot = new Robot(20, list);
//        		robot.setBounds(0, 0, 600, 600);
//        		robot.init();
//                
////                List<Point> list = new ArrayList<>();
////        		list.add(new Point(2, 2));
////        		list.add(new Point(4, 2));
////        		list.add(new Point(0, 4));
////        		list.add(new Point(4, 6));
////        		Robot robot = new Robot(8, list);
//        		
//                frame.add(robot);
////                frame.pack();
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);
//            }
//        });
//    }
//}
