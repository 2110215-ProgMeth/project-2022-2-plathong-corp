package logic.entity;

import constant.EntityState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class EyeOfQwifot extends Enemy {

	public EyeOfQwifot(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		maxHp = 300;
		currentHealth = maxHp;
		z = 100;
		image = RenderableHolder.eQ1;
	}

	@Override
	public void changeHealthTo(int health) {
		if (health >= maxHp) {
			currentHealth = maxHp;
		} else if (health <= 0) {
			currentHealth = 0;
			currentState = EntityState.DEAD;
		} else {
			currentHealth = health;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (currentState == EntityState.DEAD) {
			if (gameLogic.getCounter() / 10 % 2 == 1)
				image = RenderableHolder.eQDead1;

			else
				image = RenderableHolder.eQDead2;
		} else {
			if (delay > 60 && delay < 80)
				image = RenderableHolder.eQAtk;
			else {
				if (gameLogic.getCounter() / 10 % 2 == 1)
					image = RenderableHolder.eQ1;

				else
					image = RenderableHolder.eQ2;
			}

		}
		gc.drawImage(image, getScreenPos().getX(), getScreenPos().getY());
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(15));
		gc.fillText("Eye Of Qwifot", getScreenPos().getX() + 70, getScreenPos().getY() - 10);

//        drawHitbox(gc);

	}

	@Override
	public void attack(Entity t) {

	}

	@Override
	public void update() {
		super.update();
		if (currentState != EntityState.DEAD) {
			if (playerfound(768))
				currentState = EntityState.ATTACK;
			else
				currentState = EntityState.DEFAULT;

			if (currentState == EntityState.ATTACK) {
				if (delay == 0)
					delay = 300;
				if (delay == 60) {
					RenderableHolder.griszlyEyeSound.play(0.3);
					gameLogic.addNewObject(new GriszlyEye(getWorldPos().getX(), getWorldPos().getY(), gameLogic));
					gameLogic.addNewObject(new GriszlyEye(getWorldPos().getX() + 240, getWorldPos().getY(), gameLogic));
				}
				delay--;
			}
		}

	}

	@Override
	public void initSolidArea() {
		solidArea = new Rectangle(0, 0, 256, 256);
	}

	@Override
	public void initAttackBlock() {
		attackBlock = new Rectangle(0, 0, 0, 0);
	}

}