package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class GriszlyEye extends Enemy {
	private double angle = 0;
	public String currentState = "default";

	public GriszlyEye(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		this.maxHp = 10;
		this.currentHealth = maxHp;
		this.dmg = 3;
		this.z = -100;
		this.speed = 3;
		this.dmg = 3;
		this.image = RenderableHolder.GERight;
		initSolidArea();
		initAttackBlock();
	}

	

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		switch (direction) {
		case "right":
			if (currentState == "default")
				image = RenderableHolder.GERight;

			else {
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.GERightWalk;
				} else
					image = RenderableHolder.GERightWalk2;
			}
			break;
		case "left":
			if (currentState == "default")
				image = RenderableHolder.GELeft;
			else {
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.GELeftWalk;
				} else
					image = RenderableHolder.GELeftWalk2;
			}
			break;
		}
		gc.drawImage(image, screenX, screenY);
		drawHitbox(gc);
//		drawAttackBlock(gc);
	}

//	@Override
//	public void attack(Entity p) {
//		// TODO Auto-generated method stub
//		System.out.println("GrsizlyEye Attack");
//
//		((Player) p).changeHealthTo(gameLogic.getPlayer().getCurrentHealth()-dmg);
//	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		screenX = worldX - gameLogic.getPlayer().worldX + gameLogic.getPlayer().screenX;
		screenY = worldY - gameLogic.getPlayer().worldY + gameLogic.getPlayer().screenY;
		Player player = gameLogic.getPlayer();
		if (!canAttack(player.worldX, player.worldY, worldX, worldY, 24)) {
			angle = Math.atan2(player.worldY - worldY, player.worldX - worldX);
			double xspeed = Math.cos(angle) * speed;
			double yspeed = Math.sin(angle) * speed;
			currentState = "walking";

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

		else {
			currentState = "default";
			attack(gameLogic.getPlayer());
		}
		solidArea.setX(screenX);
		solidArea.setY(screenY);
		updateAttackBlock();
	}

		
	public void initSolidArea() {
		solidArea = new Rectangle(20, 0, 24, 32);
		solidScreen = new Rectangle(screenX,screenY,8,8);

	}

	public void initAttackBlock() {
		attackBlock = new Rectangle(20, 0, 24, 32);

	}


}
