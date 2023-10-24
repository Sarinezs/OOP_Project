package Screen.test;
// package main;

import javax.swing.JFrame;

public class main{
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("OOP's game");

        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamepanel.startgameThread();
    }
}