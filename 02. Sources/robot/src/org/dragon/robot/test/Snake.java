package org.dragon.robot.test;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class Snake extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8239994188609106044L;


	public Snake() {

        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new Snake();
                ex.setVisible(true);                
            }
        });
    }
}
