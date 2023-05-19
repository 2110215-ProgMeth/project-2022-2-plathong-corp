package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class EyeOfQwifot extends Enemy{

    public EyeOfQwifot(double x, double y, GameLogic gameLogic) {
        super(x, y, gameLogic);
        maxHp = 10;
        currentHealth = maxHp;
        z = 100;
        image = RenderableHolder.EQ1;
        initAttackBlock();
        initSolidArea();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO Auto-generated method stub
        if(currentState == "dead") {
        	if (gameLogic.getCounter()/10%2==1) 
                image = RenderableHolder.EQDead1;

            else
                image = RenderableHolder.EQDead2;
        } else {
            if (gameLogic.getCounter()/10%2==1) 
                image = RenderableHolder.EQ1;

            else
                image = RenderableHolder.EQ2;

        }
        gc.drawImage(image,screenX,screenY);
        // TODO Auto-generated method stub
        drawHitbox(gc);
        
    }

    @Override
    public void attack(Entity t) {
        // TODO Auto-generated method stub
    	
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        super.update();
		if (playerfound(768)) 
			currentState = "attacking";
		else
			currentState = "default";
		

        if(currentState =="attacking") {
        	if(delay == 0)
        		delay = 300;
        	if(delay == 60) {
        	gameLogic.addNewObject(new GriszlyEye(worldX, worldY, gameLogic));
        	gameLogic.addNewObject(new GriszlyEye(worldX+20, worldY, gameLogic));
        	}
        	delay--;
        }
        
        
    }

	@Override
	public void initSolidArea() {
		// TODO Auto-generated method stub
		solidArea = new Rectangle(0,0,256,256);
	}

	@Override
	public void initAttackBlock() {
//		 TODO Auto-generated method stub
		attackBlock = new Rectangle(0,0,0,0);
	}
	
	public void changeHealthTo(int health) {
		if (health>=maxHp) {
			currentHealth = maxHp;
		}
		else if (health<=0) {
			currentHealth = 0;
			currentState = "dead";
		}
		else {
			currentHealth = health;
		}
	}

}