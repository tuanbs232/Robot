package org.dragon.robot.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.dragon.robot.common.DustColor;
import org.dragon.robot.common.HedgeAlgebra;
import org.dragon.robot.common.ProcessUtil;
import org.dragon.robot.test.Robot;

public class CellPane extends JPanel {

	private int status;
	private Image img;

	private int size;

	private JLabel lbl;

	private Color free = new Color(255, 255, 255);

	private Color unCleaned = new Color(211, 211, 211);

	private final int ROBOT_UP = 1, ROBOT_DOWN = 2, ROBOT_LEFT = 3,
			ROBOT_RIGHT = 4;

	private int repeatCount = 0;
	private String vectorName = "";
	private List<JLabel> vectorImages;

	private String dustValueText;
	private JLabel dustLabel;
	private int dustValue;
	private boolean showDustValue;

	private int timeProcess;

	private Robot robot;
	
	private int oldStatus;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CellPane() {
	}

	public CellPane(int status) {
		this.status = status;

		if (status == 0) {
			setBackground(unCleaned);
		}
	}

	public CellPane(int status, int size, Robot robot) {
		this.size = size;
		this.setBounds(0, 0, size, size);
		this.setLayout(null);
		this.status = status;
		this.robot = robot;

		vectorImages = new ArrayList<>();

		if (status == 0) {
			setBackground(unCleaned);
		} else {
			changeToBarrier();
		}

		this.setLayout(null);
	}

	@Override
	public Dimension getPreferredSize() {
		if (size > 0) {

			return new Dimension(size, size);
		} else {

			return new Dimension(20, 20);
		}
	}

	public void resetBackGround(int status) {
		if (lbl != null) {
			this.remove(lbl);
			repaint();
		}

		if (status == 0) {
			setBackground(unCleaned);
		} else {
			setBackground(free);
			if (dustLabel != null) {
				remove(dustLabel);
			}
		}

		repaint();
	}

	public void changeStatus(int nextVector) {
		this.status++;
		if (!vectorName.equals("")) {
			vectorName += "_";
		}
		switch (nextVector) {
		case ROBOT_UP:
			vectorName += "D_U";
			break;
		case ROBOT_DOWN:
			vectorName += "U_D";
			break;

		case ROBOT_LEFT:
			vectorName += "R_L";
			break;

		case ROBOT_RIGHT:
			vectorName += "L_R";
			break;

		default:
			break;
		}
		resetBackGround(status);

		JLabel lbl = new JLabel();
		lbl.setBounds(1, 1, size - 2, size - 2);
		ImageIcon icon = new ImageIcon(
				"resources/" + vectorName + "_" + repeatCount + ".png");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(size, size,
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		lbl.setIcon(newIcon);
		vectorImages.add(lbl);

		robot.increaseDuplicateSteps(repeatCount);

		vectorName = "";
		repeatCount += 1;
		this.add(lbl);
		repaint();
	}

	private void changeBackGround(String backName) {
		ImageIcon icon = new ImageIcon("resources/" + backName);
		img = icon.getImage();
		Image newimg = img.getScaledInstance(size, size,
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		lbl.setIcon(newIcon);
	}

	public void changeToBarrier() {
		this.oldStatus = status;
		this.status = -1;

		lbl = new JLabel();
		lbl.setBounds(1, 1, size - 2, size - 2);
		changeBackGround("back_01.png");
		this.add(lbl);

		if (dustLabel != null) {
			remove(dustLabel);
		}
		repaint();
	}
	
	public void changeToOldStatus(){
		this.status = oldStatus;
		if(lbl != null){
			remove(lbl);
		}
		
		if(status == 0){
			add(dustLabel);
		}
		
		revalidate();
		repaint();
	}

	public void changeToNormal() {
		this.status = 0;
		resetBackGround(status);
		repeatCount = 0;
		timeProcess = 10 * dustValue;
		if (dustLabel != null) {
			add(dustLabel);
		}

		for (JLabel lbl : vectorImages) {
			this.remove(lbl);
		}
		repaint();
	}

	public void changeToRobot(int vector) {
		if (lbl != null) {
			this.remove(lbl);
			repaint();
		}
		this.status = 1;
		lbl = new JLabel();
		lbl.setBounds(1, 1, size - 2, size - 2);
		switch (vector) {
		case ROBOT_UP:
			changeBackGround("robot_up.jpg");
			vectorName = "D_U";
			break;
		case ROBOT_DOWN:
			changeBackGround("robot_down.jpg");
			vectorName = "U_D";
			break;

		case ROBOT_LEFT:
			changeBackGround("robot_left.jpg");
			vectorName = "R_L";
			break;

		case ROBOT_RIGHT:
			changeBackGround("robot_right.jpg");
			vectorName = "L_R";
			break;

		default:
			break;
		}
		this.add(lbl);
		repaint();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isFree() {
		return this.status == 0;
	}

	public String getDustValueText() {
		return dustValueText;
	}

	public void setDustValueText(String dustValueText) {
		this.dustValueText = dustValueText;

		int showDustValueProp = ProcessUtil.getShowDustValue();
		if (showDustValueProp == 0) {
			showDustValue = false;
		} else {
			showDustValue = true;
		}
		HedgeAlgebra hedge = new HedgeAlgebra();
		dustValue = hedge.getLanguageValue(dustValueText);

		timeProcess = 10 * dustValue;

		unCleaned = DustColor.getColor(dustValue);
		setBackground(unCleaned);

		if (dustLabel != null) {
			remove(dustLabel);
		}

		String html = "<html><center>VALUE</center></html>";
		html = html.replace("VALUE", dustValueText);
		dustLabel = new JLabel(html);
		dustLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dustLabel.setForeground(Color.WHITE);
		dustLabel.setBounds(1, 1, size - 2, size - 2);

		this.add(dustLabel);
		dustLabel.setVisible(showDustValue);
		repaint();
	}

	public void switchShowDustValueType(boolean value) {
		this.showDustValue = value;
		dustLabel.setVisible(value);
		repaint();
	}

	public void resetLanguage() {
		try {
			Integer.parseInt(dustValueText);
		} catch (Exception e) {
			String lang = ProcessUtil.getLanguage();

			String[] standard;
			String[] replace;
			if ("vi".equals(lang)) {
				standard = HedgeAlgebra.langValueEn;
				replace = HedgeAlgebra.langValuesVi;
			} else {
				standard = HedgeAlgebra.langValuesVi;
				replace = HedgeAlgebra.langValueEn;
			}
			for (int i = 0; i < standard.length; i++) {
				if (dustValueText.equals(standard[i])) {
					dustValueText = replace[i];
					String html = "<html><center>VALUE</center></html>";
					html = html.replace("VALUE", dustValueText);
					dustLabel.setText(html);
					repaint();
					return;
				}
			}
		}
	}

	public int getTimeProcess() {
		int result = timeProcess;
		timeProcess = 0;
		return result;
	}

	public void setTimeProcess(int timeProcess) {
		this.timeProcess = timeProcess;
	}
}