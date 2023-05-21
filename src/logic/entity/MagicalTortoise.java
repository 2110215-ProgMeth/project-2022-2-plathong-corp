package logic.entity;

import java.util.ArrayList;

import constant.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class MagicalTortoise extends Entity {
	private Image image = RenderableHolder.mTRight1;
	protected ArrayList<String> dialogues;

	public MagicalTortoise(double x, double y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		this.dialogues = new ArrayList<String>();
		z = -100;
		// TODO Auto-generated constructor stub
		setDialogues();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		switch (direction) {
		case RIGHT:
			if (gameLogic.getCounter() / 10 % 2 == 1)
				image = RenderableHolder.mTRight1;

			else
				image = RenderableHolder.mTRight2;
			break;
		case LEFT:
			if (gameLogic.getCounter() / 10 % 2 == 1)
				image = RenderableHolder.mTLeft1;

			else
				image = RenderableHolder.mTLeft2;

			break;
		}
		gc.drawImage(image, getScreenPos().getX(), getScreenPos().getY());

	}

	@Override
	public void attack(Entity p) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		Player player = gameLogic.getPlayer();
		double xDirection = Math.cos(Math.atan2(player.getWorldPos().getY() - getWorldPos().getY(),
				player.getWorldPos().getX() - getWorldPos().getX()));
		if (xDirection < 0)
			direction = Direction.LEFT;
		else
			direction = Direction.RIGHT;
	}

	public void setDialogues() {
		dialogues.add("Goodluck Traveller!");
		dialogues.add("Hi , I'm Magical Tortoise");
		dialogues.add("You would be \"THE TRAVELLER\" \n in my prophecy");
		dialogues.add("This world will collapse soon");
		dialogues.add("Everything will be colorless");
		dialogues.add("People have no emotion");
		dialogues.add("You are the only one");
		dialogues.add("Please, save this world");
		dialogues.add("For the color of the world");
		dialogues.add("For the emotion of people");
		dialogues.add("Please, get rid of all 3 inverders");
		dialogues.add("The Eye of QWIFOT");
		dialogues.add("MoleDerKaiser");
		dialogues.add("LlaristicKnight");
	}

	public boolean playerfound() {
		int rangeX = (int) Math.abs(getWorldPos().getX() - gameLogic.getPlayer().getWorldPos().getX());
		int rangeY = (int) Math.abs(getWorldPos().getY() - gameLogic.getPlayer().getWorldPos().getY());
		int range = (int) Math.sqrt(Math.pow(rangeX, 2) + Math.pow(rangeY, 2));
		return range < 100;
	}

	public String getDialogues(int i) {
		return dialogues.get(i);
	}

	public int getDialoguesSize() {
		return dialogues.size();
	}

	public void speak() {

	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}
}
