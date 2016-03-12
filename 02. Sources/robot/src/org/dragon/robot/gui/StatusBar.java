package org.dragon.robot.gui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.dragon.robot.common.LanguageUtil;

public class StatusBar extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel message;
	
	private int workingTimer = 0;

	private JLabel lbl1;
	/** Creates a new instance of StatusBar */
    public StatusBar() {
        super();
        this.setLayout(null);
        Border loweredbevel = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);


        this.setBorder(loweredbevel);
        String lbl1Text = LanguageUtil.getProperty(LanguageUtil.MAINGUI_ROBOT_WORKING_AT);
        lbl1 = new JLabel(lbl1Text);
        lbl1.setBounds(10, 1, 150, 25);
        this.add(lbl1);
        
        message = new JLabel();
        message.setBounds(100, 1, 300, 25);
        this.add(message);
        
        resetMessage();
    }

    public void setMessage(int x, int y, int time) {
    	workingTimer += time;
        String lbl1Text = LanguageUtil.getProperty(LanguageUtil.MAINGUI_WORKING_TIME);
        message.setText("[ "+ x + ", " + y + " ]   |   " + lbl1Text + workingTimer + " (miliseconds)");        
    }   
    
    public void resetMessage(){
    	workingTimer = 0;
        String lbl1Text = LanguageUtil.getProperty(LanguageUtil.MAINGUI_WORKING_TIME);
    	message.setText("[ "+ "_" + ", " + "_" + " ]   |   " + lbl1Text + "____" + " (miliseconds)");
    }
    
    public void resetLanguage(){
        String lbl1Text = LanguageUtil.getProperty(LanguageUtil.MAINGUI_ROBOT_WORKING_AT);
    	lbl1.setText(lbl1Text);
        String lbl2Text = LanguageUtil.getProperty(LanguageUtil.MAINGUI_WORKING_TIME);
    	message.setText("[ "+ "_" + ", " + "_" + " ]   |   " + lbl2Text + "____" + " (miliseconds)");
    }
    
    public int getworkingTime(){
    	return workingTimer;
    }
}