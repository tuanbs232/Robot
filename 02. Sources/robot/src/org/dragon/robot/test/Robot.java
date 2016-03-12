package org.dragon.robot.test;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import org.dragon.robot.common.HedgeAlgebra;
import org.dragon.robot.common.ProcessUtil;
import org.dragon.robot.gui.CellPane;
import org.dragon.robot.gui.MainGui;
import org.dragon.robot.object.DynamicBarrier;
import org.dragon.robot.object.MegaCell;

public class Robot extends JPanel implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size;
	private Timer timer;
	private int DELAY = 1000;

	private List<CellPane> listCells;
	private List<Boolean> listCellStatus;
	private List<Integer> listWorkingTime;

	private MegaCell current;

	private int x = -1, y = -1, xPrev = -1, yPrev = -1;

	private int moveFlag = 0;

	private int moveTrend;

	private int workFirst = 0;
	
	List<DynamicBarrier> listDynamicBarriers;

	// Dieu khien robot lam viec hay tam dung boi nguoi dung
	private int workStatus = 0;

	// Luu so buoc di chuyen cua robot
	private int numOfSteps = 0;
	private int duplicateSteps = 0;

	public void increaseDuplicateSteps(int value) {
		duplicateSteps += value;
	}

	public int getDuplicateSteps() {
		return this.duplicateSteps;
	}

	// Lua chon che do lam viec cua robot
	private int workingMode;

	private MainGui container;

	public Robot(int size) {
		this.size = size;
		listCellStatus = new ArrayList<>();
		for (int i = 0; i < size * size; i++) {
			listCellStatus.add(true);
		}
	}

	public Robot(int size, MainGui f) {
		this.container = f;
		this.size = size;
		listCellStatus = new ArrayList<>();
		for (int i = 0; i < size * size; i++) {
			listCellStatus.add(true);
		}
	}

	public void start(int workingMode) {
		switch (workingMode) {
		case MainGui.STANDARD_MODE:
			DELAY = container.getStandardWorkingTime();
			break;
		case MainGui.DSGT_MODE:
			DELAY = 0;
			break;
		case MainGui.DSGT_DINAYMIC_MODE:
			DELAY = 0;
			break;
		default:
			break;
		}
		this.workStatus = 1;
		this.workingMode = workingMode;
		for (int i = 0; i < listCells.size(); i++) {
			listCells.get(i).removeMouseListener(this);
		}
	}

	public void stop() {
		this.workStatus = 0;
	}

	public void restart(int workingMode) {
		timer.stop();
		this.workStatus = 0;
		moveFlag = 0;
		isFinalStep = false;
		workFirst = 0;
		numOfSteps = 0;
		duplicateSteps = 0;
		for (int i = 0; i < listCells.size(); i++) {
			CellPane cell = listCells.get(i);
			if (cell.getStatus() != -1) {
				cell.changeToNormal();
				listCellStatus.set(i, true);
			}
		}

		this.workingMode = workingMode;

		current = new MegaCell();
		current.setParent(null);
		current.setStatus(true);
		current.setX(0);
		current.setY(0);
		x = current.getX();
		y = current.getY();

		this.workStatus = 1;
		timer.start();
	}

	public void init() {
		workStatus = 0;
		current = new MegaCell();
		current.setParent(null);
		current.setStatus(true);
		current.setX(0);
		current.setY(0);
		moveFlag = 0;

		int robotSize = this.getWidth() / size;

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		listCells = new ArrayList<>();
		listWorkingTime = new ArrayList<>();

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				gbc.gridx = col;
				gbc.gridy = row;

				CellPane cellPane = new CellPane(0, robotSize, this);
				cellPane.addMouseListener(this);
				listCells.add(cellPane);
				Border border = null;
				border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
				cellPane.setBorder(border);
				add(cellPane, gbc);

				double r = Math.random() * 10;
				int workingTimeInit = (int) (Math.round(r) * 100);
				listWorkingTime.add(workingTimeInit);
			}
		}
		listCells.get(0).changeToRobot(4);
		
		listDynamicBarriers = new ArrayList<>();

		timer = new Timer(DELAY, this);
		timer.start();
	}

	public void changeCellStatus(Point point) {
		int x = point.x;
		int y = point.y;
		listCellStatus.set(x + y * size, false);
	}

	private boolean isFinalStep = false;

	public void stc() {
		current.setStatus(false);
		setOldMegacell(current);

		if (moveFlag == 0) {
			MegaCell neightboor = findFreeeNeighboor(current);
			if (neightboor != null) {
				x = current.getX();
				y = current.getY();
				current = neightboor;
			} else {
				if (!isFinalStep) {
					x = current.getX();
					y = current.getY();
					isFinalStep = true;
				} else {
					finalStep();
				}
			}
		} else {
			robotWork(x, y);
		}
	}

	private void setOldMegacell(MegaCell mega) {
		listCellStatus.set(mega.getX() + mega.getY() * size, false);
	}

	private MegaCell findFreeeNeighboor(MegaCell current) {
		// up
		MegaCell up = new MegaCell();
		up.setParent(current);
		up.setX(((current.getX()) / 2) * 2);
		up.setY(((current.getY() - 2) / 2) * 2);
		if ((up.getY() >= 0) && isFree(up)) {
			up.setX(up.getX() + 1);
			up.setY(up.getY() + 1);
			up.setStatus(true);
			moveTrend = 1;
			moveFlag = 1;

			return up;
		}
		// left
		MegaCell left = new MegaCell();
		left.setParent(current);
		left.setX(((current.getX() - 2) / 2) * 2);
		left.setY(((current.getY()) / 2) * 2);
		if ((left.getX() >= 0) && isFree(left)) {
			left.setX(left.getX() + 1);
			left.setStatus(true);
			moveTrend = 2;
			moveFlag = 1;

			return left;
		}
		// down
		MegaCell down = new MegaCell();
		down.setParent(current);
		down.setX(((current.getX()) / 2) * 2);
		down.setY(((current.getY() + 2) / 2) * 2);
		if ((down.getY() < (size - 1)) && isFree(down)) {
			down.setStatus(true);
			moveTrend = 3;
			moveFlag = 1;

			return down;
		}
		// right
		MegaCell right = new MegaCell();
		right.setParent(current);
		right.setX(((current.getX() + 2) / 2) * 2);
		right.setY(((current.getY()) / 2) * 2);
		if ((right.getX() < (size - 1)) && isFree(right)) {
			right.setY(right.getY() + 1);
			right.setStatus(true);
			moveTrend = 4;
			moveFlag = 1;

			return right;
		}

		if (current.getParent() != null) {

			if (current.getParent().isEqual(up)) {
				current.getParent().setX(up.getX() + 1);
				current.getParent().setY(up.getY() + 1);
				moveTrend = 1;
			} else if (current.getParent().isEqual(left)) {
				current.getParent().setX(left.getX() + 1);
				current.getParent().setY(left.getY());
				moveTrend = 2;
			} else if (current.getParent().isEqual(down)) {
				current.getParent().setX(down.getX());
				current.getParent().setY(down.getY());
				moveTrend = 3;
			} else if (current.getParent().isEqual(right)) {
				current.getParent().setX(right.getX());
				current.getParent().setY(right.getY() + 1);
				moveTrend = 4;
			}
			moveFlag = 1;
		}

		return current.getParent();

	}

	private void moveUP() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;

			return;
		} else {
			xPrev = x;
			yPrev = y;
		}

		if (x < ((x / 2) * 2 + 1) && y < ((y / 2) * 2 + 1)) {
			y++;
			workingAndChangeStatus(x, y);

			return;
		}

		if (x < ((x / 2) * 2 + 1)) {
			x++;
			workingAndChangeStatus(x, y);

			return;
		}

		if (y > (y / 2) * 2) {
			y--;
			workingAndChangeStatus(x, y);

			return;
		} else {
			workFirst = 0;
			moveFlag = 0;
		}
	}

	private void moveDOWN() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;

			return;
		} else {
			xPrev = x;
			yPrev = y;
		}

		if (x > ((x / 2) * 2) && y > (y / 2) * 2) {
			y--;
			workingAndChangeStatus(x, y);

			return;
		}

		if (x > ((x / 2) * 2)) {
			x--;
			workingAndChangeStatus(x, y);

			return;
		}
		if (y < ((y / 2) * 2 + 1)) {
			y++;
			workingAndChangeStatus(x, y);

			return;
		} else {
			workFirst = 0;

			moveFlag = 0;
		}
	}

	private void moveLEFT() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;

			return;
		} else {
			xPrev = x;
			yPrev = y;
		}

		if (y > ((y / 2) * 2) && x < ((x / 2) * 2 + 1)) {
			x++;
			workingAndChangeStatus(x, y);

			return;
		}
		if (y > ((y / 2) * 2)) {
			y--;
			workingAndChangeStatus(x, y);

			return;
		}

		if (x > ((x / 2) * 2)) {
			x--;
			workingAndChangeStatus(x, y);

			return;
		} else {
			workFirst = 0;

			moveFlag = 0;
		}
	}

	private void moveRIGHT() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;

			return;
		} else {
			xPrev = x;
			yPrev = y;
		}

		if (y < ((y / 2) * 2 + 1) && x > ((x / 2) * 2)) {
			x--;
			workingAndChangeStatus(x, y);

			return;
		}
		if (y < ((y / 2) * 2 + 1)) {
			y++;
			workingAndChangeStatus(x, y);

			return;
		}

		if (x < ((x / 2) * 2) + 1) {
			x++;
			workingAndChangeStatus(x, y);

			return;
		} else {
			workFirst = 0;

			moveFlag = 0;
		}
	}

	private int getWorkTime(int x, int y) {
		switch (workingMode) {
		case MainGui.STANDARD_MODE:
			DELAY = container.getStandardWorkingTime();
			break;
		case MainGui.DSGT_MODE:
			DELAY = listCells.get(x + y * size).getTimeProcess();
			break;
		case MainGui.DSGT_DINAYMIC_MODE:
			DELAY = listCells.get(x + y * size).getTimeProcess();
			break;

		default:
			break;
		}
		listWorkingTime.set(x + y * size, 0);

		return DELAY;
	}

	private void finalStep() {
		if (x == 0 && y == 0) {
			timer.stop();
			container.reset();
		} else {
			moveLEFT();
		}
	}

	private void robotWork(int x, int y) {
		if(isRobotHere(x, y)){
			return;
		};		

		if (moveTrend == 1) {
			moveUP();

			return;
		}

		if (moveTrend == 2) {
			moveLEFT();

			return;
		}

		if (moveTrend == 3) {
			moveDOWN();

			return;
		}

		if (moveTrend == 4) {
			moveRIGHT();

			return;
		}
	}

	private void workingAndChangeStatus(int x, int y) {
		int workingTime = getWorkTime(x, y);
		
		numOfSteps += 1;
		if (x > xPrev) {
			listCells.get(x + y * size).changeToRobot(4);
			if ((xPrev + yPrev * size) >= 0) {
				listCells.get(xPrev + yPrev * size).changeStatus(4);
			}
		} else if (x < xPrev) {
			listCells.get(x + y * size).changeToRobot(3);
			if ((xPrev + yPrev * size) >= 0) {
				listCells.get(xPrev + yPrev * size).changeStatus(3);
			}
		} else if (y > yPrev) {
			listCells.get(x + y * size).changeToRobot(2);
			if ((xPrev + yPrev * size) >= 0) {
				listCells.get(xPrev + yPrev * size).changeStatus(2);
			}
		} else if (y < yPrev) {
			listCells.get(x + y * size).changeToRobot(1);
			if ((xPrev + yPrev * size) >= 0) {
				listCells.get(xPrev + yPrev * size).changeStatus(1);
			}
		}

		container.changeStatusBar(x, y, workingTime);
	}

	public int getNumOfSteps() {
		return numOfSteps;
	}

	private boolean isFree(MegaCell mega) {
		int x = (mega.getX() / 2) * 2;
		int y = (mega.getY() / 2) * 2;

		int index0 = x + y * size;
		int index1 = x + 1 + y * size;
		int index2 = x + (y + 1) * size;
		int index3 = x + 1 + (y + 1) * size;
		
		return listCellStatus.get(index0) && listCellStatus.get(index1)
				&& listCellStatus.get(index2) && listCellStatus.get(index3);
	}

	public void initDust() {
		for (CellPane cell : listCells) {
			cell.setDustValueText(randomDust());
			cell.removeMouseListener(this);
		}
	}

	private String randomDust() {
		String result = "0";
		long ran = (long) (Math.random() * 100);

		if (ran > 70) {
			int index = (int) (7 * Math.random());
			if ("vi".equals(ProcessUtil.getLanguage())) {
				result = HedgeAlgebra.langValuesVi[index];
			} else {
				result = HedgeAlgebra.langValueEn[index];
			}
		} else {
			result = "" + (int) (100 * Math.random());
		}

		return result;
	}

	public void switchShowDustValueType(boolean value) {
		for (CellPane cell : listCells) {
			cell.switchShowDustValueType(value);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getWorkStatus() == 1) {
			this.timer.stop();
			this.timer.setInitialDelay(DELAY);
			this.timer.setDelay(DELAY);
			this.timer.restart();
			this.stc();
		}
	}

	public void resetLanguage() {
		if (listCells != null && listCells.size() > 0) {
			for (CellPane cell : listCells) {
				cell.resetLanguage();
			}
		}
	}

	public int getWorkStatus() {
		return workStatus;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < listCells.size(); i++) {
			CellPane cell = listCells.get(i);
			if (e.getSource() == cell) {
				if (cell.getStatus() == 0) {
					cell.changeToBarrier();
					listCellStatus.set(i, false);
				} else {
					cell.changeToNormal();
					listCellStatus.set(i, true);
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	public boolean isBarrier(int x, int y, int speed, int direction) {
		boolean result = (listCells.get(x + y * size).getStatus() == -1);
		if(!result){
			listDynamicBarriers.add(new DynamicBarrier(x, y, speed, direction, this));
		}
		return result;
	}
	
	public boolean isRobotHere(int x, int y){
		for(DynamicBarrier barrier : listDynamicBarriers){
			int x0 = (x / 2) * 2;
			int y0 = (y / 2) * 2;
			if(barrier.getX() - x0 <= 1 && barrier.getY() - y0 <= 1){
				return true;
			}                
		}
		return false;
	}

	public void changeToDynamicBarrier(int x, int y) {
		listCells.get(x + y * size).changeToBarrier();
	}

	public void changeToNormal(int x, int y) {
		listCells.get(x + y * size).changeToOldStatus();
	}
	
	public boolean isFree(int x, int y){
		int cellStatus = listCells.get(x + y*size).getStatus();
		return (cellStatus == 0 || cellStatus > 1);
	}

	public int getAreaSize() {
		return size;
	}
}
