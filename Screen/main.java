package Screen;
import javax.swing.*;

import GamePanel;
import main;

public class main extends JFrame{
    public static void main(String[] args){
        main frame = new main();
        GamePanel Screen = new GamePanel();
        // Screen
        frame.setTitle("The Adventure Of Knight");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
