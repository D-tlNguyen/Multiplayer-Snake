
package guitermproject;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class StartMenu {

    JFrame mainFrame = new JFrame();
    JButton snakeGame;
    JButton TronGame;
    JLabel howManyTitle;
    JTextField howManyInput;

    public StartMenu() {
        Init();
    }

    public void Init() {
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 400);

        Container mainFrameTools = mainFrame.getContentPane();
        mainFrameTools.setLayout(new GridBagLayout());

        GridBagConstraints z;

        this.snakeGame = new JButton();
        this.snakeGame.setText("New Snake Game");

        this.TronGame = new JButton();
        this.TronGame.setText("New Tron Game");

        this.howManyTitle = new JLabel();
        this.howManyTitle.setText("How Many Players?");

        this.howManyInput = new JTextField();
        this.howManyInput.setText("Enter Count Here");

        z = new GridBagConstraints();
        z.gridx = 1;
        z.gridy = 1;
        mainFrameTools.add(this.snakeGame, z);

        z = new GridBagConstraints();
        z.gridx = 1;
        z.gridy = 2;
        mainFrameTools.add(this.TronGame, z);

        z = new GridBagConstraints();
        z.gridx = 1;
        z.gridy = 3;
        mainFrameTools.add(this.howManyTitle, z);

        z = new GridBagConstraints();
        z.gridx = 1;
        z.gridy = 4;
        mainFrameTools.add(this.howManyInput, z);

        this.snakeGame.addActionListener(new newSnakeGame());
        this.TronGame.addActionListener(new newTronGame());

        mainFrame.setVisible(true);
        mainFrame.isActive();

    }

    private class newSnakeGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                new GameWindow("snake", 1);
            } catch (Exception ex) {

            }
        }
    }

    private class newTronGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                new GameWindow("tron", Integer.parseInt(howManyInput.getText()));
                
            } catch (Exception ex) {
                
            }
        }
    }
}
