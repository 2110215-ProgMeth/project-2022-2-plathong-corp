package logic.entity;

import constant.Direction;
import constant.EntityState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class GriszlyEye extends Enemy {

	private int normalSpeed = 3;

	public GriszlyEye(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		maxHp = 20;
		currentHealth = maxHp;
		dmg = 5;
		z = -100;
		speed = normalSpeed;
		image = RenderableHolder.gERight;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		switch (direction) {
		case RIGHT:
			if (currentState == EntityState.DEFAULT)
				image = RenderableHolder.gERight;

			else {
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.gERightWalk;
				} else

					image = RenderableHolder.gERightWalk2;
			}
			break;
		case LEFT:
			if (currentState == EntityState.DEFAULT)
				image = RenderableHolder.gELeft;
			else {
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.gELeftWalk;
				} else
					image = RenderableHolder.gELeftWalk2;
			}
			break;
		}
		gc.drawImage(image, getScreenPos().getX(), getScreenPos().getY());
//		drawHitbox(gc);
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
		canAttack = canAttack(player.getWorldPos().getX(), player.getWorldPos().getY(), getWorldPos().getX() + radius, getWorldPos().getY() + radius, 128);
		if (currentState == EntityState.ATTACK) {
			if (delay == 0) {
				speed = normalSpeed;
				delay = 120;
			}
			if (delay == 30) {
				speed = normalSpeed * 3;
				RenderableHolder.griszlyEyeSound.play(0.1);
				xspeed = Math.cos(angle) * speed;
				yspeed = Math.sin(angle) * speed;
			}

			if (delay > 30) {
				xspeed = Math.cos(angle) * speed;
				yspeed = Math.sin(angle) * speed;
			}
			move();
			
			

			attack(gameLogic.getPlayer());
			delay--;
		}

		updateAttackBlock();
	}
	@Override
	public void move() {
		if(xspeed>0)
			direction =Direction.RIGHT;
		else
			direction =Direction.LEFT;
		getWorldPos().setX(getWorldPos().getX()+xspeed);
		getWorldPos().setY(getWorldPos().getY()+yspeed);
	}
	public void initSolidArea() {
		solidArea = new Rectangle(20, 0, 24, 32);

	}

	public void initAttackBlock() {
		attackBlock = new Rectangle(0, 0, 0, 0);

	}

}
