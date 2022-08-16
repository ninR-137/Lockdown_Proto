package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Constants {
    public static final int SCREEN_WIDTH = 1060;
    public static final int SCREEN_HEIGHT = 660;
    public static final int paddingSide= 35;
    public static final int paddingTop = 50;
    public static final int paddingBottom = 30;
    public static final int estTitleHeight = 40;
    public static final int gridRowCount = 9;
    public static final int gridColumnCount = 5;
    public static final int lawnWidth = (Constants.SCREEN_WIDTH - (paddingSide*2)); //Subtract the padding then divide by the number of columns
    public static final int lawnHeight = (Constants.SCREEN_HEIGHT - estTitleHeight - (paddingTop+paddingBottom)); //40 is the Title Height, for some reason its taken accounted in 660
    public static final int gridWidth = lawnWidth / gridRowCount;
    public static final int gridHeight = lawnHeight /gridColumnCount;

    //NOT SO CONSTANT VARIABLES
    public static BufferedImage testAtlas;
    public static MainGameFrame mgf;

    public static int mouseX, mouseY;

    public static void setUp(MainGameFrame mgf){
        Constants.mgf = mgf;
        InputStream is = mgf.getClass().getResourceAsStream("/Resources/spriteatlas.png");

        try{
            testAtlas = ImageIO.read(is);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
