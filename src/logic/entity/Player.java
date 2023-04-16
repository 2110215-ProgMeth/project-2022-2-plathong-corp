package logic.entity;



import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Player extends Entity{
	
	public GameLogic gameLogic;
	public Player(int x, int y,GameLogic gameLogic) {
		this.worldX = x;
		this.worldY = y;
		this.speed = 3;
		this.radius = 32;
		this.gameLogic = gameLogic;
		this.direction = "right";
		screenX = gameLogic.getGameScreen().getWidth()/2-radius;
		screenY = gameLogic.getGameScreen().getHeight()/2-radius;
		
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		switch(direction) {
		case "left":
			gc.drawImage(RenderableHolder.playerLeft, screenX, screenY);
			break;
		case "right":
			gc.drawImage(RenderableHolder.playerRight, screenX, screenY);
			break;
		}	
//		gc.setFill(Color.BLACK);
//		gc.fillRect(gameLogic.getGameScreen().getWidth()/2, gameLogic.getGameScreen().getHeight()/2, 1, 1);
	}
	
	public void attack() {
		
	}

	public void update() {
		// TODO Auto-generated method stub
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			worldY -= speed;
		}
		if (InputUtility.getKeyPressed(KeyCode.A)) {
			worldX -= speed;
			direction = "left";
		}
		if (InputUtility.getKeyPressed(KeyCode.S)) {
			worldY += speed;
		}
		if (InputUtility.getKeyPressed(KeyCode.D)) {
			worldX += speed;
			direction = "right";
		}
		
		if (InputUtility.isLeftClickTriggered()) {
	
		}
	}

}
