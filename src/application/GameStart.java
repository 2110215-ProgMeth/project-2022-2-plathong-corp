package application;

import drawing.GameScreen;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.game.GameLogic;
import logic.entity.Player;
import logic.entity.Werewolf;
import sharedObject.RenderableHolder;

public class GameStart{
	
	
	public static void start(){
	final int width = 1280;
	final int height = 720;
	
	Stage gameWindow = new Stage();
	gameWindow.initModality(Modality.APPLICATION_MODAL);
	StackPane root = new StackPane();
	Scene gameScene = new Scene(root);
	gameWindow.setScene(gameScene);
	gameWindow.setTitle("Test");
	
	GameScreen gameScreen = new GameScreen(width, height);
	GameLogic logic = new GameLogic(gameScreen);
	root.getChildren().add(gameScreen);
	gameScreen.requestFocus();
	

	AnimationTimer animation = new AnimationTimer() {
		long previousTime = 0;
		double drawInterval = 1e9/30;
		double delta = 0;
		
		public void handle(long now) {
			delta += (now-previousTime)/drawInterval;
			previousTime = now;
			
			if (delta >= 1) {

				gameScreen.paintComponent();
				logic.logicUpdate();
				RenderableHolder.getInstance().update();
				InputUtility.updateInputState();
				delta--;
			}
		}
	};
	animation.start();
	gameWindow.show();
}
}
