package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.states.GameState;
import fr.mathieubour.minesweeper.game.Player;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreboardPanel extends JPanel {
    private static ScoreboardPanel instance;
    HashMap<String, ScorePanel> scorePanels = new HashMap<>();

    private ScoreboardPanel() {
        super(new GridBagLayout());
        draw();
    }

    public static synchronized ScoreboardPanel getInstance() {
        if (instance == null) {
            instance = new ScoreboardPanel();
        }

        return instance;
    }

    void draw() {
        HashMap<String, Player> players = GameState.getInstance().getPlayers();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;

        AtomicInteger y = new AtomicInteger(0);

        players.forEach((key, player) -> {
            ScorePanel scorePanel = new ScorePanel(player);
            constraints.gridy = y.getAndIncrement();
            add(scorePanel, constraints);;
        });
    }
}