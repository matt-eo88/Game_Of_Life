package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    JLabel GenerationLabel;
    JLabel AliveLabel;
    Grid grid;
    int currentGeneration = 0;
    JToggleButton playToggleButton;
    JButton resetButton;
    boolean isPaused;
    boolean isReset;

    public GameOfLife() {
        super("Game of Life");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //setSize(screenSize.width, screenSize.height);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        playToggleButton = new JToggleButton();
        playToggleButton.setName("PlayToggleButton");
        playToggleButton.setText("Pause/Play");
        playToggleButton.addActionListener(actionEvent -> isPaused = !isPaused);
        add(playToggleButton);

        resetButton = new JButton();
        resetButton.setName("ResetButton");
        resetButton.setText("Reset");
        resetButton.addActionListener(actionEvent -> isReset = true);
        add(resetButton);

        GenerationLabel = new JLabel();
        GenerationLabel.setName("GenerationLabel");
        GenerationLabel.setText("Generation #" + getCurrentGeneration());


        AliveLabel = new JLabel();
        AliveLabel.setName("AliveLabel");
        AliveLabel.setText("Alive: 0");

        add(GenerationLabel);
        add(AliveLabel);

        grid = new Grid();
        add(grid);

        setVisible(true);
    }

    public int getCurrentGeneration() {
        return currentGeneration;
    }

    public void nextGeneration() {
        this.currentGeneration++;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isReset() {
        return isReset;
    }

    public void reset(){
        this.currentGeneration = 0;
        this.isReset = false;
    }
}
