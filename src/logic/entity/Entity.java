package logic.entity;

import Util.Vector;
import constant.Direction;
import constant.EntityState;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public abstract class Entity implements IRenderable {
	protected boolean attackState = false;
	protected int delay = 0;
	protected Vector<Double> worldPos;
	protected Vector<Double> screenPos;
	protected int z;
	protected boolean visible, destroyed;
	protected int speed;
	protected Direction direction;
	protected Image image;
	protected Rectangle solidArea, solidScreen;
	protected boolean collisionOn = false;
	protected GameLogic gameLogic;
	protected EntityState currentState = EntityState.DEFAULT;

	// Status
	protected int maxHp;
	protected int currentHealth;
	protected int dmg;

	// AttackBlock
	protected Rectangle attackBlock;

	public Entity(double x, double y, GameLogic gameLogic) {
		visible = true;
		destroyed = false;
		worldPos = new Vector<Double>(x, y);
		screenPos = new Vector<Double>(x, y);
		this.gameLogic = gameLogic;
		direction = Direction.RIGHT;
	}

	public abstract void attack(Entity t);

	public abstract void move();
	
	public void changeHealthTo(int health) {
		if (health >= maxHp) {
			currentHealth = maxHp;
		} else if (health <= 0) {
			currentHealth = 0;
		} else {
			currentHealth = health;

		}
	}

	public void update() {
		double screenX = worldPos.getX() - gameLogic.getPlayer().getWorldPos().getX()
				+ gameLogic.getPlayer().getScreenPos().getX();
		double screenY = worldPos.getY() - gameLogic.getPlayer().getWorldPos().getY()
				+ gameLogic.getPlayer().getScreenPos().getY();
		screenPos = new Vector<Double>(screenX, screenY);
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

	public Vector<Double> getWorldPos() {
		return worldPos;
	}

	public Vector<Double> getScreenPos() {
		return screenPos;
	}

	public Direction getDirection() {
		return direction;
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
	public boolean isAttackState() {
		return attackState;
	}

	public int getDelay() {
		return delay;
	}

	public Image getImage() {
		return image;
	}

	public Rectangle getSolidScreen() {
		return solidScreen;
	}

	public GameLogic getGameLogic() {
		return gameLogic;
	}

	public EntityState getCurrentState() {
		return currentState;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getDmg() {
		return dmg;
	}

	public Rectangle getAttackBlock() {
		return attackBlock;
	}

}
