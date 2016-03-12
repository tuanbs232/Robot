package org.dragon.robot.test;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dragon.robot.common.DustColor;
import org.dragon.robot.common.HedgeAlgebra;
import org.dragon.robot.gui.CellPane;

public class Cell extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2571667571150068788L;

	private int status;

	//Luu string de hien thi len giao dien
	private String dustValueName;
	private int dustValue;
	//Vua la thoi gian xu ly, vua la gia tri do ban de tinh toan mau cho cell
	private int timeProcess = 0;
	
	private Image img;

	private int size;

	private JLabel lbl;
	
	public static void main(String[] args) {
		String result = "0";
		long ran = (long) (Math.random()*100);
		System.out.println(ran);
	}
	
	public Cell(int status, int size){
		this.setStatus(status);
		this.setLayout(null);
		this.setBounds(0, 0, size, size);
		this.size = size;
	}

	public String getDustValueName() {
		return dustValueName;
	}

	public void setDustValueName(String dustValueKey) {
		this.dustValueName = dustValueKey;

		HedgeAlgebra hedge = new HedgeAlgebra();
		dustValue = hedge.getLanguageValue(dustValueName);
		setBackground(DustColor.getColor(dustValue));
		timeProcess = dustValue*10;
	}

	public int getTimeProcess() {
		return timeProcess;
	}
	
	public void changeBackGround(String backName) {
		JLabel lbl = new JLabel();
		lbl.setBounds(1, 1, size - 2, size - 2);
		
		ImageIcon icon = new ImageIcon("resources/" + backName);
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(size, size,
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		lbl.setIcon(newIcon);
		this.add(lbl);
		repaint();
	}

	public void changeToBarrier() {
		this.status = -1;

		lbl = new JLabel();
		lbl.setBounds(1, 1, size - 2, size - 2);
		changeBackGround("back_01.png");
		this.add(lbl);
		repaint();
	}

	public void setTimeProcess(int timeProcess) {
		this.timeProcess = timeProcess;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
