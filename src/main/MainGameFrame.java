package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.*;


@SuppressWarnings("ALL")
public class MainGameFrame extends JFrame{

    private GamePanel gamePanel;
    static boolean clickedLockdown = false;
    BufferedImage img;

    MainGameFrame(){
        Constants.setUp(this);

        this.setTitle("Let OS Play");
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null); //Center frame in screen
        gamePanel = new GamePanel();

        this.add(gamePanel);
        gamePanel.start();
        this.setVisible(true);
    }


    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        //new SplashScreen();
        new MainGameFrame();
    }


}
