package logic.entity;

import Util.Vector;
import constant.Direction;
import constant.EntityState;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Player extends Entity {

	// Status Position
	private int statusBarWidth = (int) (180 * 1.5);
	private int statusBarHeight = (int) (38 * 2);
	private final Vector<Integer> statusBarPos = new Vector<Integer>((int) (10 * 1.5), (int) (10 * 1.5));

	// Bar Position
	private int healthBarWidth = (int) (144 * 1.5);
	private int healthBarHeight = (int) (4 * 2);
	private final Vector<Integer> healthBarPos = new Vector<Integer>((int) (30 * 1.5), (int) (11 * 1.5));

	private int manaBarWidth = (int) (97 * 1.5);
	private int manaBarHeight = (int) (2 * 2);
	private final Vector<Integer> manaBarPos = new Vector<Integer>((int) (42 * 1.5), (int) (30 * 1.5));

	// Status
	protected float healthWidth = healthBarWidth;
	protected int maxMana = 100;
	protected int currentMana = 0;
	protected float manaWidth = currentMana;
	protected int normalSpeed = 5;
	protected int baseDamage = 12;
	protected int iframe = 0;
	protected int duration = 0;
	private int regain = baseDamage;

	int counter;
	private Direction lastDirection;

	public Player(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		speed = normalSpeed;
		z = -100;
		maxHp = 210;
		currentHealth = maxHp;
		dmg = baseDamage;
		screenPos = new Vector<Double>(gameLogic.getGameScreen().getWidth() / 2 - 32,
				gameLogic.getGameScreen().getHeight() / 2 - 32);
		image = RenderableHolder.playerRight;
		initSolidArea();
		initAttackBlock();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (direction == Direction.LEFT) {
			if (attackState) {
				image = RenderableHolder.playerLeftAtk;
				if (delay > 10)
					gc.drawImage(RenderableHolder.slashLeft, attackBlock.getX(), attackBlock.getY());
			} else {
				image = RenderableHolder.playerLeft;
				if (currentState == EntityState.MOVING) {
					if (gameLogic.getCounter() / 10 % 2 == 1)
						image = RenderableHolder.playerLeftWalk;
				}
			}
		} else {
			if (attackState) {
				image = RenderableHolder.playerRightAtk;
				if (delay > 10)
					gc.drawImage(RenderableHolder.slashRight, attackBlock.getX() + solidArea.getWidth(),
							attackBlock.getY());
			} else {
				image = RenderableHolder.playerRight;
				if (currentState == EntityState.MOVING) {
					if (gameLogic.getCounter() / 10 % 2 == 1)
						image = RenderableHolder.playerRightWalk;
				}
			}

		}
		gc.drawImage(image, getScreenPos().getX(), getScreenPos().getY());
		drawUI(gc);

		// Debugging
//		drawHitbox(gc);
//		drawAttackBlock(gc);
	}

	public void drawHitbox(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.PINK);
		gc.strokeRect(solidScreen.getX(), solidScreen.getY(), solidScreen.getWidth(), solidScreen.getHeight());
	}

	public void drawUI(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.healthBar, statusBarPos.getX(), statusBarPos.getY(), statusBarWidth,
				statusBarHeight);
		gc.setFill(Color.BLACK);
		gc.fillRect(healthBarPos.getX() + statusBarPos.getX(), healthBarPos.getY() + statusBarPos.getY(), healthWidth,
				healthBarHeight);
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(manaBarPos.getX() + statusBarPos.getX(), manaBarPos.getY() + statusBarPos.getY(), manaWidth,
				manaBarHeight);
	}

	public void drawAttackBlock(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.strokeRect(attackBlock.getX(), attackBlock.getY(), attackBlock.getWidth(), attackBlock.getHeight());
	}

	public void attack(Entity e) {
		Enemy enemy = (Enemy) e;
		enemy.changeHealthTo(enemy.getCurrentHealth() - dmg);
		changeHealthTo(currentHealth + regain/5);
		changeManaTo(currentMana + 15);
//		System.out.println(dmg+" "+enemy.getCurrentHealth());
//		System.out.println("Player Attack " + e.getClass().getSimpleName());

	}

	public void skill() {
		RenderableHolder.playerSkill.play();
		duration = 5 * 60;
		speed = (normalSpeed*2);
		dmg = 2 * baseDamage;
		regain = 5*baseDamage;
		changeManaTo(currentMana - 100);
	}
	@Override
	public void changeHealthTo(int health) {
		if (iframe == 0) {
			super.changeHealthTo(health);
			iframe = 10;
		}

	}

	public void changeManaTo(int mana) {
		if (mana >= maxMana) {
			currentMana = maxMana;
		} else if (mana <= 0) {
			currentMana = 0;
		} else {
			currentMana = mana;
		}
		iframe = 30;

	}

	@Override
	public void update() {

		solidScreen = new Rectangle(getScreenPos().getX() + solidArea.getX(), getScreenPos().getY() + solidArea.getY(),
				solidArea.getWidth(), solidArea.getHeight());
		move();
		if (InputUtility.isLeftClickTriggered()) {

			if (delay == 0) {
				attackState = true;
				RenderableHolder.sword1.play(0.2);
				for (Entity entity : gameLogic.getGameObjectContainer()) {
					if ((entity instanceof Enemy)) {
						Enemy enemy = ((Enemy) entity);
						int x = (int) enemy.solidScreen.getX();
						int y = (int) enemy.solidScreen.getY();
						int width = (int) enemy.getSolidArea().getWidth();
						int height = (int) enemy.getSolidArea().getHeight();
						if (attackBlock.intersects(x, y, width, height)) {
							attack(entity);
						}
					}
				}
				delay = 20;
			}
		}
		if (InputUtility.getKeyPressed(KeyCode.SPACE) && currentMana >= 100) {
			skill();
			InputUtility.getKeyPressed().remove(KeyCode.SPACE);
		}

		updateHealthBar();
		updateAttackBlock();
		if (iframe > 0)
			iframe--;

		if (delay == 10)
			attackState = false;
		if (delay > 0)
			delay--;

		if (duration > 0)
			duration--;
		if (duration == 0) {
			speed = normalSpeed;
			dmg = baseDamage;
			regain = baseDamage;
		}
	}

	public void updateHealthBar() {
		healthWidth = (float) (currentHealth / (float) maxHp) * healthBarWidth;
		manaWidth = (float) (currentMana / (float) maxMana) * manaBarWidth;
	}

	public void move() {
		currentState = EntityState.DEFAULT;
		if (InputUtility.getKeyPressed(KeyCode.W) || InputUtility.getKeyPressed(KeyCode.S)
				|| InputUtility.getKeyPressed(KeyCode.D) || InputUtility.getKeyPressed(KeyCode.A))
			currentState = EntityState.MOVING;
		lastDirection = direction;
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			direction = Direction.UP;
		} else if (InputUtility.getKeyPressed(KeyCode.S))
			direction = Direction.DOWN;
		if (currentState == EntityState.MOVING) {
			setCollisionOn(false);
			gameLogic.getMap().checkTile(this);

			if (collisionOn == false) {
				switch (direction) {
				case UP:
					getWorldPos().setY(getWorldPos().getY() - speed);
					break;
				case DOWN:
					getWorldPos().setY(getWorldPos().getY() + speed);
					break;
				}
			}
		}

		if (InputUtility.getKeyPressed(KeyCode.D)) {
			direction = Direction.RIGHT;
			if (delay < 10)
				lastDirection = direction;
		} else if (InputUtility.getKeyPressed(KeyCode.A)) {
			direction = Direction.LEFT;
			if (delay < 10)
				lastDirection = direction;
		}
		if (currentState == EntityState.MOVING) {
			setCollisionOn(false);
			gameLogic.getMap().checkTile(this);
			if (collisionOn == false) {
				switch (direction) {
				case LEFT:
					getWorldPos().setX(getWorldPos().getX() - speed);
					break;
				case RIGHT:
					getWorldPos().setX(getWorldPos().getX() + speed);
					break;
				}
			}
		}
		direction = lastDirection;
	}

	public void initSolidArea() {
		solidArea = new Rectangle(16, 16, 32, 48);

	}

	public void initAttackBlock() {
		attackBlock = new Rectangle(getScreenPos().getX(), getScreenPos().getY(), solidArea.getWidth() + 48, 96);
	}

	public void updateAttackBlock() {
		if (direction == Direction.RIGHT)
			attackBlock.setX(solidScreen.getX());
		else if (direction == Direction.LEFT)
			attackBlock.setX(solidScreen.getX() + solidScreen.getWidth() - attackBlock.getWidth());
		attackBlock.setY(getScreenPos().getY() - 8);
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void reset() {
		visible = true;
		destroyed = false;
		worldPos = new Vector<Double>(getWorldPos().getX(), getWorldPos().getY());
		currentHealth = maxHp;
	}
}
