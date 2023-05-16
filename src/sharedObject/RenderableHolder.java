package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.entity.Player;
import logic.entity.Werewolf;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image playerLeft;
	public static Image playerRight;
	public static Image playerRightAtk;
	public static Image johnSprite;
	public static Image whiteTile;
	public static Image grayTile;
	public static Image pathTile;
	public static Image blackStarTile;
	public static Image blackTile;
	public static Image CKLeft,CKLeftWalk1,CKLeftWalk2,CKLeftAtk,CKRight,CKRightWalk1,CKRightWalk2,CKRightAtk;
	
	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		playerLeft = new Image(ClassLoader.getSystemResource("player/RabbiLeft.png").toString());
		playerRight = new Image(ClassLoader.getSystemResource("player/Rabbi.png").toString());
		playerRightAtk = new Image(ClassLoader.getSystemResource("player/RabbiRightAtk.png").toString());
		johnSprite = new Image(ClassLoader.getSystemResource("John.png").toString());
		whiteTile = new Image(ClassLoader.getSystemResource("Tiles/WhiteTile.png").toString());
		grayTile = new Image(ClassLoader.getSystemResource("Tiles/GrayTile.png").toString());
		pathTile = new Image(ClassLoader.getSystemResource("Tiles/pathTile.png").toString());
		blackStarTile = new Image(ClassLoader.getSystemResource("Tiles/blackStarTile.png").toString());
		blackTile = new Image(ClassLoader.getSystemResource("Tiles/blackTile.png").toString());
		
		//Chicknight's Resource
		CKLeft = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightLeft.png").toString());
		CKLeftWalk1 = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightLeftWalk1.png").toString());
		CKLeftWalk2 = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightLeftWalk2.png").toString());
		CKLeftAtk = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightLeftAtk.png").toString());
		CKRight = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightRight.png").toString());
		CKRightWalk1 = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightRightWalk1.png").toString());
		CKRightWalk2 = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightRightWalk2.png").toString());
		CKRightAtk = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightRightAtk.png").toString());
	}
	
	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
		for(IRenderable x: entities){
			if(x instanceof Werewolf) System.out.println("WereWolf");
			if(x instanceof Player) System.out.println("player");
			
		}
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
