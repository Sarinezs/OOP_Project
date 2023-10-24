package image_insert;

import java.awt.*;
import java.net.URL; 
import javax.swing.*;

public class insert extends JFrame{
    public ImageIcon[] im = new ImageIcon[7];

    public class url extends JPanel{

       
        public URL Eny_image = this.getClass().getResource("image/Boss.png");
        public Image Enemy_image = new ImageIcon(Eny_image).getImage();

        public Image get_eny_image(){
            return Enemy_image;
        }   
    }

    public void draw(Graphics g){
            url a = new url();
            // for(int i = 0; i<im.length; i++){
                // g.drawImage(im[i].getImage(),400,190,800,534,this);
            // }
            g.drawImage(a.get_eny_image(),400,190,800,534,this);
    }

    // public insert(){
    //     for(int i = 0; i<im.length; i++){
    //         im[i] = new ImageIcon(this.getClass().getResource("image/"+(i+1)+".png"));
    //     }
    // }

}

