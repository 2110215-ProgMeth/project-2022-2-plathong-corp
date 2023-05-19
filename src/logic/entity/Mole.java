package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Mole extends Enemy{
	protected String rank;
	public Mole(double x, double y, GameLogic gameLogic,String rank) {
		super(x, y, gameLogic);
		this.maxHp = 100;
		this.currentHealth = maxHp;
		this.z = -100;
		this.rank = rank;
		if(rank=="DerKaiser")
			image = RenderableHolder.moleDerKaiser;
		else
			image = RenderableHolder.mole;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(image, screenX, screenX);
	}
	
	@Override
    public void update() {
        // TODO Auto-generated method stub
        super.update();
		if (playerfound(1000)) 
			currentState = "attacking";
		else
			currentState = "default";
		if(currentState == "attacking") {
        Player player = gameLogic.getPlayer();
        angle = Math.atan2(player.worldY - worldY, player.worldX - worldX);
        double xDirection =  Math.cos(Math.atan2(player.worldY-worldY,player.worldX-worldX));
        if(xDirection<0)
            direction = "left";
        else
            direction = "right";
        
        if(delay==0) {
        	attack(gameLogic.getPlayer());
        	delay = 1*60;
        }
        delay--;
		}
    }
	@Override
	public void initSolidArea() {
		solidArea = new Rectangle(16, 8, 32, 48);
	}

	@Override
	public void initAttackBlock() {
		attackBlock = new Rectangle(0,0,0,0);
	}

}
