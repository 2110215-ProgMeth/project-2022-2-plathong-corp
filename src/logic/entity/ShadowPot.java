package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class ShadowPot extends Enemy{

	public ShadowPot(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		image = RenderableHolder.SPRight1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		switch(direction) {
		case "right":
				if (gameLogic.getCounter()/10%2==1) 
					image = RenderableHolder.SPRight1;
				
				else
					image = RenderableHolder.SPRight2;
			break;
		case "left":
				if (gameLogic.getCounter()/10%2==1) 
					image = RenderableHolder.SPLeft1;
				
				else
					image = RenderableHolder.SPLeft2;
			
			break;
		}
		gc.drawImage(image, screenX, screenY);
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
		Player player = gameLogic.getPlayer();
		double xDirection =  Math.cos(Math.atan2(player.worldY-worldY,player.worldX-worldX));
		if(xDirection<0)
			direction = "left";
		else
			direction = "right";
	}

}
