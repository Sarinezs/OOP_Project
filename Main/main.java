
import javax.swing.*;

public class main extends JFrame{
    // GamePanel gamepanel = GamePanel();
    
    public main(){
        add(new GamePanel());
    }

    public static void main(String[] args){
        GamePanel o = new GamePanel();
        
        main frame = new main();
        
        frame.setTitle("The Adventure Of Knight");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    
}