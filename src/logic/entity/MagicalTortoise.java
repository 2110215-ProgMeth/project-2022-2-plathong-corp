package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class MagicalTortoise extends Entity{
	public MagicalTortoise(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		image = RenderableHolder.MTRight1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		switch(direction) {
		case "right":
				if (gameLogic.getCounter()/10%2==1) 
					image = RenderableHolder.MTRight1;
				
				else
					image = RenderableHolder.MTRight2;
			break;
		case "left":
				if (gameLogic.getCounter()/10%2==1) 
					image = RenderableHolder.MTLeft1;
				
				else
					image = RenderableHolder.MTLeft2;
			
			break;
		}
		gc.drawImage(image, screenX, screenY);

	}

	@Override
	public void attack(Entity p) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		screenX = worldX-gameLogic.getPlayer().worldX+gameLogic.getPlayer().screenX;
		screenY = worldY-gameLogic.getPlayer().worldY+gameLogic.getPlayer().screenY;
		Player player = gameLogic.getPlayer();
		double xDirection =  Math.cos(Math.atan2(player.worldY-worldY,player.worldX-worldX));
		if(xDirection<0)
			direction = "left";
		else
			direction = "right";
	}

}
