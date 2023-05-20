package Object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.entity.Player;
import logic.game.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public abstract class Projectile implements IRenderable {
	protected Image image;
	protected double worldX, worldY;
	public double screenX, screenY;
	protected Rectangle solidArea,solidScreen;
	protected double angle;
	protected int dmg, speed;
	protected int z, radius;
	protected boolean visible, destroyed;
	public GameLogic gameLogic;
	protected double xspeed, yspeed;

	public Projectile(double worldX, double worldY, double angle, GameLogic gameLogic) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.gameLogic = gameLogic;
		this.angle = angle;
		this.z = 10;
		this.visible = true;
		this.destroyed = false;
	}

	public void update() {
		worldX += xspeed;
		worldY += yspeed;
		screenX = worldX - gameLogic.getPlayer().getWorldX() + gameLogic.getPlayer().screenX;
		screenY = worldY - gameLogic.getPlayer().getWorldY() + gameLogic.getPlayer().screenY;
		boolean isOut = screenX < 0 || screenX > 1280 || screenY < 0 || screenY > 720;
		if (isOut) {
			destroyed = true;
		}
		solidScreen = new Rectangle(screenX+solidArea.getX(),screenY+solidArea.getY(),solidArea.getWidth(),solidArea.getHeight());
		checkEnemyHit();
	}

	public void checkEnemyHit() {
		Player p =gameLogic.getPlayer();
		int x = (int) p.getScreenX();
		int y = (int) p.getScreenY();
		int width = (int) p.getSolidArea().getWidth();
		int height = (int) p.getSolidArea().getHeight();
		if (solidScreen.intersects(x,y,width,height)) {
			p.changeHealthTo(p.getCurrentHealth() - dmg);
			destroyed = true;
		}
	}

	public Rectangle getSolidArea() {
		return solidArea;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	public abstract void draw(GraphicsContext gc);
	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub

		return destroyed;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible;
	}

	// Debugger
	public void drawHitbox(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.RED);
		gc.strokeRect(solidScreen.getX(), solidScreen.getY(), solidScreen.getWidth(),
				solidScreen.getHeight());
	}
}