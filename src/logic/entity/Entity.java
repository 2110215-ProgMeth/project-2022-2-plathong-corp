package logic.entity;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {
	protected double worldX, worldY;
	public double screenX, screenY;
	protected int z, radius;
	protected boolean visible, destroyed;
	protected int speed;
	protected String direction;
	protected Image image;
	public Rectangle solidArea,solidScreen;
	public boolean collisionOn = false;
	public GameLogic gameLogic;

	// Status
	protected int maxHp;
	protected int currentHealth;
	protected int dmg;

	// AttackBlock
	protected Rectangle attackBlock;

	public Entity(double x, double y, GameLogic gameLogic) {
		visible = true;
		destroyed = false;
		worldX = x;
		worldY = y;
		this.gameLogic = gameLogic;
		this.direction = "right";
		radius =32;
	}

	public abstract void attack(Entity t);

	public void update() {
		screenX = worldX - gameLogic.getPlayer().worldX + gameLogic.getPlayer().screenX;
        screenY = worldY - gameLogic.getPlayer().worldY + gameLogic.getPlayer().screenY;
        
	}
	public boolean canAttack(double x1, double y1, double x2, double y2, int attackRange) {
		return (Math.abs(x1 - x2) < attackRange && Math.abs(y1 - y2) < attackRange);
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return destroyed;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible;
	}

	public double getWorldX() {
		return worldX;
	}

	public double getWorldY() {
		return worldY;
	}

	public String getDirection() {
		return direction;
	}

	public double getScreenX() {
		return screenX;
	}

	public double getScreenY() {
		return screenY;
	}

	public int getRadius() {
		return radius;
	}

	public int getSpeed() {
		return speed;
	}

	public Rectangle getSolidArea() {
		return solidArea;
	}

	public boolean isCollisionOn() {
		return collisionOn;
	}

	public void setCollisionOn(boolean collisionOn) {
		this.collisionOn = collisionOn;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

}
