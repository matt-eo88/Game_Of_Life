package life;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Universe universe = new Universe();
        Generation generation = new Generation(universe);
        universe.setCurrentState(generation.generationZero());
        GameOfLife gameOfLife = new GameOfLife();

        while (true) {
            // Reset button pressed
            if (gameOfLife.isReset()) {
                universe = new Universe();
                generation = new Generation(universe);
                generation.generationZero();
                gameOfLife.reset();
            }

            // Pause/Play button pressed
            if (gameOfLife.isPaused()) {
                try {
                    Thread.sleep(1);
                    continue;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            generation.nextGeneration();
            char[][] currentState = universe.getCurrentState();

            for (int i = 0; i < currentState.length; i++) {
                for (int j = 0; j < currentState.length; j++) {
                    if (currentState[i][j] == 'O') {
                        gameOfLife.grid.fillCell(i, j);
                    } else {
                        gameOfLife.grid.removeCell(i, j);
                    }
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            String gen = "Generation #" + gameOfLife.getCurrentGeneration();
            String alive = "Alive: " + generation.getAliveCount(currentState);

            gameOfLife.GenerationLabel.setText(gen);
            gameOfLife.AliveLabel.setText(alive);

            gameOfLife.nextGeneration();
        }

    }

}
