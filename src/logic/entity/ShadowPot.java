package logic.entity;

import Object.Ball;
import constant.Direction;
import constant.EntityState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class ShadowPot extends Enemy {

	public ShadowPot(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		image = RenderableHolder.sPRight1;
		maxHp = 20;
		currentHealth = maxHp;
		delay = 1 * 60;
	}

	@Override
	public void draw(GraphicsContext gc) {
		switch (direction) {
		case RIGHT:
			if (gameLogic.getCounter() < 20)
				if (currentState == EntityState.ATTACK)
					image = RenderableHolder.sPRightAtk;
				else
					image = RenderableHolder.sPRight1;

			else
				image = RenderableHolder.sPRight2;
			break;
		case LEFT:
			if (gameLogic.getCounter() < 20)
				if (currentState == EntityState.ATTACK)
					image = RenderableHolder.sPLeftAtk;
				else
					image = RenderableHolder.sPLeft1;

			else
				image = RenderableHolder.sPLeft2;

			break;
		}
		gc.drawImage(image, getScreenPos().getX(), getScreenPos().getY());
//		drawHitbox(gc);
	}

	public void attack() {
        	gameLogic.addNewProjectile(new Ball(getWorldPos().getX()+solidArea.getX(), getWorldPos().getY()+solidArea.getY(), angle,gameLogic));
    }

	@Override
	public void update() {
		super.update();
		if (playerfound(1000))
			currentState = EntityState.ATTACK;
		else
			currentState = EntityState.DEFAULT;
		if (currentState == EntityState.ATTACK) {
			double xDirection = Math.cos(angle);
			if (xDirection < 0)
				direction = Direction.LEFT;
			else
				direction = Direction.RIGHT;

			if (delay == 0) {

				attack();
				delay = 1 * 60;
			}
			delay--;
		}
	}

	@Override
	public void initSolidArea() {
		// TODO Auto-generated method stub
		solidArea = new Rectangle(16, 24, 32, 40);
	}

	@Override
	public void initAttackBlock() {
		// TODO Auto-generated method stub
		attackBlock = new Rectangle(0, 0, 0, 0);
	}

}