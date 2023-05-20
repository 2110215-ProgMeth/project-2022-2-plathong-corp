package logic.entity;

import Object.SwordBeam;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class LlaristicKnight extends Enemy{

	protected Rectangle normalAttackBlock;
	private double probablility = 0.7;
	private int xExtra,yExtra;
	public LlaristicKnight(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
        maxHp = 100;
        currentHealth = maxHp;
        z = -100;
        dmg = 50;
        speed = 1;
        image = RenderableHolder.lKRight1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		switch (direction) {
		case "right":
			if(currentState == "attacking") {

					if(delay>40)
						image = RenderableHolder.lKRightAtk;
					else {
						if (delay / 10 % 2 == 1)
							image = RenderableHolder.lKRight2;
						else
							image = RenderableHolder.lKRight1;
					}

			}
			else
				image = RenderableHolder.lKRight1;
			break;
		case "left":
			if (currentState == "attacking") {
					if (delay > 40)
						image = RenderableHolder.lKLeftAtk;
					else {
						if (delay / 10 % 2 == 1)
							image = RenderableHolder.lKLeft2;
						else
							image = RenderableHolder.lKLeft1;
					}

			} else
				image = RenderableHolder.lKLeft1;
			break;

		}

		gc.drawImage(image, screenX, screenY);
//		drawHitbox(gc);
//		drawAttackBlock(gc);
	}

	@Override
	public void update() {
		super.update();
		if(currentState!="dead") {
		if (playerfound(1000)) 
			currentState = "attacking";
		else
			currentState = "default";
		Player player = gameLogic.getPlayer();
		canAttack = canAttack(player.solidScreen.getX() + solidScreen.getWidth() / 2,
				player.solidScreen.getY() + solidScreen.getHeight() / 2,
				solidScreen.getX() + solidScreen.getWidth() / 2, solidScreen.getY() + solidScreen.getHeight() / 2, 100);
		if (currentState == "attacking") {
			if(delay==0) {
				delay = 60;
				attack(player);
				if(canAttack == ( Math.random()<probablility)) 
					specialMove();
				else
					gameLogic.addNewProjectile(new SwordBeam(worldX, worldY, angle, gameLogic));
			}			
			move();
		}	
		if(delay >0) delay--;
		}
		updateAttackBlock();
	}
	
	public void specialMove() {
		if (yspeed < 0)
			direction = "up";
		else
			direction = "down";
		setCollisionOn(false);
		gameLogic.checkTile(this);
		yExtra = (int) (yspeed * 500 * Math.random());
		if (collisionOn == false && (worldY + yExtra> 0 && worldY+yExtra< 64*47 )) {
			worldY += yExtra;
		}

		if (xspeed < 0)
			direction = "left";
		else
			direction = "right";

		setCollisionOn(false);
		gameLogic.checkTile(this);
		xExtra = (int) (xspeed * 500 * Math.random());
		if (collisionOn == false && (worldX + xExtra> 0 && worldX+xExtra< 64*63 )) {
			worldX += xExtra;
		}
	}
	@Override
	public void initSolidArea() {
		// TODO Auto-generated method stub
		solidArea = new Rectangle(20, 0, 24, 64);
	}

	@Override
	public void initAttackBlock() {
		// TODO Auto-generated method stub
		attackBlock = new Rectangle(0,0, solidArea.getWidth()+ 100, 64);
	}

	@Override
	public void changeHealthTo(int health) {
		System.out.println(health+" "+getCurrentHealth());
		if (health>=maxHp) {
			currentHealth = maxHp;
		}
		else if (health<=0) {
			currentHealth = 0;
			currentState = "dead";
		}
		else {
			currentHealth = health;
		}
	}
}