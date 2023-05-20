package Object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class SwordBeam extends Projectile{

    public SwordBeam(double worldX, double worldY, double angle, GameLogic gameLogic) {
        super(worldX, worldY, angle, gameLogic);
        speed = 5;
        xspeed = Math.cos(angle) * speed;
        yspeed = Math.sin(angle) * speed;
        dmg = 20;
        if(Math.abs(xspeed)>Math.abs(yspeed))
            solidArea = new Rectangle(0, -16, 16, 128);
        else
            solidArea = new Rectangle(-16,0,128,16);
        solidScreen = new Rectangle(screenX+solidArea.getX(),screenY+solidArea.getY(),solidArea.getWidth(),solidArea.getHeight());
        RenderableHolder.shootSound.play(0.1);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO Auto-generated method stub
        drawHitbox(gc);
    }

}