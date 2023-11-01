import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Death_Bringer  extends entity{
    // public int HP;
    // public int ATK;
    // public int x;
    public int idle_count;
    public int run_count;
    public int attack_count;
    public int hurt_count;
    public int cast_count;
    public int death_count;
    public int spell_count;

    public ImageIcon[] D_idle = new ImageIcon[8];
    public ImageIcon[] D_run = new ImageIcon[8];
    public ImageIcon[] D_attack = new ImageIcon[10];
    public ImageIcon[] D_hurt = new ImageIcon[3];
    public ImageIcon[] D_cast = new ImageIcon[9];
    public ImageIcon[] D_death = new ImageIcon[10];
    public ImageIcon[] D_spell = new ImageIcon[16];

    // public void setHP(int hp){
    //     this.HP = hp;
    // }

    // public void setATK(int atk){
    //     this.ATK = atk;
    // }

    // public void getDamage(int atk){
    //     this.HP -= atk;
    // }

    public Death_Bringer(){
        for(int i = 0; i<D_idle.length; i++){
            D_idle[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Boss/Idle/Bringer-of-Death_Idle_"+i+".png"));
        }

        for(int i = 0; i<D_attack.length; i++){
            D_attack[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Boss/Attack/Bringer-of-Death_Attack_"+(i+1)+".png"));
        }

        for(int i = 0; i<D_run.length; i++){
            D_run[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Boss/Walk/Bringer-of-Death_Walk_"+(i+1)+".png"));
        }

        for(int i = 0; i<D_hurt.length; i++){
            D_hurt[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Boss/Hurt/Bringer-of-Death_Hurt_"+(i+1)+".png"));
        }

        for(int i = 0; i<D_cast.length; i++){
            D_cast[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Boss/Cast/Bringer-of-Death_Cast_"+(i+1)+".png"));
        }

        for(int i = 0; i<D_death.length; i++){
            D_death[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Boss/Death/Bringer-of-Death_Death_"+(i+1)+".png"));
        }

        for(int i = 0; i<D_spell.length; i++){
            D_spell[i] = new ImageIcon(this.getClass().getResource("Entity/Image/Boss/Spell/Bringer-of-Death_Spell_"+(i+1)+".png"));
        }
        
    }

    public Rectangle2D Boss_HitBlock(){
        return(new Rectangle2D.Double(x+420, 300,200, 300));
    }

    public Rectangle2D Boss_areaAttack(){
        return(new Rectangle2D.Double(x+200, 300,300, 300));
    }
}
