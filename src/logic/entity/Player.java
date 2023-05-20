package logic.entity;

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
	private int statusBarX = (int) (10 * 1.5);
	private int statusBarY = (int) (10 * 1.5);

	// Bar Position
	private int healthBarWidth = (int) (144 * 1.5);
	private int healthBarHeight = (int) (4 * 2);
	private int healthBarX = (int) (30 * 1.5);
	private int healthBarY = (int) (11 * 1.5);

	private int manaBarWidth = (int) (97 * 1.5);
	private int manaBarHeight = (int) (2 * 2);
	private int manaBarX = (int) (42 * 1.5);
	private int manaBarY = (int) (30 * 1.5);

	// Status
	protected float healthWidth = healthBarWidth;
	protected int maxMana = 100;
	protected int currentMana = 0;
	protected float manaWidth = currentMana;
	protected int baseDamage = 1000;
	protected int iframe = 0;
	protected int duration = 0;

	int counter;
	private String lastDirection;

	public Player(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		this.speed = 5;
		z = -100;
		maxHp = 100;
		currentHealth = maxHp;
		dmg = baseDamage;
		screenX = gameLogic.getGameScreen().getWidth() / 2 - radius;
		screenY = gameLogic.getGameScreen().getHeight() / 2 - radius;
		image = RenderableHolder.playerRight;
		initSolidArea();
		initAttackBlock();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (direction =="left" ) {
			if (attackState)
				image = RenderableHolder.playerLeftAtk;
			else {
				image = RenderableHolder.playerLeft;
				if (currentState == "moving") {
					if (gameLogic.getCounter() / 10 % 2 == 1)
						image = RenderableHolder.playerLeftWalk;
				}
			}
		}
		else {
			if (attackState)
				image = RenderableHolder.playerRightAtk;
			else {
				image = RenderableHolder.playerRight;
				if (currentState == "moving") {
					if (gameLogic.getCounter() / 10 % 2 == 1)
						image = RenderableHolder.playerRightWalk;
				}
			}
		

		}
		gc.drawImage(image, screenX, screenY);
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
		gc.drawImage(RenderableHolder.healthBar, statusBarX, statusBarY, statusBarWidth, statusBarHeight);
		gc.setFill(Color.BLACK);
		gc.fillRect(healthBarX + statusBarX, healthBarY + statusBarY, healthWidth, healthBarHeight);
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(manaBarX + statusBarX, manaBarY + statusBarY, manaWidth, manaBarHeight);
	}

	public void drawAttackBlock(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.strokeRect(attackBlock.getX(), attackBlock.getY(), attackBlock.getWidth(), attackBlock.getHeight());
	}

	public void attack(Entity e) {
		Enemy enemy = (Enemy) e;
		System.out.println("Player Attack " + e.getClass().getSimpleName());
		enemy.changeHealthTo(enemy.getCurrentHealth() - dmg);
		System.out.println(dmg+" "+enemy.getCurrentHealth());
		changeManaTo(currentMana + 100);
	}

	public void skill() {
		RenderableHolder.playerSkill.play();
		duration = 10 * 60;
		speed = 10;
		dmg = 2*baseDamage;
		changeManaTo(currentMana - 100);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		solidScreen = new Rectangle(screenX + solidArea.getX(), screenY + solidArea.getY(), solidArea.getWidth(),
				solidArea.getHeight());
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
						attackBlock.intersects(x, y, width, height);
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
			speed = 5;
			dmg = baseDamage;
		}
	}

	public void updateHealthBar() {
		healthWidth = (float) (currentHealth / (float) maxHp) * healthBarWidth;
		manaWidth = (float) (currentMana / (float) maxMana) * manaBarWidth;
	}

	public void changeHealthTo(int health) {
		if (iframe == 0) {
			if (health >= maxHp) {
				currentHealth = maxHp;
			} else if (health <= 0) {
				currentHealth = 0;
//				GameOver();
			} else {
				currentHealth = health;
			}
			iframe = 30;
		}

	}

	public void move() {
		currentState = "default";
		if(InputUtility.getKeyPressed(KeyCode.W)||InputUtility.getKeyPressed(KeyCode.S)
				||InputUtility.getKeyPressed(KeyCode.D)||InputUtility.getKeyPressed(KeyCode.A))
			currentState = "moving";
		lastDirection = direction;
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			direction = "up";
		} else if (InputUtility.getKeyPressed(KeyCode.S)) 
			direction = "down";
		if (currentState == "moving") {
			setCollisionOn(false);
			gameLogic.checkTile(this);

			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				}
			}
		}
		
		if (InputUtility.getKeyPressed(KeyCode.D)) {
			direction = "right";
			lastDirection = direction;
		} else if (InputUtility.getKeyPressed(KeyCode.A)) {
			direction = "left";
		lastDirection = direction;}
		if (currentState == "moving") {
			setCollisionOn(false);
			gameLogic.checkTile(this);
			if (collisionOn == false) {
				switch (direction) {
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
		}
		direction = lastDirection;
	}

	public void initSolidArea() {
		solidArea = new Rectangle(16, 0, 32, 64);

	}

	public void initAttackBlock() {
		attackBlock = new Rectangle(screenX, screenY, solidArea.getWidth() + 20 * 2, 96);
	}

	public void updateAttackBlock() {
		if (direction == "right")
			attackBlock.setX(solidScreen.getX());
		else if (direction == "left")
			attackBlock.setX(solidScreen.getX() + solidScreen.getWidth() - attackBlock.getWidth());
		attackBlock.setY(screenY - 16);
	}

	public void GameOver() {
		System.exit(0);
	}

	public int getCurrentHealth() {
		return currentHealth;
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

	public void reset() {
		visible = true;
		destroyed = false;
		worldX = getWorldX();
		worldY = getWorldY();
		this.currentHealth = maxHp;
	}
}
