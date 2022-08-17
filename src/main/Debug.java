package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Debug {

    Random random;
    long start = System.nanoTime();
    long convert;
    double elapsedTimeInSecond;

    public void countSeconds(){
        long end = System.nanoTime();
        long elapsedTime = end - start;
        //System.out.println(elapsedTime);
        // 1 second = 1_000_000_000 nano seconds
        elapsedTimeInSecond += (double) elapsedTime / 1_000_000_000;
        long s = (long) elapsedTimeInSecond;
        System.out.println(s + " seconds");
        start = end;
    }

    public void resetTime(){
        start = System.nanoTime();
        convert = 0;
        elapsedTimeInSecond = 0;
    }

    public Debug(){
        random = new Random();
    }
    public void getSprites(ArrayList arrayList){
        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){
                arrayList.add(Constants.testAtlas.getSubimage(x*32, y*32, 32 ,32));
            }
        }
    }

    public Color getRandomColor(){
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r,g,b);
    }

    public void drawRandomSprites(Graphics g, ArrayList<BufferedImage> arrayList){
        for(int x = 0; x < (Constants.SCREEN_WIDTH/32) ; x++){
            for(int y = 0; y < (Constants.SCREEN_HEIGHT/32); y++){
                g.drawImage(arrayList.get(random.nextInt(100)), 32*x, 32*y, null);
            }
        }
    }
}
