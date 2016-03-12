//package org.dragon.robot.test;
//
//import java.awt.Color;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Point;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.JPanel;
//import javax.swing.Timer;
//import javax.swing.border.Border;
//import javax.swing.border.MatteBorder;
//
//import org.dragon.robot.gui.CellPane;
//import org.dragon.robot.object.MegaCell;
//
//public class Robot2 extends JPanel implements ActionListener {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	private int size;
//	private Timer timer;
//	private int DELAY = 1000;
//
//	private List<CellPane> listCells;
//	private List<Boolean> listCellStatus;
//
//	private MegaCell current;
//
//	public Robot2(int size, List<Point> list) {
//		this.size = size;
//		listCellStatus = new ArrayList<>();
//		for (int i = 0; i < size * size; i++) {
//			listCellStatus.add(true);
//		}
//
//		current = new MegaCell();
//		current.setParent(null);
//		current.setStatus(true);
//		current.setX(0);
//		current.setY(0);
//
//		setLayout(new GridBagLayout());
//
//		GridBagConstraints gbc = new GridBagConstraints();
//		listCells = new ArrayList<>();
//		for (int row = 0; row < size; row++) {
//			for (int col = 0; col < size; col++) {
//				gbc.gridx = col;
//				gbc.gridy = row;
//
//				CellPane cellPane = new CellPane(0);
//				listCells.add(cellPane);
//				Border border = null;
//				if (row < 4) {
//					if (col < 4) {
//						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
//					} else {
//						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
//					}
//				} else {
//					if (col < 4) {
//						border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
//					} else {
//						border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
//					}
//				}
//				cellPane.setBorder(border);
//				add(cellPane, gbc);
//			}
//		}
//
//		initCellStatus(list);
//	}
//
//	public void changeCellStatus(Point point) {
//		int x = point.x;
//		int y = point.y;
//		listCellStatus.set(x + y * size, false);
//	}
//
//	private void initCellStatus(List<Point> list) {
//		for (Point point : list) {
//			int x = point.x;
//			int y = point.y;
//			listCellStatus.set(x + y * size, false);
//			listCells.get(x + y * size).setStatus(-1);
//			listCells.get(x + y * size).resetBackGround(-1);
//		}
//
//		timer = new Timer(DELAY, this);
//		timer.start();
//	}
//
//	public void stc() {
//		current.setStatus(false);
//		setOldMegacell(current);
//
//		MegaCell neightboor = findFreeeNeighboor(current);
//		if (neightboor != null) {
//			current = neightboor;
//			double r = Math.random()*10;
//			DELAY = (int) Math.round(r);
//			DELAY = DELAY*1000;
//		} else{
//			timer.stop();
//		}
//	}
//
//	private void setOldMegacell(MegaCell mega) {
//		listCellStatus.set(mega.getX() + mega.getY() * size, false);
//	}
//
//	private MegaCell findFreeeNeighboor(MegaCell current) {
//		// up
//		MegaCell up = new MegaCell();
//		up.setParent(current);
//		up.setX(((current.getX()) / 2) * 2);
//		up.setY(((current.getY() - 2) / 2) * 2);
//		if ((up.getY() >= 0) && isFree(up)) {
//			up.setX(up.getX() + 1);
//			up.setY(up.getY() + 1);
//			up.setStatus(true);
//			moveUP(current);
//
//			return up;
//		}
//		// left
//		MegaCell left = new MegaCell();
//		left.setParent(current);
//		left.setX(((current.getX() - 2) / 2) * 2);
//		left.setY(((current.getY()) / 2) * 2);
//		if ((left.getX() >= 0) && isFree(left)) {
//			left.setX(left.getX() + 1);
//			left.setStatus(true);
//
//			moveLEFT(current);
//
//			return left;
//		}
//		// down
//		MegaCell down = new MegaCell();
//		down.setParent(current);
//		down.setX(((current.getX()) / 2) * 2);
//		down.setY(((current.getY() + 2) / 2) * 2);
//		if ((down.getY() < size) && isFree(down)) {
//			down.setStatus(true);
//			moveDOWN(current);
//
//			return down;
//		}
//		// right
//		MegaCell right = new MegaCell();
//		right.setParent(current);
//		right.setX(((current.getX() + 2) / 2) * 2);
//		right.setY(((current.getY()) / 2) * 2);
//		if ((right.getX() < size) && isFree(right)) {
//			right.setY(right.getY() + 1);
//			right.setStatus(true);
//			moveRIGHT(current);
//
//			return right;
//		}
//
//		if (current.getParent() != null) {
//
//			if (current.getParent().isEqual(up)) {
//				current.getParent().setX(up.getX() + 1);
//				current.getParent().setY(up.getY() + 1);
//				moveUP(current);
//			} else if (current.getParent().isEqual(left)) {
//				current.getParent().setX(left.getX() + 1);
//				current.getParent().setY(left.getY());
//				moveLEFT(current);
//			} else if (current.getParent().isEqual(down)) {
//				current.getParent().setX(down.getX());
//				current.getParent().setY(down.getY());
//				moveDOWN(current);
//			} else if (current.getParent().isEqual(right)) {
//				current.getParent().setX(right.getX());
//				current.getParent().setY(right.getY() + 1);
//				moveRIGHT(current);
//			}
//		}
//
//		return current.getParent();
//
//	}
//
//	private void moveUP(MegaCell mega) {
//		int x = mega.getX();
//		int y = mega.getY();
//		System.out.print("[" + x + ", " + y + "], ");
//		listCells.get(x + y * size).changeStatus();
//		if (x < ((x / 2) * 2 + 1) && y < ((y / 2) * 2 + 1)) {
//			y++;
//			System.out.print("[" + x + ", " + y + "], ");
//			listCells.get(x + y * size).changeStatus();
//		}
//
//		if (x < ((x / 2) * 2 + 1)) {
//			x++;
//			System.out.print("[" + x + ", " + y + "], ");
//			listCells.get(x + y * size).changeStatus();
//		}
//
//		if (y > (y / 2) * 2) {
//			y--;
//			System.out.print("[" + x + ", " + y + "]");
//			listCells.get(x + y * size).changeStatus();
//		}
//		System.out.println();
//	}
//
//	private void moveDOWN(MegaCell mega) {
//		int x = mega.getX();
//		int y = mega.getY();
//		System.out.print("[" + x + ", " + y + "], ");
//		listCells.get(x + y * size).changeStatus();
//
//		if (x > ((x / 2) * 2) && y > (y / 2) * 2) {
//			y--;
//			System.out.print("[" + x + ", " + y + "]");
//			listCells.get(x + y * size).changeStatus();
//		}
//
//		if (x > ((x / 2) * 2)) {
//			x--;
//			System.out.print("[" + x + ", " + y + "], ");
//			listCells.get(x + y * size).changeStatus();
//		}
//		if (y < ((y / 2) * 2 + 1)) {
//			y++;
//			System.out.print("[" + x + ", " + y + "]");
//			listCells.get(x + y * size).changeStatus();
//		}
//		System.out.println();
//	}
//
//	private void moveLEFT(MegaCell mega) {
//		int x = mega.getX();
//		int y = mega.getY();
//		System.out.print("[" + x + ", " + y + "], ");
//		listCells.get(x + y * size).changeStatus();
//
//		if (y > ((y / 2) * 2) && x < ((x / 2) * 2 + 1)) {
//			x++;
//			System.out.print("[" + x + ", " + y + "], ");
//			listCells.get(x + y * size).changeStatus();
//		}
//		if (y > ((y / 2) * 2)) {
//			y--;
//			System.out.print("[" + x + ", " + y + "], ");
//			listCells.get(x + y * size).changeStatus();
//		}
//
//		if (x > ((x / 2) * 2)) {
//			x--;
//			System.out.print("[" + x + ", " + y + "]");
//			listCells.get(x + y * size).changeStatus();
//		}
//		System.out.println();
//	}
//
//	private void moveRIGHT(MegaCell mega) {
//		int x = mega.getX();
//		int y = mega.getY();
//		System.out.print("[" + x + ", " + y + "], ");
//		listCells.get(x + y * size).changeStatus();
//
//		if (y < ((y / 2) * 2 + 1) && x > ((x / 2) * 2)) {
//			x--;
//			System.out.print("[" + x + ", " + y + "], ");
//			listCells.get(x + y * size).changeStatus();
//		}
//		if (y < ((y / 2) * 2 + 1)) {
//			y++;
//			System.out.print("[" + x + ", " + y + "], ");
//			listCells.get(x + y * size).changeStatus();
//		}
//
//		if (x < ((x / 2) * 2) + 1) {
//			x++;
//			System.out.print("[" + x + ", " + y + "]");
//			listCells.get(x + y * size).changeStatus();
//		}
//		System.out.println();
//	}
//
//	private boolean isFree(MegaCell mega) {
//		int x = (mega.getX() / 2) * 2;
//		int y = (mega.getY() / 2) * 2;
//
//		int index0 = x + y * size;
//		int index1 = x + 1 + y * size;
//		int index2 = x + (y + 1) * size;
//		int index3 = x + 1 + (y + 1) * size;
//
//		return listCellStatus.get(index0) && listCellStatus.get(index1) && listCellStatus.get(index2)
//				&& listCellStatus.get(index3);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//
//		MegaCell start = new MegaCell();
//		start.setParent(null);
//		start.setStatus(true);
//		start.setX(0);
//		start.setY(0);
//		this.timer.stop();
//		this.timer.setInitialDelay(DELAY);
//		this.timer.restart();
//		this.stc();
//		System.out.println(this.timer.getInitialDelay());
//	}
//
//}
