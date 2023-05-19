package logic.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;

public abstract class Enemy extends Entity{
	
	protected double angle = 0;
	protected String currentState;
	protected double delay = 0;

	public Enemy(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		// TODO Auto-generated constructor stub
	}
	
	public void changeHealthTo(int health) {
		if (health>=maxHp) {
			currentHealth = maxHp;
		}
		else if (health<=0) {
			currentHealth = 0;
			setDestroyed(true);
		}
		else {
			currentHealth = health;
//			System.out.println("Plathong" + currentHealth);
		}
	}
	public boolean checkEnemyHit() {
		Player p =gameLogic.getPlayer();
		int x = (int) p.getScreenX();
		int y = (int) p.getScreenY();
		int width = (int) p.getSolidArea().getWidth();
		int height = (int) p.getSolidArea().getHeight();
		boolean overlap = solidScreen.intersects(x,y,width,height);
//		System.out.println("Overlap = " + overlap);
//		System.out.println("X = " + x + " Y = " + y);
		return overlap;
		
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public void attack(Entity p) {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getSimpleName()+"Attack");
		if (checkEnemyHit()) ((Player) p).changeHealthTo(gameLogic.getPlayer().getCurrentHealth()-dmg);
	}
	
	//Debugger
	public void drawHitbox(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.PINK);
		gc.strokeRect(solidScreen.getX(), solidScreen.getY(), solidScreen.getWidth(), solidScreen.getHeight());
	}

	public void drawAttackBlock(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.strokeRect(solidArea.getX(), attackBlock.getY(), attackBlock.getWidth(),
				attackBlock.getHeight());
	}
	
	public void updateAttackBlock() {
		attackBlock.setX(screenX);
		attackBlock.setY(screenY);
	}
	
	public void update() {
		super.update();
//		System.out.println(getClass().getSimpleName()+playerfound());
		solidScreen = new Rectangle(screenX+solidArea.getX(),screenY+solidArea.getY(),solidArea.getWidth(),solidArea.getHeight());
	}
	
	public void reset() {		
		visible = true;
		destroyed = false;
		worldX = getWorldX();
		worldY = getWorldY();
		this.currentHealth = maxHp;
		this.direction = "right";
		}
	
	public boolean playerfound() {
		int rangeX = (int) Math.abs(worldX-gameLogic.getPlayer().getWorldX());
		int rangeY = (int) Math.abs(worldY-gameLogic.getPlayer().getWorldY());
		int range = (int) Math.sqrt(Math.pow(rangeX, 2) + Math.pow(rangeY, 2));
		if (this instanceof Chicknight) {
//			System.out.println("X = " + worldX +" Y = " + worldY);
//			System.out.println("X = " + gameLogic.getPlayer().getWorldX() +" Y = " + gameLogic.getPlayer().getWorldY() + "Range =" +range);
		}
		return range<300;
	}
	
	public abstract void initSolidArea();
	public abstract void initAttackBlock();
}