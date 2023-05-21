package logic.entity;

import constant.Direction;
import constant.EntityState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public abstract class Enemy extends Entity {
	protected double angle = 0;
	protected boolean canAttack;
	protected double xspeed, yspeed;

	public Enemy(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		initSolidArea();
		initAttackBlock();
		// TODO Auto-generated constructor stub
	}

	public void attack(Entity p) {
		// TODO Auto-generated method stub
		if (checkEnemyHit()) {
			((Player) p).changeHealthTo(gameLogic.getPlayer().getCurrentHealth() - dmg);
		}
	}
	
	public boolean checkEnemyHit() {
		Player p = gameLogic.getPlayer();
		int x = (int) p.getScreenX();
		int y = (int) p.getScreenY();
		int width = (int) p.getSolidArea().getWidth();
		int height = (int) p.getSolidArea().getHeight();
		return solidScreen.intersects(x, y, width, height) || attackBlock.intersects(x, y, width, height);

	}

	public void changeHealthTo(int health) {
		if (health >= maxHp) {
			currentHealth = maxHp;
		} else if (health <= 0) {
			RenderableHolder.monsterdie.play(0.2);
			currentHealth = 0;
			setDestroyed(true);
		} else {
			currentHealth = health;

		}
	}

	public void updateAttackBlock() {
		if (direction == Direction.RIGHT)
			attackBlock.setX(solidScreen.getX());
		else if (direction == Direction.LEFT)
			attackBlock.setX(solidScreen.getX() + solidScreen.getWidth() - attackBlock.getWidth());
		attackBlock.setY(screenY);
	}

	public void update() {
		super.update();
		solidScreen = new Rectangle(screenX + solidArea.getX(), screenY + solidArea.getY(), solidArea.getWidth(),
				solidArea.getHeight());
		Player player = gameLogic.getPlayer();
		angle = Math.atan2(player.worldY - worldY, player.worldX - worldX);
	}

	public void move() {
		xspeed = Math.cos(angle) * speed;
		yspeed = Math.sin(angle) * speed;

		if (yspeed < 0)
			direction = Direction.UP;
		else
			direction = Direction.DOWN;
		setCollisionOn(false);
		gameLogic.checkTile(this);
		if (collisionOn == false) {
			worldY += yspeed;
		}

		if (xspeed < 0)
			direction = Direction.LEFT;
		else
			direction = Direction.RIGHT;

		setCollisionOn(false);
		gameLogic.checkTile(this);
		if (collisionOn == false) {
			worldX += xspeed;
		}
	}

	public void reset() {
		visible = true;
		destroyed = false;
		worldX = getWorldX();
		worldY = getWorldY();
		this.currentHealth = maxHp;
		this.direction = Direction.RIGHT;
	}

	public boolean playerfound(int range) {
		int rangeX = (int) Math.abs(worldX - gameLogic.getPlayer().getWorldX());
		int rangeY = (int) Math.abs(worldY - gameLogic.getPlayer().getWorldY());
		return (int) Math.sqrt(Math.pow(rangeX, 2) + Math.pow(rangeY, 2)) < range;
	}

	public EntityState getCurrentState() {
		return currentState;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public boolean isDead() {
		return currentState == EntityState.DEAD;
	}
	
	// Debugger
		public void drawHitbox(GraphicsContext gc) {
			gc.setLineWidth(2);
			gc.setFill(Color.PINK);
			gc.strokeRect(solidScreen.getX(), solidScreen.getY(), solidScreen.getWidth(), solidScreen.getHeight());
		}

		public void drawAttackBlock(GraphicsContext gc) {
			gc.setFill(Color.BLACK);
			gc.strokeRect(attackBlock.getX(), attackBlock.getY(), attackBlock.getWidth(), attackBlock.getHeight());
		}
	
	public abstract void initSolidArea();

	public abstract void initAttackBlock();
}