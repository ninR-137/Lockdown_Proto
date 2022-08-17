package main.Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import static main.Constants.*;
/**
 * A prototype Character class,
 * needs to be refactored
 * only purpose atm, is for debug rendering
 */
public class Chara {

    //This position is relative off of its center
    private int positionX;
    private int positionY;
    private int row = 0, column = 0;
    private BufferedImage img;
    private int id;
    public Chara(int x, int y, int id){
        this.id = id;
        switch (id) {
            case 0 -> img = taxPayer;
            case 1 -> img = nurse;
            case 2 -> img = doctor;
            case 3 -> img = SickDude;
            default -> {
                System.out.println("ERROR");
                return;
            }
        }

        assert img != null;
        positionX = x - img.getWidth()/2;
        positionY = y - img.getHeight()/2;
    }

    public void setRowColumn(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void render(Graphics g){
            g.drawImage(img, positionX, positionY, null);
    }

    public int getId() {
        return id;
    }
}
