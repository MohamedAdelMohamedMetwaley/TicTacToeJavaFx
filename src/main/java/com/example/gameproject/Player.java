package com.example.gameproject;

public class Player {
    private int score;
    private static int turn;

    public Player(){
        score= 0;
        turn = 0;
    }

    public int getScore() {
        return score;
    }

    public void incScore() {
        this.score++;
    }
    public void resetScore(){
        this.score = 0;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        Player.turn = turn;
    }

}
