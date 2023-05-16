package logic.entity;

import javafx.scene.shape.Rectangle;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable{
	protected double worldX,worldY;
	public double screenX,screenY;
	protected int z,radius;
	protected boolean visible,destroyed;
	protected int speed;
	protected String direction;
	public Rectangle solidArea;
	public boolean collisionOn = false;
	
	protected Entity(){
		visible = true;
		destroyed = false;
	}
	
	public abstract void attack();
	
	public boolean canAttack(double x1,double y1,double x2,double y2,int attackRange) {
		return (Math.abs(x1-x2) < attackRange && Math.abs(y1-y2) < attackRange);
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
}
