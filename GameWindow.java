/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitermproject;

import javax.swing.JFrame;


public class GameWindow extends JFrame {

    SnakeGame snakewin;
    TronGame tronwin;
    static final int Width = 530, Height = 560, LocX = 0, LocY = 0;

    public GameWindow(String game, int players) {
        super("Term Project");
        if (game.equals("snake")) {
            snakewin = new SnakeGame();
            add(snakewin);
            this.setSize(Width, Height);
            this.setVisible(true);
        } else if(game.equals("tron")){
            tronwin = new TronGame(players);
            add(tronwin);
            this.setSize(Width, Height);
            this.setVisible(true);
        }

    }
}
