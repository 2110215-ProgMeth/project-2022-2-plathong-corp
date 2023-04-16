package logic.field;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;
import logic.entity.Player;
import logic.game.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Map1 implements IRenderable{
	public GameLogic gameLogic;
	private WritableImage[] croppedImage = new WritableImage[4];
	
	public Map1(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
		croppedImage[0] = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				0 * tileSize, 0, tileSize, tileSize);
		croppedImage[1] = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				1 * tileSize, 0, tileSize, tileSize);
		croppedImage[2] =new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				2 * tileSize, 0, tileSize, tileSize);
	}
	int tileSize = 64;
	private static int[][] field = { { -1, 0, 0, 0, 0, 0, 0, 0, -1,-1, 0, 0, 0, 0, 0, 0, 0, -1, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{-1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, -2, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, -2, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, -1, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,-1, 0, 0, 0, 0, 0, 0, 0, -1, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{-1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, -2, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, -2, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, -1, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,-1, 0, 0, 0, 0, 0, 0, 0, -1, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{-1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, -2, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, -2, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, -1, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,-1, 0, 0, 0, 0, 0, 0, 0, -1, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{-1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, -2, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, -2, 0, 0, 0, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{ -1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, -1, 0, 0, 0, 0, 0 ,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, -1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	public int getTerrain(int x, int y) {
		if (x < 0 || x >= field[0].length || y < 0 || y >= field.length)
			return -3;
		return field[y][x];
	}

	private int getTileIndex(int x, int y) {
		int terrain = getTerrain(x, y);
		if (terrain <= 0 && terrain >= -2)
			return -terrain;
		else
			return 0;
	}

	@Override
	public int getZ() {
		return -9999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < field[0].length && worldRow < field.length) {
			int worldX = worldCol*tileSize;
			int worldY = worldRow*tileSize;
			double screenX = worldX-gameLogic.getPlayer().getWorldX()+gameLogic.getPlayer().screenX;
			double screenY = worldY-gameLogic.getPlayer().getWorldY()+gameLogic.getPlayer().screenY;
			
//			if(worldX>gameLogic.getPlayer().getWorldX()-gameLogic.getPlayer().screenX && 
//					worldX<gameLogic.getPlayer().getWorldX()+gameLogic.getPlayer().screenX &&
//					worldY>gameLogic.getPlayer().getWorldY()-gameLogic.getPlayer().screenY &&
//					worldY>gameLogic.getPlayer().getWorldY()+gameLogic.getPlayer().screenY ) {
			if(screenX>-64 && screenX<gameLogic.getGameScreen().getWidth() && screenY>-64 && screenY < gameLogic.getGameScreen().getHeight()) {
				gc.drawImage(croppedImage[Math.abs(getTileIndex(worldCol, worldRow))], screenX, screenY);
		
			}

			worldCol++;
			
			if (worldCol == field[0].length) {
				worldCol = 0;
				worldRow++;
			}
	}
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
