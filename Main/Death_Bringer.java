import javax.swing.ImageIcon;

public class Death_Bringer  extends entity{
    // public int HP;
    // public int ATK;
    // public int x;
    public int idle_count;
    public int run_count;
    public int attack_count;
    public int block_count;
    public ImageIcon[] D_idle = new ImageIcon[8];
    public ImageIcon[] D_run = new ImageIcon[8];
    public ImageIcon[] D_attack = new ImageIcon[10];

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
    }
}
