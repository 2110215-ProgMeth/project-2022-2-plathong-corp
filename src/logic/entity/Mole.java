package logic.entity;

import Object.Ball;
import Util.Vector;
import constant.EntityState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
		if(currentState== EntityState.ATTACK) {
			if(rank=="DerKaiser") {
				gc.setFill(Color.BLACK);
				gc.setFont(new Font(15));
				gc.fillText("MoleDerKaiser", getScreenPos().getX(), getScreenPos().getY());
			}
			if( coolDown<20)
				gc.drawImage(animationImage, getScreenPos().getX(), getScreenPos().getY());
			else
				gc.drawImage(image, getScreenPos().getX(), getScreenPos().getY());
		}else if (currentState== EntityState.DEAD) {
			gc.drawImage(deadImage, getScreenPos().getX(), getScreenPos().getY());
		}
		else {
			if(coolDown<200)
				gc.drawImage(animationImage, getScreenPos().getX(), getScreenPos().getY());
		}

//		 drawHitbox(gc);
	}

	public void attack() {
    	gameLogic.addNewProjectile(new Ball(getWorldPos().getX()+solidArea.getX(), getWorldPos().getY()+solidArea.getY(), angle,gameLogic));
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if(currentState!= EntityState.DEAD) {
//		System.out.println(currentHealth);
		if (playerfound(800) && coolDown<=180)
			currentState = EntityState.ATTACK;
		else
			currentState = EntityState.DEFAULT;
		if (currentState == EntityState.ATTACK) {

			if (delay == 0) {
				attack();
				delay = 60;
			}
			delay--;
		}
		if(coolDown==0) {
			coolDown = 360;
//			System.out.println("yoooo");
			currentState = EntityState.DEFAULT;
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
		worldPos = new Vector<Double>(x+ (int)(Math.random()*width), y+ (int)(Math.random()*height));
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
