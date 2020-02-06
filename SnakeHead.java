package guitermproject;

import java.awt.Color;
import static java.awt.Color.black;
import java.awt.Graphics;
import static javax.swing.plaf.metal.MetalLookAndFeel.getBlack;

/**
 * This class deals with the head of the snake, which involves movement
 * direction, collision conditions, and point accumulation.
 *
 * Last Update: Oct. 16
 *
 */
public class SnakeHead {

    public int row = 25;
    public int col = 25;
    public int oldRow = -1;
    public int oldCol = -1;
    public int points = 0;
    public String direction = "right";
    public char symbol = '>'; //All these variables should be private... will be changed in a future update.
    private Point topLeft;
    private int size;
    public Color color;

    /**
     * Changes the direction of the snakes head, noted by the symbol This
     * applies to all four turn methods
     */
    public void turnUp() {
        direction = "up";
        symbol = '^';
    }

    public void turnDown() {
        direction = "down";
        symbol = 'v';
    }

    public void turnRight() {
        direction = "right";
        symbol = '>';
    }

    public void turnLeft() {
        direction = "left";
        symbol = '<';
    }

    /**
     * getter for direction
     *
     * @return direction returns direction value, will be utilized after
     * privatized instance variables
     */
    public String getDirection() {
        return direction;
    }

    /**
     * getter for points, with the same type of purpose as getDirection
     *
     * @return points (self-explanatory)
     */
    public int getPoints() {
        return points;
    }

    /**
     * adds a point, increases score
     */
    public void addPoints() {
        points++;
    }

    /**
     * moves the snake head down one row
     *
     * @return row gets new row after move
     */
    public int goUp() {
        oldRow = row;
        oldCol = col;
        row--;
        return row;
    }

    /**
     * moves the snake head up one row
     *
     * @return row gets new row after move
     */
    public int goDown() {
        oldRow = row;
        oldCol = col;
        row++;
        return row;
    }

    /**
     * moves the snake head right one column
     *
     * @return col gets new column after move
     */
    public int goRight() {
        oldCol = col;
        oldRow = row;
        col++;
        return col;
    }

    /**
     * moves the snake head left one column
     *
     * @return col gets new column after move
     */
    public int goLeft() {
        oldCol = col;
        oldRow = row;
        col--;
        return col;
    }

    public Point getTopLeft() {

        return new Point(col * 10, row * 10);
    }

    public void draw(Graphics g) {
        Point p = new Point(col * 10, row * 10);
        int xcoord = p.getXCoord();
        int ycoord = p.getYCoord();
        int size = 10;
        g.setColor(color);
        g.fillRect(xcoord, ycoord, size, size);
    }

}
