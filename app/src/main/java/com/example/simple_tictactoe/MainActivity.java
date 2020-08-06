package com.example.simple_tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * The class <b>MainActivity</b> is the
 * class that implements and plays the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintain the current state of
 * the game as players are making moves.
 *
 * @author Parsa Fathazam
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Variable declaration

    private final int size = 4; //We set the size of the board according to the number of buttons.
    private int moveCount = 0;
    private State state = State.PLAYING;
    private Button[][] grid = new Button[size][size];
    private int oScore = 0;
    private int xScore = 0;
    private TextView oScoreView;
    private TextView xScoreView;

    private enum State{
        XWON,
        OWON,
        PLAYING,
        DRAW
    }

    private enum Value{
        X,
        O,
        BLANK,
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xScoreView = findViewById(R.id.xScoreView);
        xScoreView.setTextColor(Color.parseColor("#E91E63"));
        xScoreView.setText("Player X: " + xScore);

        oScoreView = findViewById(R.id.oScoreView);
        oScoreView.setTextColor(Color.parseColor("#FF00BCD4"));
        oScoreView.setText("Player O: " + oScore);

        Button nextRound = findViewById(R.id.nextRound);
        nextRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state != State.PLAYING){
                    nextRound();
                }
            }
        });

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    reset();
                }
            });

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String buttonID = "button" + i + j;
                int ID = getResources().getIdentifier(buttonID, "id", getPackageName());
                grid[i][j] = findViewById(ID);
                grid[i][j].setOnClickListener(this);
            }
        }
    }


    /**
     * returns whose turn it is.
     * @return
     *  the player whose supposed to make the next move.
     */
    protected Value whosTurn(){
        if (moveCount % 2 == 0) {
            return Value.X;
        } else {
            return Value.O;
        }
    }


    /**
     * returns the value that button b contains.
     * @param b
     * button b
     * @return
     *  the value of button b.
     */
    protected Value getValue(Button b){
        if (b.getText().equals("X")) {
            return Value.X;
        } else if (b.getText().equals("O")){
            return Value.O;
        } else {
            return Value.BLANK;
        }
    }

    /**
     *returns the row that contains button b
     * @param b
     * button b
     * @return the row which b is placed at
     */
    protected int getRow(Button b){
        String ID = b.getResources().getResourceEntryName(b.getId());
        return Character.getNumericValue(ID.charAt(ID.length()-2));
    }

    /**
     *returns the column that contains button b
     * @param b
     * button b
     * @return the column which b is placed at
     */
    protected int getCol(Button b){
        String ID = b.getResources().getResourceEntryName(b.getId());
        return Character.getNumericValue(ID.charAt(ID.length()-1));
    }

    /**
     * checks whether the player placed on button b has won the game.
     * @param b
     * button b
     */
    @SuppressLint("SetTextI18n")
    protected void checkForWin(Button b){
        Value player = getValue(b);
        int countRow = 0;
        int countCol = 0;
        int countDiag = 0;
        int countAnti = 0;
        int row = getRow(b);
        int col = getCol(b);

        //Checks row
        for (int i = 0; i < size ; i++){
            if (getValue(grid[row][i]) == player){
                countRow ++;
            }
        }

        //Checks column
        for (int i = 0; i < size ; i++){
            if (getValue(grid[i][col]) == player){
                countCol ++;
            }
        }

        //Checks diagonal
        if (row == col) {
            for (int i = 0; i < size; i++) {
                if (getValue(grid[i][i]) == player) {
                    countDiag++;
                }
            }
        }

        //Checks anti-diagonal
        if (row + col == size-1){
            for (int i = 0; i < size; i++) {
                if (getValue(grid[i][size-1-i]) == player) {
                    countAnti++;
                }
            }
        }

        //Sets game state based on the winner.
        if (countRow == size || countCol == size || countDiag == size || countAnti == size){
            if (player == Value.X){
                state = State.XWON;
                xScore++;
                xScoreView.setText("Player X: " + xScore);
            }
            else if (player == Value.O) {
                state = State.OWON;
                oScore++;
                oScoreView.setText("Player O: " + oScore);
            }
        }

        else if (moveCount == size*size) {
            state = State.DRAW;
        }

    }

    /**
     * Moves a player to button b depending on whose turn it is.
     * @param b
     * button b
     */
    protected void move(Button b) {
            if (whosTurn() == Value.X) {
                b.setText("X");
            } else if (whosTurn() == Value.O) {
                b.setText("O");
            }
            updateColor(b);
            moveCount++;
        }


    /**
     * Clears the grid for another round of the game.
     *
     */
    protected void nextRound(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                Button b = grid[i][j];
                b.setText("");
                updateColor(b);
            }
        }
        moveCount = 0;
        state = State.PLAYING;
    }

    /**
     * resets the entire game.
     *
     */
    protected void reset(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                Button b = grid[i][j];
                b.setText("");
                updateColor(b);
            }
        }
        oScore = 0;
        xScore = 0;
        oScoreView.setText("Player O: " + oScore);;
        xScoreView.setText("Player X: " + xScore);;
        moveCount = 0;
        state = State.PLAYING;
    }

    /**
     * Updates color of the button b depending on what value it contains.
     * @param b
     * button b
     */
    @SuppressLint("NewApi")
    protected void updateColor(Button b){
        Value player = getValue(b);
        if (player == Value.X){
            b.setBackgroundTintList(null);
            b.setBackgroundColor(Color.parseColor("#E91E63"));
        }
        else if (player == Value.O){
            b.setBackgroundTintList(null);
            b.setBackgroundColor(Color.parseColor("#FF00BCD4"));
        }
        else {
            b.setBackgroundResource(android.R.drawable.btn_default);
            b.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFEB64")));
        }
    }


    public void onClick(View v) {
        Button b = (Button) v;
        if (getValue(b) == Value.BLANK && state == State.PLAYING) {
            move(b);
            checkForWin(b);
        }
    }


}