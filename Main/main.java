import java.awt.*;
import java.net.*;
import javax.swing.*;

import Entity.Player;
import image_insert.insert;

// import image_insert.insert;

public class main extends JFrame{

    
    main(){
        JPanel p1 = new JPanel();
        add(p1);
        add(new drawimage());
    }

    public class drawimage extends JPanel{
        URL Backgroundimage = this.getClass().getResource("Entity/image/background.png");
        Image B_image = new ImageIcon(Backgroundimage).getImage();

        drawimage(){

        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(B_image,0,0,getWidth(),getHeight(),this);
            // g.drawImage(M_image,0,410,380,200,this);
            // g.drawImage(Boss_image,400,190,800,534,this);
            Player Hero = new Player();
            Hero.draw(g);
            insert a = new insert();
            a.draw(g);

        }
        
    }
    public static void main(String[] args){
        main frame = new main();
        
        frame.setTitle("game");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setFocusable(true);
        System.out.println();
        frame.setVisible(true);
    }
}