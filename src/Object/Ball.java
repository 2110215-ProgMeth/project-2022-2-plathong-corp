package Object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class Ball extends Projectile{

    public Ball(double worldX, double worldY, double angle, GameLogic gameLogic) {
        super(worldX, worldY, angle, gameLogic);
        speed = 7;
        xspeed = Math.cos(angle) * speed;
        yspeed = Math.sin(angle) * speed;
        dmg = 5;
        solidArea = new Rectangle(0, 0, 8, 8);
        solidScreen = new Rectangle(screenPos.getX()+solidArea.getX(),screenPos.getY()+solidArea.getY(),solidArea.getWidth(),solidArea.getHeight());
        RenderableHolder.shootSound.play(0.1);
        image = RenderableHolder.ball;
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO Auto-generated method stub
        gc.drawImage(image, screenPos.getX(), screenPos.getY());
//        drawHitbox(gc);
    }

}