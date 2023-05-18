package logic.entity;

import Object.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class ShadowPot extends Enemy{
	
	private int cooldown;
    public ShadowPot(int x, int y, GameLogic gameLogic) {
        super(x, y, gameLogic);
        image = RenderableHolder.SPRight1;
        maxHp = 10;
        currentHealth = maxHp;
        cooldown = 1*60;
        initSolidArea();
        initAttackBlock();
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
        screenX = worldX - gameLogic.getPlayer().worldX + gameLogic.getPlayer().screenX;
        screenY = worldY - gameLogic.getPlayer().worldY + gameLogic.getPlayer().screenY;
        Player player = gameLogic.getPlayer();
        angle = Math.atan2(player.worldY - worldY, player.worldX - worldX);
        double xDirection =  Math.cos(Math.atan2(player.worldY-worldY,player.worldX-worldX));
        if(xDirection<0)
            direction = "left";
        else
            direction = "right";
        
        if(cooldown==0) {
        	attack(gameLogic.getPlayer());
        	cooldown = 1*60;
        }
        cooldown--;
    }

	@Override
	public void initSolidArea() {
		// TODO Auto-generated method stub
		solidArea = new Rectangle(16,24,32,40);
		solidScreen = new Rectangle(screenX,screenY,8,8);
	}

	@Override
	public void initAttackBlock() {
		// TODO Auto-generated method stub
		attackBlock = new Rectangle(16,24,32,40);
	}

}