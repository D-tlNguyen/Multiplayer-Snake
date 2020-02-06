package guitermproject;

import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.red;
import java.awt.Graphics;

/**
 * This class generates a pellet for the snake to consume in a specified
 * location.
 *
 * Last Update: Oct. 16
 *
 */
public class Pellet {

    public int row;
    public int col;

    /**
     * generates the pellet at the parameter-specified
     *
     * @param row the row of the array that the pellet spawns in
     * @param col the column of the array that the pellet spawns in
     */
    public Pellet(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * empty constructor method reserved for future use
     */
    public Pellet() {
    }

    public Point getTopLeft() {

        return new Point(col * 10, row * 10);
    }

    public void draw(Graphics g) {
        Point p = new Point(col * 10, row * 10);
        int xcoord = p.getXCoord();
        int ycoord = p.getYCoord();
        int size = 10;
        g.setColor(red);
        g.fillRect(xcoord, ycoord, size, size);
    }
}
