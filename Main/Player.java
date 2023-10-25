

import javax.swing.ImageIcon;

public class Player extends Entity{
    // public int HP;
    // public int ATK;
    // public int x;
    public int idle_count;
    public int run_count;
    public int attack_count;
    public int block_count;
    public ImageIcon[] P_idle = new ImageIcon[8];
    public ImageIcon[] P_run = new ImageIcon[10];
    public ImageIcon[] P_attack = new ImageIcon[8];
    public ImageIcon[] P_block = new ImageIcon[8];

    
    
    public Player(){
        for(int i = 0; i<P_idle.length; i++){
            P_idle[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/Idle/HeroKnight_Idle_"+(i)+".png"));   
        }                                                          //Entity/Image/Hero/Idle/HeroKnight_Idle_0.png

        for(int i = 0; i<P_attack.length; i++){
            P_attack[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/Attack3/HeroKnight_Attack3_"+(i)+".png"));
        }

        for(int i = 0; i<P_run.length; i++){
            P_run[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/Run/HeroKnight_Run_"+(i)+".png"));
        }

        for(int i = 0; i<P_block.length; i++){
            P_block[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Hero/block/HeroKnight_Block Idle_"+(i)+".png"));
        }
    }
}