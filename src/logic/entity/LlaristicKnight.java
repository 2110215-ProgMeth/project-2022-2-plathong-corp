package logic.entity;

import Object.SwordBeam;
import constant.Direction;
import constant.EntityState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class LlaristicKnight extends Enemy{

	protected Rectangle normalAttackBlock;
	private double probablility = 0.7;
	private int xExtra,yExtra;
	private int normalSpeed = 1;
	public LlaristicKnight(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
        maxHp = 300;
        currentHealth = maxHp;
        z = -100;
        dmg = 20;
        speed = normalSpeed;
        image = RenderableHolder.lKRight1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(currentState == EntityState.DEAD)
			image = RenderableHolder.lKDead;
		else {
		switch (direction) {
		case RIGHT:
			if(currentState == EntityState.ATTACK) {

					if(delay>40)
						image = RenderableHolder.lKRightAtk;
					else {
						if (delay / 10 % 2 == 1)
							image = RenderableHolder.lKRight2;
						else
							image = RenderableHolder.lKRight1;
					}

			}
			else
				image = RenderableHolder.lKRight1;
			break;
		case LEFT:
			if (currentState == EntityState.ATTACK) {
					if (delay > 40)
						image = RenderableHolder.lKLeftAtk;
					else {
						if (delay / 10 % 2 == 1)
							image = RenderableHolder.lKLeft2;
						else
							image = RenderableHolder.lKLeft1;
					}

			} else
				image = RenderableHolder.lKLeft1;
			break;
		}
		}

		gc.drawImage(image, screenX, screenY);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(15));
		gc.fillText("LlaristicKnight", screenX, screenY);
//		drawHitbox(gc);
//		drawAttackBlock(gc);
	}

	@Override
	public void update() {
		super.update();
		if(currentState!=EntityState.DEAD) {
		if (playerfound(600)) {
			currentState = EntityState.ATTACK;
			if(gameLogic.getGameSong()!=RenderableHolder.llaristicTheme) {
				gameLogic.getGameSong().stop();
				gameLogic.setGameSong(RenderableHolder.llaristicTheme);
			}
		}
		else {
			currentState = EntityState.DEFAULT;
			
			if(gameLogic.getGameSong()!=RenderableHolder.inGameSong) {
				gameLogic.getGameSong().stop();
				gameLogic.setGameSong(RenderableHolder.inGameSong);
			}
		}
		Player player = gameLogic.getPlayer();
		canAttack = canAttack(player.solidScreen.getX() + solidScreen.getWidth() / 2,
				player.solidScreen.getY() + solidScreen.getHeight() / 2,
				solidScreen.getX() + solidScreen.getWidth() / 2, solidScreen.getY() + solidScreen.getHeight() / 2, 100);
		if (currentState == EntityState.ATTACK) {
			if(delay==0) {
				delay = 60;
				attack(player);
				RenderableHolder.katana.play(0.2);
				gameLogic.addNewProjectile(new SwordBeam(worldX, worldY, angle, gameLogic));
			}	
			else if (delay == 30 && canAttack == ( Math.random()>probablility)) {
				specialMove();
			}
			move();
		}	
		if(delay >0) delay--;
		}
		updateAttackBlock();
	}
	
	public void specialMove() {
		speed = (int) (normalSpeed * 500 * Math.random());
		move();
		speed = normalSpeed;
	}
	
	@Override
	public void move() {
		xspeed = Math.cos(angle) * speed;
		yspeed = Math.sin(angle) * speed;
		if(xspeed>0)
			direction = Direction.RIGHT;
		else
			direction = Direction.LEFT;
		if(worldX+xspeed<64)
			worldX = 64;
		else if (worldX+xspeed>64*63)
			worldX = 64*63;
		else worldX += xspeed;
		if(worldY+yspeed<64)
			worldY = 64;
		else if (worldY+yspeed>64*47)
			worldY = 64*47;
		else worldY += yspeed;
	}
	@Override
	public void initSolidArea() {
		// TODO Auto-generated method stub
		solidArea = new Rectangle(20, 0, 24, 64);
	}

	@Override
	public void initAttackBlock() {
		// TODO Auto-generated method stub
		attackBlock = new Rectangle(0,0, solidArea.getWidth()+ 60, 64);
	}

	@Override
	public void changeHealthTo(int health) {
		System.out.println(health+" "+getCurrentHealth());
		if (health>=maxHp) {
			currentHealth = maxHp;
		}
		else if (health<=0) {
			currentHealth = 0;
			currentState = EntityState.DEAD;
		}
		else {
			currentHealth = health;
		}
	}
}