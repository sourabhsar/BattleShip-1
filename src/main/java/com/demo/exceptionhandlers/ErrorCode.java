package com.demo.exceptionhandlers;

public enum ErrorCode {

	INVALIDINPUT   (011, "Invalid input passed."),
	OUTOFBATTLEAREA(012, "Co-ordinates are out of Battle Area Size."),
	ALREADYOCCUPIED(013, "Co-ordinates are already occupied.");

	private final int id;
	private final String description;

	private ErrorCode(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return id + ": " + description;
	}
}
