package logic.field;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;

public class WhiteMap implements IRenderable{

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return -9999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.fillRect(0, 0, 768, 576);
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

}
