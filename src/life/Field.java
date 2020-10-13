package life;

import java.util.Random;

public class Field {
    Cell[][] universe;
    int size;
    long seed;
    int aliveCells = 0;
    int generation = 1;

    public Field(int size, long seed) {
        this.size = size;
        this.seed = seed;
        //Random rand = new Random(seed);
        Random rand = new Random();
        universe = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                universe[i][j] = new Cell(rand.nextBoolean());
            }
        }
        aliveCounter();
    }

    public void showInfo() {
        System.out.printf("Generation #%d\n", this.generation);
        System.out.printf("Alive: %d\n", this.aliveCells);
    }

    public void aliveCounter() {
        this.aliveCells = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (universe[i][j].isAlive())
                    this.aliveCells++;
            }
        }
    }

    public Cell[][] copy(Cell[][] from) {
        Cell[][] to = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                to[i][j] = from[i][j];
            }
        }
        return to;
    }

    public void getNextGen() {

        Cell[][] nextGen = increase(copy(universe)); //увеличенная копия из которой будем изменять

        int[][] neightborsArr = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                neightborsArr[i][j] = getNeighbors(nextGen, i+1, j+1);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int neigh = neightborsArr[i][j];
                if (universe[i][j].isAlive() && (neigh < 2 || neigh > 3)) {
                    this.universe[i][j].set(false);
                }
                if (!universe[i][j].isAlive() && neigh == 3) {
                    this.universe[i][j].set(true);
                }
            }
        }
        this.generation++;
        aliveCounter();
    }

    public int getNeighbors(Cell[][] field, int i, int j) {
        int count = 0;

        for (int xoff = -1; xoff <= 1; xoff++) {
            for (int yoff = -1; yoff <= 1 ; yoff++) {

                if (xoff == 0 && yoff == 0) {
                    continue;
                }
                int x = i + xoff;
                int y = j + yoff;

                if (field[x][y].isAlive()) {
                    count++;
                }
            }
        }
        return count;
    }

    public Cell[][] increase(Cell[][] field) {

        Cell[][] incr = new Cell[field.length+2][field[0].length+2];

        for (int i = 0; i < incr.length; i++) {
            for (int j = 0; j < incr.length; j++) {
                if (j == 0) { //лево
                    if (i == 0) {
                        incr[i][j] = field[field.length-1][field.length-1];
                    } else if (i == incr.length-1) {
                        incr[i][j] = field[0][field.length-1];
                    } else
                        incr[i][j] = field[i-1][field.length-1];
                }
                if (j == incr.length-1) { //право
                    if (i == 0) {
                        incr[i][j] = field[field.length-1][0];
                    } else if (i == incr.length-1) {
                        incr[i][j] = field[0][0];
                    } else
                        incr[i][j] = field[i-1][0];
                }
                if (i == 0) { //верх
                    if (j == 0) {
                        incr[i][j] = field[field.length-1][field.length-1];
                    } else if (j == incr.length-1) {
                        incr[i][j] = field[field.length-1][0];
                    } else
                        incr[i][j] = field[field.length-1][j-1];
                }
                if (i == incr.length-1) { //низ
                    if (j == 0) {
                        incr[i][j] = field[0][field.length-1];
                    } else if (j == incr.length-1) {
                        incr[i][j] = field[0][0];
                    } else
                        incr[i][j] = field[0][j-1];
                }
            }
        }

        for (int i = 1; i < incr.length-1; i++) {
            for (int j = 1; j < incr.length-1; j++) {
                incr[i][j] = field[i-1][j-1];
            }
        }
        return incr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[0].length; j++) {
                sb.append(universe[i][j]);
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }
}
