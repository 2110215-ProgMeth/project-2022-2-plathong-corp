package application;

import drawing.GameScreen;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.game.GameLogic;
import logic.entity.Player;
import sharedObject.RenderableHolder;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		final int width = 1280;
		final int height = 720;
		// TODO Auto-generated method stub
		StackPane root = new StackPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("2D Game");
		
		
		GameScreen gameScreen = new GameScreen(width, height);
		GameLogic logic = new GameLogic(gameScreen);
		root.getChildren().add(gameScreen);
		gameScreen.requestFocus();
		
		stage.show();
		
		AnimationTimer animation = new AnimationTimer() {
			long previousTime = 0;
			double drawInterval = 1e9/60;
			double delta = 0;
			
			public void handle(long now) {
				delta += (now-previousTime)/drawInterval;
				previousTime = now;
				logic.checkGameState();
				if (delta >= 1) {
					logic.count();
//					logic.logicUpdate();
//					gameScreen.paintComponent();
					logic.update();
					RenderableHolder.getInstance().update();
					InputUtility.updateInputState();
					delta--;
				}
			}
		};
		animation.start();
	}

}
