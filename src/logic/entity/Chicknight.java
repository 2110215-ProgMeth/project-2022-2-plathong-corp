package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Chicknight extends Entity{
	private double angle = 0;
	private Image image = RenderableHolder.CKRight;
	private String currentState = "default";
	public Chicknight(int x, int y,GameLogic gameLogic) {
		super(x,y,gameLogic);
		this.z = -100;
		this.speed =1;
		solidArea = new Rectangle(16,0,32,64);
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
		
		switch(direction) {
		case "right":
			if(currentState=="default")
				if (gameLogic.getCounter()/10%2==1) {
					image = RenderableHolder.CKRight;
				}
				else
					image = RenderableHolder.CKRightAtk;
			else {
				if (gameLogic.getCounter()/10%2==1) {
					image = RenderableHolder.CKRightWalk1;
				}
				else
					image = RenderableHolder.CKRight;
			}
			break;
		case "left":
			if(currentState=="default")
				if (gameLogic.getCounter()/10%2==1) {
					image = RenderableHolder.CKLeft;
				}
				else
					image = RenderableHolder.CKLeftAtk;
			else {
				if (gameLogic.getCounter()/10%2==1) {
					image = RenderableHolder.CKLeftWalk1;
				}
				else
					image = RenderableHolder.CKLeft;
			}
			break;
		}
		gc.drawImage(image, screenX, screenY);
	}

	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		screenX = worldX-gameLogic.getPlayer().worldX+gameLogic.getPlayer().screenX;
		screenY = worldY-gameLogic.getPlayer().worldY+gameLogic.getPlayer().screenY;
		Player player = gameLogic.getPlayer();
		if (!canAttack(player.worldX, player.worldY, worldX, worldY, 24)) {
			angle = Math.atan2(player.worldY-worldY,player.worldX-worldX);
			double xspeed = Math.cos(angle) * speed;
			double yspeed = Math.sin(angle) * speed;
			currentState = "walking";

			if(yspeed<0)
				direction = "up";
			else
				direction = "down";
			setCollisionOn(false);
			gameLogic.checkTile(this);
			if (collisionOn == false) {
				worldY += yspeed;

				}
			
			if (xspeed<0)
				direction = "left";
			else direction = "right";
			
			setCollisionOn(false);
			gameLogic.checkTile(this);
			if (collisionOn == false) {
			worldX += xspeed;
			}
			
		}
		
		else {
			currentState = "default";
			attack();
		}
	}

}