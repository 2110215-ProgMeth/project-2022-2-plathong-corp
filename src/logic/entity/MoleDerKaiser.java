package logic.entity;

import constant.EntityState;
import logic.game.GameLogic;

public class MoleDerKaiser extends Mole{
	private Mole[] moles = new Mole [3];
	
	public MoleDerKaiser(double x,double y,GameLogic gameLogic,int width,int height) {
		super(x, y, gameLogic, "DerKaiser", width, height);
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
			currentState = EntityState.DEAD;
			for(int i = 0;i<3;i++) {
				moles[i].currentState = EntityState.DEAD;
			}
		}
		else {
			currentHealth = health;
		}
	}
}
