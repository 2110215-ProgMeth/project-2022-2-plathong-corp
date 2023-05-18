package logic.game;

import java.util.ArrayList;
import java.util.List;

import drawing.GameScreen;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.entity.Chicknight;
import logic.entity.Entity;
import logic.entity.GriszlyEye;
import logic.entity.MagicalTortoise;
import logic.entity.Player;
import logic.field.Map1;
import logic.field.WhiteMap;
import sharedObject.RenderableHolder;

public class GameLogic {
	private ArrayList<Entity> gameObjectContainer;
	private static int counter = 0;
	
	private GameScreen gameScreen;
	private  Player player;
	private Chicknight ck1;
	private GriszlyEye GE1;
	private MagicalTortoise MG;
	private Map1 map;
	
	//GameState
	public int gameState = 1;
	public final int playState = 1;
	public final int pauseState = 2;
	
	public GameLogic(GameScreen gameScreen){
		this.gameObjectContainer = new ArrayList<Entity>();
		this.gameScreen = gameScreen;
		player = new Player(384,288,this);
		
		map = new Map1(this);
		RenderableHolder.getInstance().add(map);
		
		ck1 = new Chicknight(200,0,this);
		MG = new MagicalTortoise(200,200,this);
		GE1 = new GriszlyEye(200,200,this);
		addNewObject(player);
		addNewObject(ck1);
		addNewObject(MG);
		addNewObject(GE1);
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}

	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	public void checkTile(Entity entity) {
		int entityLeftWorldX = (int) (entity.getWorldX() + entity.solidArea.getX());
		int entityRightWorldX = (int) (entity.getWorldX() + entity.solidArea.getX() + entity.solidArea.getWidth());
		int entityTopWorldY = (int) (entity.getWorldY() + entity.solidArea.getY());
		int entityBottomWorldY = (int) (entity.getWorldY() + entity.solidArea.getY() + entity.solidArea.getHeight());
	 
		int entityLeftCol = entityLeftWorldX/map.getTileSize();
		int entityRightCol = entityRightWorldX/map.getTileSize();
		int entityTopRow = entityTopWorldY/map.getTileSize();
		int entityBottomRow = entityBottomWorldY/map.getTileSize();
		
		int tile1 = 0;
		int tile2 = 0;
		
		switch(entity.getDirection()) {
		case "up" :
			entityTopRow = (entityTopWorldY-entity.getSpeed())/map.getTileSize();
			tile1 = map.getTileIndex(entityLeftCol, entityTopRow);
			tile2 = map.getTileIndex(entityRightCol, entityTopRow);
//			tile1 = map.field[entityLeftCol][entityTopRow];
//			tile2 = map.field[entityRightCol][entityTopRow];
			
			if(map.getTiles()[tile1].collision == true || map.getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY+entity.getSpeed())/map.getTileSize();
			tile1 = map.getTileIndex(entityLeftCol, entityBottomRow);
			tile2 = map.getTileIndex(entityRightCol, entityBottomRow);
//			tile1 = map.field[entityLeftCol][entityBottomRow];
//			tile2 = map.field[entityRightCol][entityBottomRow];
			if(map.getTiles()[tile1].collision == true || map.getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX-entity.getSpeed())/map.getTileSize();
			tile1 = map.getTileIndex(entityLeftCol, entityTopRow);
			tile2 = map.getTileIndex(entityLeftCol, entityBottomRow);
//			tile1 = map.field[entityLeftCol][entityTopRow];
//			tile2 = map.field[entityLeftCol][entityBottomRow];
			if(map.getTiles()[tile1].collision == true || map.getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX+entity.getSpeed())/map.getTileSize();
			tile1 = map.getTileIndex(entityRightCol, entityTopRow);
			tile2 = map.getTileIndex(entityRightCol, entityBottomRow);
//			tile1 = map.field[entityRightCol][entityTopRow];
//			tile2 = map.field[entityRightCol][entityBottomRow];
			if(map.getTiles()[tile1].collision == true || map.getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		}
		
	}
	
	public void update() {
//		System.out.println(gameState);
		if(gameState == playState) {
		logicUpdate();

		gameScreen.paintComponent();
		}
		else if (gameState == pauseState) {
//			System.out.println(500);
		}
	}
	
	public void logicUpdate(){
		if (counter == 60){
			counter = 0;}
		ArrayList<Entity> objectContainer = getGameObjectContainer();
		for (int i = 0 ;i<objectContainer.size();i++) {
			(objectContainer.get(i)).update();
			if (objectContainer.get(i).isDestroyed()) getGameObjectContainer().remove(objectContainer.get(i));
		}
		
	}
	
	public Player getPlayer() {
		return player;
	}

	public int getCounter() {
		return counter;
	}
	
	public void count() {
		counter++;
		if(counter%10 == 0) {
			System.out.println(counter);
		}
	}
	
	public void checkGameState() {
		if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
			
			if(gameState == playState) {
				gameState = pauseState;
				gameScreen.drawGamePauseOverlay();
			}
			else if (gameState == pauseState) {
				gameState = playState;
				
				
			}
			InputUtility.getKeyPressed().remove(KeyCode.ESCAPE);
		}
	}
	
	public ArrayList<Entity> getGameObjectContainer(){
		return gameObjectContainer;
	}
	
}
