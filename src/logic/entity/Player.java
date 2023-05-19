package logic.entity;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Player extends Entity{
	
	
	private String attackState = "no";
	
	//Status Position
	private int statusBarWidth = (int) (180*1.5);
	private int statusBarHeight = (int) (38*2);
	private int statusBarX = (int) (10*1.5);
	private int statusBarY = (int) (10*1.5);
	
	//Bar Position 
	private int healthBarWidth = (int) (144*1.5);
	private int healthBarHeight = (int) (4*2);
	private int healthBarX = (int) (30*1.5);
	private int healthBarY = (int) (11*1.5);
	
	//Status
	protected int maxHp = 200;
	protected int currentHealth = maxHp;
	protected float healthWidth = healthBarWidth;
	protected int dmg = 10;
	protected int iframe = 0;
	
	//AttackBlock
	private Rectangle attackBlock;
	
	int counter;
	public Player(double x, double y,GameLogic gameLogic) {
		super(x,y,gameLogic);
		this.speed = 5;

		screenX = gameLogic.getGameScreen().getWidth()/2-radius;
		screenY = gameLogic.getGameScreen().getHeight()/2-radius;
		image = RenderableHolder.playerRight;
		initSolidArea();
		initAttackBlock();
	}
	@Override
	public void draw(GraphicsContext gc) {
		image = RenderableHolder.playerRight;
		// TODO Auto-generated method stub
		switch(direction) {
		case "left":
			image = (RenderableHolder.playerLeft);
			break;
		case "right":
			image = RenderableHolder.playerRight;
			break;

		}	
		gc.drawImage(image , screenX, screenY);
		drawUI(gc);
		
		//Debugging
		drawHitbox(gc);
		drawAttackBlock(gc);
	}
	
	public void drawHitbox(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.PINK);
		gc.strokeRect(solidScreen.getX(),solidScreen.getY(),solidScreen.getWidth(),solidScreen.getHeight());
	}
	
	public void drawUI(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.healthBar, statusBarX, statusBarY, statusBarWidth, statusBarHeight);
		gc.setFill(Color.BLACK);
		gc.fillRect(healthBarX+statusBarX,healthBarY+statusBarY,healthWidth,healthBarHeight);
	}
	
	public void drawAttackBlock(GraphicsContext gc){
		gc.setFill(Color.BLACK);
		gc.strokeRect(attackBlock.getX(), attackBlock.getY(), attackBlock.getWidth(),attackBlock.getHeight());
	}
	
	public void attack(Entity e) {
		
		attackState = "yes";
		Enemy enemy = (Enemy)e;
		System.out.println("Player Attack "+e.getClass().getSimpleName());
		enemy.changeHealthTo(enemy.getCurrentHealth()-dmg);
		
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		solidScreen = new Rectangle(screenX+solidArea.getX(),screenY+solidArea.getY(),solidArea.getWidth(),solidArea.getHeight());
		move();
		if (InputUtility.isLeftClickTriggered()) {
			for (Entity entity: gameLogic.getGameObjectContainer()) {
				if ((entity instanceof Enemy)) {
					Enemy enemy = ((Enemy)entity);
					int x = (int) enemy.solidScreen.getX();
					int y = (int) enemy.solidScreen.getY();
					int width = (int) enemy.getSolidArea().getWidth();
					int height = (int) enemy.getSolidArea().getHeight();
					 attackBlock.intersects(x,y,width,height);
					if (attackBlock.intersects(x,y,width,height)) {
						attack(entity);
					}
					if(enemy instanceof EyeOfQwifot) {
//						System.out.println(enemy.solidScreen.getWidth()+" "+enemy.solidScreen.getHeight());
					}
					
				}
			}
			
		}
		
		updateHealthBar();
		updateAttackBlock();
		if(iframe>0)iframe--;
	}
	
	public void updateHealthBar() {
		healthWidth = (float) (currentHealth/(float) maxHp) * healthBarWidth;
	}
	
	public void changeHealthTo(int health) {
		if(iframe == 0){
			if (health>=maxHp) {
				currentHealth = maxHp;
			}
			else if (health<=0) {
				currentHealth = 0;
//				GameOver();
			}
			else {
				currentHealth = health;
				System.out.println("Plathong" + currentHealth);
			}
			iframe = 30;
		}
		
	}
	
	public void move() {
		direction = "";
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			direction = "up";
		}
		if (InputUtility.getKeyPressed(KeyCode.S)) {
			direction = "down";
		}
			
		setCollisionOn(false);
		gameLogic.checkTile(this);
		
		if (collisionOn == false) {
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			}
		}
		
		if (InputUtility.getKeyPressed(KeyCode.D)) {
			direction = "right";
		}
		if (InputUtility.getKeyPressed(KeyCode.A)) {
			direction = "left";
		}
		
		setCollisionOn(false);
		gameLogic.checkTile(this);
		if (collisionOn == false) {
			switch(direction) {
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
	}
	public void initSolidArea() {
		solidArea = new Rectangle(16,0,32,64);

	}
	
	public void initAttackBlock() {
		attackBlock = new Rectangle(screenX,screenY,solidArea.getWidth()+20*2,96);
	}
	
	public void updateAttackBlock() {
		if (direction == "right")
			attackBlock.setX(solidScreen.getX());
		else if (direction == "left")
			attackBlock.setX(solidScreen.getX()+solidScreen.getWidth()-attackBlock.getWidth());
		attackBlock.setY(screenY-16);
	}
	
	public void GameOver() {
		System.exit(0);
	}
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public void reset() {		
		visible = true;
		destroyed = false;
		worldX = getWorldX();
		worldY = getWorldY();
		this.currentHealth = maxHp;
		}
}
