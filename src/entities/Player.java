package entities;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;
    public Player(KeyHandler keyH, GamePanel gp) {
        this.gp = gp;
        this.keyHandler = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){

        try {
            up1    = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking_sprites/boy_up_1.png"));
            up2    = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking_sprites/boy_up_2.png"));
            down1  = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking_sprites/boy_down_1.png"));
            down2  = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking_sprites/boy_down_2.png"));
            left1  = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking_sprites/boy_left_1.png"));
            left2  = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking_sprites/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking_sprites/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking_sprites/boy_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if (keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right ){
            if (keyHandler.up){
                direction = "up";
                y -= speed;
            }
            if (keyHandler.down) {
                direction = "down";
                y += speed;
            }
            if (keyHandler.left){
                direction = "left";
                x -= speed;
            }
            if (keyHandler.right) {
                direction = "right";
                x += speed;
            }
            spriteCounter ++;
            if(spriteCounter > 11){
                if(spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum == 1)
                    image = up1;
                if (spriteNum == 2)
                    image = up2;
                break;
            case "down":
                if (spriteNum == 1)
                    image = down1;
                if (spriteNum == 2)
                    image = down2;
                break;
            case "left":
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;
                break;
            case "right":
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;
                break;
        };

        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
