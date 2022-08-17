package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
            is = mgf.getClass().getResourceAsStream("/Resources/TestField.png");
            testField = ImageIO.read(is);
            is = mgf.getClass().getResourceAsStream("/Resources/Sickdude.png");
            SickDude = ImageIO.read(is);

            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
