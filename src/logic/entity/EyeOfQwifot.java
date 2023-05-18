package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class EyeOfQwifot extends MiniBoss{

    public EyeOfQwifot(int x, int y, GameLogic gameLogic) {
        super(x, y, gameLogic);
        maxHp = 100;
        currentHealth = maxHp;
        z = -100;
        image = RenderableHolder.EQ1;
        currentState = "alive";
//        initAttackBlock();
        initSolidArea();
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
    }

	@Override
	public void initSolidArea() {
		// TODO Auto-generated method stub
		solidArea = new Rectangle(0,0,256,256);
	}

	@Override
	public void initAttackBlock() {
//		 TODO Auto-generated method stub
//		attackBlock = new Rectangle(0,0,256,256);
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
//			System.out.println("Plathong" + currentHealth);
		}
	}

}