package com.example.simple_tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    protected int moveCount = 0;
    protected State state = State.PLAYING;
    protected Button[][] grid = new Button[3][3];
    protected int xScore = 0;
    protected int oScore = 0;

    public enum State{
        XWON,
        OWON,
        PLAYING,
        DRAW;
    }

    public enum Value{
        X,
        O,
        BLANK,
    }

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
     * returns the value of button b.
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
        }else {
            return Value.BLANK;
        }
    }

    /**
     *returns the row of button b
     * @param b
     * button b
     * @return the row which b is placed at
     */
    protected int getRow(Button b){
        String ID = b.getResources().getResourceEntryName(b.getId());
        return Character.getNumericValue(ID.charAt(ID.length()-2));
    }

    /**
     *returns the column of button b
     * @param b
     * button b
     * @return the column which b is placed at
     */
    protected int getCol(Button b){
        String ID = b.getResources().getResourceEntryName(b.getId());
        return Character.getNumericValue(ID.charAt(ID.length()-1));
    }

    /**
     * checks whether the player placed in button b has won the game.
     * @param b
     * button b
     */
    protected void checkForWin(Button b){
        Value player = getValue(b);
        int countRow = 0;
        int countCol = 0;
        int countDiag = 0;
        int countAnti = 0;
        int row = getRow(b);
        int col = getCol(b);

        //Checks row
        for (int i = 0; i < 3 ; i++){
            if (getValue(grid[row][i]) == player){
                countRow ++;
            }
        }

        //Checks column
        for (int i = 0; i < 3 ; i++){
            if (getValue(grid[i][col]) == player){
                countCol ++;
            }
        }

        //Checks diagonal
        if (row == col) {
            for (int i = 0; i < 3; i++) {
                if (getValue(grid[i][i]) == player) {
                    countDiag++;
                }
            }
        }


        //Checks anti-diagonal
        if (row + col == 3-1){
            for (int i = 0; i < 3; i++) {
                if (getValue(grid[i][2-i]) == player) {
                    countAnti++;
                }
            }
        }


        if (countRow == 3 || countCol == 3 || countDiag == 3 || countAnti == 3){
            if (player == Value.X){
                state = State.XWON;
            }
            else if (player == Value.O) {
                state = State.OWON;
            }
        }
    }


    public void onClick(View v) {
        Button b = (Button) v;
        if (getValue(b) == Value.BLANK && state == State.PLAYING){
            if (whosTurn() == Value.X){
                b.setText("X");
            }
            else if (whosTurn() == Value.O){
                b.setText("O");
            }
            moveCount ++;

            checkForWin(b);

        }
    }



}