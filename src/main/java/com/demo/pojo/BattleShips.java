package com.demo.pojo;

public class BattleShips {
	
	private int width,height;
	private char typeOfShip;
	private int[] location;
	
	public BattleShips(int width, int height, char typeOfShip, int[] loc) {
		super();
		this.width = width;
		this.height = height;
		this.typeOfShip = typeOfShip;
		this.location = loc;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public char getTypeOfShip() {
		return typeOfShip;
	}

	public int[] getLocation() {
		return location;
	}

}
