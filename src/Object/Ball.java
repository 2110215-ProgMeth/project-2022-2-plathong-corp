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
        solidScreen = new Rectangle(screenX+solidArea.getX(),screenY+solidArea.getY(),solidArea.getWidth(),solidArea.getHeight());
        RenderableHolder.shootSound.play(0.1);
        image = RenderableHolder.ball;
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO Auto-generated method stub
        gc.drawImage(image, screenX, screenY);
//        drawHitbox(gc);
    }

}