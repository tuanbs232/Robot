package org.dragon.robot.object;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.dragon.robot.test.Robot;

public class DynamicBarrier implements ActionListener {
	private Timer timer;

	private Robot robot;
	private int x, y, direction;
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	private static final int NGANG = 0;
	private static final int DOC = 1;

	private int step = 1;

	public DynamicBarrier(int x, int y, int speed, int direction, Robot robot) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.robot = robot;

		timer = new Timer(speed, this);
		this.timer.setInitialDelay(speed);
		this.timer.setDelay(speed);
		this.timer.start();
	}

	
	//TODO ton tai 1 loi khi them vat can di dong ngay sat vat can co dinh
	//thi vat can co dinh bi mat
	@Override
	public void actionPerformed(ActionEvent arg0) {
		robot.changeToDynamicBarrier(x, y);
		if (NGANG == direction) {
			if (x >= 0 && x < robot.getAreaSize() ) {
				robot.changeToNormal(x - step, y);
			}
			if (x <= 0 || x >= robot.getAreaSize() - 1 || !robot.isFree(x + step, y) ) {
				step = -step;
			} 
			x = x + step;
		}
		if (DOC == direction) {
			if (y >= 0 && y < robot.getAreaSize()) {
				robot.changeToNormal(x, y - step);
			}
			if (y <= 0 || y >= robot.getAreaSize() - 1 || !robot.isFree(x, y + step)) {
				step = -step;
			} 
			y = y + step;
		}
	}
}
