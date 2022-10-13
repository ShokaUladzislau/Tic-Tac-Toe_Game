package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class GameEngine {
    private static final char[][] table = new char[3][3];
    private static char playerMark;
    static boolean gameStarted = false;
    static Component[] fieldComponents;
    static Component[] toolBarComponents;
    static Component statusComponent;

    public GameEngine(Component[] fieldButtons, Component[] toolBarComponents, Component statusComponent) {
        GameEngine.fieldComponents = fieldButtons;
        GameEngine.toolBarComponents = toolBarComponents;
        GameEngine.statusComponent = statusComponent;
    }

    public static void getButton(int i, int j, GameButton gameButton) {
        playerMark = (getPlayerMark()) ? 'O' : 'X';
        table[i][j] = playerMark;
        gameButton.setText(String.valueOf(playerMark));
    }

    public static void resetGame() {
        gameStarted = false;

        for (char[] chars : table) {
            Arrays.fill(chars, ' ');
        }

        for (Component button : fieldComponents) {
            ((GameButton) button).setText(" ");
        }

        ((JLabel) statusComponent).setText("Game is not started");
    }

    public static boolean gameNotFinished() {
        if (win(playerMark)) {
            System.out.println(playerMark + " wins");
            ((JLabel) statusComponent).setText(playerMark == 'X' ?
                    String.format("The %s Player (%s) wins", ((JButton) toolBarComponents[0]).getText(), playerMark) :
                    String.format("The %s Player (%s) wins", ((JButton) toolBarComponents[2]).getText(), playerMark));
            return false;
        }

        if (draw()) {
            ((JLabel) statusComponent).setText("Draw");
            return false;
        }

        return true;
    }

    private static void makeMove(String parameter) {
        if (Objects.equals(parameter, "Robot")) easyBotMove();
    }

    private static void easyBotMove() {
        System.out.println("Making move level \"easy\"\n");
        Random random = new Random();

        while (true) {
            int firstCoordinate = random.nextInt(3);
            int secondCoordinate = random.nextInt(3);

            if (table[firstCoordinate][secondCoordinate] == ' ') {
                for (Component button : fieldComponents) {
                    switch (button.getName()) {
                        case "ButtonA3":
                            if (firstCoordinate == 0 && secondCoordinate == 0)
                                GameEngine.getButton(0, 0, (GameButton) button);
                            break;
                        case "ButtonA2":
                            if (firstCoordinate == 1 && secondCoordinate == 0)
                                GameEngine.getButton(1, 0, (GameButton) button);
                            break;
                        case "ButtonA1":
                            if (firstCoordinate == 2 && secondCoordinate == 0)
                                GameEngine.getButton(2, 0, (GameButton) button);
                            break;
                        case "ButtonB3":
                            if (firstCoordinate == 0 && secondCoordinate == 1)
                                GameEngine.getButton(0, 1, (GameButton) button);
                            break;
                        case "ButtonB2":
                            if (firstCoordinate == 1 && secondCoordinate == 1)
                                GameEngine.getButton(1, 1, (GameButton) button);
                            break;
                        case "ButtonB1":
                            if (firstCoordinate == 2 && secondCoordinate == 1)
                                GameEngine.getButton(2, 1, (GameButton) button);
                            break;
                        case "ButtonC3":
                            if (firstCoordinate == 0 && secondCoordinate == 2)
                                GameEngine.getButton(0, 2, (GameButton) button);
                            break;
                        case "ButtonC2":
                            if (firstCoordinate == 1 && secondCoordinate == 2)
                                GameEngine.getButton(1, 2, (GameButton) button);
                            break;
                        case "ButtonC1":
                            if (firstCoordinate == 2 && secondCoordinate == 2)
                                GameEngine.getButton(2, 2, (GameButton) button);
                            break;
                    }
                }
                gameLoop();
                break;
            }
        }
    }

    private static boolean win(char mark) {
        boolean win = false;

        for (int i = 0; i < GameEngine.table.length; i++) {
            win = win || Arrays.equals(GameEngine.table[i], new char[]{mark, mark, mark}) || (GameEngine.table[0][i] == mark && GameEngine.table[1][i] == mark && GameEngine.table[2][i] == mark);
        }

        return win || (GameEngine.table[0][0] == mark && GameEngine.table[1][1] == mark && GameEngine.table[2][2] == mark) || (GameEngine.table[0][2] == mark && GameEngine.table[1][1] == mark && GameEngine.table[2][0] == mark);
    }

    private static boolean draw() {
        int emptyPlaces = 0;

        for (char[] row : GameEngine.table) {
            for (char mark : row) {
                emptyPlaces += (mark == ' ') ? 1 : 0;
            }
        }

        return emptyPlaces == 0;
    }

    public static String getToolbarButton(GameButton button) {

        switch (button.getName()) {
            case "ButtonPlayer1", "ButtonPlayer2" -> {
                return Objects.equals(button.getText(), "Robot") ? "Human" : "Robot";
            }
            case "ButtonStartReset" -> {
                if (Objects.equals(button.getText(), "Start")) {
                    freezeButtons(toolBarComponents);
                    unfreezeButtons(fieldComponents);
                    gameLoop();
                    return "Reset";
                } else {
                    resetGame();
                    freezeButtons(fieldComponents);
                    unfreezeButtons(toolBarComponents);
                    return "Start";
                }
            }
            default -> {
                return "";
            }
        }
    }

    public static void setGameFromMenu(String player1, String player2) {
        resetGame();

        for (Component button : toolBarComponents) {
            switch (button.getName()) {
                case "ButtonPlayer1" -> ((JButton) button).setText(player1);
                case "ButtonPlayer2" -> ((JButton) button).setText(player2);
                case "ButtonStartReset" -> {
                    freezeButtons(toolBarComponents);
                    unfreezeButtons(fieldComponents);
                    gameLoop();
                    ((JButton) button).setText("Reset");
                }
            }
        }
    }

    static void gameLoop() {
        gameStarted = true;

        if (gameNotFinished()) {
            playerMark = (getPlayerMark()) ? 'O' : 'X';

            if (playerMark == 'X') {
                ((JLabel) statusComponent).setText(String.format("The turn of " + (((JButton) toolBarComponents[0]).getText()) + " Player (" + playerMark + ")"));
                makeMove(((JButton) toolBarComponents[0]).getText());
            }
            if (playerMark == 'O') {
                ((JLabel) statusComponent).setText(String.format("The turn of " + (((JButton) toolBarComponents[2]).getText()) + " Player (" + playerMark + ")"));
                makeMove(((JButton) toolBarComponents[2]).getText());
            }
        }
    }

    private static void unfreezeButtons(Component[] buttons) {
        for (Component button : buttons) {
            if (!Objects.equals(button.getName(), "ButtonStartReset")) {
                button.setEnabled(true);
            }
        }
    }

    private static void freezeButtons(Component[] buttons) {
        for (Component button : buttons) {
            if (!Objects.equals(button.getName(), "ButtonStartReset")) {
                button.setEnabled(false);
            }
        }
    }

    public void setTable() {
        StringBuilder fieldButtonsValues = new StringBuilder();
        for (Component button : fieldComponents) {
            fieldButtonsValues.append(((GameButton) button).getText());
            button.setEnabled(false);
        }

        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = fieldButtonsValues.charAt(count);
                count++;
            }
        }
    }

    private static boolean getPlayerMark() {
        int x = 0;
        int o = 0;

        for (char[] chars : table) {
            for (char aChar : chars) {
                if (aChar == 'X') x++;
                if (aChar == 'O') o++;
            }
        }

        return x > o;
    }
}