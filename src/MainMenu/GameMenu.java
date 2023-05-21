package MainMenu;

import application.Main;
import constant.Constant;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameMenu extends Parent {

	public GameMenu() {
		GameMenu gameMenu = this;
		VBox mainMenu = new VBox(10);
		VBox playMenu = new VBox(10);
		mainMenu.setTranslateX(Constant.ScreenSize.GAMEWIDTH / 2.5);
		mainMenu.setTranslateY(Constant.ScreenSize.GAMEHEIGHT * 3 / 4);
		playMenu.setTranslateX(Constant.ScreenSize.GAMEWIDTH / 1.5);
		playMenu.setTranslateY(Constant.ScreenSize.GAMEHEIGHT * 3 / 4);

		// Main menu
		MenuButton startBtn = new MenuButton("START");

		startBtn.setOnMouseClicked(e -> {
			transition(mainMenu, playMenu, -640);
//			gameMenu.getChildren().remove(mainMenu);
			gameMenu.getChildren().addAll(playMenu);
		});

		MenuButton exitBtn = new MenuButton("QUIT");

		exitBtn.setOnMouseClicked(e -> {
			System.exit(0);
		});

//		MenuButton optionBtn = new MenuButton("OPTION");
//
//		optionBtn.setOnMouseClicked(e -> {
//			getChildren().add(playMenu);
//			transition(mainMenu, playMenu, -640);
//
//		});

		// PlayMenu

		MenuButton playBtn = new MenuButton("PLAY");

		playBtn.setOnMouseClicked(e -> {
			FadeTransition ft = new FadeTransition(Duration.seconds(0.2), this);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setOnFinished(evt -> setVisible(false));
			ft.play();
			Main.isStart = true;
			Main.GameStart();

			gameMenu.getChildren().remove(playMenu);
			gameMenu.getChildren().addAll(mainMenu);
			transition(playMenu, mainMenu, 640);
		});

//		MenuButton ContBtn = new MenuButton("CONTINUE");
//
//		ContBtn.setOnMouseClicked(e -> {
//
//		});

		MenuButton backBtn = new MenuButton("BACK");

		backBtn.setOnMouseClicked(e -> {
			gameMenu.getChildren().remove(playMenu);
			gameMenu.getChildren().addAll(mainMenu);
			transition(playMenu, mainMenu, 640);

		});

		Rectangle bg = new Rectangle(Constant.ScreenSize.GAMEWIDTH, Constant.ScreenSize.GAMEHEIGHT);
		bg.setOpacity(0.1);
		bg.setFill(Color.WHITE);
		mainMenu.getChildren().addAll(startBtn, exitBtn);
		playMenu.getChildren().addAll(playBtn, backBtn);

		getChildren().addAll(bg, mainMenu);

	}

	public void transition(VBox menuToOut, VBox menuToin, int Xtrans) {
		TranslateTransition ttOut = new TranslateTransition(Duration.seconds(0.2), menuToOut);
		ttOut.setToX(menuToOut.getTranslateX() + Xtrans);

		TranslateTransition ttIn = new TranslateTransition(Duration.seconds(0.2), menuToin);
		ttIn.setToX(menuToOut.getTranslateX());

		ttOut.play();
		ttIn.play();

		ttOut.setOnFinished(evt -> getGameMenu().getChildren().remove(menuToOut));
	}

	public GameMenu getGameMenu() {
		return this;
	}
}
