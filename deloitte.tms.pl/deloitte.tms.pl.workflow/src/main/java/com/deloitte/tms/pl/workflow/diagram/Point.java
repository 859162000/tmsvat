package com.deloitte.tms.pl.workflow.diagram;

/**
 * @author Jacky.gao
 * @since 2013年9月8日
 */
public class Point implements Cloneable{
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
