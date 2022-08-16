package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


/**
 * This class serves as the Panel for the Lockdown game
 * Contains GameLoop for the rendering and update logic
 */
public class GamePanel extends JPanel implements Runnable{

    private Random random;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private Thread gameThread;
    public GamePanel(){
        random = new Random();
        getSprites();
    }


    private void getSprites(){
        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){
                sprites.add(Constants.testAtlas.getSubimage(x*32, y*32, 32 ,32));
            }
        }
    }



    private Color getRandomColor(){
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r,g,b);
    }


    public void start(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / 120.0; //1 Second;
        double timePerUpdate = 1000000000.0 / 60.0;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;
        while(true){
            //RENDER
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = System.nanoTime();
                frames++;
            }
            //UPDATE
            if(System.nanoTime() - lastUpdate >= timePerUpdate){
                update();
                lastUpdate = System.nanoTime();
                updates++;
            }

            //CHECKING FPS AND UPS
            if(System.currentTimeMillis() - lastTimeCheck >= 1000){
                System.out.println("FPS: " + frames + "|" + " UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int x = 0; x < (Constants.SCREEN_WIDTH/32) ; x++){
            for(int y = 0; y < (Constants.SCREEN_HEIGHT/32); y++){
                g.drawImage(sprites.get(random.nextInt(100)), 32*x, 32*y, null);
            }
        }
    }

    public void update(){

    }
}
