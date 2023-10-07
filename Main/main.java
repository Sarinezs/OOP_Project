import java.awt.*;
import java.net.*;
import javax.swing.*;


public class main extends JFrame{
    JLabel name = new JLabel("suriya");

    
    main(){
        JPanel p1 = new JPanel();
        add(p1);
        add(new drawimage());
    }

    public class drawimage extends JPanel{
        URL Backgroundimage = this.getClass().getResource("image/background.png");
        Image B_image = new ImageIcon(Backgroundimage).getImage();

        URL Mainimage = this.getClass().getResource("image/Hero.png");
        Image M_image = new ImageIcon(Mainimage).getImage();

        URL Bossimage = this.getClass().getResource("image/Boss.png");
        Image Boss_image = new ImageIcon(Bossimage).getImage();


        drawimage(){

        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(B_image,0,0,getWidth(),getHeight(),this);
            g.drawImage(M_image,0,410,380,200,this);
            g.drawImage(Boss_image,500,190,800,534,this);
        }
        
    }
    public static void main(String[] args){
        main frame = new main();
        
        frame.setTitle("game");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setFocusable(true);

        // GamePanel gamepanel = new GamePanel();
        frame.setVisible(true);
    }
}