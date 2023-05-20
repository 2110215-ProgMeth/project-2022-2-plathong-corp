package logic.entity;

import constant.Direction;
import constant.EntityState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Chicknight extends Enemy {

	public Chicknight(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		this.maxHp = 30;
		this.currentHealth = maxHp;
		this.dmg = 5;
		this.z = -100;
		this.speed = 1;
		image = RenderableHolder.cKRight;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
//		System.out.println(c);
		switch (direction) {
		case RIGHT:
			if (attackState)
				image = RenderableHolder.cKRightAtk;
			else {
				image = RenderableHolder.cKRight;
				if (currentState == EntityState.ATTACK) {
					if (gameLogic.getCounter() / 10 % 2 == 1)
						image = RenderableHolder.cKRightWalk1;
				}
			}
			break;
		case LEFT:
			if (attackState)
				image = RenderableHolder.cKLeftAtk;
			else {
				image = RenderableHolder.cKLeft;
				if (currentState == EntityState.ATTACK) {
					if (gameLogic.getCounter() / 10 % 2 == 1)
						image = RenderableHolder.cKLeftWalk1;
				}
			}
			break;
		}

		gc.drawImage(image, screenX, screenY);

//		drawHitbox(gc);
//		drawAttackBlock(gc);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if (playerfound(500))
			currentState = EntityState.ATTACK;
		else
			currentState = EntityState.DEFAULT;
		Player player = gameLogic.getPlayer();
		canAttack = canAttack(player.solidScreen.getX() + solidScreen.getWidth() / 2,
				player.solidScreen.getY() + solidScreen.getHeight() / 2,
				solidScreen.getX() + solidScreen.getWidth() / 2, solidScreen.getY() + solidScreen.getHeight() / 2,
				(int) (32));
		if (currentState == EntityState.ATTACK) {
			if (canAttack) {
				if (delay == 0) {
					attackState = true;
					attack(gameLogic.getPlayer());
					RenderableHolder.chicknightSound.play(0.2);
					delay = 60;
				}
			} else {
				xspeed = Math.cos(angle) * speed;
				yspeed = Math.sin(angle) * speed;

				if (yspeed < 0)
					direction = Direction.UP;
				else
					direction = Direction.DOWN;
				setCollisionOn(false);
				gameLogic.checkTile(this);
				if (collisionOn == false) {
					worldY += yspeed;

				}

				if (xspeed < 0)
					direction = Direction.LEFT;
				else
					direction = Direction.RIGHT;

				setCollisionOn(false);
				gameLogic.checkTile(this);
				if (collisionOn == false) {
					worldX += xspeed;
				}
			}
			if (delay == 40)
				attackState = false;
			if (delay > 0)
				delay--;

		}
		updateAttackBlock();
	}

	public void initSolidArea() {
		solidArea = new Rectangle(20, 0, 24, 64);
	}

	public void initAttackBlock() {
		attackBlock = new Rectangle(screenX + solidArea.getWidth(), screenY, solidArea.getWidth() + 10 * 2, 64);
	}

//	Debug Chick
	public void drawAttackBlock(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.strokeRect(attackBlock.getX(), attackBlock.getY(), attackBlock.getWidth(), attackBlock.getHeight());
	}

}
