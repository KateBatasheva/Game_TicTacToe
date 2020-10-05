package Game;

import javax.swing.*;
import java.awt.*;

public class ResultWindow extends JFrame {
    private GameWindow gameWindow;

    static final int WINDOW_X = GameWindow.WINDOW_X + 50;
    static final int WINDOW_Y = GameWindow.WINDOW_Y + 50;
    static final int WINDOW_WIDTH = 405;
    static final int WINDOW_HEIGHT = 200;
    private SettingWindow settingWindow;
    protected String result;

    public ResultWindow(GameWindow gameWindow, String result) {
        this.gameWindow = gameWindow;
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Game over");
        JPanel Jpan = new JPanel(new GridLayout(1,2));
        JButton exitGame = new JButton("Exit");
        JButton OK = new JButton("New Game");
        Jpan.add(OK);
        Jpan.add(exitGame);
        add(Jpan, BorderLayout.SOUTH);
        add(new JLabel(result));

        settingWindow = new SettingWindow(this.gameWindow);

        OK.addActionListener(e -> {
             settingWindow.setVisible(true);
             setVisible(false);
            });
                exitGame.addActionListener(e -> {
        System.exit(0);
    });
        setVisible(false);


    }

}
