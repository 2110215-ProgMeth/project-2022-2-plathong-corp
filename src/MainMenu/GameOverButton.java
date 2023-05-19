package MainMenu;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameOverButton {

	protected int x, y, width, height;
	protected Text text;
	protected Rectangle bounds;

	public GameOverButton(int x, int y, int width, int height,String text) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = new Text(text);
		bounds = new Rectangle(x, y, width, height);
		
	}
	
	public void draw(GraphicsContext gc) {
		//(1280/2.5,720/1.5,200,40)
		gc.strokeRect(x,y,width,height);
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));
		gc.fillText(text.getText(), (x+width/2-55), (y+height/1.3));
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}

	public Rectangle getBounds() {
		return bounds;
	}


}