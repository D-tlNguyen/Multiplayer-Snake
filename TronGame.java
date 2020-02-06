/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitermproject;

import static java.awt.Color.black;
import static java.awt.Color.blue;
import static java.awt.Color.green;
import static java.awt.Color.red;
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


public class TronGame extends JPanel implements KeyListener {

    public String endString;
    public boolean gameOver = false;
    public Timer timer = new Timer();
    public SnakeHead[] snakes;
    public SnakeTail[] tailArray = new SnakeTail[2500];
    public Random rand = new Random();
    public int snakesLeft;
    public int currentTails = 0;
    public TimerTask task = new TimerTask() {
        @Override
        public void run() {
            nextFrame();
        }
    };
    JLabel points;

    public TronGame(int players) {
        snakes = new SnakeHead[players];
        snakesLeft = players;
        for (int x = 0; x < players; x++) {
            snakes[x] = new SnakeHead();
            if (x == 0) {
                snakes[x].color = black;
                snakes[x].col = 10;
                snakes[x].row = 10;
            } else if (x == 1) {
                snakes[x].color = blue;
                snakes[x].col = 40;
                snakes[x].row = 40;
            } else if (x == 2) {
                snakes[x].color = red;
                snakes[x].col = 10;
                snakes[x].row = 40;
            } else if (x == 3) {
                snakes[x].color = green;
                snakes[x].col = 40;
                snakes[x].row = 10;
            }
        }
        Init();
    }

    public void Init() {
        timer.schedule(task, 125, 125);
        setSize(530, 560);

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
                if (snakes[0] != null) {
                    snakes[0].turnUp();
                }
                break;
            case 'A':
                if (snakes[0] != null) {
                    snakes[0].turnLeft();
                }
                break;
            case 'S':
                if (snakes[0] != null) {
                    snakes[0].turnDown();
                }
                break;
            case 'D':
                if (snakes[0] != null) {
                    snakes[0].turnRight();
                }
                break;

            case 'T':
                if (snakes[1] != null) {
                    snakes[1].turnUp();
                }
                break;
            case 'F':
                if (snakes[1] != null) {
                    snakes[1].turnLeft();
                }
                break;
            case 'G':
                if (snakes[1] != null) {
                    snakes[1].turnDown();
                }
                break;
            case 'H':
                if (snakes[1] != null) {
                    snakes[1].turnRight();
                }
                break;

            case 'I':
                if (snakes[2] != null) {
                    snakes[2].turnUp();
                }
                break;
            case 'J':
                if (snakes[2] != null) {
                    snakes[2].turnLeft();
                }
                break;
            case 'K':
                if (snakes[2] != null) {
                    snakes[2].turnDown();
                }
                break;
            case 'L':
                if (snakes[2] != null) {
                    snakes[2].turnRight();
                }
                break;

            case KeyEvent.VK_UP:
                if (snakes[3] != null) {
                    snakes[3].turnUp();
                }
                break;
            case KeyEvent.VK_LEFT:
                if (snakes[3] != null) {
                    snakes[3].turnLeft();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snakes[3] != null) {
                    snakes[3].turnDown();
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (snakes[3] != null) {
                    snakes[3].turnRight();
                }
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
        // Required for KeyListener, but we are not interested in this event so we'll
        // do nothing.		
    }

    private void nextFrame() {
        manageMaps();
        for (int x = 0; x < snakes.length; x++) {
            if (snakes[x] != null) {
                String direction = snakes[x].getDirection();
                if (direction.equals("up")) {
                    snakes[x].goUp();
                }
                if (direction.equals("down")) {
                    snakes[x].goDown();
                }
                if (direction.equals("left")) {
                    snakes[x].goLeft();
                }
                if (direction.equals("right")) {
                    snakes[x].goRight();
                }
            }
        }

        checkCollision();
        repaint();
    }

    @Override
    public void paint(Graphics canvas) {
        super.paint(canvas);
        for (int x = 0; x < snakes.length; x++) {
            if (snakes[x] != null) {
                snakes[x].draw(canvas);
            }
        }

        for (SnakeTail tail : tailArray) {
            if (tail != null) {
                tail.draw(canvas);
            }
            if (gameOver) {

                canvas.drawString(endString, 10, 10);
            }
        }
    }

    public void manageMaps() {

        for (int i = 0; i < snakes.length; i++) {
            for (int x = 0; x < 2500; x++) {
                if (tailArray[x] == null && snakes[i] != null) {
                    SnakeTail tempTail = new SnakeTail(snakes[i].row, snakes[i].col);
                    tempTail.color = snakes[i].color;
                    tailArray[x] = tempTail;
                    break; //will be edited out later
                }
            }
        }
    }

    public void checkCollision() {
        for (int i = 0; i < snakes.length; i++) {
            if (snakes[i] != null) {
                for (int x = 0; x < 2500; x++) {
                    if (tailArray[x] != null && snakes[i] != null) {
                        if (snakes[i].col == tailArray[x].col && snakes[i].row == tailArray[x].row) {
                            snakes[i] = null;//snake running into itself
                            snakesLeft--;
                        }

                    }
                }
            }
            if (snakes[i] != null) {
                for (int x = 0; x < snakes.length; x++) {
                    if (snakes[x] != null && snakes[i] != null) {
                        if (!snakes[i].equals(snakes[x]) && snakes[i].row == snakes[x].row && snakes[i].col == snakes[x].col) {
                            SnakeTail tempTail1 = new SnakeTail(snakes[i].oldRow, snakes[i].oldCol);
                            tempTail1.color = snakes[i].color;
                            tailArray[currentTails] = (tempTail1);
                            currentTails++;
                            SnakeTail tempTail2 = new SnakeTail(snakes[x].oldRow, snakes[x].oldCol);
                            tempTail1.color = snakes[x].color;
                            tailArray[currentTails] = (tempTail2);
                            currentTails++;
                            snakes[i] = null;
                            snakes[x] = null;
                            snakesLeft -= 2;

                        }
                    }
                }
            }
            if (snakes[i] != null) {
                if (snakes[i].col == 51 || snakes[i].row == 51 || snakes[i].col == -1 || snakes[i].row == -1) { //snake hiting wall
                    snakesLeft--;
                    snakes[i] = null;
                }
            }

            if (snakesLeft <= 1) {
                gameOver();
            }
        }
    }

    public void gameOver() {
        task.cancel();

        gameOver = true;
        Boolean tie = true;
        for (int x = 0; x < snakes.length; x++) {
            if (snakes[x] != null) {
                endString = "The Winner is player " + (x + 1);
                tie = false;
            }
        }
        if (tie == true) {
            endString = "The Game was a tie!";
        }
        repaint();

    }

    public void youWin() {
        task.cancel();
        getGraphics().drawString("You won!", 250, 250);
        repaint();
    }
}
