package fr.mathieubour.minesweeper.client.panels;

import fr.mathieubour.minesweeper.client.states.ClientGameState;
import fr.mathieubour.minesweeper.game.Player;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreboardPanel extends JPanel {
    private static ScoreboardPanel instance;
    private ConcurrentHashMap<String, ScorePanel> scorePanels = new ConcurrentHashMap<>();

    private ScoreboardPanel() {
        super(new GridBagLayout());
        redraw();
    }

    public static synchronized ScoreboardPanel getInstance() {
        if (instance == null) {
            instance = new ScoreboardPanel();
        }

        return instance;
    }

    public ConcurrentHashMap<String, ScorePanel> getScorePanels() {
        return scorePanels;
    }

    public void redraw() {
        removeAll();
        ConcurrentHashMap<String, Player> players = ClientGameState.getInstance().getPlayers();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;

        AtomicInteger y = new AtomicInteger(0);

        players.forEach((key, player) -> {
            ScorePanel scorePanel = new ScorePanel(player);
            constraints.gridy = y.getAndIncrement();
            add(scorePanel, constraints);
            scorePanels.put(player.getId(), scorePanel);
        });
    }
}
