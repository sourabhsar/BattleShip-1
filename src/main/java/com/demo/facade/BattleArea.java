package com.demo.facade;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.exceptionhandlers.ErrorCode;
import com.demo.exceptionhandlers.ProhibitedException;
import com.demo.pojo.BattleShips;
import com.demo.pojo.Coordinate;

public class BattleArea {
	
	private static final Logger logger = LoggerFactory.getLogger(BattleArea.class);
	
	private String belongsTo;
	private int width,height;
	private ArrayList<BattleShips> battleShips;
	private Set<Coordinate> occupied=new TreeSet<Coordinate>();
	private int[][] board=null;
	private boolean lost=false;
	
	public BattleArea(String belongsTo, int width, int height, ArrayList<BattleShips> battleShips) {
		super();
		this.belongsTo = belongsTo;
		this.width = width;
		this.height = height;
		this.battleShips = battleShips;
		this.board=new int[this.width][this.height];
	}
	
	public void placeShips(){
		for(BattleShips ship:this.battleShips){
			int x=ship.getLocation()[1];
			int y=ship.getLocation()[0];
			if(ship.getWidth()+x>this.width || ship.getHeight()+y>this.height){
				logger.error("Coordinate x-"+x+" y-"+y+" for "+this.belongsTo+" is not avilable.");
				throw new ProhibitedException("Ship cannot be placed in this location.",ErrorCode.OUTOFBATTLEAREA);
			}else{
				Coordinate c=new Coordinate(x, y);
				if(occupied.contains(c)){
					logger.error("Coordinate x-"+c.getX()+" y-"+c.getY()+" for "+this.belongsTo+" is already occupied.");
					throw new ProhibitedException("Ship cann't be placed in this location.",ErrorCode.ALREADYOCCUPIED);
				}else{
					Coordinate tempC;
					for(int i=x;i<ship.getWidth()+x;i++){
						for(int j=y;j<ship.getHeight()+y;j++){
							logger.debug("Placing at x-"+i+" y-"+j+" for "+this.belongsTo);
							tempC=new Coordinate(i, j);
							occupied.add(tempC);
							if(ship.getTypeOfShip()=='P'){
								board[i][j]=1; 
							}else if(ship.getTypeOfShip()=='Q'){
								board[i][j]=2;
							}
						}
					}
				}
			}
		}
	}
	
	public boolean fireMissile(Coordinate c, BattleArea enemyBattleArea){
		int x=c.getX();
		int y=c.getY();
		logger.info("Firing at "+enemyBattleArea.belongsTo+" x-"+x+" y-"+y+" :");
		if(enemyBattleArea.board[x][y]!=0){
			if(enemyBattleArea.board[x][y]==-1){ 
				logger.debug("Already blasted!");
				return false;
			}
			else if(enemyBattleArea.board[x][y]==1){
				Coordinate temp=new Coordinate(x,y);
				enemyBattleArea.occupied.remove(temp);
				enemyBattleArea.board[x][y]=-1;
				if(enemyBattleArea.occupied.size()==0){
					enemyBattleArea.setLost(true);
				}
				logger.debug("Suucessfully blasted!!");
				return true;
			}else{
				enemyBattleArea.board[x][y]=enemyBattleArea.board[x][y]-1;
				logger.debug("Half life left!!");
				return true;
			}
		}else{
			logger.debug("Missed");
			return false;
		}
	}

	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}
}
