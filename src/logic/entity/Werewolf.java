package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;


public class Werewolf extends Entity{
	private double angle = 0;
	public GameLogic gameLogic;
	public Werewolf(int x, int y,GameLogic gameLogic) {
		this.worldX = x;
		this.worldY = y;
		this.z = -100;
		this.speed =1;
		this.gameLogic = gameLogic;;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		screenX = worldX-gameLogic.getPlayer().worldX+gameLogic.getPlayer().screenX;
		screenY = worldY-gameLogic.getPlayer().worldY+gameLogic.getPlayer().screenY;
		gc.drawImage(RenderableHolder.johnSprite, screenX,  screenY);
	}
	
	public void update(Player player) {
		// TODO Auto-generated method stub	
		if (!canAttack(player.worldX, player.worldY, worldX, worldY, 24)) {
			angle = Math.atan2(player.worldY-worldY,player.worldX-worldX);
			double xspeed = Math.cos(angle) * speed;
			double yspeed = Math.sin(angle) * speed;
			
			if (xspeed<0)
				direction = "left";
			else direction = "right";
			
			worldX += xspeed;
			worldY += yspeed;
		}
		
		else {
			attack();
		}
	}
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

}
