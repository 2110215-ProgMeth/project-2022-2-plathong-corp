package MainMenu;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameMenu extends Parent{

    public GameMenu() {
        GameMenu gameMenu = this;
        VBox mainMenu = new VBox(10);
        VBox playMenu = new VBox(10);

        mainMenu.setTranslateX(1280/2.5);
        mainMenu.setTranslateY(7203/4);
        playMenu.setTranslateX(1280/1.5);
        playMenu.setTranslateY(7203/4);

        //Main menu
        MenuButton startBtn = new MenuButton("START");

        startBtn.setOnMouseClicked(e->{
            transition(mainMenu,playMenu,-640);
//            gameMenu.getChildren().remove(mainMenu);
            gameMenu.getChildren().addAll(playMenu);
        });

        MenuButton exitBtn = new MenuButton("QUIT");

        exitBtn.setOnMouseClicked(e->{
            System.exit(0);
        });

        MenuButton optionBtn = new MenuButton("OPTION");

        optionBtn.setOnMouseClicked(e->{
            getChildren().add(playMenu);
            transition(mainMenu,playMenu,-640);

        });

        //PlayMenu

        MenuButton playBtn = new MenuButton("PLAY");

        playBtn.setOnMouseClicked(e->{
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(evt-> setVisible(false));
            ft.play();
            Main.isStart = true;
            Main.GameStart();

            gameMenu.getChildren().remove(playMenu);
            gameMenu.getChildren().addAll(mainMenu);
            transition(playMenu,mainMenu,640);
        });

        MenuButton ContBtn = new MenuButton("CONTINUE");

        ContBtn.setOnMouseClicked(e->{

        });

        MenuButton backBtn = new MenuButton("BACK");

        backBtn.setOnMouseClicked(e->{
            gameMenu.getChildren().remove(playMenu);
            gameMenu.getChildren().addAll(mainMenu);
            transition(playMenu,mainMenu,640);

        });


        Rectangle bg = new Rectangle(1280,720);
        bg.setOpacity(0.1);
        bg.setFill(Color.PINK);
        mainMenu.getChildren().addAll(startBtn,optionBtn,exitBtn);
        playMenu.getChildren().addAll(playBtn,ContBtn,backBtn);

        getChildren().addAll(bg,mainMenu);

    }

    public void transition(VBox menuToOut,VBox menuToin,int Xtrans) {
        TranslateTransition ttOut = new TranslateTransition(Duration.seconds(0.5),menuToOut);
        ttOut.setToX(menuToOut.getTranslateX()+Xtrans);

        TranslateTransition ttIn = new TranslateTransition(Duration.seconds(0.5),menuToin);
        ttIn.setToX(menuToOut.getTranslateX());

        ttOut.play();
        ttIn.play();

        ttOut.setOnFinished(evt-> getGameMenu().getChildren().remove(menuToOut));
    }

    public GameMenu getGameMenu() {
        return this;
    }
}