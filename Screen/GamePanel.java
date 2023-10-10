package Screen;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    final int width = 1280;
    final int height = 720;

    public GamePanel(){
        this.setSize(width, height);
        // this.setTitle("The Adventure Of Knight");
        // this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
    }


}


