package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Chicknight extends Enemy {


	public Chicknight(double x, double y, GameLogic gameLogic) {
		super(x,y,gameLogic);
		this.maxHp = 10;
		this.currentHealth = maxHp;
		this.dmg = 5;
		this.z = -100;
		this.speed = 1;
		image = RenderableHolder.CKRight;
		initSolidArea();
		initAttackBlock();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
//		System.out.println(c);
		switch (direction) {
		case "right":
			if (currentState == "attacking")
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.CKRight;
				} else {
					if(canAttack)
						image = RenderableHolder.CKRightAtk;
					else
						image =RenderableHolder.CKRightWalk1;
				}
					
			else {
					image = RenderableHolder.CKRight;
			}
			break;
		case "left":
			if (currentState == "attacking")
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.CKLeft;
				} else {
					if(canAttack)
						image = RenderableHolder.CKLeftAtk;
					else
						image =RenderableHolder.CKLeftWalk1;
				}
					
			else {
					image = RenderableHolder.CKLeft;
			}
			break;
		
		}

		gc.drawImage(image, screenX, screenY);

		drawHitbox(gc);
		drawAttackBlock(gc);
	}

	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if (playerfound(500)) 
			currentState = "attacking";
		else
			currentState = "default";
		Player player = gameLogic.getPlayer();
		canAttack = canAttack(player.solidScreen.getX()+solidScreen.getWidth()/2, player.solidScreen.getY()+solidScreen.getHeight()/2, solidScreen.getX()+solidScreen.getWidth()/2, solidScreen.getY()+solidScreen.getHeight()/2,
				(int) (32));
		if (currentState == "attacking") {
			if(canAttack) {
				attack(gameLogic.getPlayer());
			}
			else {
				double xspeed = Math.cos(angle) * speed;
				double yspeed = Math.sin(angle) * speed;
				

				if (yspeed < 0)
					direction = "up";
				else
					direction = "down";
				setCollisionOn(false);
				gameLogic.checkTile(this);
				if (collisionOn == false) {
					worldY += yspeed;

				}

				if (xspeed < 0)
					direction = "left";
				else
					direction = "right";

				setCollisionOn(false);
				gameLogic.checkTile(this);
				if (collisionOn == false) {
					worldX += xspeed;
				}
			}


		}
		updateAttackBlock();
	}

	public void initSolidArea() {
		solidArea = new Rectangle(20, 0, 24, 64);
	}

	public void initAttackBlock() {
		attackBlock = new Rectangle(screenX+solidArea.getWidth(), screenY, solidArea.getWidth()+10 * 2, 64);
	}
	
//	Debug Chick
	public void drawAttackBlock(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.strokeRect(attackBlock.getX(), attackBlock.getY(), attackBlock.getWidth(),
				attackBlock.getHeight());
	}


}
