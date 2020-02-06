/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitermproject;

import static java.awt.Color.black;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class SnakeGame extends JPanel implements KeyListener {

    public boolean gameOver = false;
    public Timer timer = new Timer();
    public SnakeHead snake = new SnakeHead();
    public SnakeTail[] tailArray = new SnakeTail[2500];
    public Pellet pellet = new Pellet();
    public Random rand = new Random();
    public TimerTask task = new TimerTask() {
        @Override
        public void run() {
            nextFrame();
        }
    };
    JLabel points;

    public SnakeGame() {
        Init();
    }

    public void Init() {
        snake.color = black;
        timer.schedule(task, 125, 125);
        setSize(530, 560);
        pellet.col = rand.nextInt(49) + 1; //**spawns the pellet; privacy leak will be fixed later
        pellet.row = rand.nextInt(49) + 1;

//        Container snakeFrameTools = snakeFrame.getContentPane();
//        snakeFrameTools.setLayout(new GridBagLayout());
//
//        GridBagConstraints z;
//        this.points = new JLabel();
//        this.points.setText("1000");
//
//        z = new GridBagConstraints();
//        z.gridx = 1;
//        z.gridy = 1;
//        snakeFrameTools.add(this.points, z);
        this.addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // Required for KeyListener, but we are not interested in this event so we'll
        // do nothing.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 'W':
                snake.turnUp();
                break;
            case 'A':
                snake.turnLeft();
                break;
            case 'S':
                snake.turnDown();
                break;
            case 'D':
                snake.turnRight();
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Required for KeyListener, but we are not interested in this event so we'll
        // do nothing.		
    }

    private void nextFrame() {
        manageMaps();

        String direction = snake.getDirection();
        if (direction.equals("up")) {
            snake.goUp();
        }
        if (direction.equals("down")) {
            snake.goDown();
        }
        if (direction.equals("left")) {
            snake.goLeft();
        }
        if (direction.equals("right")) {
            snake.goRight();
        }
        checkCollision();
        repaint();
    }

    @Override
    public void paint(Graphics canvas) {
        super.paint(canvas);
        pellet.draw(canvas);
        snake.draw(canvas);
        for (SnakeTail tail : tailArray) {
            if (tail != null) {
                tail.draw(canvas);
            }
        }
        if (gameOver) {
            canvas.drawString("You lost! you had " + snake.points + " points!", 30, 30);
        }
    }

    public void manageMaps() {
        if (snake.points == 2499) {
            youWin();
        }
        for (int x = 0; x < 2500; x++) {
            if (tailArray[x] == null) {
                tailArray[x] = (new SnakeTail(snake.row, snake.col));
                break; //will be edited out later
            }
        }

        for (int x = 0; x < 2500; x++) {
            if (tailArray[x] != null) {
                tailArray[x].oneFrame();
                if (tailArray[x].timeAround > snake.points) {
                    tailArray[x] = null;
                }
            }
        }
    }

    public void checkCollision() {
        for (int x = 0; x < 2500; x++) {
            if (tailArray[x] != null) {
                if (snake.col == tailArray[x].col && snake.row == tailArray[x].row) {
                    gameOver = true;
                    gameOver(); //snake running into itself

                }

            }
        }
        if (snake.col == pellet.col && snake.row == pellet.row) {
            snake.addPoints(); //snake running into pellet
            pellet.col = rand.nextInt(49) + 1;
            pellet.row = rand.nextInt(49) + 1;
            boolean pelletInViableLocation = false;
            while (!pelletInViableLocation) {
                pelletInViableLocation = true;
                for (int x = 0; x < 2500; x++) {
                    if (tailArray[x] != null) {
                        if (pellet.col == tailArray[x].col && pellet.row == tailArray[x].row) {
                            pellet.col = rand.nextInt(49) + 1;
                            pellet.row = rand.nextInt(49) + 1;
                            pelletInViableLocation = false;
                        }

                    }
                }
            }
        }
        if (snake.col == 51 || snake.row == 51 || snake.col == -1 || snake.row == -1) {
            gameOver = true;
            gameOver(); //snake hiting wall
        }
    }

    public void gameOver() {
        task.cancel();

        repaint();

    }

    public void youWin() {
        task.cancel();
        getGraphics().drawString("You won!", 250, 250);
        repaint();
    }
}
