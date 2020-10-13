package life;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid extends JPanel {

    private ArrayList<Point> cells;
    private int size = 20;

    public Grid() {
        cells = new ArrayList<>();
    }

    public Grid(int size) {
        cells = new ArrayList<>();
        this.size = size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw rectangle around outside of grid
        g.drawRect(10, 10, size * 10, size * 10);

        // Draw column lines for grid
        for (int i = 10; i <= size * 10; i += 10) {
            g.drawLine(i, 10, i, size * 10 + 10);
        }

        // Draw row lines for grid
        for (int i = 10; i <= size * 10; i += 10) {
            g.drawLine(10, i, size * 10 + 10, i);
        }
        g.setColor(Color.BLACK);

        // Fill alive cells
        for (Point cell : cells) {
            int cellX = 10 + (cell.x * 10);
            int cellY = 10 + (cell.y * 10);

            g.setColor(Color.RED);
            g.fillRect(cellX, cellY, 10, 10);
        }

    }

    public void fillCell(int x, int y) {
        cells.add(new Point(x, y));
        repaint();
    }

    public void removeCell(int x, int y) {
        cells.remove(new Point(x, y));
        repaint();
    }
}

