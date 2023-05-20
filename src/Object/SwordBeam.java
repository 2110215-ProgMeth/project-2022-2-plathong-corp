package Object;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class SwordBeam extends Projectile{
	ImageView rotatedImage;
	SnapshotParameters params;
    public SwordBeam(double worldX, double worldY, double angle, GameLogic gameLogic) {
        super(worldX, worldY, angle, gameLogic);
        speed = 5;
        xspeed = Math.cos(angle) * speed;
        yspeed = Math.sin(angle) * speed;
        dmg = 20;
        image = RenderableHolder.beamRight;
    	rotatedImage = new ImageView(image);
    	rotatedImage.setRotate(angle*180/Math.PI);
    	params = new SnapshotParameters();
    	params.setFill(Color.TRANSPARENT);
        if(Math.abs(xspeed)>Math.abs(yspeed)) {
            solidArea = new Rectangle(0, -32, 32, 128);
        }
        else {
            solidArea = new Rectangle(-32,0,128,32);
        }
        solidScreen = new Rectangle(screenX+solidArea.getX(),screenY+solidArea.getY(),solidArea.getWidth(),solidArea.getHeight());
        RenderableHolder.shootSound.play(0.1);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO Auto-generated method stub
    	gc.drawImage(rotatedImage.snapshot(params, null), screenX, screenY);
    	
//        drawHitbox(gc);
    }

}