package logic.field;

import javafx.scene.canvas.GraphicsContext;
import logic.entity.Entity;
import logic.game.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Map1 implements IRenderable {
	protected GameLogic gL;
//	private WritableImage[] croppedImage = new WritableImage[4];
	private Tile[] tiles = new Tile[29];

	public Map1(GameLogic gameLogic) {
		this.gL = gameLogic;
		tiles[0] = new Tile(RenderableHolder.ground1Tile, false);
		tiles[1] = new Tile(RenderableHolder.ground2Tile, false);
		tiles[2] = new Tile(RenderableHolder.ground3Tile, false);
		tiles[3] = new Tile(RenderableHolder.ground1Tile, true);
		tiles[4] = new Tile(RenderableHolder.water1Tile, true);
		tiles[5] = new Tile(RenderableHolder.water2Tile, true);
		tiles[6] = new Tile(RenderableHolder.topLeftMapTile, true);
		tiles[7] = new Tile(RenderableHolder.topMapTile, true);
		tiles[8] = new Tile(RenderableHolder.topRightMapTile, true);
		tiles[9] = new Tile(RenderableHolder.rightMapTile, true);
		tiles[10] = new Tile(RenderableHolder.bottomRightMapTile, true);
		tiles[11] = new Tile(RenderableHolder.bottomMapTile, true);
		tiles[12] = new Tile(RenderableHolder.bottomLeftMapTile, true);
		tiles[13] = new Tile(RenderableHolder.leftMapTile, true);
		tiles[14] = new Tile(RenderableHolder.tombTile, true);
		tiles[15] = new Tile(RenderableHolder.spawn1Tile, false);
		tiles[16] = new Tile(RenderableHolder.spawn2Tile, false);
		tiles[17] = new Tile(RenderableHolder.waterTopTile, true);
		tiles[18] = new Tile(RenderableHolder.waterBottomTile, false);
		tiles[19] = new Tile(RenderableHolder.waterLeftTile, true);
		tiles[20] = new Tile(RenderableHolder.waterRightTile, true);
		tiles[21] = new Tile(RenderableHolder.waterTopLeftTile, true);
		tiles[22] = new Tile(RenderableHolder.waterTopRightTile, true);
		tiles[23] = new Tile(RenderableHolder.waterBottomLeftTile, false);
		tiles[24] = new Tile(RenderableHolder.waterBottomRightTile, false);
		tiles[25] = new Tile(RenderableHolder.waterEdge1, true);
		tiles[26] = new Tile(RenderableHolder.waterEdge2, true);
		tiles[27] = new Tile(RenderableHolder.waterEdge3, true);
		tiles[28] = new Tile(RenderableHolder.waterEdge4, true);
	}

	int tileSize = 64;
	private int[][] field = {
			{ 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
					7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8 },
			{ 13, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 9 },
			{ 13, 0, 21, 17, 17, 17, 17, 22, 0, 0, 21, 17, 17, 17, 17, 22, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
					0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 19, 5, 4, 4, 4, 20, 0, 0, 19, 4, 5, 4, 4, 20, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 19, 4, 27, 18, 18, 24, 0, 0, 23, 18, 18, 28, 5, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 19, 4, 20, 0, 0, 2, 0, 0, 0, 0, 0, 19, 4, 20, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 19, 4, 20, 0, 0, 21, 17, 17, 22, 2, 0, 19, 4, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 23, 18, 24, 0, 0, 23, 18, 18, 24, 0, 0, 23, 18, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 21, 17, 22, 0, 0, 21, 17, 17, 22, 0, 0, 21, 17, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 19, 4, 20, 0, 0, 23, 18, 18, 24, 0, 0, 19, 4, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 19, 4, 20, 0, 0, 0, 0, 0, 0, 0, 0, 19, 4, 20, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 19, 4, 25, 17, 17, 22, 0, 0, 21, 17, 17, 26, 4, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 19, 4, 5, 4, 4, 20, 0, 0, 19, 4, 5, 4, 4, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 9 },
			{ 13, 0, 23, 18, 18, 18, 18, 24, 0, 0, 23, 18, 18, 18, 18, 24, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 17, 22, 0, 2, 21, 17, 17, 17,
					17, 17, 17, 22, 0, 0, 21, 17, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 4, 20, 0, 0, 19, 5, 4, 4, 4, 4,
					4, 20, 0, 0, 19, 5, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 4, 20, 0, 0, 19, 4, 4, 4, 4, 5,
					4, 20, 0, 0, 19, 4, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 5, 20, 0, 0, 23, 18, 18, 18, 18,
					18, 18, 24, 0, 0, 19, 4, 20, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 4, 20, 0, 2, 0, 0, 0, 16, 0, 0,
					0, 0, 0, 0, 19, 5, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 19, 4, 20, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 19, 4, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 4, 25, 17, 17, 17, 17, 22, 1, 0,
					21, 17, 17, 17, 17, 26, 4, 20, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 5, 4, 4, 4, 4, 5, 20, 0, 0, 19,
					4, 4, 4, 4, 5, 4, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 4, 5, 4, 4, 4, 4, 20, 0, 0, 19,
					5, 4, 5, 4, 4, 4, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 4, 4, 4, 5, 4, 4, 20, 0, 0, 19,
					4, 5, 4, 4, 4, 5, 20, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 18, 18, 18, 18, 18, 18, 24, 0,
					0, 23, 18, 18, 18, 18, 18, 18, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
					9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 14, 0, 0, 0, 14, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
					11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
					11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 10 } };

	public int getTerrain(int x, int y) {
		if (x < 0 || x >= field[0].length || y < 0 || y >= field.length)
			return -3;
		return field[y][x];
	}

	public int getTileIndex(int x, int y) {
		int terrain = getTerrain(x, y);
		if (terrain >= 0)
			return terrain;
		else
			return 3;
	}
	public void checkTile(Entity entity) {
		int entityLeftWorldX = (int) (entity.getWorldPos().getX() + entity.getSolidArea().getX());
		int entityRightWorldX = (int) (entity.getWorldPos().getX() + entity.getSolidArea().getX() + entity.getSolidArea().getWidth());
		int entityTopWorldY = (int) (entity.getWorldPos().getY() + entity.getSolidArea().getY());
		int entityBottomWorldY = (int) (entity.getWorldPos().getY() + entity.getSolidArea().getY() + entity.getSolidArea().getHeight());

		int entityLeftCol = entityLeftWorldX / getTileSize();
		int entityRightCol = entityRightWorldX / getTileSize();
		int entityTopRow = entityTopWorldY / getTileSize();
		int entityBottomRow = entityBottomWorldY / getTileSize();

		int tile1 = 0;
		int tile2 = 0;

		switch (entity.getDirection()) {
		case UP:
			entityTopRow = (entityTopWorldY - entity.getSpeed()) / getTileSize();
			tile1 = getTileIndex(entityLeftCol, entityTopRow);
			tile2 = getTileIndex(entityRightCol, entityTopRow);
			if (getTiles()[tile1].collision == true || getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case DOWN:
			entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / getTileSize();
			tile1 = getTileIndex(entityLeftCol, entityBottomRow);
			tile2 = getTileIndex(entityRightCol, entityBottomRow);
			if (getTiles()[tile1].collision == true || getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case LEFT:
			entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / getTileSize();
			tile1 = getTileIndex(entityLeftCol, entityTopRow);
			tile2 = getTileIndex(entityLeftCol, entityBottomRow);
			if (getTiles()[tile1].collision == true || getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		case RIGHT:
			entityRightCol = (entityRightWorldX + entity.getSpeed()) / getTileSize();
			tile1 = getTileIndex(entityRightCol, entityTopRow);
			tile2 = getTileIndex(entityRightCol, entityBottomRow);
			if (getTiles()[tile1].collision == true || getTiles()[tile2].collision == true) {
				entity.setCollisionOn(true);
			}
			break;
		}

	}
	@Override
	public int getZ() {
		return -9999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < field[0].length && worldRow < field.length) {
			int worldX = worldCol * tileSize;
			int worldY = worldRow * tileSize;
			int screenX = (int) (worldX - gL.getPlayer().getWorldPos().getX() + gL.getPlayer().getScreenPos().getX());
			int screenY = (int) (worldY - gL.getPlayer().getWorldPos().getY() + gL.getPlayer().getScreenPos().getY());

			if (screenX > -tileSize && screenX < gL.getGameScreen().getWidth() && screenY > -tileSize
					&& screenY < gL.getGameScreen().getHeight()) {
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
