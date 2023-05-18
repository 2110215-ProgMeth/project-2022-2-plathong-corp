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
	protected int maxHp = 100;
	protected int currentHealth = maxHp;
	protected float healthWidth = healthBarWidth;
	protected int dmg = 5;
	protected int iframe = 0;
	
	//AttackBlock
	private Rectangle attackBlock;
	
	int counter;
	public Player(int x, int y,GameLogic gameLogic) {
		super(x,y,gameLogic);
		this.speed = 3;
		this.radius = 32;

		screenX = gameLogic.getGameScreen().getWidth()/2-radius;
		screenY = gameLogic.getGameScreen().getHeight()/2-radius;
		initSolidArea();
		initAttackBlock();
	}
	@Override
	public void draw(GraphicsContext gc) {
		int count = 0;
		Image playerImage = RenderableHolder.playerRight;
		// TODO Auto-generated method stub
		switch(direction) {
		case "left":
			playerImage = (RenderableHolder.playerLeft);
			break;
		case "right":
			playerImage = RenderableHolder.playerRight;
			break;

		}	
		gc.drawImage(playerImage , screenX, screenY);
		drawUI(gc);
		
		//Debugging
		drawHitbox(gc, 44, 64);
		drawAttackBlock(gc);
	}
	
	public void drawHitbox(GraphicsContext gc,int width , int height) {
		gc.setLineWidth(2);
		gc.setFill(Color.PINK);
		gc.strokeRect(screenX,screenY,width,height);
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
		System.out.println("Player Attack "+e.getClass().getSimpleName());
		((Enemy)e).changeHealthTo(((Enemy)e).getCurrentHealth()-dmg);
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
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
		if (InputUtility.isLeftClickTriggered()) {
			for (Entity entity: gameLogic.getGameObjectContainer()) {
				if ((entity instanceof Enemy)) {
					Enemy enemy = ((Enemy)entity);
					boolean canAtk = canAttack(worldX,worldY,enemy.getWorldX(),enemy.getWorldY(),(int) (solidArea.getWidth()+attackBlock.getWidth()));
					if (canAtk) {
						attack(entity);
					}
					if(enemy instanceof EyeOfQwifot) {
						System.out.println(enemy.solidScreen.getWidth()+" "+enemy.solidScreen.getHeight());
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
//		healthWidth = (int) (0.5 * healthBarWidth);
//		System.out.println(healthWidth);
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
	
	public void initSolidArea() {
		solidArea = new Rectangle(0,0,32,64);
	}
	
	public void initAttackBlock() {
		attackBlock = new Rectangle(worldX,worldY,20*2,20*2);
	}
	
	public void updateAttackBlock() {
		if (direction=="right") {
			attackBlock.setX(screenX+(int)solidArea.getWidth());
//			attackBlock.setLayoutX(0);
		}else if(direction=="left") {
			attackBlock.setX(screenX+(int)solidArea.getWidth()-(int)(30*2));
		}
		attackBlock.setY(screenY+(int)(10*2));
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
