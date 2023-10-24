
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener{
    public ImageIcon bgimg = new ImageIcon(this.getClass().getResource("Entity/Image/background.png"));
    // public ImageIcon heroimg = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/Idle/HeroKnight_Idle_0.png"));
    Player p = new Player();

    Thread time = new Thread(new Runnable() {
       public void run(){
        while(true){
            try{
                Thread.sleep(80);
            }
            catch(Exception e){}
            p.idle_count++;
            repaint();
            
        }
       } 
    });

    public GamePanel(){
        this.setFocusable(true);
        this.setLayout(null);

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                int a = e.getKeyCode();
                if(a == KeyEvent.VK_A){
                    p.x -= 10;
                    p.run_count++;
                }
                else if(a == KeyEvent.VK_D){
                    p.x += 10;
                    p.run_count++;
                }
                if(p.run_count > 9){
                    p.run_count = 0;
                }
            }

            public void keyReleased(KeyEvent e){
                p.run_count = 0;
            }
        });
        time.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(p.idle_count == 8){
            p.idle_count = 0;
        }
        g.drawImage(bgimg.getImage(), 0, 0, getWidth(), getHeight(), this);
        g.drawImage(p.P_run[p.idle_count].getImage(), p.x, 300, 300, 165, this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

}