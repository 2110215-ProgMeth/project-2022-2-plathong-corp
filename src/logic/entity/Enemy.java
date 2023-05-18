package logic.entity;

import logic.game.GameLogic;

public abstract class Enemy extends Entity{
	public Enemy(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		// TODO Auto-generated constructor stub
	}

	protected String currentState = "alive";
}
