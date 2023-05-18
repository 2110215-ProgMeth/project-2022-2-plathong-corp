package application;

import drawing.GameScreen;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.game.GameLogic;
import logic.entity.Player;
import sharedObject.RenderableHolder;

public class GameStart{
	
	
	public static void start(){
	final int width = 1280;
	final int height = 720;
//	VBox statBar = new VBox();
	ProgressBar hpBar = new ProgressBar();
	hpBar.setStyle("-fx-accent: Green; \n -fx-background-color: White; \n");
	hpBar.setMaxWidth(width);
	hpBar.setProgress(1);
	ProgressBar manaBar = new ProgressBar();
	manaBar.setStyle("-fx-accent: Blue; \n -fx-background-color: White; \n");
	manaBar.setMaxWidth(width);
	manaBar.setProgress(1);
	
	Stage gameWindow = new Stage();
	gameWindow.initModality(Modality.APPLICATION_MODAL);
	VBox root = new VBox();
	Scene gameScene = new Scene(root);
	gameWindow.setScene(gameScene);
	gameWindow.setTitle("Test");
	
	GameScreen gameScreen = new GameScreen(width, height);
	GameLogic logic = new GameLogic(gameScreen);
	root.getChildren().addAll(gameScreen,hpBar,manaBar);
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
