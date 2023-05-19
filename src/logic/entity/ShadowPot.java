package logic.entity;

import Object.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class ShadowPot extends Enemy{

    public ShadowPot(double x, double y, GameLogic gameLogic) {
        super(x, y, gameLogic);
        image = RenderableHolder.SPRight1;
        maxHp = 10;
        currentHealth = maxHp;
        delay = 1*60;
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO Auto-generated method stub
        switch(direction) {
        case "right":
                if (gameLogic.getCounter()<20) 
                	if(currentState == "attacking")
                		image = RenderableHolder.SPRightAtk;
                	else
                		image = RenderableHolder.SPRight1;

                else
                    image = RenderableHolder.SPRight2;
            break;
        case "left":
                if (gameLogic.getCounter()<20) 
                	if(currentState == "attacking")
                		image = RenderableHolder.SPLeftAtk;
                	else
                		image = RenderableHolder.SPLeft1;

                else
                    image = RenderableHolder.SPLeft2;

            break;
        }
        gc.drawImage(image, screenX, screenY);
        // TODO Auto-generated method stub
        drawHitbox(gc);
    }

    @Override
    public void attack(Entity t) {
        // TODO Auto-generated method stub

    		Projectile projectile = new Projectile(worldX+solidArea.getX(), worldY+solidArea.getY(), angle,gameLogic);
        	gameLogic.addNewProjectile(projectile);
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
		// TODO Auto-generated method stub
		solidArea = new Rectangle(16,24,32,40);
	}

	@Override
	public void initAttackBlock() {
		// TODO Auto-generated method stub
		attackBlock = new Rectangle(0,0,0,0);
	}

}