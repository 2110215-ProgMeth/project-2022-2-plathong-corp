package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Chicknight extends Enemy {
	private Image image = RenderableHolder.CKRight;
	private String currentState = "default";

	public Chicknight(int x, int y, GameLogic gameLogic) {
		super(x,y,gameLogic);
		this.maxHp = 10;
		this.currentHealth = maxHp;
		this.dmg = 5;
		this.z = -100;
		this.speed = 1;
		initSolidArea();
		initAttackBlock();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
//		System.out.println(c);
		switch (direction) {
		case "right":
			if (currentState == "attack")
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.CKRight;
				} else {
					image = RenderableHolder.CKRightAtk;
				}
					
			else {
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.CKRightWalk1;
				} else
					image = RenderableHolder.CKRight;
			}
			break;
		case "left":
			if (currentState == "attack")
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.CKLeft;
				} else {
					image = RenderableHolder.CKLeftAtk;
				}
					
			else {
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.CKLeftWalk1;
				} else
					image = RenderableHolder.CKLeft;
			}
			break;
		
		}
		gc.drawImage(image, screenX, screenY);
		// debugging
//		gc.drawImage(RenderableHolder.pauseMenu,screenX,screenY);
		drawHitbox(gc);
		drawAttackBlock(gc);
	}

	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		screenX = worldX - gameLogic.getPlayer().worldX + gameLogic.getPlayer().screenX;
		screenY = worldY - gameLogic.getPlayer().worldY + gameLogic.getPlayer().screenY;
		Player player = gameLogic.getPlayer();
		if (!canAttack(player.worldX, player.worldY, worldX, worldY, (int)(attackBlock.getWidth()+solidArea.getWidth()))) {
			angle = Math.atan2(player.worldY - worldY, player.worldX - worldX);
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
			currentState = "attack";
			attack(gameLogic.getPlayer());
		}
		solidArea.setX(screenX);
		solidArea.setY(screenY);
		updateAttackBlock();
	}

	public void initSolidArea() {
		solidArea = new Rectangle(0, 0, 32, 64);
		solidScreen = new Rectangle(screenX+solidArea.getX(),screenY+solidArea.getY(),8,8);
	}

	public void initAttackBlock() {
		attackBlock = new Rectangle(screenX+solidArea.getWidth(), screenY, 10 * 2, 7 * 2);
	}
	
//	Debug Chick
	public void drawAttackBlock(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.strokeRect(attackBlock.getX()+solidArea.getWidth(), attackBlock.getY(), attackBlock.getWidth(),
				attackBlock.getHeight());
	}


}
