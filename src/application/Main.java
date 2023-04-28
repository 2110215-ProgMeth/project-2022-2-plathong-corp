package application;

import MainMenu.GameMenu;
import drawing.GameScreen;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.game.GameLogic;
import logic.entity.Player;
import logic.entity.Werewolf;
import sharedObject.RenderableHolder;

public class Main extends Application{
	
	Scene gameScene,mainScene ;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GameMenu gameMenu;
			
		Pane root = new Pane();
		final int width = 1280;
		final int height = 720;
		root.setPrefSize(width, height);
		
		gameMenu = new GameMenu();
		
		Image img = new Image(ClassLoader.getSystemResource("Elysia.jpeg").toString());
		ImageView bg = new ImageView(img);
		bg.setFitWidth(width);
		bg.setFitHeight(height);
		
		root.getChildren().addAll(bg,gameMenu);
		gameMenu.setVisible(false);

		Scene scene = new Scene(root);
		
		scene.setOnKeyPressed(e->{
			if (!gameMenu.isVisible()){
				FadeTransition ft = new FadeTransition(Duration.seconds(1),gameMenu);
				ft.setFromValue(0);
				ft.setToValue(1);
				gameMenu.setVisible(true);
				ft.play();
			}
		});
		
		
		
		primaryStage.setTitle("Test");
		primaryStage.setScene(scene);
		
		primaryStage.show();

}
}
