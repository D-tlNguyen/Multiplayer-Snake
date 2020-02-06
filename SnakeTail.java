
package guitermproject;

import java.awt.Color;
import static java.awt.Color.black;
import java.awt.Graphics;


public class SnakeTail {
    public int timeAround;
    public boolean exists = true;
    public int row;
    public int col;
    public Color color;

    /**
     * constructor method that initiates at the specified row and col
     *
     * @param row (self-explanatory)
     * @param col (column, self-explanatory)
     */
    public SnakeTail(int row, int col) {
        timeAround = 0;
        this.row = row;
        this.col = col;
    }

    /**
     * increments timeAround variable, which makes the snake grow
     */
    public void oneFrame() {
        timeAround++;
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
