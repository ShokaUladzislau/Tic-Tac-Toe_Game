package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Board extends JPanel {

    String[] ToolbarButtonsNames = {"Player1", "StartReset", "Player2"};
    String[] CellNames = {"A3", "B3", "C3", "A2", "B2", "C2", "A1", "B1", "C1"};
    public Board() {
        setLayout(new BorderLayout());

        //toolbar v2
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new GridLayout(1, 3));

        for (String name : ToolbarButtonsNames) {
            toolbarPanel.add(new GameButton(name));
        }

        add(toolbarPanel, BorderLayout.NORTH);

        //gameField
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(3,3));

        for (String name : CellNames) {
            fieldPanel.add(new GameButton(name));
        }

        add(fieldPanel, BorderLayout.CENTER);

        //statusField
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BorderLayout());

        JLabel labelStatus = new JLabel();
        labelStatus.setText("Game is not started");
        labelStatus.setName("LabelStatus");
        labelStatus.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        statusPanel.add(labelStatus, BorderLayout.WEST);

        add(statusPanel, BorderLayout.SOUTH);

        //set game engine
        Component[] fieldComponents = fieldPanel.getComponents();
        Component[] toolBarComponents = toolbarPanel.getComponents();
        Component statusComponent = statusPanel.getComponent(0);

        GameEngine gameEngine = new GameEngine(fieldComponents, toolBarComponents, statusComponent);
        gameEngine.setTable();
    }
}
