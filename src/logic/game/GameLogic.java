package logic.game;

import java.util.ArrayList;
import java.util.List;

import MainMenu.GameOverButton;
import Object.Projectile;
import application.Main;
import drawing.GameScreen;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.entity.Chicknight;
import logic.entity.Enemy;
import logic.entity.Entity;
import logic.entity.EyeOfQwifot;
import logic.entity.GriszlyEye;
import logic.entity.LlaristicKnight;
import logic.entity.MagicalTortoise;
import logic.entity.Mole;
import logic.entity.MoleDerKaiser;
import logic.entity.Player;
import logic.entity.ShadowPot;
import logic.field.Map1;
import sharedObject.RenderableHolder;

public class GameLogic {
	private ArrayList<Entity> gameObjectContainer;
	private ArrayList<Projectile> projectilesContainer;
	private static int counter = 0;

	private GameScreen gameScreen;
	private Player player;
	private EyeOfQwifot eQ;
	private Map1 map;
	private MagicalTortoise mT;
	private MoleDerKaiser mDK;
	private LlaristicKnight LN;
	// GameState
	public int gameState = 1;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int npcState = 3;
	public final int gameOverState = 4;
	public final int winState = 5;

	public int dialogueIndex = 0;
	private boolean isFinish = true;
	private int timeStamp;

	public GameLogic(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		startNewGame();

	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	public void addNewProjectile(Projectile p) {
		projectilesContainer.add(p);
		RenderableHolder.getInstance().add(p);
	}

	public void startNewGame() {
		RenderableHolder.inGameSong.play();
		this.gameObjectContainer = new ArrayList<Entity>();
		this.projectilesContainer = new ArrayList<Projectile>();
		RenderableHolder.getInstance().getEntities().clear();
		map = new Map1(this);
		RenderableHolder.getInstance().add(map);

		player = new Player(1920, 1444, this);
		eQ = new EyeOfQwifot(3456, 512, this);
		mT = new MagicalTortoise(2040, 1444, this);
		LN = new LlaristicKnight(8 * 64, 10 * 64, this);
		addNewObject(player);
		addNewObject(new Chicknight(3000, 200, this));
		for (int i = 0; i < 50; i++) {
			double pointX = Math.random();
			double pointY = Math.random();
			boolean spawnPoint = ((pointX > 0.4 && pointX < 0.6) || (pointY > 0.4 && pointY < 0.6));
			while (pointX < 0.05 || pointX > 0.95 || pointY < 0.05 || pointY > 0.95 || spawnPoint) {
				pointX = Math.random();
				pointY = Math.random();
				spawnPoint = ((pointX > 0.4 && pointX < 0.6) || (pointY > 0.4 && pointY < 0.6));
			}
			addNewObject(new Chicknight(pointX * 64 * 64, pointY * 64 * 48, this));
		}

		for (int i = 0; i < 20; i++) {
			double pointX = Math.random();
			double pointY = Math.random();
			boolean spawnPoint = ((pointX > 0.4 && pointX < 0.6) || (pointY > 0.4 && pointY < 0.6));
			while (pointX < 0.05 || pointX > 0.95 || pointY < 0.05 || pointY > 0.95 || spawnPoint) {
				pointX = Math.random();
				pointY = Math.random();
				spawnPoint = ((pointX > 0.4 && pointX < 0.6) || (pointY > 0.4 && pointY < 0.6));
			}
			addNewObject(new ShadowPot(pointX * 64 * 64, pointY * 64 * 48, this));
		}
		addNewObject(mT);

		addNewObject(LN);
		addNewObject(new GriszlyEye(3000, 200, this));
		addNewObject(new ShadowPot(3000, 500, this));
		addNewObject(eQ);
		mDK = new MoleDerKaiser(1800, 64 * 37, this, 448, 448);
		addNewObject(mDK);
		for (Mole m : mDK.getMoles()) {
			addNewObject(m);
		}
		gameState = playState;
		System.out.println("New Game");

	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = (int) (entity.getWorldX() + entity.solidArea.getX());
		int entityRightWorldX = (int) (entity.getWorldX() + entity.solidArea.getX() + entity.solidArea.getWidth());
		int entityTopWorldY = (int) (entity.getWorldY() + entity.solidArea.getY());
		int entityBottomWorldY = (int) (entity.getWorldY() + entity.solidArea.getY() + entity.solidArea.getHeight());

		int entityLeftCol = entityLeftWorldX / map.getTileSize();
		int entityRightCol = entityRightWorldX / map.getTileSize();
		int entityTopRow = entityTopWorldY / map.getTileSize();
		int entityBottomRow = entityBottomWorldY / map.getTileSize();

		int tile1 = 0;
		int tile2 = 0;

		switch (entity.getDirection()) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.getSpeed()) / map.getTileSize();
			tile1 = map.getTileIndex(entityLeftCol, entityTopRow);
			tile2 = map.getTileIndex(entityRightCol, entityTopRow);
			if (map.getTiles()[tile1].collision == true || map.getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / map.getTileSize();
			tile1 = map.getTileIndex(entityLeftCol, entityBottomRow);
			tile2 = map.getTileIndex(entityRightCol, entityBottomRow);
			if (map.getTiles()[tile1].collision == true || map.getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / map.getTileSize();
			tile1 = map.getTileIndex(entityLeftCol, entityTopRow);
			tile2 = map.getTileIndex(entityLeftCol, entityBottomRow);
			if (map.getTiles()[tile1].collision == true || map.getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.getSpeed()) / map.getTileSize();
			tile1 = map.getTileIndex(entityRightCol, entityTopRow);
			tile2 = map.getTileIndex(entityRightCol, entityBottomRow);
			if (map.getTiles()[tile1].collision == true || map.getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		}

	}

	public void update() {
//		System.out.println(gameState);
		if (!RenderableHolder.inGameSong.isPlaying()) {
			RenderableHolder.inGameSong.play();
		}
		if (gameState == playState) {
			logicUpdate();
			gameScreen.paintComponent();
		} else if (gameState == pauseState) {
			drawGamePauseOverlay();
			if (InputUtility.getKeyPressed(KeyCode.M)) {
				Main.GoToMenu();
				RenderableHolder.inGameSong.stop();
				InputUtility.getKeyPressed().remove(KeyCode.M);
			}
//			System.out.println(500);
		} else if (gameState == gameOverState) {
			drawGameOverOverlay();
			RenderableHolder.inGameSong.stop();
			if (InputUtility.getKeyPressed(KeyCode.R)) {
				startNewGame();
//				reset();
			} else if (InputUtility.getKeyPressed(KeyCode.M)) {
				InputUtility.getKeyPressed().remove(KeyCode.M);
				Main.GoToMenu();
			}
		} else if (gameState == npcState) {
			if (!getMagicalTortoise().playerfound()) {

				gameState = playState;
				dialogueIndex = 0;
			}
			logicUpdate();
			gameScreen.paintComponent();
			drawDialogueScreen(dialogueIndex);
			if (InputUtility.isLeftClickTriggered()) {
				dialogueIndex++;
				RenderableHolder.npcSound.play(0.2);
			}
			if (getMagicalTortoise().getDialoguesSize() - 1 < dialogueIndex)
				dialogueIndex = 0;
		} else if (gameState == winState) {
			RenderableHolder.inGameSong.stop();
			if (isFinish) {
				timeStamp = Main.second;
				isFinish = false;
				
			}
			drawWinGameOvelay(timeStamp);
			if (InputUtility.getKeyPressed(KeyCode.M)) {
				Main.GoToMenu();
				InputUtility.getKeyPressed().remove(KeyCode.M);
				RenderableHolder.inGameSong.stop();
			}
//			System.out.println(Main.second);
		}
	}

	public void logicUpdate() {
		if (counter == 60) {
			counter = 0;
		}
		ArrayList<Entity> objectContainer = getGameObjectContainer();
		for (int i = 0; i < objectContainer.size(); i++) {
			(objectContainer.get(i)).update();
			if (objectContainer.get(i).isDestroyed())
				getGameObjectContainer().remove(objectContainer.get(i));
		}

		ArrayList<Projectile> projectileContainer = getProjectileContainer();
		for (int i = 0; i < projectileContainer.size(); i++) {
			(projectileContainer.get(i)).update();
			if (projectileContainer.get(i).isDestroyed())
				getProjectileContainer().remove(projectileContainer.get(i));
		}
	}

	public MagicalTortoise getMagicalTortoise() {
		return mT;
	}

	public void drawDialogueScreen(int i) {
		GraphicsContext gc = getGameScreen().getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRoundRect(390, 100, 500, 200, 35, 35);
		gc.setStroke(Color.WHITE);
		gc.strokeRoundRect(390, 100, 500, 200, 35, 35);
		gc.setFont(Font.font(null, FontWeight.LIGHT, 32));

		gc.setFill(Color.WHITE);
		gc.fillText(getMagicalTortoise().getDialogues(i), 420, 150);

	}

	public void drawGamePauseOverlay() {
		GraphicsContext gc = getGameScreen().getGraphicsContext2D();
		gc.drawImage(RenderableHolder.pauseOverlay, 480, 252);
	}

	public void drawGameOverOverlay() {
		GameOverButton retry = new GameOverButton((int) (1280 / 3.5), (int) (720 / 1.5), 200, 40, "RETRY(R)");
		GameOverButton mainMenu = new GameOverButton((int) (1280 / 3.5) + 320, (int) (720 / 1.5), 200, 40, " Menu(M)");
		GraphicsContext gc = getGameScreen().getGraphicsContext2D();
		gc.drawImage(RenderableHolder.gameOverOverlay, 0, 0);
		retry.draw(gc, Color.BLACK, Color.BLACK);
		mainMenu.draw(gc, Color.BLACK, Color.BLACK);
	}

	public void drawWinGameOvelay(int time) {
		// TODO Auto-generated method stub
		GameOverButton mainMenu = new GameOverButton((int) (1280 / 3.5) + 480, (int) (720 / 1.5), 200, 40, " Menu(M)");
		GraphicsContext gc = getGameScreen().getGraphicsContext2D();
		gc.drawImage(RenderableHolder.wingameOverlay, 0, 0);
		gc.fillText("Finish Time : " + time + " seconds", (int) (1280 / 3.5) + 420, (int) (720 / 1.7));
		mainMenu.draw(gc, Color.WHITE, Color.WHITE);

	}

	public Player getPlayer() {
		return player;
	}

	public int getCounter() {
		return counter;
	}

	public void count() {
		counter++;
		if (counter % 10 == 0) {
//			System.out.println(counter);
		}
	}

	public void checkGameState() {
		if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {

			if (gameState == playState) {
				gameState = pauseState;

			} else if (gameState == pauseState) {
				gameState = playState;
			}
			InputUtility.getKeyPressed().remove(KeyCode.ESCAPE);
		}

		if (InputUtility.isLeftClickTriggered() && getMagicalTortoise().playerfound()) {
			int start = 0;
			if (gameState == playState) {
				gameState = npcState;

			} else if (gameState == npcState) {
				int total = getMagicalTortoise().getDialoguesSize() - 1;
				if (start < total) {
					drawDialogueScreen(start);
					start += 1;
				} else {
					gameState = playState;
				}

			}

		}
		if (getPlayer().getCurrentHealth() == 0) {
			gameState = gameOverState;
		}

		boolean win = LN.getCurrentState() == "dead" && eQ.getCurrentState() == "dead"
				&& mDK.getCurrentState() == "dead";
		if (win)
			gameState = winState;
	}

	public ArrayList<Entity> getGameObjectContainer() {
		return gameObjectContainer;
	}

	public ArrayList<Projectile> getProjectileContainer() {
		return projectilesContainer;
	}

}
