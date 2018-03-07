package com.demo.facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.demo.exceptionhandlers.ErrorCode;
import com.demo.exceptionhandlers.InputException;
import com.demo.facade.BattleArea;
import com.demo.pojo.BattleShips;
import com.demo.pojo.Coordinate;

@Component
public class StartGame {

	private static final Logger logger = LoggerFactory.getLogger(StartGame.class);

	public String init(File inputFile) throws FileNotFoundException, InputException {
		// TODO Auto-generated method stub
		ArrayList<BattleShips> p1s = new ArrayList<BattleShips>();
		ArrayList<BattleShips> p2s = new ArrayList<BattleShips>();
		int areaWidth = 0;
		int areahight = 0;
		ArrayList<Coordinate> player1missiles = null;
		ArrayList<Coordinate> player2missiles = null;
		try{
			Scanner sc = new Scanner(inputFile);
			
			areaWidth = sc.nextInt();
			if(areaWidth>9 || areaWidth<1){
				raiseException("Supplied area width is invalid.",sc);
			}
			areahight = sc.next().toUpperCase().charAt(0) - 64;
			if(areahight>25 || areahight<0){
				raiseException("Supplied area height is invalid.",sc);
			}
			sc.nextLine();
			int noOfships = sc.nextInt();
			if(noOfships>areahight*areaWidth || noOfships<1){
				raiseException("Supplied no of ships is invalid.",sc);
			}
			sc.nextLine();
			for (int j = 0; j < noOfships; j++) {
				char typeOfShip = sc.next().toUpperCase().charAt(0);
				if(typeOfShip!='P' && typeOfShip!='Q'){
					raiseException("Supplied type of ship is invalid.",sc);
				}
				int shipWidth = sc.nextInt();
				if(shipWidth>areaWidth || shipWidth<0){
					raiseException("Supplied ship width is invalid.",sc);
				}
				int shiphight = sc.nextInt();
				if(shiphight>areahight || shiphight<0){
					raiseException("Supplied ship height is invalid.",sc);
				}
				BattleShips ship;
				for (int i = 0; i <= 1; i++) {
					char[] locCharArr = sc.next().toUpperCase().toCharArray();
					int[] loc = new int[2];
					loc[0] = locCharArr[0] - 65;
					loc[1] = locCharArr[1] - 49;
					if(loc[0]>areahight || loc[0]<0 || loc[1]>areaWidth || loc[1]<0){
						raiseException("Supplied ship location is invalid.",sc);
					}
					ship = new BattleShips(shipWidth, shiphight, typeOfShip, loc);
					if (i % 2 == 0)
						p1s.add(ship);
					else
						p2s.add(ship);
				}
				sc.nextLine();
			}
	
			player1missiles = returnMissileCoordinates(sc.nextLine());
			player2missiles = returnMissileCoordinates(sc.nextLine());
			sc.close();
		}catch(InputMismatchException e){
			throw new InputException("Invalid Input supplied.",ErrorCode.INVALIDINPUT);
		}
		BattleArea player1 = new BattleArea("player1", areaWidth, areahight, p1s);
		BattleArea player2 = new BattleArea("player2", areaWidth, areahight, p2s);

		player1.placeShips();
		player2.placeShips();

		while (!player1.isLost() && !player2.isLost()) {
			for (int i = 0; i < player1missiles.size();) {
				Coordinate c = player1missiles.get(i);
				while (player1.fireMissile(c, player2)) {
					player1missiles.remove(i);
					if (i < player1missiles.size()) {
						c = player1missiles.get(i);
					} else
						break;
				}
				if (player1missiles.size() > 0) {
					player1missiles.remove(i);
				}
				break;
			}
			for (int j = 0; j < player2missiles.size();) {
				Coordinate c = player2missiles.get(j);
				while (player2.fireMissile(c, player1)) {
					player2missiles.remove(j);
					if (j < player2missiles.size()) {
						c = player2missiles.get(j);
					} else
						break;
				}
				if (player2missiles.size() > 0) {
					player2missiles.remove(j);
				}
				break;
			}
		}

		if (player1.isLost()) {
			logger.info("-------------------------");
			logger.info("Player 2 has Won the Game");
			logger.info("-------------------------");
			return "Player 2 has Won the Game";
		} else {
			logger.info("-------------------------");
			logger.info("Player 1 has Won the Game");
			logger.info("-------------------------");
			return "Player 1 has Won the Game";
		}
	}

	private static ArrayList<Coordinate> returnMissileCoordinates(String nextLine) {
		// TODO Auto-generated method stub
		ArrayList<Coordinate> tmp = new ArrayList<Coordinate>();
		String[] arr = nextLine.split("\\ ");
		Coordinate tmpC;
		for (String s : arr) {
			char[] charArr = s.toCharArray();
			tmpC = new Coordinate(charArr[1] - 49, charArr[0] - 65);
			tmp.add(tmpC);
		}
		return tmp;
	}

	private void raiseException(String message, Scanner sc) throws InputException {
		sc.close();
		throw new InputException(message, ErrorCode.INVALIDINPUT);
	}
}
