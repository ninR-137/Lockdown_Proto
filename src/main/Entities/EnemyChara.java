package main.Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Constants.*;
import static main.Constants.soldier;

public class EnemyChara {
    //This position is relative off of its center
    private double positionX;
    private double positionY;
    private int columnCount = 0;
    private BufferedImage img;
    private int id;
    public EnemyChara(int columnCount){
        this.columnCount = columnCount;
        img = SickDude;
        positionX = 1060; // AT THE END OF THE SCREEN
        positionY = paddingTop + ((columnCount * gridHeight) - (gridHeight/2)) - img.getHeight()/2;
    }


    public boolean shouldDisappear(){

        return positionX < paddingLeft - 80;
    }

    public int getColumn() {
        return columnCount;
    }


    public double getPositionX() {
        return positionX;
    }

    public void updatePosition(){
        positionX -= 1;
    }

    public void render(Graphics g){
        g.drawImage(img, (int)positionX, (int)positionY, null);
        //g.drawRect(positionX, positionY, 10, 10);
    }

    public int getId() {
        return id;
    }
}
