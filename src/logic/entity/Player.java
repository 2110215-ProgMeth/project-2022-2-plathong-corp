package logic.entity;



import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Player extends Entity{
	
	public GameLogic gameLogic;
	private String attackState = "no";
	int counter;
	public Player(int x, int y,GameLogic gameLogic) {
		this.worldX = x;
		this.worldY = y;
		this.speed = 3;
		this.radius = 32;
		this.gameLogic = gameLogic;
		this.direction = "right";
		screenX = gameLogic.getGameScreen().getWidth()/2-radius;
		screenY = gameLogic.getGameScreen().getHeight()/2-radius;
		solidArea = new Rectangle(16,32,32,32);
	}
	@Override
	public void draw(GraphicsContext gc) {
		int count = 0;
		Image playerImage = RenderableHolder.playerRight;
		// TODO Auto-generated method stub
		switch(direction) {
		case "left":
			playerImage = (RenderableHolder.playerLeft);
			break;
		case "right":
			playerImage = RenderableHolder.playerRight;
			break;

		}	
		gc.drawImage(playerImage , screenX, screenY);
	}
	
	public void attack() {

		attackState = "yes";
	}

	public void update() {
		// TODO Auto-generated method stub
		direction = "";
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			direction = "up";
		}
		if (InputUtility.getKeyPressed(KeyCode.S)) {
			direction = "down";
		}
		
		
		setCollisionOn(false);
		gameLogic.checkTile(this);
		
		if (collisionOn == false) {
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;

			}
		}
		
		if (InputUtility.getKeyPressed(KeyCode.D)) {
			direction = "right";
		}
		if (InputUtility.getKeyPressed(KeyCode.A)) {
			direction = "left";
		}
		
		setCollisionOn(false);
		gameLogic.checkTile(this);
		if (collisionOn == false) {
			switch(direction) {
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
		if (InputUtility.isLeftClickTriggered()) {
	
		}

	}

}
