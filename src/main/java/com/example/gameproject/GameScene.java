package com.example.gameproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameScene {

    Button[] xoBtns = new Button[9];
    Button reset;
    Player[] players;
    Label[] playersLabels;
    Label[] scoresLabels;
    boolean[] xPositions;
    boolean[] oPositions;
    Text txt;
    int choice;
    GridPane grid = new GridPane();
    VBox vBox;

    public GameScene() {
        initController();
        renderScene();
        initActions();
    }

    public void renderScene() {
        grid.add(playersLabels[0], 0, 0);
        grid.add(xoBtns[0], 1, 0);
        grid.add(xoBtns[1], 2, 0);
        grid.add(xoBtns[2], 3, 0);
        grid.add(playersLabels[1], 4, 0);
        grid.add(scoresLabels[0], 0, 1);
        grid.add(xoBtns[3], 1, 1);
        grid.add(xoBtns[4], 2, 1);
        grid.add(xoBtns[5], 3, 1);
        grid.add(scoresLabels[1], 4, 1);
        grid.add(xoBtns[6], 1, 2);
        grid.add(xoBtns[7], 2, 2);
        grid.add(xoBtns[8], 3, 2);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(20);

        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(40);

        vBox.setStyle("-fx-background-color: darkslategrey");

        //font: Edwardian Script ITC
        txt.setFont(Font.font("Edwardian Script ITC", 50));
        txt.setFill(Color.BEIGE);
        txt.setStrokeWidth(2);
        txt.setStroke(Color.DARKKHAKI);
    }

    public void initController() {
        choice = 0;
        txt = new Text("Tic Tac Toe");
        reset = new Button("Reset");

        for (int i = 0; i < xoBtns.length; i++) {
            xoBtns[i] = new Button();
            xoBtns[i].setPrefWidth(35);
            xoBtns[i].setPrefHeight(35);
        }

        xPositions = new boolean[9];
        oPositions = new boolean[9];

        players = new Player[]{new Player(), new Player()};
        playersLabels = new Label[]{new Label("X"), new Label("O")};
        scoresLabels = new Label[]{new Label("Score: " + players[0].getScore()), new Label("Score: " + players[1].getScore())};
        playersLabels[0].getStyleClass().add("xoBtn");
        playersLabels[1].getStyleClass().add("xoBtn");

        playersLabels[0].prefWidth(20);
        vBox = new VBox(txt, grid, reset);
    }

    public void initActions() {

        for (int i = 0; i < xoBtns.length; i++) {//add action event for all the buttons
            int finalI = i;
            xoBtns[i].setOnAction(actionEvent -> {//if the user clicks the button, and it is empty update 'choice' variable and call the method 'handleMatch'
                    choice = finalI;
                if (xoBtns[choice].getText().isEmpty()) {//if the button selected doesn't already have an X or an O
                    handleMatch();
                }
            });
        }
        reset.setOnAction(actionEvent -> reset(true));

        markPlayer();

    }

    public boolean winCondition(boolean[] positions) {
        return (positions[0] && positions[1] && positions[2]) || (positions[3] && positions[4] && positions[5]) || (positions[6] && positions[7] && positions[8]) || (positions[0] && positions[3] && positions[6]) || (positions[1] && positions[4] && positions[7]) || (positions[2] && positions[5] && positions[8]) || (positions[0] && positions[4] && positions[8]) || (positions[2] && positions[4] && positions[6]);
    }

    public void handleMatch() {
        int turn = players[0].getTurn();//the current player
        switch (turn) {
            case 0 -> {
                xoBtns[choice].setText("X");
                xPositions[choice] = true; //mark the position that was selected
            }
            case 1 -> {
                xoBtns[choice].setText("O");
                oPositions[choice] = true;
            }
        }
        if (winCondition(xPositions) || winCondition(oPositions)) {//if it is a win condition increment the score of the current player
            players[turn].incScore();
            scoresLabels[turn].setText("Score: " + players[turn].getScore());
            reset(false);
        }
        players[0].setTurn(turn == 1 ? 0 : 1);//if the current player is player 1 switch to player 2, else switch to player 1
        markPlayer();
    }

    private void reset(boolean resetAll) {
        for (Button btn : xoBtns)
            btn.setText("");
        oPositions = new boolean[9];
        xPositions = new boolean[9];
        if (resetAll) {
            players[0].setTurn(0);
            players[0].resetScore();
            players[1].resetScore();
            scoresLabels[0].setText("Score: " + 0);
            scoresLabels[1].setText("Score: " + 0);
        }
        markPlayer();
    }

    public void markPlayer() {
        int turn = players[0].getTurn();
        for (int i = 0; i < 2; i++) {
            if (i == turn)
                playersLabels[turn].getStyleClass().add("turn");
            else {
                playersLabels[i].getStyleClass().clear();
                playersLabels[i].getStyleClass().add("xoBtn");
            }
        }
    }

    Scene getScene() {
        return new Scene(vBox, 460, 500);
    }


}
