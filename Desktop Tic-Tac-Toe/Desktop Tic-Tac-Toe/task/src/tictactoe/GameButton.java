package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class GameButton extends JButton {
    public GameButton(String name) {

        setName("Button" + name);

        if (name.equals("Player1") || name.equals("Player2")) {
            setText("Human");
            setFont(new Font("Arial", Font.BOLD, 10));
        } else if (name.equals("StartReset")) {
            setText("Start");
            setFont(new Font("Arial", Font.BOLD, 10));
        } else {
            setText(" ");
            setFont(new Font("Arial", Font.BOLD, 40));
        }


        addActionListener(e -> {
            if (name.equals("Player1") || name.equals("Player2") || name.equals("StartReset"))
                setText(GameEngine.getToolbarButton(this));

            if (GameEngine.gameNotFinished()) {
                if (Objects.equals(getText(), " ")) {
                    switch (name) {
                        case "A3" -> GameEngine.getButton(0, 0, this);
                        case "A2" -> GameEngine.getButton(1, 0, this);
                        case "A1" -> GameEngine.getButton(2, 0, this);
                        case "B3" -> GameEngine.getButton(0, 1, this);
                        case "B2" -> GameEngine.getButton(1, 1, this);
                        case "B1" -> GameEngine.getButton(2, 1, this);
                        case "C3" -> GameEngine.getButton(0, 2, this);
                        case "C2" -> GameEngine.getButton(1, 2, this);
                        case "C1" -> GameEngine.getButton(2, 2, this);
                    }
                    GameEngine.gameLoop();
                }
            }
        });
    }
}