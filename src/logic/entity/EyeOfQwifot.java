package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class EyeOfQwifot extends Miniboss{

	public EyeOfQwifot(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		maxHp = 100;
		z = -100;
		image = RenderableHolder.EQ1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		switch(currentState) {
		case "alive":
			if (gameLogic.getCounter()/10%2==1) 
				image = RenderableHolder.EQ1;
			
			else
				image = RenderableHolder.EQ2;
			break;
		case "dead":
			if (gameLogic.getCounter()/10%2==1) 
				image = RenderableHolder.EQDead1;
			
			else
				image = RenderableHolder.EQDead2;
			break;
		}
		gc.drawImage(image,screenX,screenY);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(Entity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		screenX = worldX - gameLogic.getPlayer().worldX + gameLogic.getPlayer().screenX;
		screenY = worldY - gameLogic.getPlayer().worldY + gameLogic.getPlayer().screenY;
	}

}
