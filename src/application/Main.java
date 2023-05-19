package application;

import MainMenu.GameMenu;
import drawing.GameScreen;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.game.GameLogic;
import logic.entity.Player;
import sharedObject.RenderableHolder;

public class Main extends Application {

	Scene gameScene, mainScene;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	public static boolean isStart = false;
	private static GameMenu gameMenu;
	public static Stage stage;
	private static StackPane root;
	private static ImageView bg;
	private static GameScreen gameScreen;
	private static GameLogic logic;
	private static AnimationTimer animation;

	final static int width = 1280;
	final static int height = 720;

	public Stage getStage() {
		return stage;
	}

	public boolean isStart() {
		return isStart;
	}

	public static void GameStart() {

		System.out.println(isStart);
		root.getChildren().removeAll(bg, gameMenu);
		gameScreen = new GameScreen(width, height);
		logic = new GameLogic(gameScreen);
		root.getChildren().add(gameScreen);
		gameScreen.requestFocus();
//		
//		
			 animation = new AnimationTimer() {
				long previousTime = 0;
				double drawInterval = 1e9 / 60;
				double delta = 0;

				public void handle(long now) {
					delta += (now - previousTime) / drawInterval;
					previousTime = now;
					logic.checkGameState();
					if (delta >= 1) {
						logic.count();
						logic.update();
						RenderableHolder.getInstance().update();
						InputUtility.updateInputState();
						delta--;
					}
				}
			};
			animation.start();
		}

	public static void GoToMenu() {
		animation.stop();
		root.getChildren().remove(gameScreen);
		root.getChildren().addAll(bg,gameMenu);
	}

	@Override
	public void start(Stage stage) {

		Main.stage = stage;
		Main.root = new StackPane();
		root.setPrefSize(width, height);

		gameMenu = new GameMenu();

		Image img = new Image(ClassLoader.getSystemResource("Elysia.jpeg").toString());
		bg = new ImageView(img);
		bg.setFitWidth(width);
		bg.setFitHeight(height);

		root.getChildren().addAll(bg, gameMenu);
		gameMenu.setVisible(false);

		Scene scene = new Scene(root);

		scene.setOnKeyPressed(e -> {
			if (!gameMenu.isVisible()) {
				FadeTransition ft = new FadeTransition(Duration.seconds(1), gameMenu);
				ft.setFromValue(0);
				ft.setToValue(1);
				gameMenu.setVisible(true);
				ft.play();
			}
		});

		stage.setTitle("2D Game");
		stage.setScene(scene);
		stage.show();
		// TODO Auto-generated method stub

//		
//		
	}
}
