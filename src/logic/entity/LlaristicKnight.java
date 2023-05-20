package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;

public class LlaristicKnight extends Enemy{

	public LlaristicKnight(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
        maxHp = 100;
        currentHealth = maxHp;
        z = -100;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		drawHitbox(gc);
		drawAttackBlock(gc);
	}

	@Override
	public void update() {
		super.update();
		updateAttackBlock();
	}
	
	@Override
	public void initSolidArea() {
		// TODO Auto-generated method stub
		solidArea = new Rectangle(20, 0, 24, 64);
	}

	@Override
	public void initAttackBlock() {
		// TODO Auto-generated method stub
		attackBlock = new Rectangle(screenX+solidArea.getWidth(), screenY, solidArea.getWidth()+ 40, 64);
	}

	@Override
	public void changeHealthTo(int health) {
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
