package logic.field;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;
import logic.entity.Player;
import logic.game.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Map1 implements IRenderable{
	public GameLogic gL;
//	private WritableImage[] croppedImage = new WritableImage[4];
	private Tile [] tiles = new Tile[2];
	
	public Map1(GameLogic gameLogic) {
		this.gL = gameLogic;
		tiles[0] = new Tile(RenderableHolder.whiteTile,false);
		tiles[1] = new Tile(RenderableHolder.grayTile,true);
//		croppedImage[0] = new WritableImage(RenderableHolder.superMapWowZaSprite.getPixelReader(),
//				0 * tileSize, 0, tileSize, tileSize);
//		croppedImage[1] = new WritableImage(RenderableHolder.superMapWowZaSprite.getPixelReader(),
//				1 * tileSize, 0, tileSize, tileSize);
//		croppedImage[2] =new WritableImage(RenderableHolder.superMapWowZaSprite.getPixelReader(),
//				2 * tileSize, 0, tileSize, tileSize);
	}
	int tileSize = 64;
	public int[][] field = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

	public int getTerrain(int x, int y) {
		if (x < 0 || x >= field[0].length || y < 0 || y >= field.length)
			return -3;
		return field[y][x];
	}

	public int getTileIndex(int x, int y) {
		int terrain = getTerrain(x, y);
		if (terrain <= 0 && terrain >= -2)
			return -terrain;
		else
			return 1;
	}

	@Override
	public int getZ() {
		return -9999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		double width = field[0].length*tileSize;
		double height = field.length*tileSize;
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < field[0].length && worldRow < field.length) {
			int worldX = worldCol * tileSize;
			int worldY = worldRow * tileSize;
			int screenX = (int) (worldX - gL.getPlayer().getWorldX() + gL.getPlayer().screenX);
			int screenY = (int) (worldY - gL.getPlayer().getWorldY() + gL.getPlayer().screenY);		

			if (screenX > -tileSize && screenX < gL.getGameScreen().getWidth() && screenY > -tileSize
					&& screenY < gL.getGameScreen().getHeight()) {
//			if(worldX > gL.getPlayer().getWorldX()-gL.getPlayer().getScreenX()&&
//					worldX<gL.getPlayer().getWorldX()+gL.getPlayer().getScreenX()&&
//					worldY>gL.getPlayer().getWorldY()-gL.getPlayer().getScreenY()&&
//					worldY<gL.getPlayer().getWorldY()+gL.getPlayer().getScreenY()) {
				gc.drawImage(tiles[(getTileIndex(worldCol, worldRow))].getImage(), screenX, screenY);

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

	public GameLogic getGameLogic() {
		return gL;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public int getTileSize() {
		return tileSize;
	}

	public int[][] getField() {
		return field;
	}
	
}
