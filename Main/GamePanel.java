
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
    boolean isblock = false;
    boolean isidle = true;
    boolean isaction = false; // check ว่าตัวหลักกำลังใช้ท่าไรอยู่ไหม
    

    Death_Bringer d = new Death_Bringer();
    boolean isbossattack = false;
    boolean isbossaction = false;
    int Boss_delay = -1;

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

    Thread Boss = new Thread(new Runnable(){
        public void run(){
            while(true){
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){}
                if((d.x - p.x) <= 100){
                    isbossattack = true;
                    isbossaction = true;
                    // if(isbossattack && Boss_delay < 0){
                    //     d.attack_count++;
                    //     if(d.attack_count >= 10){
                    //         Boss_delay = 5;
                    //         isbossattack = false;
                    //     }
                    // }

                }
                else{
                    d.idle_count++;
                }
                repaint();
            }
        }
    });

    Thread Boss_attack = new Thread(new Runnable() {
        public void run(){
            while(true){
                if(isbossaction){
                    if(isbossattack && Boss_delay < 0){
                        d.attack_count++;
                        if(d.attack_count >= 10){
                            Boss_delay = 5;
                            isbossattack = false;
                        }
                    }
                }
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){}
                
                repaint();
            }
        }
    });

    Thread delay = new Thread(new Runnable() {
        public void run(){
            while(true){
                if(Boss_delay >= 0){
                    d.idle_count++;
                    Boss_delay -= 1;
                    repaint();
                }
                // if(isbossaction){
                //     if(isbossattack && Boss_delay < 0){
                //         d.attack_count++;
                //         if(d.attack_count >= 10){
                //             Boss_delay = 5;
                //             isbossattack = false;
                //         }
                //     }
                // }
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){}
                
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

    

    Thread walk = new Thread(new Runnable(){
        public void run(){
            while(true){
                if(iswalk){
                    try{
                        Thread.sleep(100);
                    }
                    catch(Exception e){}
                    repaint();
                }
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
                    isaction = false;
                }
            }
        }
    });

    Thread Block = new Thread(new Runnable() {
       public void run(){
        while(true){
            try{
                Thread.sleep(100);
            }
            catch(Exception e){}
            if(isblock){
                p.block_count++;
                repaint();
            }
            if(p.block_count >= 8){
                p.block_count = 0;
            }
        }
       } 
    });

    public GamePanel(){
        d.x = 500;

        this.setFocusable(true);
        this.setLayout(null);

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                if(isaction != true || iswalk == true){
                    iswalk = true;
                    isaction = true;
                    int a = e.getKeyCode();
                    if(a == KeyEvent.VK_A){
                        if(p.x >= -40){
                            p.x -= 10;
                            p.run_count++;    
                        }
                        else{
                            p.run_count++;
                        }
                        
                    }
                    else if(a == KeyEvent.VK_D){
                        if(p.x <= getWidth()){
                            p.x += 10;
                            p.run_count++;
                        }
                        
                    }
                    if(p.run_count >= 9){
                        p.run_count = 0;
                    }
                }
                
            }

            public void keyReleased(KeyEvent e){
                iswalk = false;
                isaction = false;
                p.run_count = 0;
                
                
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if(e.getButton() == MouseEvent.BUTTON1){
                    if(isaction != true){ // can't attack during guarding
                        isattack = true;
                        isidle = false;
                        isaction = true;
                    }
                    
                }
            }

            public void mousePressed(MouseEvent e){ // guarding
                if(e.getButton() == MouseEvent.BUTTON3){
                    if(isaction != true){
                        isblock = true;
                        isidle = false;
                        isaction = true;
                    }
                    
                }
            }

            public void mouseReleased(MouseEvent e){ 
                if(e.getButton() == MouseEvent.BUTTON3){
                    if(isaction != true || isblock == true){
                        isblock = false;
                        isidle = true;
                        isaction = false;
                    }
                    
                }
            }
        });
        
        time.start();
        actortime.start();
        attack.start();
        Block.start();
        walk.start();
        Boss.start();
        delay.start();
        Boss_attack.start();
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
        if(isblock){
            g.drawImage(p.P_block[p.block_count].getImage(), p.x, 440, 300, 165, this);
        }
        if((d.x - p.x) <= 100){
            
            if(Boss_delay >= 0){
                if(d.idle_count >= 8){
                    d.idle_count = 0;
                }
                g.drawImage(d.D_idle[d.idle_count].getImage(), d.x, 105, 700, 500, this);
            }
            else{
                if(d.attack_count >= 10){
                    d.attack_count = 0;
                }
                g.drawImage(d.D_attack[d.attack_count].getImage(), d.x, 105, 700, 500, this);
            }
        }
        else{
            if(d.idle_count >= 8){
                d.idle_count = 0;
            }
            g.drawImage(d.D_idle[d.idle_count].getImage(), d.x, 105, 700, 500, this);
        }
        if(isbossattack){
            if((d.x - p.x) > 100){
                if(d.attack_count >= 10){
                    d.attack_count = 0;
                }
                g.drawImage(d.D_attack[d.attack_count].getImage(), d.x, 105, 700, 500, this);
            }
        }
        

    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

}