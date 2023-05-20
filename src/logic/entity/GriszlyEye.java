package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class GriszlyEye extends Enemy {

	private int normalSpeed = 3;
	public GriszlyEye(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		this.maxHp = 10;
		this.currentHealth = maxHp;
		this.dmg = 3;
		this.z = -100;
		this.speed = normalSpeed;
		this.dmg = 3;
		this.image = RenderableHolder.GERight;
	}

	

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		switch (direction) {
		case "right":
			if (currentState == "default")
				image = RenderableHolder.GERight;

			else {
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.GERightWalk;
				} else
					
					image = RenderableHolder.GERightWalk2;
			}
			break;
		case "left":
			if (currentState == "default")
				image = RenderableHolder.GELeft;
			else {
				if (gameLogic.getCounter() / 10 % 2 == 1) {
					image = RenderableHolder.GELeftWalk;
				} else
					image = RenderableHolder.GELeftWalk2;
			}
			break;
		}
		gc.drawImage(image, screenX, screenY);
		drawHitbox(gc);
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if (playerfound(500)) 
			currentState = "attacking";
		else
			currentState = "default";
		Player player = gameLogic.getPlayer();
		canAttack = canAttack(player.worldX, player.worldY, worldX+radius, worldY+radius,
				128);
		if (currentState=="attacking") {	
			if (delay==0) {
				speed = normalSpeed;			
				delay =120;
			}
			if (delay ==30) {
				speed = normalSpeed*3;
				RenderableHolder.griszlyEyeSound.play(0.1);
				xspeed = Math.cos(angle) * speed;
				yspeed = Math.sin(angle) * speed;
			}
			
			if(delay>30) {			
				xspeed = Math.cos(angle) * speed;
				yspeed = Math.sin(angle) * speed;
				move();
		}
			else {
				worldX += xspeed;
				worldY += yspeed;
			}
				
			attack(gameLogic.getPlayer());
			delay--;
		}
	
		updateAttackBlock();
	}

		
	public void initSolidArea() {
		solidArea = new Rectangle(20, 0, 24, 32);

	}

	public void initAttackBlock() {
		attackBlock = new Rectangle(0,0,0,0);

	}
	
	


}
