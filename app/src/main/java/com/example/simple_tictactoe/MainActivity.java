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
     * a button
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
        }
    }

}