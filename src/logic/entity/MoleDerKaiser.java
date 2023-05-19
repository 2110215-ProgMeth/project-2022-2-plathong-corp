package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class MoleDerKaiser extends Mole{
	private Mole[] moles = new Mole [3];
	
	public MoleDerKaiser(double x,double y,GameLogic gameLogic,int width,int height) {
		super(x, y, gameLogic, "DerKaiser", width, height);
		this.maxHp = 100;
		this.gameLogic =gameLogic;
		for(int i = 0;i<3;i++) {
			moles[i] = new Mole(x,y,gameLogic,"",width,height);
		}
	}
		
	public Mole[] getMoles() {
		return moles;
	}

	public void changeHealthTo(int health) {
		if (health>=maxHp) {
			currentHealth = maxHp;
		}
		else if (health<=0) {
			currentHealth = 0;
			currentState = "dead";
			for(int i = 0;i<3;i++) {
				moles[i].currentState = "dead";
			}
		}
		else {
			currentHealth = health;
		}
	}
}
