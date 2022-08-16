package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Constants {
    public static final int SCREEN_WIDTH = 1060;
    public static final int SCREEN_HEIGHT = 660;
    public static BufferedImage testAtlas;
    public static MainGameFrame mgf;

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
