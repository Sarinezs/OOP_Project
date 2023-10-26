// package Entity;
public class entity {
    public int HP;
    public int ATK;
    public int x;

    public void setHP(int hp){
        this.HP = hp;
    }

    public void setATK(int atk){
        this.ATK = atk;
    }

    public void getDamage(int atk){
        this.HP -= atk;
    }

}