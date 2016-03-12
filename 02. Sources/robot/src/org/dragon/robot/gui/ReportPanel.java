package org.dragon.robot.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.dragon.robot.common.LanguageUtil;

public class ReportPanel extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7735709131705263625L;
	
	private MainGui mainGui;
	
	private JLabel timeProcessLbl, numOfStepsLbl, duplicateStepsLbl, coverageAreaLbl;
	private TitledBorder border;
	
	private JLabel timeprocess, numOfSteps, duplicateSteps, coverageArea;
	
	public ReportPanel(MainGui main){
		super();

		String title = LanguageUtil.getProperty(LanguageUtil.REPORT_TITLE);
		setTitle(title);
		
		setSize(400, 260);
		setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setBackground(Color.WHITE);
		JPanel pane0 = new JPanel();
		pane0.setLayout(null);
		pane0.setBackground(Color.WHITE);
		pane0.setBounds(0, 0, getWidth(), getHeight());
		add(pane0);
		
		this.mainGui = main;
		setLocationRelativeTo(mainGui);
		
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10, 10, getWidth()-25, getHeight()-50);

		String result = LanguageUtil.getProperty(LanguageUtil.REPORT_RESULT);
		border = BorderFactory.createTitledBorder(result);
		border.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		pane.setBorder(border);
		pane0.add(pane);

		String timeProcessLblTxt = LanguageUtil.getProperty(LanguageUtil.REPORT_TIME_PROCESS);
		timeProcessLbl = new JLabel(timeProcessLblTxt, SwingConstants.RIGHT);
		timeProcessLbl.setBounds(20, 20, 100, 25);
		pane.add(timeProcessLbl);
		
		Font valueFont = new Font("Tahoma", Font.BOLD, 12);
		
		timeprocess = new JLabel("58000", SwingConstants.LEFT);
		timeprocess.setFont(valueFont);
		timeprocess.setBounds(150, 20, 100, 25);
		pane.add(timeprocess);
		
		JLabel note1 = new JLabel("(miliseconds)");
		note1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		note1.setBounds(210, 20, 100, 25);
		pane.add(note1);
		
		JSeparator s1 = new JSeparator();
		s1.setBounds(60, 45, 250, 25);
		pane.add(s1);

		String numOfStepsLblTxt = LanguageUtil.getProperty(LanguageUtil.REPORT_NUMBER_OF_STEPS);
		numOfStepsLbl = new JLabel(numOfStepsLblTxt, SwingConstants.RIGHT);
		numOfStepsLbl.setBounds(20, 70, 100, 25);
		pane.add(numOfStepsLbl);
		
		numOfSteps = new JLabel("101", SwingConstants.LEFT);
		numOfSteps.setFont(valueFont);
		numOfSteps.setBounds(150, 70, 100, 25);
		pane.add(numOfSteps);
		
		JSeparator s2 = new JSeparator();
		s2.setBounds(60, 95, 250, 25);
		pane.add(s2);

		String duplicateStepsLblTxt = LanguageUtil.getProperty(LanguageUtil.REPORT_DUPLICATE_STEPS);
		duplicateStepsLbl = new JLabel(duplicateStepsLblTxt, SwingConstants.RIGHT);
		duplicateStepsLbl.setBounds(20, 120, 100, 25);
		pane.add(duplicateStepsLbl);
		
		duplicateSteps = new JLabel("4", SwingConstants.LEFT);
		duplicateSteps.setFont(valueFont);
		duplicateSteps.setBounds(150, 120, 100, 25);
		pane.add(duplicateSteps);
		
		JSeparator s3 = new JSeparator();
		s3.setBounds(60, 145, 250, 25);
		pane.add(s3);

		String coverageAreaLblTxt = LanguageUtil.getProperty(LanguageUtil.REPORT_COVERAGE_AREA);
		coverageAreaLbl = new JLabel(coverageAreaLblTxt, SwingConstants.RIGHT);
		coverageAreaLbl.setBounds(20, 170, 100, 25);
		pane.add(coverageAreaLbl);
		
		coverageArea = new JLabel("65.66", SwingConstants.LEFT);
		coverageArea.setFont(valueFont);
		coverageArea.setBounds(150, 170, 100, 25);
		pane.add(coverageArea);
		
		JLabel note2 = new JLabel("(%)");
		note2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		note2.setBounds(210, 170, 100, 25);
		pane.add(note2);
		
		JSeparator s4 = new JSeparator();
		s4.setBounds(60, 195, 250, 25);
		pane.add(s4);
		
		revalidate();
		repaint();
	}
	
	public void showReport(int time, int steps, int duplicateSteps, double coverageArea){
		timeprocess.setText("" + time);
		numOfSteps.setText("" + steps);
		this.duplicateSteps.setText("" + duplicateSteps);
		this.coverageArea.setText("" + coverageArea);
		
		revalidate();
		repaint();
		setVisible(true);
	}
	
	public void resetLanguage(){
		String title = LanguageUtil.getProperty(LanguageUtil.REPORT_TITLE);
		setTitle(title);
		String result = LanguageUtil.getProperty(LanguageUtil.REPORT_TITLE);
		border.setTitle(result);
		String timeProcessLblTxt = LanguageUtil.getProperty(LanguageUtil.REPORT_TIME_PROCESS);
		timeProcessLbl.setText(timeProcessLblTxt);
		String numOfStepsLblTxt = LanguageUtil.getProperty(LanguageUtil.REPORT_NUMBER_OF_STEPS);
		numOfStepsLbl.setText(numOfStepsLblTxt);
		String duplicateStepsLblTxt = LanguageUtil.getProperty(LanguageUtil.REPORT_DUPLICATE_STEPS);
		duplicateStepsLbl.setText(duplicateStepsLblTxt);
		String coverageAreaLblTxt = LanguageUtil.getProperty(LanguageUtil.REPORT_COVERAGE_AREA);
		coverageAreaLbl.setText(coverageAreaLblTxt);
		
		revalidate();
		repaint();
	}

}
