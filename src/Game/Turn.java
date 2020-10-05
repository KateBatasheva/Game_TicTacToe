package Game;

import javax.swing.*;
import java.awt.*;

public class Turn extends JFrame {
    private GameWindow gameWindow;
    static final int WINDOW_X = GameWindow.WINDOW_X + 50;
    static final int WINDOW_Y = GameWindow.WINDOW_Y + 50;
    static final int WINDOW_WIDTH = 300;
    static final int WINDOW_HEIGHT = 200;

    public Turn(GameWindow gameWindow, String player){
        this.gameWindow = gameWindow;
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Next player's turn");
        JButton OK = new JButton("OK");
        add(new JLabel(player));
        add(OK, BorderLayout.SOUTH);

        OK.addActionListener(e -> {
                    setVisible(false);
    });
    }
}
