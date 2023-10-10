package image_insert;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class insert extends JFrame{
    public class url extends JPanel{
        public URL Eny_image = this.getClass().getResource("image/Boss.png");
        public Image Enemy_image = new ImageIcon(Eny_image).getImage();

        public Image get_eny_image(){
            return Enemy_image;
        }

        
    }

    public void draw(Graphics g){
            url a = new url();
            g.drawImage(a.get_eny_image(),400,190,800,534,this);
    }
   

}
