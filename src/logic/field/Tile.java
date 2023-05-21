package logic.field;

import javafx.scene.image.Image;

public class Tile {
	protected Image image;
	public boolean collision = false;

	public Tile(Image image, boolean collision) {
		this.image = image;
		this.collision = collision;
	}

	public Image getImage() {
		return image;
	}

	public boolean isCollision() {
		return collision;
	}
}
