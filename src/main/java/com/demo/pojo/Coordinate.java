package com.demo.pojo;

public class Coordinate implements Comparable<Coordinate> {

	private int x,y;

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int compareTo(Coordinate o) {
		// TODO Auto-generated method stub
		if(this.x==o.x && this.y==o.y)
			return 0;
		else if(this.x<o.x && this.y<o.y)
			return -1;
		else
			return 1;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
			
}
