package logic.entity;

import Object.Ball;
import Object.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Mole extends Enemy{
	protected String rank;
	private int coolDown = 360;
	private int height ,width;
	private double x,y;
	private Image deadImage,animationImage;
	public Mole(double x, double y, GameLogic gameLogic,String rank,int width,int height) {
		super(x+(int)(Math.random()*width),  y+ (int)(Math.random()*height), gameLogic);
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		this.maxHp = 300;
		this.currentHealth = maxHp;
		this.z = -100;
		this.rank = rank;
		if (rank == "DerKaiser") {
			image = RenderableHolder.moleDerKaiser;
			deadImage = RenderableHolder.moleDerKaiserDead;
			animationImage = RenderableHolder.moleDerKaiser1;
		} else {
			image = RenderableHolder.mole;
			deadImage = RenderableHolder.moleDead;
			animationImage = RenderableHolder.mole1;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(currentState=="attacking") {
			if(rank=="DerKaiser") {
				gc.setFill(Color.BLACK);
				gc.fillText("MoleDerKaiser", screenX, screenY);
			}
			if( coolDown<20)
				gc.drawImage(animationImage, screenX, screenY);
			else
				gc.drawImage(image, screenX, screenY);
		}else if (currentState=="dead") {
			gc.drawImage(deadImage, screenX, screenY);
		}
		else {
			if(coolDown<200)
				gc.drawImage(animationImage, screenX,screenY);
		}

//		 drawHitbox(gc);
	}

	public void attack() {
    	gameLogic.addNewProjectile(new Ball(worldX+solidArea.getX(), worldY+solidArea.getY(), angle,gameLogic));
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if(currentState!="dead") {
//		System.out.println(currentHealth);
		if (playerfound(800) && coolDown<=180)
			currentState = "attacking";
		else
			currentState = "default";
		if (currentState == "attacking") {

			if (delay == 0) {
				attack();
				delay = 60;
			}
			delay--;
		}
		if(coolDown==0) {
			coolDown = 360;
			System.out.println("yoooo");
			currentState = "default";
			move();
		}
		coolDown--;
		}
	}
	@Override
	public void initSolidArea() {
		solidArea = new Rectangle(16, 8, 32, 48);
	}
	@Override
	public void move() {
		worldX = x+ (int)(Math.random()*width);
		worldY = y+ (int)(Math.random()*height);
//		System.out.println(worldX+" "+worldY);
	}
	@Override
	public void changeHealthTo(int health) {
	
		}
	@Override
	public void initAttackBlock() {
		attackBlock = new Rectangle(0,0,0,0);
	}

}
