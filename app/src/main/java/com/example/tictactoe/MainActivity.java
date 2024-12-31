package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean playerOneActive;
    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons=new Button[9];
    private Button reset,playAgain;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int rounds;
    private int playerOneScoreCount, playerTwoScoreCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore = findViewById(R.id.score_player1);
        playerTwoScore = findViewById(R.id.score_player2);
        playerStatus = findViewById(R.id.textstatus);
        reset = findViewById(R.id.btn_reset);
        playAgain = findViewById(R.id.btn_play_again);

        buttons[0] = findViewById(R.id.btn0);
        buttons[1] = findViewById(R.id.btn1);
        buttons[2] = findViewById(R.id.btn2);
        buttons[3] = findViewById(R.id.btn3);
        buttons[4] = findViewById(R.id.btn4);
        buttons[5] = findViewById(R.id.btn5);
        buttons[6] = findViewById(R.id.btn6);
        buttons[7] = findViewById(R.id.btn7);
        buttons[8] = findViewById(R.id.btn8);

        for(int i=0;i<buttons.length;i++)
        {
            buttons[i].setOnClickListener(this);
        }
        playerOneScoreCount=0;
        playerTwoScoreCount=0;
        playerOneActive=true;
        rounds=0;


    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals(""))
        {
            return;
        }
        else if(checkWinner())
        {
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));
        if(playerOneActive)
        {
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#ffc34a"));
            gameState[gameStatePointer]=0;
        }
        else {
            ((Button)v).setText("0");
            ((Button)v).setTextColor(Color.parseColor("#70fc3a"));
            gameState[gameStatePointer]=1;
            
        }
        rounds++;
        if(checkWinner())
        {
            if(playerOneActive)
            {
                playerOneScoreCount++;
                updatePlayerScore();
                playerStatus.setText("Player-1 has won");
            }
            else
            {
                playerTwoScoreCount++;
                updatePlayerScore();
                playerStatus.setText("Player-2 has won");
            }
        }
        else if(rounds==9)
        {
            playerStatus.setText("No Winner");
        }
        else
        {
            playerOneActive = !playerOneActive;
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount=0;
                playerTwoScoreCount=0;
                updatePlayerScore();
            }
        });
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });
    }

    private boolean checkWinner() {
        boolean winnerResults = false;
        for(int[] winningPositions: winningPositions)
        {
            if(gameState[winningPositions[0]]==gameState[winningPositions[1]] &&
                    gameState[winningPositions[1]]==gameState[winningPositions[2]]&&
                    gameState[winningPositions[0]]!=2)
            {
                winnerResults=true;
            }
        }
        return winnerResults;
    }

    private void playAgain() {
        rounds=0;
        playerOneActive=true;
        for(int i=0;i<buttons.length;i++)
        {
            gameState[i]=2;
            buttons[i].setText("");
        }
        playerStatus.setText("Status");
    }

    private void updatePlayerScore() {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }
}