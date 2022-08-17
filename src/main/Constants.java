package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.InputStream;

public class Constants {
    public static final int SCREEN_WIDTH = 1060;
    public static final int SCREEN_HEIGHT = 660;
    public static final int paddingLeft = 140;
    public static final int paddingRight = 45;
    public static final int paddingSide= 40;
    public static final int paddingTop = 100;
    public static final int paddingBottom = 30;
    public static final int estTitleHeight = 40;
    public static final int gridRowCount = 9;
    public static final int gridColumnCount = 5;
    public static final int lawnWidth = (Constants.SCREEN_WIDTH - (paddingLeft+paddingRight)); //Subtract the padding then divide by the number of columns
    public static final int lawnHeight = (Constants.SCREEN_HEIGHT - estTitleHeight - (paddingTop+paddingBottom)); //40 is the Title Height, for some reason its taken accounted in 660
    public static final int gridWidth = lawnWidth / gridRowCount;
    public static final int gridHeight = lawnHeight /gridColumnCount;
    public static final int selectorPaddingLeft = 20;



    //NOT SO CONSTANT VARIABLES
    public static BufferedImage testAtlas;
    public static BufferedImage taxPayer;
    public static BufferedImage nurse;
    public static BufferedImage doctor;
    public static BufferedImage soldier;
    public static BufferedImage fire;
    public static BufferedImage testField;
    public static BufferedImage SickDude;
    public static BufferedImage testFieldMiddle;
    public static BufferedImage testFieldBottom;
    public static MainGameFrame mgf;
    public static int mouseX, mouseY;
    public static int globalCharaID = -1;

    public static void setUp(MainGameFrame mgf){
        Constants.mgf = mgf;

        try{
            InputStream is = mgf.getClass().getResourceAsStream("/Resources/spriteatlas.png");
            testAtlas = ImageIO.read(is);
            is = mgf.getClass().getResourceAsStream("/Resources/tax.png");
            taxPayer = ImageIO.read(is);
            is = mgf.getClass().getResourceAsStream("/Resources/nurse.png");
            nurse = ImageIO.read(is);
            is = mgf.getClass().getResourceAsStream("/Resources/doctor.png");
            doctor = ImageIO.read(is);
            is = mgf.getClass().getResourceAsStream("/Resources/soldier.png");
            soldier = ImageIO.read(is);
            is = mgf.getClass().getResourceAsStream("/Resources/Fire.png");
            fire = ImageIO.read(is);
            is = mgf.getClass().getResourceAsStream("/Resources/TestFieldTop.png");
            testField = ImageIO.read(is);
            is = mgf.getClass().getResourceAsStream("/Resources/TestFieldMiddle.png");
            testFieldMiddle = ImageIO.read(is);
            is = mgf.getClass().getResourceAsStream("/Resources/TestFieldBottom.png");
            testFieldBottom = ImageIO.read(is);
            is = mgf.getClass().getResourceAsStream("/Resources/Sickdude.png");
            SickDude = ImageIO.read(is);

            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static BufferedImage colorImage(BufferedImage loadImg, Color color) {

        //CLONE
        ColorModel cm = loadImg.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = loadImg.copyData(null);
        BufferedImage image = new BufferedImage(cm, raster, isAlphaPremultiplied, null);

        //TINT THE CLONE AND RETURN
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y), true);
                int r = (pixelColor.getRed() + color.getRed()) / 2;
                int g = (pixelColor.getGreen() + color.getGreen()) / 2;
                int b = (pixelColor.getBlue() + color.getBlue()) / 2;
                int a = pixelColor.getAlpha();
                int rgba = (a << 24) | (r << 16) | (g << 8) | b;
                image.setRGB(x, y, rgba);
            }
        }

        return image;
    }
}
