package MainMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MenuButton extends StackPane{
	private Text text;
	
	public MenuButton(String name) {
		text = new Text(name);
		text.setFont(text.getFont().font(20));
		
		Rectangle bg = new Rectangle(250,30);
		bg.setOpacity(0.5);
		bg.setFill(Color.WHITE);
		bg.setEffect(new Bloom(3));
		
		setAlignment(Pos.CENTER);
		
		getChildren().addAll(text,bg);
		
		setOnMouseEntered(e->{
			bg.setTranslateX(10);
			text.setTranslateX(10);
			bg.setFill(Color.LIGHTPINK);
			text.setFill(Color.RED);
			
		});
		
		setOnMouseExited(e->{
			bg.setTranslateX(0);
			text.setTranslateX(0);
			bg.setFill(Color.WHITE);
			text.setFill(Color.BLACK);
		});
		
		setStyle("-fx-background-radius: 50px;");
	}
	
}
