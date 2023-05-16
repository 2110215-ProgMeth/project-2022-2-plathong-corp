package logic.game;

import java.util.ArrayList;
import java.util.List;

import drawing.GameScreen;
import logic.entity.Chicknight;
import logic.entity.Entity;
import logic.entity.Player;
import logic.entity.Werewolf;
import logic.field.Map1;
import logic.field.WhiteMap;
import sharedObject.RenderableHolder;

public class GameLogic {
	private List<Entity> gameObjectContainer;
	private static int counter = 0;
	
	private GameScreen gameScreen;
	private  Player player;
	private Chicknight ck1;
	private Map1 map;
	
	public GameLogic(GameScreen gameScreen){
		this.gameObjectContainer = new ArrayList<Entity>();
		this.gameScreen = gameScreen;
		player = new Player(384,288,this);
		
		map = new Map1(this);
		RenderableHolder.getInstance().add(map);
		
		ck1 = new Chicknight(384,288,this);
		addNewObject(player);
		addNewObject(ck1);
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
	public void logicUpdate(){
		if (counter == 60){
			counter = 0;}
		ck1.update();
		player.update();
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
	
}
