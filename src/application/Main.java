package application;

import MainMenu.GameMenu;
import constant.Constant;
import drawing.GameScreen;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Main extends Application {

	Scene gameScene, mainScene;

	public static int second;
	private static long lastTimeTriggered = -1;
	public static boolean isStart = false;
	private static GameMenu gameMenu;
	public static Stage stage;
	private static StackPane root;
	private static ImageView bg;
	private static GameScreen gameScreen;
	private static GameLogic logic;
	private static AnimationTimer animation;

	public Stage getStage() {
		return stage;
	}
	
	public static void GameStart() {

		System.out.println(isStart);
		root.getChildren().removeAll(bg, gameMenu);
		gameScreen = new GameScreen(Constant.ScreenSize.GAMEWIDTH, Constant.ScreenSize.GAMEHEIGHT);
		logic = new GameLogic(gameScreen);
		root.getChildren().add(gameScreen);
		gameScreen.requestFocus();
//		
//		
		second = 0;
		animation = new AnimationTimer() {
			long previousTime = 0;
			double drawInterval = 1e9 / 60;
			double delta = 0;

			public void handle(long now) {
				// timer
				lastTimeTriggered = (lastTimeTriggered < 0 ? now : lastTimeTriggered);
				if (now - lastTimeTriggered >= 1000000000) {
					second++;
					lastTimeTriggered = now;
				}

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
//				System.out.println(second);
			}
		};
		animation.start();
	}

	public static void GoToMenu() {
		animation.stop();
		root.getChildren().remove(gameScreen);
		root.getChildren().addAll(bg, gameMenu);
	}

	@Override
	public void start(Stage stage) {

		Main.stage = stage;
		Main.root = new StackPane();
		root.setPrefSize(Constant.ScreenSize.GAMEWIDTH, Constant.ScreenSize.GAMEHEIGHT);

		gameMenu = new GameMenu();

		Image img = new Image(ClassLoader.getSystemResource("other/darkSoul4.png").toString());
		bg = new ImageView(img);
		bg.setFitWidth(Constant.ScreenSize.GAMEWIDTH);
		bg.setFitHeight(Constant.ScreenSize.GAMEHEIGHT);

		root.getChildren().addAll(bg, gameMenu);
		gameMenu.setVisible(false);

		Scene scene = new Scene(root);
		if (!gameMenu.isVisible()) {
			scene.setOnKeyPressed(e -> {
//				System.out.println("attempt");

				FadeTransition ft = new FadeTransition(Duration.seconds(1), gameMenu);
				ft.setFromValue(0);
				ft.setToValue(1);
				gameMenu.setVisible(true);
				ft.play();

			});
		}
		stage.setResizable(false);
		stage.setTitle("Black Light Matter");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}
