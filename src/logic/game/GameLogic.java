package logic.game;

import java.util.ArrayList;
import java.util.List;

import MainMenu.GameOverButton;
import Object.Projectile;
import drawing.GameScreen;
import input.InputUtility;
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
import logic.entity.MagicalTortoise;
import logic.entity.Player;
import logic.entity.ShadowPot;
import logic.field.Map1;
import logic.field.WhiteMap;
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

	// GameState
	public int gameState = 1;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int npcState = 3;
	public final int gameOverState = 4;

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
		
		player = new Player(3400, 100, this);
		eQ = new EyeOfQwifot(3456, 512, this);
		mT = new MagicalTortoise(3400, 200, this);
		addNewObject(player);
		addNewObject(new Chicknight(3400, 200, this));
//		addNewObject(mT);
//		addNewObject(new GriszlyEye(3400, 200, this));
		addNewObject(eQ);
//		addNewObject(new ShadowPot(3400, 500, this));
		
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
//			tile1 = map.field[entityLeftCol][entityTopRow];
//			tile2 = map.field[entityRightCol][entityTopRow];

			if (map.getTiles()[tile1].collision == true || map.getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / map.getTileSize();
			tile1 = map.getTileIndex(entityLeftCol, entityBottomRow);
			tile2 = map.getTileIndex(entityRightCol, entityBottomRow);
//			tile1 = map.field[entityLeftCol][entityBottomRow];
//			tile2 = map.field[entityRightCol][entityBottomRow];
			if (map.getTiles()[tile1].collision == true || map.getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / map.getTileSize();
			tile1 = map.getTileIndex(entityLeftCol, entityTopRow);
			tile2 = map.getTileIndex(entityLeftCol, entityBottomRow);
//			tile1 = map.field[entityLeftCol][entityTopRow];
//			tile2 = map.field[entityLeftCol][entityBottomRow];
			if (map.getTiles()[tile1].collision == true || map.getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.getSpeed()) / map.getTileSize();
			tile1 = map.getTileIndex(entityRightCol, entityTopRow);
			tile2 = map.getTileIndex(entityRightCol, entityBottomRow);
//			tile1 = map.field[entityRightCol][entityTopRow];
//			tile2 = map.field[entityRightCol][entityBottomRow];
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
		if(gameState == playState) {
		logicUpdate();
		gameScreen.paintComponent();
		}
		else if (gameState == pauseState) {
			drawGamePauseOverlay();
//			System.out.println(500);
		}else if (gameState == gameOverState) {
			drawGameOverOverlay();
			RenderableHolder.inGameSong.stop();
			if (InputUtility.getKeyPressed(KeyCode.R)) {
				startNewGame();
//				reset();
			}
		}
		else if (gameState == npcState) {
			drawDialogueScreen(0);
			logicUpdate();
			gameScreen.paintComponent();
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
		retry.draw(gc);
		mainMenu.draw(gc);
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

			}
			else if (gameState == npcState) {
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
	}

	public ArrayList<Entity> getGameObjectContainer() {
		return gameObjectContainer;
	}

	public ArrayList<Projectile> getProjectileContainer() {
		return projectilesContainer;
	}

}
