package org.dragon.robot.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;

import org.dragon.robot.common.LanguageUtil;
import org.dragon.robot.common.ProcessUtil;
import org.dragon.robot.test.Robot;

public class MainGui extends JFrame
		implements ActionListener, MouseListener, ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem exit;
	private JMenuItem aboutUs;
	private JMenuItem guide;
	private JMenuItem newRobot;
	private JCheckBoxMenuItem vietnamese, english, showDustValue;
	private JMenu language, fileMenu, optionMenu, helpMenu;

	JLabel start, initWorkArea, initDust, restart;
	private JLabel areaInputLabel;

	private Robot robot;

	private PlaceholderTextField areaSizeInput;
	private int areaSize;
	public int getAreaSize(){
		return areaSize;
	}
	private JComboBox<String> modeList;

	private StatusBar statusBar;
	private JPanel pane;

	private HegePanel hedgePan;
	private StandardModePanel standardModePan;
	private HedgeAndBarrierPanel hedgeAndBarrierPan;
	private ReportPanel report;

	public static final int STANDARD_MODE = 0;
	public static final int DSGT_MODE = 1;
	public static final int DSGT_DINAYMIC_MODE = 2;

	private int currentMode = DSGT_MODE;
	private int showDustValueProp = 0;

	private JLabel modeLbl;
	private JPanel menu;

	public static void main(String[] args) {
		MainGui gui = new MainGui();
		gui.validate();
	}

	public MainGui() {
		super(LanguageUtil.getProperty(LanguageUtil.MAINGUI_PROGRAM_NAME));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException ex) {
		}
		this.setIconImage(new ImageIcon("resources/shortcut.png").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(908, 626);
		this.setResizable(false);

		init();

		statusBar = new StatusBar();
		statusBar.setBounds(1, 550, 900, 25);
		this.add(statusBar);
		this.setJMenuBar(createMenu());
		this.setLocationRelativeTo(null);

		menu = new JPanel();
		menu.setBounds(550, 0, 351, 550);
		menu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		menu.setLayout(null);
		menu.setBackground(Color.WHITE);

		String modeLblText = LanguageUtil
				.getProperty(LanguageUtil.SELECT_MODE_TITLE);
		modeLbl = new JLabel(modeLblText);
		modeLbl.setBounds(20, 35, 200, 25);
		menu.add(modeLbl);

		String startText = LanguageUtil.getProperty(LanguageUtil.MAINGUI_START);
		start = new JLabel(startText, SwingConstants.CENTER);
		start.setFont(new Font("Tahoma", Font.PLAIN, 12));
		start.setBounds(22, 490, 145, 35);
		start.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		start.setEnabled(false);
		menu.add(start);

		String restartText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_RESTART);
		restart = new JLabel(restartText, SwingConstants.CENTER);
		restart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		restart.setBounds(185, 490, 145, 35);
		restart.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		restart.setEnabled(false);
		menu.add(restart);

		String areaInputLblText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_ENTER_SIZE_OF_WORK_AREA);
		areaInputLabel = new JLabel(areaInputLblText);
		areaInputLabel.setBounds(20, 100, 290, 25);
		menu.add(areaInputLabel);

		areaSizeInput = new PlaceholderTextField();
		String areaSizeInputText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_SIZE_OF_WORK_AREA);
		areaSizeInput.setPlaceholder(areaSizeInputText);
		areaSizeInput.setBounds(20, 127, 310, 25);
		areaSizeInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					initMap();
				}
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		menu.add(areaSizeInput);

		String initWorkAreaText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_INIT_WORK_AREA);
		initWorkArea = new JLabel(initWorkAreaText, SwingConstants.CENTER);
		initWorkArea.setFont(new Font("Tahoma", Font.PLAIN, 12));

		initWorkArea.setBounds(22, 160, 145, 25);
		initWorkArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		initWorkArea.addMouseListener(this);
		menu.add(initWorkArea);

		String initDustText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_IINIT_DUST);
		initDust = new JLabel(initDustText, SwingConstants.CENTER);
		initDust.setFont(new Font("Tahoma", Font.PLAIN, 12));
		initDust.setBounds(185, 160, 145, 25);
		initDust.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		initDust.setEnabled(false);
		menu.add(initDust);

		hedgePan = new HegePanel(this);
		hedgePan.setBounds(20, 200, 310, 275);

		standardModePan = new StandardModePanel();
		standardModePan.setBounds(20, 200, 312, 275);

		hedgeAndBarrierPan = new HedgeAndBarrierPanel(this);
		hedgeAndBarrierPan.setBounds(20, 200, 310, 275);

		switch (currentMode) {
		case STANDARD_MODE:
			menu.add(standardModePan);
			break;
		case DSGT_MODE:
			menu.add(hedgePan);
			break;
		case DSGT_DINAYMIC_MODE:
			menu.add(hedgeAndBarrierPan);
			break;

		default:
			break;
		}

		String standardModeName = LanguageUtil
				.getProperty(LanguageUtil.SELECT_STANDARD_MODE);
		String hedgeModeName = LanguageUtil
				.getProperty(LanguageUtil.SELECT_DSGT_MODE);
		String hedandBarierModeName = LanguageUtil
				.getProperty(LanguageUtil.SELECT_DSGT_DYNAMIC_MODE);
		String[] modes = { standardModeName, hedgeModeName,
				hedandBarierModeName };

		modeList = new JComboBox<>(modes);
		modeList.setBounds(20, 60, 310, 25);
		modeList.addItemListener(this);
		modeList.setSelectedIndex(currentMode);
		menu.add(modeList);

		pane = new JPanel();
		pane.setBounds(1, 0, 549, 550);
		pane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		JLabel gif = new JLabel();
		ImageIcon i = new ImageIcon("resources/default.gif");
		gif.setIcon(i);
		gif.setBounds(0, 0, 549, 549);
		pane.add(gif);
		this.add(pane);

		this.add(menu);
		
		report = new ReportPanel(this);

		this.setVisible(true);
		repaint();
	}

	private void init() {
		currentMode = ProcessUtil.getCurrentWorkingMode();
		showDustValueProp = ProcessUtil.getShowDustValue();
	}

	public void changeStatusBar(int x, int y, int time) {
		statusBar.setMessage(x, y, time);
	}

	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();

		String fileMenuText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_FILE);
		fileMenu = new JMenu(fileMenuText);
		fileMenu.setMnemonic(KeyEvent.VK_F);

		menuBar.add(fileMenu);
		String newRobotText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_RESET_MAP);
		newRobot = new JMenuItem(newRobotText);
		newRobot.setIcon(new ImageIcon("resources/reset.png"));
		newRobot.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newRobot.addActionListener(this);
		fileMenu.add(newRobot);

		String exitText = LanguageUtil.getProperty(LanguageUtil.MAINGUI_EXIT);
		exit = new JMenuItem(exitText);
		exit.setIcon(new ImageIcon("resources/close.png"));
		exit.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		exit.addActionListener(this);
		fileMenu.add(exit);

		String editText = LanguageUtil.getProperty(LanguageUtil.MAINGUI_OPTION);
		optionMenu = new JMenu(editText);
		optionMenu.setMnemonic(KeyEvent.VK_E);

		language = new JMenu("Language");

		String lan = ProcessUtil.getLanguage();
		boolean isEnglish = true;
		boolean isVietNamese = false;
		if ("vi".equals(lan)) {
			isEnglish = false;
			isVietNamese = true;
		}
		vietnamese = new JCheckBoxMenuItem("Tiếng Việt",
				new ImageIcon("resources/vietnamese.png"));
		vietnamese.addActionListener(this);
		english = new JCheckBoxMenuItem("English",
				new ImageIcon("resources/english.png"));
		english.addActionListener(this);
		english.setEnabled(!isEnglish);
		vietnamese.setEnabled(!isVietNamese);
		language.add(vietnamese);
		language.addSeparator();
		language.add(english);
		optionMenu.add(language);

		optionMenu.addSeparator();
		String showDustValueTxt = LanguageUtil
				.getProperty(LanguageUtil.SHOW_DUST_VALUE);
		showDustValue = new JCheckBoxMenuItem(showDustValueTxt);
		showDustValue.addActionListener(this);
		if (showDustValueProp == 0) {
			showDustValue.setSelected(false);
		} else {
			showDustValue.setSelected(true);
		}
		optionMenu.add(showDustValue);

		menuBar.add(optionMenu);

		String helpText = LanguageUtil.getProperty(LanguageUtil.MAINGUI_HELP);
		helpMenu = new JMenu(helpText);
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpMenu);

		String aboutText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_ABOUT_US);
		aboutUs = new JMenuItem(aboutText);
		aboutUs.setIcon(new ImageIcon("resources/about.png"));
		aboutUs.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		aboutUs.addActionListener(this);
		helpMenu.add(aboutUs);

		String manualText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_MANUAL);
		guide = new JMenuItem(manualText);
		guide.setIcon(new ImageIcon("resources/manual.png"));
		guide.addActionListener(this);
		helpMenu.add(guide);

		return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			System.exit(0);
		}

		if (e.getSource() == newRobot) {
			if (robot != null) {
				robot.stop();
				this.remove(robot);
				start.removeMouseListener(this);
				restart.removeMouseListener(this);
				this.repaint();
			}
			this.remove(pane);
			robot = new Robot(areaSize, this);
			robot.setBounds(1, 0, 549, 550);
			robot.init();
			this.add(robot);

			start.setEnabled(true);
			String startText = LanguageUtil
					.getProperty(LanguageUtil.MAINGUI_START);
			start.setText(startText);
			restart.setEnabled(false);
			start.addMouseListener(this);

			initWorkArea.addMouseListener(this);
			initWorkArea.setEnabled(true);
			areaSizeInput.setEditable(true);

			statusBar.resetMessage();

			revalidate();
			this.repaint();
		}

		if (e.getSource() == vietnamese && vietnamese.isEnabled()) {
			vietnamese.setEnabled(false);
			english.setEnabled(true);
			ProcessUtil.setLanguage("vi");
			resetLanguage();
		}

		if (e.getSource() == english && english.isEnabled()) {
			english.setEnabled(false);
			vietnamese.setEnabled(true);
			ProcessUtil.setLanguage("en");
			resetLanguage();
		}

		if (e.getSource() == showDustValue) {
			if (showDustValue.isSelected()) {
				ProcessUtil.setSowDustVaue("" + 1);
				showDustValue.setSelected(true);
				robot.switchShowDustValueType(true);
			} else {
				ProcessUtil.setSowDustVaue("" + 0);
				showDustValue.setSelected(false);
				robot.switchShowDustValueType(false);
			}
		}

	}

	public void reset() {
		language.setEnabled(true);
		hedgePan.setComponentEnable(true);
		areaSizeInput.setText("");
		areaSizeInput.setEditable(true);
		initWorkArea.addMouseListener(this);
		initWorkArea.setEnabled(true);
		start.setEnabled(false);
		
		int time = statusBar.getworkingTime();
		int numOfSteps = robot.getNumOfSteps();
		int duplicateSteps = robot.getDuplicateSteps();
		
		int areaWorked =numOfSteps - 1 - duplicateSteps;
		
		double coverage = areaWorked*1.0/(areaSize * areaSize);
		
		DecimalFormat df = new DecimalFormat("##.##");
		String str = df.format(coverage);
		coverage = 100*Double.parseDouble(str);
		
		report.showReport(time, numOfSteps, duplicateSteps, coverage);

		statusBar.resetMessage();

		repaint();
	}

	private void initMap() {
		String inputValue = areaSizeInput.getText();
		String warningTitle = LanguageUtil
				.getProperty(LanguageUtil.TITLE_WARNING);
		if (inputValue == null || inputValue.equals("")) {
			String sizeText = LanguageUtil.getProperty(
					LanguageUtil.MAINGUI_ERROR_ENTER_SIZE_OF_WORK_FIRST);
			JOptionPane.showMessageDialog(this, sizeText, warningTitle,
					JOptionPane.WARNING_MESSAGE);
			start.removeMouseListener(this);
			start.setEnabled(false);
			restart.removeMouseListener(this);
			restart.setEnabled(false);
		} else {
			areaSize = Integer.parseInt(inputValue);

			if (areaSize < 2) {
				String sizeText = LanguageUtil.getProperty(
						LanguageUtil.MAINGUI_ERROR_MINIMUM_WORK_SIZE);
				JOptionPane.showMessageDialog(this, sizeText, warningTitle,
						JOptionPane.WARNING_MESSAGE);
				start.removeMouseListener(this);
				start.setEnabled(false);
				restart.removeMouseListener(this);
				restart.setEnabled(false);
			} else {
				if (robot != null) {
					this.remove(robot);
					start.removeMouseListener(this);
					restart.removeMouseListener(this);
					this.repaint();
				}
				this.remove(pane);
				robot = new Robot(areaSize, this);
				robot.setBounds(1, 0, 549, 550);
				robot.init();
				this.add(robot);

				String startText = LanguageUtil
						.getProperty(LanguageUtil.MAINGUI_START);
				start.setText(startText);
				restart.setEnabled(false);

				initDust.setEnabled(true);
				initDust.addMouseListener(this);

				revalidate();
				this.repaint();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == initWorkArea) {
			initMap();
		}

		if (e.getSource() == start) {
			hedgePan.setComponentEnable(false);
			language.setEnabled(false);
			initWorkArea.removeMouseListener(this);
			initWorkArea.setEnabled(false);
			initDust.removeMouseListener(this);
			initDust.setEnabled(false);
			areaSizeInput.setEditable(false);
			
			hedgeAndBarrierPan.enableComponents(false);

			if (robot.getWorkStatus() == 0) {
				String pauseText = LanguageUtil
						.getProperty(LanguageUtil.MAINGUI_PAUSE);
				start.setText(pauseText);
				robot.start(currentMode);

				restart.setEnabled(true);
				restart.addMouseListener(this);
			} else {
				String continueText = LanguageUtil
						.getProperty(LanguageUtil.MAINGUI_CONTINUE);
				start.setText(continueText);
				robot.stop();
			}
		}

		if (e.getSource() == restart) {
			initWorkArea.removeMouseListener(this);
			initWorkArea.setEnabled(false);
			initDust.removeMouseListener(this);
			initDust.setEnabled(false);
			areaSizeInput.setEditable(false);
			String pauseText = LanguageUtil
					.getProperty(LanguageUtil.MAINGUI_PAUSE);
			start.setText(pauseText);
			start.setEnabled(true);
			robot.restart(currentMode);
			
			hedgeAndBarrierPan.enableComponents(false);
		}

		if (e.getSource() == initDust && initDust.isEnabled()) {
			robot.initDust();
			start.setEnabled(true);
			start.addMouseListener(this);
			initWorkArea.removeMouseListener(this);
			initWorkArea.setEnabled(false);
			
			hedgeAndBarrierPan.enableComponents(true);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == start && start.isEnabled()) {
			start.setCursor(new Cursor(Cursor.HAND_CURSOR));
			start.setOpaque(true);
			start.setBackground(new Color(228, 237, 244));
		}
		if (e.getSource() == restart && restart.isEnabled()) {
			restart.setCursor(new Cursor(Cursor.HAND_CURSOR));
			restart.setOpaque(true);
			restart.setBackground(new Color(228, 237, 244));
		}
		if (e.getSource() == initWorkArea && initWorkArea.isEnabled()) {
			initWorkArea.setCursor(new Cursor(Cursor.HAND_CURSOR));
			initWorkArea.setOpaque(true);
			initWorkArea.setBackground(new Color(228, 237, 244));
		}
		if (e.getSource() == initDust && initDust.isEnabled()) {
			initDust.setCursor(new Cursor(Cursor.HAND_CURSOR));
			initDust.setOpaque(true);
			initDust.setBackground(new Color(228, 237, 244));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == start && start.isEnabled()) {
			start.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			start.setOpaque(true);
			start.setBackground(Color.WHITE);
		}
		if (e.getSource() == restart && restart.isEnabled()) {
			restart.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			restart.setOpaque(true);
			restart.setBackground(Color.WHITE);
		}

		if (e.getSource() == initWorkArea && initWorkArea.isEnabled()) {
			initWorkArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			initWorkArea.setOpaque(true);
			initWorkArea.setBackground(Color.WHITE);
		}
		if (e.getSource() == initDust && initDust.isEnabled()) {
			initDust.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			initDust.setOpaque(true);
			initDust.setBackground(Color.WHITE);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public void resetLanguage() {
		statusBar.resetLanguage();
		hedgePan.resetLanguage();
		standardModePan.resetLanguage();
		hedgeAndBarrierPan.resetLanguage();
		if (robot != null) {
			robot.resetLanguage();
		}

		String programTitle = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_PROGRAM_NAME);
		this.setTitle(programTitle);

		String startText = LanguageUtil.getProperty(LanguageUtil.MAINGUI_START);
		start.setText(startText);
		String restartText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_RESTART);
		restart.setText(restartText);
		String areaInputLblText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_ENTER_SIZE_OF_WORK_AREA);
		areaInputLabel.setText(areaInputLblText);
		String areaSizeInputText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_SIZE_OF_WORK_AREA);
		areaSizeInput.setPlaceholder(areaSizeInputText);
		String initWorkAreaText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_INIT_WORK_AREA);
		initWorkArea.setText(initWorkAreaText);
		String fileMenuText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_FILE);
		fileMenu.setText(fileMenuText);
		String newRobotText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_RESET_MAP);
		newRobot.setText(newRobotText);
		String exitText = LanguageUtil.getProperty(LanguageUtil.MAINGUI_EXIT);
		exit.setText(exitText);
		String editText = LanguageUtil.getProperty(LanguageUtil.MAINGUI_OPTION);
		optionMenu.setText(editText);
		String helpText = LanguageUtil.getProperty(LanguageUtil.MAINGUI_HELP);
		helpMenu.setText(helpText);
		String aboutText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_ABOUT_US);
		aboutUs.setText(aboutText);
		String manualText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_MANUAL);
		guide.setText(manualText);

		modeList.removeItemListener(this);
		menu.remove(modeList);
		String standardModeName = LanguageUtil
				.getProperty(LanguageUtil.SELECT_STANDARD_MODE);
		String hedgeModeName = LanguageUtil
				.getProperty(LanguageUtil.SELECT_DSGT_MODE);
		String hedandBarierModeName = LanguageUtil
				.getProperty(LanguageUtil.SELECT_DSGT_DYNAMIC_MODE);
		String[] modes = { standardModeName, hedgeModeName,
				hedandBarierModeName };
		modeList = new JComboBox<>(modes);
		modeList.setBounds(20, 60, 310, 25);
		modeList.addItemListener(this);
		modeList.setSelectedIndex(currentMode);
		menu.add(modeList);

		String modeLblText = LanguageUtil
				.getProperty(LanguageUtil.SELECT_MODE_TITLE);
		modeLbl.setText(modeLblText);

		String showDustValueTxt = LanguageUtil
				.getProperty(LanguageUtil.SHOW_DUST_VALUE);
		showDustValue.setText(showDustValueTxt);
		String initDustText = LanguageUtil
				.getProperty(LanguageUtil.MAINGUI_IINIT_DUST);
		initDust.setText(initDustText);

		revalidate();
		repaint();
	}

	public int getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(int currentMode) {
		this.currentMode = currentMode;
	}

	private void clearModePanel() {
		if (hedgePan != null) {
			menu.remove(hedgePan);
		}
		if (hedgeAndBarrierPan != null) {
			menu.remove(hedgeAndBarrierPan);
		}
		if (standardModePan != null) {
			menu.remove(standardModePan);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		clearModePanel();
		int index = modeList.getSelectedIndex();
		switch (index) {
		case STANDARD_MODE:
			currentMode = STANDARD_MODE;
			menu.add(standardModePan);
			break;
		case DSGT_MODE:
			currentMode = DSGT_MODE;
			menu.add(hedgePan);
			break;
		case DSGT_DINAYMIC_MODE:
			currentMode = DSGT_DINAYMIC_MODE;
			menu.add(hedgeAndBarrierPan);
			break;

		default:
			break;
		}
		ProcessUtil.setCurrentWorkingMode("" + currentMode);

		revalidate();
		repaint();
	}

	public int getStandardWorkingTime() {
		return standardModePan.getStandardWorkingTime();
	}

	public boolean addDynamicBarrier(int x, int y, int speed, int direction){
		if(x == 0 && y == 0){
			return false;
		}
		
		return !robot.isBarrier(x, y, speed, direction);
	}
}
