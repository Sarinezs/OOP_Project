package Screen.test;
// package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player (GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        // getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed  = 4;
        // direction = "left";
    }

    // public void getPlayerImage(){
    //     try{
    //         left = ImageIO.read(getClass().getResourceAsStream("/OOP_image/space1_1.png"));
    //         right = ImageIO.read(getClass().getResourceAsStream("/OOP_image/space1.png"));
    //     }
    //     catch(IOException e){
    //         e.printStackTrace();
    //     }
    // }

    public void update(){

        if(keyH.upPressed == true){
            // direction = "right";
            y -= speed;
        }
        else if(keyH.downPressed == true){
            // direction = "left";
            y += speed;
        }
        else if(keyH.leftPressed == true){
            // direction = "left";
            x -= speed;
        }
        else if(keyH.rightPressed == true){
            // direction = "right";
            x += speed;
        }

    }
    public void draw(Graphics2D g2){

        g2.setColor(Color.black);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        // BufferedImage image = null;

        // switch(direction){
        // case "right":
        //     image = right;
        //     break;
        // case "left":
        //     image = left;
        //     break;
        // }

        // g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
