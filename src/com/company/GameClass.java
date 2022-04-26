package com.company;

import java.awt.*;
import  java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class GameClass implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    int[] cells = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    List<Integer> playableCells = new ArrayList<Integer>(9);
    //int[] playableCells = new int[9];
    boolean player1Turn;
    int[][] winningCombination = {{0,1,2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    //Constructor
    GameClass() {

        playableCells.add(0);
        playableCells.add(1);
        playableCells.add(2);
        playableCells.add(3);
        playableCells.add(4);
        playableCells.add(5);
        playableCells.add(6);
        playableCells.add(7);
        playableCells.add(8);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);


        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));


        for (int i = 0; i < buttons.length; i++) {

            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

        }


        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);


        firstTurn();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //This will run 9 times
        for (int i = 0; i < playableCells.size(); i++) {

            if(e.getSource() == buttons[i]) {

                if(player1Turn) {
                    if(buttons[i].getText() == "") {
                        System.out.println(i + "Player");
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        textField.setText("Com Turn");
                        playableCells.remove(Integer.valueOf(i));

                        check(buttons[i].getDisplayedMnemonicIndex(), "X");
                        player1Turn = false;

                        AICom();

                    }
                } else {
                  /*  if(buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1Turn = true;
                        textField.setText("X Turn");
                        check(buttons[i].getDisplayedMnemonicIndex(), "O");
                    }*/
                }

            }

        }

    }

    public void firstTurn() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(random.nextInt(2) == 0){
            player1Turn = true;
            textField.setText("X Turn");
        } else {
            player1Turn = false;
            AICom();
            textField.setText("Com Turn");
        }

    }

    public void AICom() {

            try {
             Thread.sleep(500);
          } catch (InterruptedException e) {
              e.printStackTrace();
         }


        System.out.println("Playable Cells Left: " + playableCells);


           int randomIndex = ThreadLocalRandom.current().nextInt(0, 9);
            //int randomIndex = ThreadLocalRandom.current().nextInt(playableCells.size());
            //int randomIndex = random.nextInt(playableCells.size());
            System.out.println(randomIndex);
            chooseMove(randomIndex);

            // Index corresponds to number in list
            System.out.println("Com Next Move: " + randomIndex);
                System.out.println("Com Turn...");
                buttons[randomIndex].setForeground(new Color(0, 0, 255));
                buttons[randomIndex].setText("O");
                player1Turn = true;
                textField.setText("Player Turn");
                //boolean isRemoved = playableCells.remove("randomIndex");
                playableCells.remove(Integer.valueOf(randomIndex));
            System.out.println("Playable Cells Left: " + playableCells);
                check(buttons[randomIndex].getDisplayedMnemonicIndex(), "O");


    }

    public void chooseMove(int move) {
        if (!playableCells.contains(move)) {
            System.out.println("Number not available, check again");
            AICom();
        }
    }

    public void check(int a, String player) {
//      Check if X wins TODO: Refactor This Mess
        // OnMouseClick get number
        // get the 3 numbers
        // int[] storedPoints = new int[0];
        //
/*
        for (int i = 0; i < a; i++) {
            storedPoints[i] = a;
            System.out.println(storedPoints);
        }

        for (int i = 0; i < winningCombination.length; i++) {

            OptionalInt combination = Arrays.stream(winningCombination[i]).findFirst();

            System.out.println(combination.getAsInt());

        }


*/
        if(
                (buttons[0].getText() == "X")
                && (buttons[1].getText() == "X")
                && (buttons[2].getText() == "X")
        ) {
            xWins(0, 1 ,2);
        }
        if(
                (buttons[3].getText() == "X")
                        && (buttons[4].getText() == "X")
                        && (buttons[5].getText() == "X")
        ) {
            xWins(3, 4 ,5);
        }

        if(
                (buttons[6].getText() == "X")
                        && (buttons[7].getText() == "X")
                        && (buttons[8].getText() == "X")
        ) {
            xWins(6, 7 ,8);
        }
        if(
                (buttons[1].getText() == "X")
                        && (buttons[4].getText() == "X")
                        && (buttons[7].getText() == "X")
        ) {
            xWins(1, 4 ,7);
        }


    }

    public void xWins(int a, int b, int c) {

        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);

        }

        textField.setText("X Wins");

    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);

        }

        textField.setText("O Wins");
    }
}
