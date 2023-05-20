package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.entity.Entity;
import logic.entity.Player;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image playerLeft,playerLeftWalk,playerLeftAtk,playerRight,playerRightWalk,playerRightAtk;
	public static Image CKLeft, CKLeftWalk1, CKLeftWalk2, CKLeftAtk, CKRight, CKRightWalk1, CKRightWalk2, CKRightAtk;
	public static Image gameOverOverlay, pauseOverlay, pauseMenu, soundButton, urm, volumeButton,wingameOverlay;
	public static Image GELeft, GELeftWalk, GELeftWalk2, GERight, GERightWalk, GERightWalk2;
	// Magical Tortoise Sprite
	public static Image MTLeft1, MTLeft2, MTRight1, MTRight2;
	// ShadowPot Sprite
	public static Image SPLeft1, SPLeft2, SPRight2, SPRight1, SPLeftAtk, SPRightAtk, ball;
	// EyeOfQwifot Sprite
	public static Image EQ1, EQ2, EQDead1, EQDead2;
	//MoleDerKaiser
	public static Image mole,moleDerKaiser,moleDead,moleDerKaiserDead;
	public static Image moonSprite;
	public static Image healthBar;
	//Song & Effect
	public static AudioClip inGameSong,sword1,chicknightSound,shootSound,griszlyEyeSound,npcSound,playerSkill,monsterdie;
	// Map Tile
	public static Image ground1Tile,ground2Tile,ground3Tile,water1Tile,water2Tile,waterTopTile,waterBottomTile,
	waterLeftTile,waterRightTile,waterTopLeftTile,waterTopRightTile,waterBottomLeftTile,waterBottomRightTile,
	waterEdge1,waterEdge2,waterEdge3,waterEdge4,topLeftMapTile,topMapTile,topRightMapTile,
	rightMapTile,bottomRightMapTile,bottomMapTile,bottomLeftMapTile,leftMapTile,tombTile,spawn1Tile,spawn2Tile;
	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			else if (o1.getZ() == o2.getZ()) {
				if (o1 instanceof Entity && o2 instanceof Entity) {
					if (((Entity) o1).getWorldY() > ((Entity) o2).getWorldY()) {
						return 1;
					}
				}
			}
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		//player Sprite
		loadPlayer();
		loadMapTile();
		loadChicknight();
		loadGriszlyEye();
		loadMagicalTortoise();
		loadShadowPot();
		loadEyeOfQwifot();
		loadMoleDerKaiser();
		loadSound();
		//
		//Game State
		pauseOverlay = new Image(ClassLoader.getSystemResource("pause/PauseOverlay.png").toString());
		gameOverOverlay = new Image(ClassLoader.getSystemResource("pause/GameOver.png").toString());
		wingameOverlay = new Image(ClassLoader.getSystemResource("pause/Victory.png").toString());
		// StatusBar
		healthBar = new Image(ClassLoader.getSystemResource("health_power_bar.png").toString());

	}
	
	public static void loadSound() {
		inGameSong = new AudioClip(ClassLoader.getSystemResource("sound/IngameSong.wav").toString());
		chicknightSound = new AudioClip(ClassLoader.getSystemResource("sound/chicknight.mp3").toString());
		shootSound = new AudioClip(ClassLoader.getSystemResource("sound/shootSound.mp3").toString());
		sword1 = new AudioClip(ClassLoader.getSystemResource("sound/swing.mp3").toString());
		griszlyEyeSound = new AudioClip(ClassLoader.getSystemResource("sound/GriszlyEyeSound.wav").toString());
		npcSound = new AudioClip(ClassLoader.getSystemResource("sound/npcSound.wav").toString());
		playerSkill = new AudioClip(ClassLoader.getSystemResource("sound/skill1.wav").toString());
		monsterdie = new AudioClip(ClassLoader.getSystemResource("sound/monsterdie.wav").toString());
	}
	public static void loadPlayer() {
		playerLeft = new Image(ClassLoader.getSystemResource("player/playerLeft.png").toString());
		playerLeftWalk = new Image(ClassLoader.getSystemResource("player/playerLeftWalk.png").toString());
		playerLeftAtk = new Image(ClassLoader.getSystemResource("player/playerLeftAtk.png").toString());
		playerRight = new Image(ClassLoader.getSystemResource("player/playerRight.png").toString());
		playerRightWalk = new Image(ClassLoader.getSystemResource("player/playerRightWalk.png").toString());
		playerRightAtk = new Image(ClassLoader.getSystemResource("player/playerRightAtk.png").toString());
	}
	public static void loadMapTile() {
		ground1Tile =  new Image(ClassLoader.getSystemResource("mapTile/ground1.png").toString());
		ground2Tile =  new Image(ClassLoader.getSystemResource("mapTile/ground2.png").toString());
		ground3Tile =  new Image(ClassLoader.getSystemResource("mapTile/ground3.png").toString());
		water1Tile =  new Image(ClassLoader.getSystemResource("mapTile/water1.png").toString());
		water2Tile = new Image(ClassLoader.getSystemResource("mapTile/water2.png").toString());
		waterTopTile = new Image(ClassLoader.getSystemResource("mapTile/waterTop.png").toString());
		waterBottomTile = new Image(ClassLoader.getSystemResource("mapTile/waterBottom.png").toString());
		waterLeftTile = new Image(ClassLoader.getSystemResource("mapTile/waterLeft.png").toString());
		waterRightTile = new Image(ClassLoader.getSystemResource("mapTile/waterRight.png").toString());
		waterTopRightTile = new Image(ClassLoader.getSystemResource("mapTile/waterTopRight.png").toString());
		waterBottomRightTile = new Image(ClassLoader.getSystemResource("mapTile/waterBottomRight.png").toString());
		waterTopLeftTile = new Image(ClassLoader.getSystemResource("mapTile/waterTopLeft.png").toString());
		waterBottomLeftTile = new Image(ClassLoader.getSystemResource("mapTile/waterBottomLeft.png").toString());
		waterEdge1 = new Image(ClassLoader.getSystemResource("mapTile/waterEdge1.png").toString());
		waterEdge2 = new Image(ClassLoader.getSystemResource("mapTile/waterEdge2.png").toString());
		waterEdge3 = new Image(ClassLoader.getSystemResource("mapTile/waterEdge3.png").toString());
		waterEdge4 = new Image(ClassLoader.getSystemResource("mapTile/waterEdge4.png").toString());
		topLeftMapTile =  new Image(ClassLoader.getSystemResource("mapTile/TopLeftMap.png").toString());
		topMapTile =  new Image(ClassLoader.getSystemResource("mapTile/TopMap.png").toString());
		topRightMapTile =  new Image(ClassLoader.getSystemResource("mapTile/TopRightMap.png").toString());
		rightMapTile =  new Image(ClassLoader.getSystemResource("mapTile/RightMap.png").toString());
		bottomRightMapTile =  new Image(ClassLoader.getSystemResource("mapTile/BottomRightMap.png").toString());
		bottomMapTile =  new Image(ClassLoader.getSystemResource("mapTile/BottomMap.png").toString());
		bottomLeftMapTile =  new Image(ClassLoader.getSystemResource("mapTile/BottomLeftMap.png").toString());
		leftMapTile =  new Image(ClassLoader.getSystemResource("mapTile/LeftMap.png").toString());
		tombTile =  new Image(ClassLoader.getSystemResource("mapTile/Tomb.png").toString());
		spawn1Tile =  new Image(ClassLoader.getSystemResource("mapTile/spawn1.png").toString());
		spawn2Tile =  new Image(ClassLoader.getSystemResource("mapTile/spawn2.png").toString());
	}
	public static void loadChicknight() {
		CKLeft = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightLeft.png").toString());
		CKLeftWalk1 = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightLeftWalk1.png").toString());
		CKLeftWalk2 = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightLeftWalk2.png").toString());
		CKLeftAtk = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightLeftAtk.png").toString());
		CKRight = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightRight.png").toString());
		CKRightWalk1 = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightRightWalk1.png").toString());
		CKRightWalk2 = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightRightWalk2.png").toString());
		CKRightAtk = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightRightAtk.png").toString());
	}
	public static void loadGriszlyEye() {
		GELeft = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeLeft.png").toString());
		GELeftWalk = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeLeftWalk.png").toString());
		GELeftWalk2 = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeLeftWalk2.png").toString());
		GERight = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeRight.png").toString());
		GERightWalk = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeRightWalk.png").toString());
		GERightWalk2 = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeRightWalk2.png").toString());
	}
	public static void loadMagicalTortoise() {
		MTLeft1 = new Image(ClassLoader.getSystemResource("MagicalTortoise/MagicalTortoiseLeft1.png").toString());
		MTLeft2 = new Image(ClassLoader.getSystemResource("MagicalTortoise/MagicalTortoiseLeft2.png").toString());
		MTRight1 = new Image(ClassLoader.getSystemResource("MagicalTortoise/MagicalTortoiseRight1.png").toString());
		MTRight2 = new Image(ClassLoader.getSystemResource("MagicalTortoise/MagicalTortoiseRight2.png").toString());
	}
	public static void loadEyeOfQwifot() {
		EQ1 =  new Image(ClassLoader.getSystemResource("EyeOfQwifot/EyeOfQwifot1.png").toString());
		EQ2 =  new Image(ClassLoader.getSystemResource("EyeOfQwifot/EyeOfQwifot2.png").toString());
		EQDead1 =  new Image(ClassLoader.getSystemResource("EyeOfQwifot/EyeOfQwifotDead1.png").toString());
		EQDead2 =  new Image(ClassLoader.getSystemResource("EyeOfQwifot/EyeOfQwifotDead2.png").toString());
	}
	public static void loadShadowPot() {
		SPLeft1 =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotLeft1.png").toString());
		SPLeft2 =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotLeft2.png").toString());
		SPLeftAtk =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotLeftAtk.png").toString());
		SPRight1 =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotRight1.png").toString());
		SPRight2 =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotRight2.png").toString());
		SPRightAtk =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotRightAtk.png").toString());
		ball = new Image(ClassLoader.getSystemResource("ShadowPot/ball.png").toString());
	}
	public static void loadMoleDerKaiser() {
		mole = new Image(ClassLoader.getSystemResource("MoleDerKaiser/normalMole.png").toString());
		moleDerKaiser = new Image(ClassLoader.getSystemResource("MoleDerKaiser/moleDerKaiser.png").toString());
		moleDead = new Image(ClassLoader.getSystemResource("MoleDerKaiser/normalMoleDead.png").toString());
		moleDerKaiserDead = new Image(ClassLoader.getSystemResource("MoleDerKaiser/moleDerKaiserDead.png").toString());
	}
	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
		for (IRenderable x : entities) {
			if (x instanceof Player)
				System.out.println("player");

		}
	}

	public void update() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).isDestroyed())
                entities.remove(i);
        }
        Collections.sort(entities, comparator);
    }

	public List<IRenderable> getEntities() {
		return entities;
	}
}
