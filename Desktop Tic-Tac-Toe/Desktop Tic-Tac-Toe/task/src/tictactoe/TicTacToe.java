package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class TicTacToe extends JFrame {
    public TicTacToe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setResizable(false);
        setSize(450, 450);

        //menu-bar
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem humanVsHuman = new JMenuItem("Human vs Human");
        JMenuItem humanVsRobot = new JMenuItem("Human vs Robot");
        JMenuItem robotVsHuman = new JMenuItem("Robot vs Human");
        JMenuItem robot_vs_robot = new JMenuItem("Robot vs Robot");
        JMenuItem exit = new JMenuItem("Exit");

        gameMenu.setName("MenuGame");
        humanVsHuman.setName("MenuHumanHuman");
        humanVsRobot.setName("MenuHumanRobot");
        robotVsHuman.setName("MenuRobotHuman");
        robot_vs_robot.setName("MenuRobotRobot");
        exit.setName("MenuExit");

        humanVsHuman.addActionListener(event -> GameEngine.setGameFromMenu("Human", "Human"));
        humanVsRobot.addActionListener(event -> GameEngine.setGameFromMenu("Human", "Robot"));
        robotVsHuman.addActionListener(event -> GameEngine.setGameFromMenu("Robot", "Human"));
        robot_vs_robot.addActionListener(event -> GameEngine.setGameFromMenu("Robot", "Robot"));
        exit.addActionListener(event -> System.exit(0));

        gameMenu.add(humanVsHuman);
        gameMenu.add(humanVsRobot);
        gameMenu.add(robotVsHuman);
        gameMenu.add(robot_vs_robot);
        gameMenu.addSeparator();
        gameMenu.add(exit);

        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        Component board = new Board();
        add(board);
        setVisible(true);
    }
}