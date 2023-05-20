package logic.entity;

import Object.Ball;
import Object.Projectile;
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
		// TODO Auto-generated method stub
		switch (direction) {
		case "right":
			if (gameLogic.getCounter() < 20)
				if (currentState == "attacking")
					image = RenderableHolder.sPRightAtk;
				else
					image = RenderableHolder.sPRight1;

			else
				image = RenderableHolder.sPRight2;
			break;
		case "left":
			if (gameLogic.getCounter() < 20)
				if (currentState == "attacking")
					image = RenderableHolder.sPLeftAtk;
				else
					image = RenderableHolder.sPLeft1;

			else
				image = RenderableHolder.sPLeft2;

			break;
		}
		gc.drawImage(image, screenX, screenY);
		// TODO Auto-generated method stub
//		drawHitbox(gc);
	}

	public void attack() {
        // TODO Auto-generated method stub
  
        	gameLogic.addNewProjectile(new Ball(worldX+solidArea.getX(), worldY+solidArea.getY(), angle,gameLogic));
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if (playerfound(1000))
			currentState = "attacking";
		else
			currentState = "default";
		if (currentState == "attacking") {
			double xDirection = Math.cos(angle);
			if (xDirection < 0)
				direction = "left";
			else
				direction = "right";

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