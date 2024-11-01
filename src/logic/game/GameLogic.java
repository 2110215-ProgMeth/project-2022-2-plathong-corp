package logic.game;

import java.util.ArrayList;

import MainMenu.GameOverButton;
import Object.Projectile;
import application.Main;
import constant.Constant;
import constant.GameState;
import drawing.GameScreen;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.entity.Chicknight;
import logic.entity.Entity;
import logic.entity.EyeOfQwifot;
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
	private EyeOfQwifot eyeOfQwifot;
	private Map1 map;
	private MagicalTortoise magicalTortoise;
	private MoleDerKaiser moleDerKaiser;
	private LlaristicKnight llaristicKnight;
	private AudioClip gameSong;
	// GameState
	private GameState gameState = GameState.PLAYSTATE;

	private int dialogueIndex = 0;
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
		gameSong = RenderableHolder.inGameSong;
		gameSong.play();
		this.gameObjectContainer = new ArrayList<Entity>();
		this.projectilesContainer = new ArrayList<Projectile>();
		RenderableHolder.getInstance().getEntities().clear();
		map = new Map1(this);
		RenderableHolder.getInstance().add(map);
//1920+64
		player = new Player(1920+64 , 1440, this);
		eyeOfQwifot = new EyeOfQwifot(3456, 512, this);
		magicalTortoise = new MagicalTortoise(2040+32, 1444, this);
		llaristicKnight = new LlaristicKnight(8 * 64, 10 * 64, this);
		moleDerKaiser = new MoleDerKaiser(1800, 64 * 37, this, 448, 448);
		addNewObject(player);
		addNewObject(magicalTortoise);
		addNewObject(llaristicKnight);
		addNewObject(eyeOfQwifot);
		addNewObject(moleDerKaiser);
		for (Mole m : moleDerKaiser.getMoles()) {
			addNewObject(m);
		}

		for (int i = 0; i < 50; i++) {
			double pointX = Math.random();
			double pointY = Math.random();
			boolean spawnPoint = ((pointX > 0.35 && pointX < 0.65) || (pointY > 0.3 && pointY < 0.7));

			while (pointX < 0.05 || pointX > 0.95 || pointY < 0.05 || pointY > 0.95 || spawnPoint || (pointX < 0.25&&pointY<0.35)) {
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
			while (pointX < 0.05 || pointX > 0.95 || pointY < 0.05 || pointY > 0.95 || spawnPoint || (pointX < 0.25&&pointY<0.35)) {
				pointX = Math.random();
				pointY = Math.random();
				spawnPoint = ((pointX > 0.4 && pointX < 0.6) || (pointY > 0.4 && pointY < 0.6));
			}
			addNewObject(new ShadowPot(pointX * 64 * 64, pointY * 64 * 48, this));
		}

		gameState = GameState.PLAYSTATE;
		gameSong = RenderableHolder.inGameSong;
		System.out.println("New Game");

	}


	public void update() {
//		System.out.println(gameState);
		if (!gameSong.isPlaying()) {
			gameSong.play();
		}
		if (gameState == GameState.PLAYSTATE) {
			logicUpdate();
			gameScreen.paintComponent();
			player.drawUI(gameScreen.getGraphicsContext2D());
		} else if (gameState == GameState.PAUSESTATE) {
			drawGamePauseOverlay();
			gameSong.stop();
			if (InputUtility.getKeyPressed(KeyCode.M)) {
				Main.GoToMenu();
				
				InputUtility.getKeyPressed().remove(KeyCode.M);
			}
//			System.out.println(500);
		} else if (gameState == GameState.GAMEOVERSTATE) {
			drawGameOverOverlay();
			gameSong.stop();
			if (InputUtility.getKeyPressed(KeyCode.R)) {
				startNewGame();
//				reset();
			} else if (InputUtility.getKeyPressed(KeyCode.M)) {
				InputUtility.getKeyPressed().remove(KeyCode.M);
				Main.GoToMenu();
			}
		} else if (gameState == GameState.NPCSTATE) {
			if (!getMagicalTortoise().playerfound()) {

				gameState = GameState.PLAYSTATE;
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
		} else if (gameState == GameState.WINSTATE) {
			gameSong.stop();
			if (isFinish) {
				timeStamp = Main.second;
				isFinish = false;

			}
			drawWinGameOvelay(timeStamp);
			if (InputUtility.getKeyPressed(KeyCode.M)) {
				Main.GoToMenu();
				InputUtility.getKeyPressed().remove(KeyCode.M);
				gameSong.stop();
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
		return magicalTortoise;
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
		gc.drawImage(RenderableHolder.pauseOverlay, 480*Constant.ScreenSize.GAMEWIDTH/1280, 252*Constant.ScreenSize.GAMEHEIGHT/720);
	}

	public void drawGameOverOverlay() {
		GameOverButton retry = new GameOverButton((int) (Constant.ScreenSize.GAMEWIDTH / 3.5), (int) (Constant.ScreenSize.GAMEHEIGHT / 1.5), 200, 40, "RETRY(R)");
		GameOverButton mainMenu = new GameOverButton((int) (Constant.ScreenSize.GAMEWIDTH / 3.5) + 320, (int) (Constant.ScreenSize.GAMEHEIGHT  / 1.5), 200, 40, " Menu(M)");
		GraphicsContext gc = getGameScreen().getGraphicsContext2D();
		gc.drawImage(RenderableHolder.gameOverOverlay, 0, 0);
		retry.draw(gc, Color.BLACK, Color.BLACK);
		mainMenu.draw(gc, Color.BLACK, Color.BLACK);
	}

	public void drawWinGameOvelay(int time) {
		// TODO Auto-generated method stub
		GameOverButton mainMenu = new GameOverButton((int) (Constant.ScreenSize.GAMEWIDTH / 3.5) + 480, (int) (Constant.ScreenSize.GAMEHEIGHT  / 1.5), 200, 40, " Menu(M)");
		GraphicsContext gc = getGameScreen().getGraphicsContext2D();
		gc.drawImage(RenderableHolder.wingameOverlay, 0, 0,Constant.ScreenSize.GAMEWIDTH,Constant.ScreenSize.GAMEHEIGHT );
		gc.fillText("Finish Time : " + time + " seconds", (int) (Constant.ScreenSize.GAMEWIDTH / 3.5) + 420, (int) (Constant.ScreenSize.GAMEHEIGHT  / 1.7));
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

			if (gameState == GameState.PLAYSTATE) {
				gameState = GameState.PAUSESTATE;

			} else if (gameState == GameState.PAUSESTATE) {
				gameState = GameState.PLAYSTATE;
			}
			InputUtility.getKeyPressed().remove(KeyCode.ESCAPE);
		}

		if (InputUtility.isLeftClickTriggered() && getMagicalTortoise().playerfound()) {
			int start = 0;
			if (gameState == GameState.PLAYSTATE) {
				gameState = GameState.NPCSTATE;

			} else if (gameState == GameState.NPCSTATE) {
				int total = getMagicalTortoise().getDialoguesSize() - 1;
				if (start < total) {
					drawDialogueScreen(start);
					start += 1;
				} else {
					gameState = GameState.PLAYSTATE;
				}

			}

		}
		if (getPlayer().getCurrentHealth() == 0) {
			gameState = GameState.GAMEOVERSTATE;
		}

		boolean win = llaristicKnight.isDead() && eyeOfQwifot.isDead() && moleDerKaiser.isDead();
		if (win)
			gameState = GameState.WINSTATE;
	}

	public ArrayList<Entity> getGameObjectContainer() {
		return gameObjectContainer;
	}

	public ArrayList<Projectile> getProjectileContainer() {
		return projectilesContainer;
	}

	public AudioClip getGameSong() {
		return gameSong;
	}

	public void setGameSong(AudioClip gameSong) {
		this.gameSong = gameSong;
	}

	public Map1 getMap() {
		return map;
	}

	
}
