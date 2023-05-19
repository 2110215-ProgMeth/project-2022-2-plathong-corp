package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class MoleDerKaiser extends Enemy{
	private int width = 7;
	private int height  = 7;
	public MoleDerKaiser(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		this.maxHp = 100;
		this.currentHealth = maxHp;
		this.z = -100;
		image = RenderableHolder.CKRight;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update() {
		
	}
	@Override
	public void initSolidArea() {
		// TODO Auto-generated method stub
		solidArea = new Rectangle(16, 8, 32, 48);
	}

	@Override
	public void initAttackBlock() {
		attackBlock = new Rectangle(0,0,0,0);
		
	}

}
