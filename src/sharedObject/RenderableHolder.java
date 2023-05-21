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
	public static Image playerLeft,playerLeftWalk,playerLeftAtk,playerRight,playerRightWalk,playerRightAtk,slashLeft,slashRight;
	public static Image cKLeft, cKLeftWalk1, cKLeftAtk, cKRight, cKRightWalk1, cKRightAtk;
	public static Image gameOverOverlay, pauseOverlay, pauseMenu, soundButton, urm, volumeButton,wingameOverlay;
	public static Image gELeft, gELeftWalk, gELeftWalk2, gERight, gERightWalk, gERightWalk2;
	// Magical Tortoise Sprite
	public static Image mTLeft1, mTLeft2, mTRight1, mTRight2;
	// ShadowPot Sprite
	public static Image sPLeft1, sPLeft2, sPRight2, sPRight1, sPLeftAtk, sPRightAtk;
	// EyeOfQwifot Sprite
	public static Image eQ1, eQ2, eQDead1, eQDead2,eQAtk;
	//MoleDerKaiser
	public static Image mole,moleDerKaiser,moleDead,moleDerKaiserDead,mole1,moleDerKaiser1;
	//llaristicKnight
	public static Image lKLeft1,lKLeft2,lKLeftAtk,lKRight1,lKRight2,lKRightAtk,lKDead;
	//projectile
	public static Image ball,beam;
	public static Image healthBar;
	//Song & Effect
	public static AudioClip inGameSong,sword1,chicknightSound,shootSound,griszlyEyeSound,npcSound,playerSkill,monsterdie,katana,llaristicTheme;
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
					if (((Entity) o1).getWorldPos().getY() > ((Entity) o2).getWorldPos().getY()) {
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
		loadLlaristicKnight();
		loadProjectile();
		loadSound();
		loadOther();
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
		katana = new AudioClip(ClassLoader.getSystemResource("sound/katana.mp3").toString());
		llaristicTheme = new AudioClip(ClassLoader.getSystemResource("sound/llaristicTheme.wav").toString());
	}
	public static void loadPlayer() {
		playerLeft = new Image(ClassLoader.getSystemResource("player/playerLeft.png").toString());
		playerLeftWalk = new Image(ClassLoader.getSystemResource("player/playerLeftWalk.png").toString());
		playerLeftAtk = new Image(ClassLoader.getSystemResource("player/playerLeftAtk.png").toString());
		playerRight = new Image(ClassLoader.getSystemResource("player/playerRight.png").toString());
		playerRightWalk = new Image(ClassLoader.getSystemResource("player/playerRightWalk.png").toString());
		playerRightAtk = new Image(ClassLoader.getSystemResource("player/playerRightAtk.png").toString());
		slashRight = new Image(ClassLoader.getSystemResource("player/slashRight.png").toString());
		slashLeft = new Image(ClassLoader.getSystemResource("player/slashLeft.png").toString());
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
		cKLeft = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightLeft.png").toString());
		cKLeftWalk1 = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightLeftWalk1.png").toString());
		cKLeftAtk = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightLeftAtk.png").toString());
		cKRight = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightRight.png").toString());
		cKRightWalk1 = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightRightWalk1.png").toString());
		cKRightAtk = new Image(ClassLoader.getSystemResource("Chicknight/ChicknightRightAtk.png").toString());
	}
	public static void loadGriszlyEye() {
		gELeft = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeLeft.png").toString());
		gELeftWalk = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeLeftWalk.png").toString());
		gELeftWalk2 = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeLeftWalk2.png").toString());
		gERight = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeRight.png").toString());
		gERightWalk = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeRightWalk.png").toString());
		gERightWalk2 = new Image(ClassLoader.getSystemResource("GriszlyEye/GriszlyEyeRightWalk2.png").toString());
	}
	public static void loadMagicalTortoise() {
		mTLeft1 = new Image(ClassLoader.getSystemResource("MagicalTortoise/MagicalTortoiseLeft1.png").toString());
		mTLeft2 = new Image(ClassLoader.getSystemResource("MagicalTortoise/MagicalTortoiseLeft2.png").toString());
		mTRight1 = new Image(ClassLoader.getSystemResource("MagicalTortoise/MagicalTortoiseRight1.png").toString());
		mTRight2 = new Image(ClassLoader.getSystemResource("MagicalTortoise/MagicalTortoiseRight2.png").toString());
	}
	public static void loadEyeOfQwifot() {
		eQ1 =  new Image(ClassLoader.getSystemResource("EyeOfQwifot/EyeOfQwifot1.png").toString());
		eQ2 =  new Image(ClassLoader.getSystemResource("EyeOfQwifot/EyeOfQwifot2.png").toString());
		eQDead1 =  new Image(ClassLoader.getSystemResource("EyeOfQwifot/EyeOfQwifotDead1.png").toString());
		eQDead2 =  new Image(ClassLoader.getSystemResource("EyeOfQwifot/EyeOfQwifotDead2.png").toString());
		eQAtk = new Image(ClassLoader.getSystemResource("EyeOfQwifot/EyeOfQwifotAtk.png").toString());
	}
	public static void loadShadowPot() {
		sPLeft1 =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotLeft1.png").toString());
		sPLeft2 =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotLeft2.png").toString());
		sPLeftAtk =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotLeftAtk.png").toString());
		sPRight1 =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotRight1.png").toString());
		sPRight2 =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotRight2.png").toString());
		sPRightAtk =  new Image(ClassLoader.getSystemResource("ShadowPot/ShadowPotRightAtk.png").toString());
	}
	public static void loadMoleDerKaiser() {
		mole = new Image(ClassLoader.getSystemResource("MoleDerKaiser/normalMole.png").toString());
		moleDerKaiser = new Image(ClassLoader.getSystemResource("MoleDerKaiser/moleDerKaiser.png").toString());
		moleDead = new Image(ClassLoader.getSystemResource("MoleDerKaiser/normalMoleDead.png").toString());
		moleDerKaiserDead = new Image(ClassLoader.getSystemResource("MoleDerKaiser/moleDerKaiserDead.png").toString());
		mole1 = new Image(ClassLoader.getSystemResource("MoleDerKaiser/normalMole1.png").toString());
		moleDerKaiser1 = new Image(ClassLoader.getSystemResource("MoleDerKaiser/moleDerKaiser1.png").toString());
	}
	public static void loadLlaristicKnight() {

		lKLeft1 = new Image(ClassLoader.getSystemResource("llaristicKnight/llaristicKnightLeft1.png").toString());
		lKLeft2 = new Image(ClassLoader.getSystemResource("llaristicKnight/llaristicKnightLeft2.png").toString());
		lKLeftAtk = new Image(ClassLoader.getSystemResource("llaristicKnight/llaristicKnightLeftAtk.png").toString());
		lKRight1 = new Image(ClassLoader.getSystemResource("llaristicKnight/llaristicKnightRight1.png").toString());
		lKRight2 = new Image(ClassLoader.getSystemResource("llaristicKnight/llaristicKnightRight2.png").toString());
		lKRightAtk = new Image(ClassLoader.getSystemResource("llaristicKnight/llaristicKnightRightAtk.png").toString());
		lKDead = new Image(ClassLoader.getSystemResource("llaristicKnight/llaristicKnightDead.png").toString());
	}
	public static void loadProjectile() {
		ball = new Image(ClassLoader.getSystemResource("ShadowPot/ball.png").toString());
		beam = new Image(ClassLoader.getSystemResource("swordBeam/beamRight.png").toString());
	}
	public static void loadOther() {
		//Overlay
		pauseOverlay = new Image(ClassLoader.getSystemResource("pause/PauseOverlay.png").toString());
		gameOverOverlay = new Image(ClassLoader.getSystemResource("pause/GameOver.png").toString());
		wingameOverlay = new Image(ClassLoader.getSystemResource("pause/Victory.png").toString());
		// StatusBar
		healthBar = new Image(ClassLoader.getSystemResource("other/health_power_bar.png").toString());

	}
	public void add(IRenderable entity) {
//		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
//		for (IRenderable x : entities) {
//			if (x instanceof Player)
//				System.out.println("player");
//
//		}
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
