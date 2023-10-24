
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener{
    public ImageIcon bgimg = new ImageIcon(this.getClass().getResource("Entity/Image/background.png"));
    // public ImageIcon heroimg = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/Idle/HeroKnight_Idle_0.png"));
    Player p = new Player();
    boolean iswalk = false;
    boolean isattack = false;
    boolean isidle = true;

    Thread actortime = new Thread(new Runnable() {
       public void run(){
        while(true){
            try{
                Thread.sleep(100);
            }
            catch(Exception e){}
            p.idle_count++;
            repaint();
            
        }
       } 
    });

    Thread time = new Thread(new Runnable(){
        public void run(){
            while(true){
            try{
                Thread.sleep(10);
            }
            catch(Exception e){}
            repaint();
            
            }
        }
    });

    Thread attack = new Thread(new Runnable() {
        public void run(){
            while(true){
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){}
                if(isattack){
                    p.attack_count++;
                    repaint();
                }
                if(p.attack_count >= 8){ // reset after attack
                    p.attack_count = 0;
                    isidle = true;
                    isattack = false;
                }
            }
        }
    });

    public GamePanel(){
        this.setFocusable(true);
        this.setLayout(null);

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                iswalk = true;
                int a = e.getKeyCode();
                if(a == KeyEvent.VK_A){
                    p.x -= 5;
                    p.run_count++;
                }
                else if(a == KeyEvent.VK_D){
                    p.x += 5;
                    p.run_count++;
                }
                if(p.run_count >= 9){
                    p.run_count = 0;
                }
            }

            public void keyReleased(KeyEvent e){
                iswalk = false;
                p.run_count = 0;
                
                
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                isattack = true;
                isidle = false;
            }

            // public void mouseReleased(MouseEvent e){ // bolck
            //     isattack = false;
            //     isidle = true;
            //     p.attack_count = 0;
            // }
        });
        
        time.start();
        actortime.start();
        attack.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgimg.getImage(), 0, 0, getWidth(), getHeight(), this);
        
        if(iswalk){
            g.drawImage(p.P_run[p.run_count].getImage(), p.x, 440, 300, 165, this);
        }
        else if(isidle){
            if(p.idle_count >= 8){
                p.idle_count = 0;
            }
            g.drawImage(p.P_idle[p.idle_count].getImage(), p.x, 440, 300, 165, this);
        }

        if(isattack){
            g.drawImage(p.P_attack[p.attack_count].getImage(), p.x, 440, 300, 165, this);
            
        }
        else{
            // if(p.idle_count >= 8){
            //     p.idle_count = 0;
            // }
            // g.drawImage(p.P_idle[p.idle_count].getImage(), p.x, 440, 300, 165, this);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

}