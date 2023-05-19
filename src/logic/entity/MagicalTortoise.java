package logic.entity;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.game.GameLogic;
import sharedObject.RenderableHolder;

public class MagicalTortoise extends Entity{
    private Image image = RenderableHolder.MTRight1;
    protected ArrayList<String> dialogues;

    public MagicalTortoise(int x, int y, GameLogic gameLogic) {
        super(x, y, gameLogic);
        this.dialogues = new ArrayList<String>();
        // TODO Auto-generated constructor stub
        setDialogues();
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO Auto-generated method stub
        switch(direction) {
        case "right":
                if (gameLogic.getCounter()/10%2==1) 
                    image = RenderableHolder.MTRight1;

                else
                    image = RenderableHolder.MTRight2;
            break;
        case "left":
                if (gameLogic.getCounter()/10%2==1) 
                    image = RenderableHolder.MTLeft1;

                else
                    image = RenderableHolder.MTLeft2;

            break;
        }
        gc.drawImage(image, screenX, screenY);

    }

    @Override
    public void attack(Entity p) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    	super.update();
        Player player = gameLogic.getPlayer();
        double xDirection =  Math.cos(Math.atan2(player.worldY-worldY,player.worldX-worldX));
        if(xDirection<0)
            direction = "left";
        else
            direction = "right";
    }

    public void setDialogues() {
        dialogues.add("Hi , I'm GAY");
        dialogues.add("This is GayZone So u r Gay");
    }

    public boolean playerfound(){
        int rangeX = (int) Math.abs(worldX-gameLogic.getPlayer().getWorldX());
        int rangeY = (int) Math.abs(worldY-gameLogic.getPlayer().getWorldY());
        int range = (int) Math.sqrt(Math.pow(rangeX, 2) + Math.pow(rangeY, 2));
        return range<30;
    }
    public String getDialogues(int i) {
        return dialogues.get(i);
    }

    public int getDialoguesSize() {
        return dialogues.size();
    }

    public void speak() {

    }
}
