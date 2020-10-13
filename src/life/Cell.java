package life;

public class Cell {
    public boolean alive;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void set(boolean alive) {
        this.alive = alive;
    }

    @Override
    public String toString() {
        return alive ? "O" : " ";
    }
}
