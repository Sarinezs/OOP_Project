import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Player extends entity{
    public int def;
   
    public int idle_count;
    public int run_count;
    public int attack_count;
    public int block_count;
    public int death_count;
    public int hurt_count;
    public int roll_count;
    public ImageIcon[] P_idle = new ImageIcon[8];
    public ImageIcon[] P_run = new ImageIcon[10];
    public ImageIcon[] P_attack = new ImageIcon[8];
    public ImageIcon[] P_block = new ImageIcon[8];
    public ImageIcon[] P_death = new ImageIcon[10];
    public ImageIcon[] P_hurt = new ImageIcon[3];
    public ImageIcon[] P_roll = new ImageIcon[9];

    public void setdef(int d){
        this.def = d;
    }

    // public void setHP(int hp){
    //     this.HP = hp;
    // }

    // public void setATK(int atk){
    //     this.ATK = atk;
    // }

    // public void getDamage(int atk){
    //     this.HP -= atk;
    // }

    
    
    public Player(){
        for(int i = 0; i<P_idle.length; i++){
            P_idle[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/Idle/HeroKnight_Idle_"+(i)+".png"));   
        }                                                          

        for(int i = 0; i<P_attack.length; i++){
            P_attack[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/Attack3/HeroKnight_Attack3_"+(i)+".png"));
        }

        for(int i = 0; i<P_run.length; i++){
            P_run[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/Run/HeroKnight_Run_"+(i)+".png"));
        }

        for(int i = 0; i<P_block.length; i++){
            P_block[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/block/HeroKnight_Block Idle_"+(i)+".png"));
        }

        for(int i = 0; i<P_death.length; i++){
            P_death[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/DeathNoBlood/HeroKnight_DeathNoBlood_"+(i)+".png"));
        }

        for(int i = 0; i<P_hurt.length; i++){
            P_hurt[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/Hurt/HeroKnight_Hurt_"+(i)+".png"));
        }

        for(int i = 0; i<P_roll.length; i++){
            P_roll[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/Roll/HeroKnight_Roll_"+(i)+".png"));
        }
    }

    public Rectangle2D Player_HitBlock(){
        return(new Rectangle2D.Double(x+100, 480,90, 120));
    }

    public Rectangle2D Player_areaAttack(){
        return(new Rectangle2D.Double(x+150, 480,150, 120));
    }

}