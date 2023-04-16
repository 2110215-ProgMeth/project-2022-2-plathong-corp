package logic.game;

import java.util.ArrayList;
import java.util.List;

import drawing.GameScreen;
import logic.entity.Entity;
import logic.entity.Player;
import logic.entity.Werewolf;
import logic.field.Map1;
import logic.field.WhiteMap;
import sharedObject.RenderableHolder;

public class GameLogic {
	private List<Entity> gameObjectContainer;
	
	private GameScreen gameScreen;
	private  Player player;
	private Werewolf werewolf;
	public GameLogic(GameScreen gameScreen){
		this.gameObjectContainer = new ArrayList<Entity>();
		this.gameScreen = gameScreen;
		player = new Player(384,288,this);
		
		Map1 field = new Map1(this);
		RenderableHolder.getInstance().add(field);
		
		werewolf = new Werewolf(100,100,this);
		addNewObject(player);
		addNewObject(werewolf);
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}

	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		werewolf.update(player);
		player.update();
	}
	
	public Player getPlayer() {
		return player;
	}
}
