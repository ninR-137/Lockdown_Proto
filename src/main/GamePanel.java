package main;

import main.Entities.Chara;
import main.Entities.EnemyChara;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static main.Constants.*;


/**
 * This class serves as the Panel for the Lockdown game
 * Contains GameLoop for the rendering and update logic
 */
public class GamePanel extends JPanel implements Runnable{
    public ArrayList<BufferedImage> sprites = new ArrayList<>();
    private ArrayList<GridHandler> grids = new ArrayList<>();
    private ArrayList<Chara> charas = new ArrayList<>();
    private ArrayList<EnemyChara> enemyCharas = new ArrayList<>();
    private ArrayList<SelectorHandler> selectors = new ArrayList<>();
    private Thread gameThread;
    private Debug debug;


    private BufferedImage tempImg;
    public GamePanel(){
        setFocusable(true);
        debug = new Debug();
        debug.getSprites(sprites);

        for(int x = 0; x < gridRowCount; x++){
            for(int y = 0; y < gridColumnCount; y++){
                grids.add(new GridHandler(x, y));
            }
        }


        for(int x = 0; x < 5; x++){
            selectors.add(new SelectorHandler(x));
        }


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                switch (e.getKeyChar()){
                    case '1' ->{
                        enemyCharas.add(new EnemyChara(1));
                    }
                    case '2' ->{
                        enemyCharas.add(new EnemyChara(2));
                    }
                    case '3' ->{
                        enemyCharas.add(new EnemyChara(3));
                    }
                    case '4' ->{
                        enemyCharas.add(new EnemyChara(4));
                    }
                    case '5' ->{
                        enemyCharas.add(new EnemyChara(5));
                    }
                }
            }
        });

        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mouseX = e.getX();
                mouseY = e.getY();



                //SELECTORS
                for(SelectorHandler selector : selectors){
                    selector.setCollision(mouseX, mouseY);
                    if (selector.isClicked()){
                        globalCharaID = selector.getColumnIndex();
                    }
                }

                ///GRIDS
                for (GridHandler grid : grids) {
                    grid.setCollision(mouseX, mouseY);
                    if (grid.isClicked()) {
                        int rowCount = grid.getRowCount();
                        int columnCount = grid.getColumnCount();
                        //System.out.println("Row: " + rowCount + "| Column: " + columnCount);
                        grid.setCollision(false);

                        if(grid.isOccupied()) {
                            for(int i = 0; i < charas.size(); i++){
                                if(charas.get(i).getRow() == rowCount && charas.get(i).getColumn() == columnCount && globalCharaID == 4){
                                    charas.remove(i);
                                    grid.setOccupied(false);
                                    i--;
                                }
                            }
                        } else {
                            if(globalCharaID == 4 || globalCharaID == -1) return;
                            Chara testCharacter = new Chara(grid.getXcenter(), grid.getYcenter(), globalCharaID);
                            testCharacter.setRowColumn(rowCount, columnCount);
                            charas.add(testCharacter);
                            grid.setOccupied(true);
                            globalCharaID = -1;

                            for(SelectorHandler selectorHandler : selectors){
                                selectorHandler.setCollision(false);
                            }
                        }
                    }
                }
            }
        });
    }


    public void start(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / 120.0;
        double timePerUpdate = 1000000000.0 / 60.0; //1 Second;
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
                //System.out.println("FPS: " + frames + "|" + " UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawGrid(g);
        for (Chara chara : charas) {
            chara.render(g);
        }

        for(EnemyChara enemyChara : enemyCharas){
            enemyChara.render(g);
        }
        //SELECTORS
        drawSelector(g);

    }


    public void update(){
        //debug.countSeconds();
        /*
        for(EnemyChara enemyChara : enemyCharas){
            enemyChara.update();
        }
        */

        for(int i = 0; i < enemyCharas.size(); i++){
            boolean skip = false;
            for(Chara chara : charas){
                boolean condition1 = chara.getBoundingX() >= enemyCharas.get(i).getPositionX();
                boolean condition2 = chara.getColumn() == enemyCharas.get(i).getColumn();

                if(condition1 && condition2) {
                    skip = true;
                    chara.setCollidedWithInfected(true);
                }
            }

            if(skip) continue;

            enemyCharas.get(i).updatePosition();
            if(enemyCharas.get(i).shouldDisappear()){
                enemyCharas.remove(i);
                i--;
            }
        }


    }

    private void drawGrid(Graphics g){
        //MARGIN
        g.drawRect(paddingLeft, paddingTop, lawnWidth,lawnHeight);
        int initY = paddingTop;
        int initX = paddingLeft;


        //DRAW THE GRID
        g.setColor(Color.BLACK);

        for(int x = 0; x < gridRowCount; x++) {
            for (int y = 0; y < gridColumnCount; y++) {
                g.drawRect(initX + (x * gridWidth), initY + (y * gridHeight), gridWidth, gridHeight);
                int Xcenter = initX + (x * gridWidth);
                int Ycenter = initY + (y * gridHeight);
                switch (y){
                    case 0 -> g.drawImage(testField,  Xcenter, Ycenter, null);
                    case gridColumnCount - 1-> g.drawImage(testFieldBottom,  Xcenter, Ycenter, null);
                    default -> g.drawImage(testFieldMiddle,  Xcenter, Ycenter, null);
                }
            }
        }

    }

    public void drawSelector(Graphics g){
        int initY = paddingTop;
        int initX = paddingLeft;
        //SELECTOR
        for(int x = 0; x < 5; x++){

            if(selectors.get(x).isClicked()){
                g.setColor(Color.red);
                g.fillRect( selectorPaddingLeft , initY + (x*gridHeight), gridWidth, gridHeight);
            } else {
                g.setColor(Color.gray);
                g.fillRect(selectorPaddingLeft, initY + (x * gridHeight), gridWidth, gridHeight);
            }
            int Xcenter = selectorPaddingLeft + (gridWidth/2);
            int Ycenter = paddingTop + ((x * gridHeight) + (gridHeight/2));


            switch (x) {
                case 0 -> tempImg = taxPayer;
                case 1 -> tempImg = nurse;
                case 2 -> tempImg = doctor;
                case 3 -> tempImg = soldier;
                case 4 -> tempImg = fire;
            }

            g.drawImage(tempImg, Xcenter - (tempImg.getWidth()/2), Ycenter - (tempImg.getHeight()/2), null);
        }
    }
}
