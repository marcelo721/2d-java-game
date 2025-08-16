package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //FPS
    int FPS = 60;

    // SCREEN SETTING
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize; //768 pixels
    final int screenHeight = maxScreenRow * tileSize; // 576 pixels

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    //player default position
    int playerX, playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread !=null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta --;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        if (keyHandler.up){
            playerY -= playerSpeed;
        }
        if (keyHandler.down) {
            playerY += playerSpeed;
        }
        if (keyHandler.left){
            playerX -= playerSpeed;
        }
        if (keyHandler.right) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(playerX, playerY, tileSize, tileSize);
        g2d.dispose();
    }
}
