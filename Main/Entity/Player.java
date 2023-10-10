package Entity;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

// import image_insert.insert.url;

public class Player extends entity{
    Graphics g;
    public Player(){
       Player1 hero = new Player1();
    //    hero.draw(g);
    }

    public class Player1 extends JFrame{
        Graphics g;
        Player1(){
            // draw(g);
        }

        public class ImageURL extends JPanel{
        public URL Heroimage = this.getClass().getResource("image/Hero.png");
        public Image Hero_image = new ImageIcon(Heroimage).getImage();

        
    }

    public Image get_hero_image(){
            return Hero_image;
        }
    
    }

    public void draw(Graphics g){
        ImageURL img = new ImageURL();
        g.drawImage(img.get_hero_image(),0,410,380,200,this);
    }
    
}
