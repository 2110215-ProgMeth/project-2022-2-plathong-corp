package logic.entity;

import Object.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Mole extends Enemy{
	protected String rank;
	private int coolDown = 360;
	private int height ,width;
	private double x,y;
	private Image deadImage;
	public Mole(double x, double y, GameLogic gameLogic,String rank,int width,int height) {
		super(x+(int)(Math.random()*width),  y+ (int)(Math.random()*height), gameLogic);
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		this.maxHp = 100;
		this.currentHealth = maxHp;
		this.z = -100;
		this.rank = rank;
		if (rank == "DerKaiser") {
			image = RenderableHolder.moleDerKaiser;
			deadImage = RenderableHolder.moleDerKaiserDead;
		} else {
			image = RenderableHolder.mole;
			deadImage = RenderableHolder.moleDead;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(currentState=="attacking")
			gc.drawImage(image, screenX, screenY);
		else if (currentState=="dead") {
			gc.drawImage(deadImage, screenX, screenY);
		}

		 drawHitbox(gc);
	}

	public void attack() {
		Projectile projectile = new Projectile(worldX+solidArea.getX(), worldY+solidArea.getY(), angle,gameLogic);
    	gameLogic.addNewProjectile(projectile);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if(currentState!="dead") {
		System.out.println(currentHealth);
		if (playerfound(800) && coolDown<180)
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
		System.out.println(worldX+" "+worldY);
	}
	@Override
	public void changeHealthTo(int health) {
	
		}
	@Override
	public void initAttackBlock() {
		attackBlock = new Rectangle(0,0,0,0);
	}

}
