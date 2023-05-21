package Object;

import Util.Vector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.entity.Player;
import logic.game.GameLogic;
import sharedObject.IRenderable;

public abstract class Projectile implements IRenderable {
    protected Image image;
    protected Vector<Double> worldPos;
    protected Vector<Double> screenPos;
    protected Rectangle solidArea, solidScreen;
    protected double angle;
    protected int dmg, speed;
    protected int z, radius;
    protected boolean visible, destroyed;
    protected GameLogic gameLogic;
    protected double xspeed, yspeed;

    public Projectile(double worldX, double worldY, double angle, GameLogic gameLogic) {
        worldPos = new Vector<Double>(worldX, worldY);
        screenPos = new Vector<Double>(worldX, worldY);
        this.gameLogic = gameLogic;
        this.angle = angle;
        this.z = 10;
        this.visible = true;
        this.destroyed = false;
    }

    public void update() {
        worldPos.setX(worldPos.getX() + xspeed);
        worldPos.setY(worldPos.getY() + yspeed);
        screenPos.setX(worldPos.getX() - gameLogic.getPlayer().getWorldPos().getX()
                + gameLogic.getPlayer().getScreenPos().getX());
        screenPos.setY(worldPos.getY() - gameLogic.getPlayer().getWorldPos().getY() + gameLogic.getPlayer().getScreenPos().getY());
        boolean isOut = (screenPos.getX() < 0 && xspeed<0) ||(screenPos.getX() > 1280 && xspeed>0)||
        		(screenPos.getY() < 0 && yspeed <0) || (screenPos.getY() > 720 && yspeed >0);
        if (isOut) {
            destroyed = true;
        }
        solidScreen = new Rectangle(screenPos.getX() + solidArea.getX(), screenPos.getY() + solidArea.getY(), solidArea.getWidth(),
                solidArea.getHeight());
        checkEnemyHit();
    }

    public void checkEnemyHit() {
        Player p = gameLogic.getPlayer();
        double x = (double) p.getScreenPos().getX();
        double y = (double) p.getScreenPos().getY();
        int width = (int) p.getSolidArea().getWidth();
        int height = (int) p.getSolidArea().getHeight();
        if (solidScreen.intersects(x, y, width, height)) {
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
        gc.strokeRect(solidScreen.getX(), solidScreen.getY(), solidScreen.getWidth(), solidScreen.getHeight());
    }
}