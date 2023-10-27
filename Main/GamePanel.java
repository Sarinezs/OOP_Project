
import java.awt.Color;
import java.awt.Font;
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
    public ImageIcon bgimg1 = new ImageIcon(this.getClass().getResource("Entity/Image/background1.png"));

    Player p = new Player();
    
    boolean iswalk = false;
    boolean isattack = false;
    boolean isblock = false;
    boolean ishurt = false;
    boolean isdeath = false;
    boolean isroll = false;
    boolean isreverseroll = false;
    boolean isaction = false; // check ว่าตัวหลักกำลังใช้ท่าไรอยู่ไหม
    boolean isidle = true;
    int P_delay = -1;
    int P_rollduration;
    

    Death_Bringer d = new Death_Bringer();
    boolean isbossattack = false;
    boolean isbossaction = false;
    boolean isbosswalk = false;
    boolean isbossidle = true;
    boolean willcontinue = false;
    int Boss_atk_range = -100;
    int Boss_vision = 0;
    int Boss_delay = -1;

    JLabel P_HP = new JLabel(String.valueOf(p.HP));
    JLabel D_HP = new JLabel(String.valueOf(d.HP));


    Thread actortime = new Thread(new Runnable() {
       public void run(){
        while(true){
            try{
                Thread.sleep(100);
            }
            catch(Exception e){}
            p.idle_count++;

          
            // repaint();
            
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
                if((d.x - p.x) <= Boss_atk_range && p.HP > 0){
                    isbossattack = true;
                    isbossaction = true;
                    isbossidle = false;
                    isbosswalk = false;
                   
                }
                else if((d.x - p.x) <= Boss_vision && !isbossattack && p.HP > 0){
                    isbosswalk = true;
                    isbossidle = false;
                    isbossaction = true;
                    if(d.x > Boss_vision){
                        d.x -= 10;
                        d.run_count++;
                    }
                    // else if(d.x > 200 && Boss_delay >= 0){
                    //     isbossidle = true;
                    //     // isbosswalk = false;
                    // }
                    else{
                        isbossidle = true;
                        isbosswalk = false;
                        isbossaction = false;
                        d.idle_count++;
                    }
                    
                }
                
                else{
                    isbossidle = true;
                    d.idle_count++;
                }
                // repaint();
            }
        }
    });

    Thread Boss_attack = new Thread(new Runnable() {
        public void run(){
            while(true){
                if(isbossaction){
                    if(isbossattack && Boss_delay < 0){
                        isbossidle = false;
                        isbosswalk = false;
                        d.attack_count++;
                        if(d.attack_count == d.D_attack.length-5){
                            if(isblock){
                                // ishurt = true;
                                P_delay = 5;
                                // isidle = false;
                                // isaction = true;
                                p.getDamage(d.ATK - p.def);
                            }
                            else{
                                // ishurt = true;
                                P_delay = 5;
                                // isidle = false;
                                // isaction = true;
                                p.getDamage(d.ATK);
                            }
                        }
                        if(d.attack_count >= d.D_attack.length){
                            Boss_delay = 30;
                            isbossattack = false;
                            isbossidle = true;
                        }
                    }
                }
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){}
                
                // repaint();
            }
        }
    });

    Thread delay = new Thread(new Runnable() {
        public void run(){
            while(true){
                // isbossidle = true;
                if(Boss_delay >= 0){
                    if((d.x - p.x) > Boss_atk_range){ //ถ้ายังอยู่ในระยะboss ** กันเฟรมมันรันเร็ว
                        Boss_delay -= 1;
                        // repaint();
                    }
                    else{// ถ้าไม่ได้อยู่ในระยะboss
                        d.idle_count++;
                        Boss_delay -= 1;
                        // repaint();
                    }
                    
                }
                else if((d.x - p.x) <= Boss_atk_range){
                    isbossattack = true;
                    isbossaction = true;
                    isbossidle = false;
                }
                // else if((d.x - p.x) > Boss_atk_range){

                // }
              
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){}
                
                // repaint();
            }
        }
    });

    Thread time = new Thread(new Runnable(){
        public void run(){
            while(true){
            if(p.HP <= 0){
                isidle = false;
                isaction = true;              
                try{
                    Thread.sleep(200);
                }
                catch(Exception e){}
                if(p.death_count == p.P_death.length-1){
                    try{
                        Thread.sleep(1000000);
                    }
                    catch(Exception e){}
                }
                repaint();
            }
            else if(isroll){
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){}
                p.x += 5;

                repaint();
            }
            else if(isreverseroll){
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){}
                p.x -= 5;

                repaint();
            }

            else{
                try{
                    Thread.sleep(10);
                }
                catch(Exception e){}
                repaint();
            }
            
            
            
            
            }
        }
    });

    

    Thread Bosswalk = new Thread(new Runnable(){
        public void run(){
            while(true){
                if(isbosswalk){
                    try{
                        Thread.sleep(100);
                    }
                    catch(Exception e){}
                    d.run_count++;
                    // repaint();
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
                    if(p.attack_count == p.P_attack.length-6){
                        d.getDamage(p.ATK);
                    }
                    // repaint();
                }
                if(p.attack_count >= p.P_attack.length){ // reset after attack
                    p.attack_count = 0;
                    isidle = true;
                    isattack = false;
                    isaction = false;
                }
            }
        }
    });

    Thread Hurt = new Thread(new Runnable() {
        public void run(){
            while(true){
                if(P_delay >= 0){
                    P_delay -= 1;
                    System.out.println(P_delay);
                }
                repaint();
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){}
            }
            
            
            
        } 
    });

    Thread Roll = new Thread(new Runnable() {
        public void run(){
            while(true){
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){}
                if(isroll){
                    p.roll_count++;
                    // P_rollduration--;
                    // System.out.println("1");
                    // repaint();
                }
                else if(isreverseroll){
                    p.roll_count++;

                }
                if(p.roll_count >= p.P_roll.length){
                    p.roll_count = 0;
                    isidle = true;
                    isaction = false;
                    isroll = false;
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
                // repaint();
            }
            if(p.block_count >= p.P_block.length){
                p.block_count = 0;
            }
        }
       } 
    });

    public GamePanel(){
        p.setHP(500);
        p.setATK(900);
        p.setdef(10);
        p.x = 0;

        d.setHP(1000);
        d.setATK(40);
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
                    if(a == KeyEvent.VK_D){
                        if(p.x <= getWidth()){
                            p.x += 10;
                            p.run_count++;
                        }
                    }
                    
                    if(a == KeyEvent.VK_E){
                        isaction = false;
                        isroll = true;
                        isidle = false;
                        // System.out.println("asgd");
                    }

                    if(a == KeyEvent.VK_Q){
                        isaction = false;
                        isreverseroll = true;
                        isidle = false;
                        // System.out.println("asgd");
                    }

                    if(p.run_count >= p.P_run.length){
                        p.run_count = 0;
                    }
                }

                // if(isaction != true){
                //     int a = e.getKeyCode();
                //     if(a == KeyEvent.VK_E){
                //         isaction = true;
                //         isroll = true;
                //         isidle = false;
                //         p.x += 5;
                //         System.out.println("asgd");
                //     }
                // }
                
                
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
                        ishurt = false;
                    }
                    
                }
            }

            public void mousePressed(MouseEvent e){ // guarding
                if(e.getButton() == MouseEvent.BUTTON3){
                    if(!isaction){
                        isblock = true;
                        isidle = false;
                        isaction = true;
                        ishurt = false;
                    }
                    
                }
            }

            public void mouseReleased(MouseEvent e){ 
                if(e.getButton() == MouseEvent.BUTTON3){
                    if(!isaction || isblock){
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
        Roll.start();
        Hurt.start();
        Block.start();
        Bosswalk.start();
        Boss.start();
        delay.start();
        Boss_attack.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(d.HP <= 500){
            g.drawImage(bgimg1.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        else{
            g.drawImage(bgimg.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        g.setColor(Color.green);
        g.setFont(new Font("Hobo Std", Font.BOLD, 40));
        g.drawString("HP : "+p.HP, 100, 100);
        g.setColor(Color.red);
        g.drawString("Boss : "+d.HP, 900, 100);

        if(p.HP <= 0){
            if(p.death_count < p.P_death.length-1){
                p.death_count++;
            }
            g.drawImage(p.P_death[p.death_count].getImage(), p.x, 440, 300, 165, this);
        }
        else{
            if(iswalk){
                g.drawImage(p.P_run[p.run_count].getImage(), p.x, 440, 300, 165, this);
            }
            else if(isidle){
                if(p.idle_count >= p.P_idle.length){
                    p.idle_count = 0;
                }
                g.drawImage(p.P_idle[p.idle_count].getImage(), p.x, 440, 300, 165, this);

            }
            // else if(P_delay >= 0){
            //     if(p.hurt_count >= p.P_hurt.length){
            //         p.hurt_count = 0;
            //     }
            //     g.drawImage(p.P_hurt[p.hurt_count].getImage(), p.x, 440, 300, 165, this);
            // }

            else if(isattack){
                g.drawImage(p.P_attack[p.attack_count].getImage(), p.x, 440, 300, 165, this);

            }
            else if(isblock){

                g.drawImage(p.P_block[p.block_count].getImage(), p.x, 440, 300, 165, this);
            }
            else if(isroll){
                if(p.roll_count < p.P_roll.length-1){
                    if(p.roll_count >= p.P_roll.length){
                       p.roll_count = 0;
                       isroll = false;
                    }
                }
                    g.drawImage(p.P_roll[p.roll_count].getImage(), p.x, 440, 300, 165, this);
                
            }
            else if(isreverseroll){
                if(p.roll_count < p.P_roll.length-1){
                    if(p.roll_count >= p.P_roll.length){
                       p.roll_count = 0;
                       isreverseroll = false;
                    }
                }
                    g.drawImage(p.P_roll[p.roll_count].getImage(), p.x, 440, 300, 165, this);
                
            }

        }
        


        if((d.x - p.x) <= Boss_atk_range){// เมื่อเข้ามาในระยะ boss จะโจมตี
            
            if(Boss_delay >= 0){ //ช่วงboss delay
                if(d.idle_count >= d.D_idle.length){
                    d.idle_count = 0;
                }
                isbossidle = true;
                // g.drawImage(d.D_idle[d.idle_count].getImage(), d.x, 105, 700, 500, this);
            }
            else{
                if(d.attack_count >= d.D_attack.length){
                    d.attack_count = 0;
                }
                g.drawImage(d.D_attack[d.attack_count].getImage(), d.x, 105, 700, 500, this);
            }
        }
        if(isbossidle){
            if(d.idle_count >= d.D_idle.length){
                d.idle_count = 0;
            }
            g.drawImage(d.D_idle[d.idle_count].getImage(), d.x, 105, 700, 500, this);
        }
        if(isbossattack){
            if((d.x - p.x) > Boss_atk_range){  // ถ้าเกินระยะ boss โจมตี
                if(Boss_delay < 0){ // ถ้าboss อยู่ในช่วงโจมตี จะทำการโจมตีให้จบอนิเมชั่น
                    isbossidle = false;
                    if(d.attack_count >= d.D_attack.length){
                        d.attack_count = 0;
                    }
                    g.drawImage(d.D_attack[d.attack_count].getImage(), d.x, 105, 700, 500, this);
                }
                
            }
        }
        if(isbosswalk){

            if(d.run_count >= d.D_run.length){
                d.run_count = 0;
            }
            g.drawImage(d.D_run[d.run_count].getImage(), d.x, 105, 700, 500, this);
        }
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

}