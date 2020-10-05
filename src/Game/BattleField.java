package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleField extends JPanel {
    private GameWindow gameWindow;

    private int mode;
    private int fieldSize;
    private int winningLength;

    private boolean isInit;

    int cellWidth;
    int cellHeight;
    int turn;
    Turn play_X = new Turn(gameWindow, "Player 1 turn (X)");
    Turn play_O = new Turn(gameWindow, "Player 2 turn (O)");

    public BattleField(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(Color.ORANGE);
        turn = 1;


// catch the coordinates of the mouse click
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int cellX = e.getX() / cellWidth;
                int cellY = e.getY() / cellHeight;
                String result;


// the humans turns in a HvH mode
                if (mode == 1) {
                    if (!Logic.isFinishedGame) {
                        if (turn == 1) {
                            Logic.humanTurn(cellX, cellY, Logic.DOT_X);
                            turn = 0;
                            play_O.setVisible(true);
                        } else {
                            Logic.humanTurn(cellX, cellY, Logic.DOT_O);
                            turn = 1;
                            play_X.setVisible(true);
                        }
                        Logic.goHuman();
                    }
                    repaint();
                } else {
// human and AI turns in a HvAI mode
                    if (!Logic.isFinishedGame) {
                        Logic.humanTurn(cellX, cellY, Logic.DOT_X);
                        Logic.goHuman();
                    }
                    if (!Logic.isFinishedGame) {
                        Logic.aiTurn();
                        Logic.goAI();
                    }
                    repaint();
                }
// check the winnings and show the frame with the result
                if (Logic.isFinishedGame) {
                    if (Logic.checkWinLines(Logic.DOT_X, Logic.DOTS_TO_WIN)) {
                        result = "Х player wins";
                    } else if (Logic.checkWinLines(Logic.DOT_O, Logic.DOTS_TO_WIN)) {
                        if (mode == 1) {
                            result = "O player wins";
                        } else {
                            result = "AI wins";
                        }
                    } else {
                        result = "Draw";
                    }
                    ResultWindow resultWindow1 = new ResultWindow(gameWindow, result);
                    resultWindow1.setVisible(true);
                }


                repaint();
            }
        });
    }


    public void startNewGame(int mode, int fieldSize, int winningLength) {
        this.mode = mode;
        this.fieldSize = fieldSize;
        this.winningLength = winningLength;
        play_X.setVisible(true);

        isInit = true;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isInit) {
            return;
        }

        cellWidth = getWidth() / fieldSize;
        cellHeight = getHeight() / fieldSize;

        for (int i = 0; i < fieldSize; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, getWidth(), y);
        }

        for (int i = 0; i < fieldSize; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, getHeight());
        }

        for (int i = 0; i < Logic.SIZE; i++) {
            for (int j = 0; j < Logic.SIZE; j++) {
                if (Logic.map[i][j] == Logic.DOT_X) {
                    drawX(g, j, i);
                }
                if (Logic.map[i][j] == Logic.DOT_O) {
                    drawO(g, j, i);
                }
            }
        }

    }

    private void drawO(Graphics g, int cellX, int cellY) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.BLUE);
        g.drawOval(cellX * cellWidth + cellWidth / 5, cellY * cellHeight + cellHeight / 10, cellWidth - cellWidth / 3, cellWidth - cellWidth / 5);
    }

    private void drawX(Graphics g, int cellX, int cellY) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.RED);
        g.drawLine(cellX * cellWidth + cellWidth / 10, cellY * cellHeight + cellHeight / 10,
                (cellX + 1) * cellWidth - cellWidth / 10, (cellY + 1) * cellHeight - cellHeight / 10);
        g.drawLine((cellX + 1) * cellWidth - cellWidth / 10, (cellY * cellHeight + cellHeight / 10),
                cellX * cellWidth + cellWidth / 10, (cellY + 1) * cellHeight - cellHeight / 10);

    }
}
