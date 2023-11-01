
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

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
    boolean isspell = false;
    boolean willcontinue = false;
    int Boss_atk_range = -50;
    int Boss_atk_range_phrase2 = 1000;
    int spell_position;

    int Boss_vision = 0;
    int Boss_delay = -1;
    int Boss_HP = 1000;
    int Boss_phrase2 = Boss_HP / 2;

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
                
                if(d.HP <= 0){
                    try{
                        Thread.sleep(10000);
                    }
                    catch(Exception e){}
                    // System.out.println("KK");
                    // repaint();
                }
                
                else{
                    try{
                        Thread.sleep(100);
                    }
                    catch(Exception e){}

                    if(d.HP <= Boss_HP/2){
                        if((d.x - p.x) <= Boss_atk_range_phrase2 && p.HP > 0 && Boss_delay >= 0){
                            isbossattack = false;
                            isbossaction = false;
                            isbossidle = true;
                            isbosswalk = true;
                            d.idle_count++;
                            // System.out.println(d.idle_count);
                            
                        }
                        else if((d.x - p.x) <= Boss_atk_range_phrase2 && p.HP > 0){
                            isbossattack = true;
                            isbossaction = true;
                            isbossidle = false;
                            isbosswalk = false;
                            // d.idle_count++;
                            // System.out.println(d.idle_count);
                        }
                        else if((d.x - p.x) <= Boss_vision && !isbossattack && p.HP > 0){
                            isbosswalk = true;
                            isbossidle = false;
                            isbossaction = true;
                            if(d.x < Boss_vision){
                                if(d.x < 100){
                                    isbosswalk = false;
                                    isbossidle = true;
                                    isbossaction = false;
                                }
                                else{
                                    d.x -= 10;
                                    d.run_count++;
                                }
                            }
                            else{
                                isbossidle = true;
                                isbosswalk = false;
                                isbossaction = false;
                                // d.idle_count++;
                            }
                    
                        }
                        // else{
                        //     isbossidle = true;
                        //     d.idle_count++;
                        // }
                    }
                    else{
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
                            if(d.x < Boss_vision){
                                if(d.x < 100){
                                    isbosswalk = false;
                                    isbossidle = true;
                                    isbossaction = false;
                                }
                                else{
                                    d.x -= 10;
                                    d.run_count++;
                                }
                            }
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
                    }
                    if((d.x - p.x) <= Boss_atk_range && p.HP > 0){
                        isbossattack = true;
                        isbossaction = true;
                        isbossidle = false;
                        isbosswalk = false;
                        
                    }
                   
                    // else{
                    //     isbossidle = true;
                    //     d.idle_count++;
                    // }
                // repaint();
                }
                
            }
        }
    });

    Thread Boss_spell = new Thread(new Runnable() {
        public void run(){
            while(true){
                if(isspell){
                    try{
                        Thread.sleep(150);
                    }
                    catch(Exception e){}
                    d.spell_count++;
                    System.out.println(d.spell_count);
                }
                
            }
        } 
    });

    Thread Boss_attack = new Thread(new Runnable() {
        public void run(){
            while(true){
                
                if(d.HP <= Boss_HP/2){
                    if(isbossaction){
                        if(isbossattack && Boss_delay < 0){
                            isbossidle = false;
                            isbosswalk = false;
                            d.cast_count++;
                            // d.spell_count++;
                            if(d.cast_count >= d.D_cast.length-1){
                                
                                if(d.spell_count == d.D_spell.length-3){
                                    if(IsHit(d.Boss_areaAttack(), p.Player_HitBlock())){
                                        if(isblock){
                                            p.getDamage(d.ATK - p.def);
                                        }
                                        else{
                                            if(!isroll && !isreverseroll){
                                                p.getDamage(d.ATK);
                                                ishurt = true;
                                                iswalk = false;
                                                isidle = false;
                                            }
                                            if(p.hurt_count >= p.P_hurt.length){
                                                ishurt = false;
                                                iswalk = false;
                                                isidle = true;
                                            }
                                        }
                                    }
                                }
                                
                            }
                            if(d.cast_count >= d.D_cast.length){
                                Boss_delay = 30;
                                isbossattack = false;
                                isbossidle = true;
                                // if(Math.random()*200 > p.x && Math.random()*200 < p.x+20){
                                    spell_position = (int) ((Math.random()*200)-200+p.x);
                                // }
                                System.out.println(spell_position);
                            }
                        }
                    }
                }
                else{
                    if(isbossaction){
                        if(isbossattack && Boss_delay < 0){
                            isbossidle = false;
                            isbosswalk = false;
                            d.attack_count++;
                            if(d.attack_count == d.D_attack.length-3){
                                if(IsHit(d.Boss_areaAttack(), p.Player_HitBlock())){
                                    if(isblock){
                                        p.getDamage(d.ATK - p.def);
                                    }
                                    else{
                                        if(!isroll && !isreverseroll){
                                            // P_delay = p.P_hurt.length-1;
                                            p.getDamage(d.ATK);
                                            ishurt = true;
                                            iswalk = false;
                                            isidle = false;
                                        }
                                        if(p.hurt_count >= p.P_hurt.length){
                                            ishurt = false;
                                            iswalk = false;
                                            isidle = true;
                                        }
                                    }
                                }
                            }
                            if(d.attack_count >= d.D_attack.length){
                                Boss_delay = 30;
                                isbossattack = false;
                                isbossidle = true;
                            }
                        }
                    }
                }
                
                
                try{
                    Thread.sleep(150);
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
                    if(d.HP <= Boss_HP/2){ // boss phrase 2
                        if((d.x - p.x) > Boss_atk_range_phrase2){ // ถ้าไม่ได้อยู่ในระยะboss
                            Boss_delay -= 1;
                        }
                        else{//ถ้ายังอยู่ในระยะboss ** กันเฟรมมันรันเร็ว
                            Boss_delay -= 1;
                            d.spell_count++;
                            // System.out.println(Boss_delay);

                        }
                    }
                    else{
                        if((d.x - p.x) > Boss_atk_range){
                            Boss_delay -= 1;
                        }
                        else{// ถ้าไม่ได้อยู่ในระยะboss
                            d.idle_count++;
                            Boss_delay -= 1;
                        }
                    }
                    
                    
                }
                if(d.HP <= Boss_HP/2){

                }
                else{
                    if((d.x - p.x) <= Boss_atk_range){
                        isbossattack = true;
                        isbossaction = true;
                        isbossidle = false;
                }
                }
                // make phrase two boss
                
              
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
            else if(d.HP <= 0){
                isbossidle = false;
                isbosswalk = false;
                try{
                    Thread.sleep(100);
                }
                catch(Exception e){}
                if(d.death_count > d.D_death.length-1){
                    try{
                        Thread.sleep(1000000);
                    }
                    catch(Exception e){}
                    // System.out.println("LLL");
                }
                repaint();
            }
            else if(isroll){
                try{
                    Thread.sleep(10);
                }
                catch(Exception e){}
                p.x += 4;

                repaint();
            }
            else if(isreverseroll){
                try{
                    Thread.sleep(10);
                }
                catch(Exception e){}
                p.x -= 4;

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
                    if(IsHit(p.Player_areaAttack(), d.Boss_HitBlock())){
                        if(p.attack_count == p.P_attack.length-6){
                            d.getDamage(p.ATK);
                        }
                    }
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
                if(ishurt){
                    p.hurt_count++;
                    if(p.hurt_count >= p.P_hurt.length){
                        p.hurt_count = 0;
                        ishurt = false;
                        iswalk = false;
                        isidle = true;
                    }
                }
                // repaint();
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
                    if(p.roll_count >= p.P_roll.length-1){
                        p.roll_count = 0;
                        isidle = true;
                        isaction = false;
                        isroll = false;
                        isreverseroll = false;
                    }
                    
                }
                
                if(isreverseroll){
                    p.roll_count--;
                    if(p.roll_count < 0){
                        p.roll_count = 0;
                        isidle = true;
                        isaction = false;
                        isroll = false;
                        isreverseroll = false;
                    }
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
        p.setATK(500);
        p.setdef(10);
        p.x = 0;

        d.setHP(Boss_HP);
        d.setATK(10);
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
                        isaction = true;
                        isroll = true;
                        isidle = false;
                        // System.out.println("asgd");
                    }

                    if(a == KeyEvent.VK_Q){
                        p.roll_count = p.P_roll.length-1;
                        isaction = true;
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
                    if(!isaction){ // can't attack during guarding
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
        Boss_spell.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        

        if(d.HP <= Boss_HP/2){
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
        // g.drawRect(d.x+200, 300,300, 300); // กรอบวัดตำแหน่งวัตถุ

        if(p.HP > 0 && d.HP <= 0){
            if(d.death_count == d.D_attack.length-1){
                g.setColor(Color.red);
                g.drawString("YOU WIN", (getWidth()/2)-100, getHeight()/2);
            }
            
        }

        if(p.HP <= 0){
            if(p.death_count < p.P_death.length-1){
                p.death_count++;
            }
            g.drawImage(p.P_death[p.death_count].getImage(), p.x, 440, 300, 165, this);
            if(p.death_count == p.P_death.length-1){
                g.setColor(Color.red);
            g.drawString("YOU DIED", (getWidth()/2)-100, getHeight()/2);
            }
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
            else if(ishurt){
                if(p.hurt_count >= p.P_hurt.length){
                    p.hurt_count = 0;
                }
                g.drawImage(p.P_hurt[p.hurt_count].getImage(), p.x, 440, 300, 165, this);
            }

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
                if(p.roll_count > p.P_roll.length-1){
                    if(p.roll_count < p.P_roll.length){
                       p.roll_count = 0;
                       isreverseroll = false;
                    }
                }
                    g.drawImage(p.P_roll[p.roll_count].getImage(), p.x, 440, 300, 165, this);
                
            }

        }
        
        if(d.HP <= 0){
            if(d.death_count < d.D_death.length-1){
                d.death_count++;
            }
            g.drawImage(d.D_death[d.death_count].getImage(), d.x, 105, 700, 500, this);
        }

        else{
            if(d.HP <= Boss_HP/2){
                if((d.x - p.x) <= Boss_atk_range_phrase2){
                    if(Boss_delay >= 0){
                        if(d.idle_count >= d.D_idle.length){
                            d.idle_count = 0;
                        }
                        g.drawImage(d.D_idle[d.idle_count].getImage(), d.x, 105, 700, 500, this);
                        if(Boss_delay > 14){
                            // d.spell_count++;
                            if(d.spell_count >= d.D_spell.length){
                                d.spell_count = 0;
                            }
                            g.drawImage(d.D_spell[d.spell_count].getImage(), spell_position, 105, 700, 500, this);
                            // System.out.println(spell_position);
                        }
                    }
                    else{
                        if(d.cast_count >= d.D_cast.length){
                            d.cast_count = 0;
                            // isspell =  true;
                        }
                        g.drawImage(d.D_cast[d.cast_count].getImage(), d.x, 105, 700, 500, this);
                        
                        // if(d.spell_count >= d.D_spell.length){
                        //     d.spell_count = 0;
                        // }
                        // g.drawImage(d.D_spell[d.spell_count].getImage(), p.x, 105, 700, 500, this);
                    }
                    // if(isbossidle){
                    //     if(d.idle_count >= d.D_idle.length){
                    //         d.idle_count = 0;
                    //     }
                    //     g.drawImage(d.D_idle[d.idle_count].getImage(), d.x, 105, 700, 500, this);
                    // }
                }
                 
            }
            else{
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
            
            if(isbossidle){
                if(d.idle_count >= d.D_idle.length){
                    d.idle_count = 0;
                }
                g.drawImage(d.D_idle[d.idle_count].getImage(), d.x, 105, 700, 500, this);
            }
            
        }
        
        
    }

    public boolean IsHit(Rectangle2D P, Rectangle2D D) {
        return (P.intersects(D));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

}