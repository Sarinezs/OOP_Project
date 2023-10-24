import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class main extends JFrame implements ActionListener{
    // GamePanel gamepanel = GamePanel();
    
    main(){
        add(new GamePanel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args){
        // GamePanel o = new GamePanel();
        
        main frame = new main();
        
        frame.setTitle("The Adventure Of Knight");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    
}