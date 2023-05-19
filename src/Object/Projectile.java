package Object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.entity.Player;
import logic.game.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Projectile implements IRenderable {

	protected double worldX, worldY;
	public double screenX, screenY;
	private Rectangle solidArea,solidScreen;
	private double angle;
	private int dmg, speed;
	protected int z, radius;
	protected boolean visible, destroyed;
	public GameLogic gameLogic;
	private double xspeed, yspeed;

	public Projectile(double worldX, double worldY, double angle, GameLogic gameLogic) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.gameLogic = gameLogic;
		this.angle = angle;
		this.speed = 3;
		this.z = 10;
		this.visible = true;
		this.destroyed = false;
		this.xspeed = Math.cos(angle) * speed;
		this.yspeed = Math.sin(angle) * speed;
		this.dmg = 10;
		solidArea = new Rectangle(0, 0, 8, 8);
		solidScreen = new Rectangle(screenX,screenY,8,8);
	}

	public void update() {
		worldX += xspeed;
		worldY += yspeed;
		screenX = worldX - gameLogic.getPlayer().getWorldX() + gameLogic.getPlayer().screenX;
		screenY = worldY - gameLogic.getPlayer().getWorldY() + gameLogic.getPlayer().screenY;
		solidArea.setX(screenX);
		solidArea.setY(screenY);
//		System.out.println("X =" + xspeed + " Y = " + yspeed);
		boolean isOut = screenX < 0 || screenX > 1280 || screenY < 0 || screenY > 720;
		if (isOut) {
			destroyed = true;
		}
		checkEnemyHit();
	}

	public void checkEnemyHit() {
		Player p =gameLogic.getPlayer();
		int x = (int) p.getScreenX();
		int y = (int) p.getScreenY();
		int width = (int) p.getSolidArea().getWidth();
		int height = (int) p.getSolidArea().getHeight();
		boolean overlap = solidArea.intersects(x,y,width,height);
//		System.out.println("Overlap = " + overlap);
//		System.out.println("X = " + x + " Y = " + y);
		if (overlap) {
			p.changeHealthTo(p.getCurrentHealth() - dmg);
			destroyed = true;
		}
		solidArea.setX(screenX);
		solidArea.setY(screenY);
	}

	public Rectangle getSolidArea() {
		return solidArea;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.ball, screenX, screenY);
		drawHitbox(gc);
	}

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
		gc.strokeRect(solidArea.getX(), solidArea.getY(), solidArea.getWidth(),
				solidArea.getHeight());
	}
}
