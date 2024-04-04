package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Match3 extends AppCompatActivity {


    int[] colors = {Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN};

    int[][] buttonColors = new int[5][5];

    Button[][] buttons = new Button[5][5];


    Button restartBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match3);

        initButtons();
    }


    ////////////////// CLICK STUFF
    MatchBtn b1;
    MatchBtn b2;
    boolean isFirst = true;


    void swapColors(MatchBtn a, MatchBtn b) {
        int prevB1Color = buttonColors[a.row][a.col];
        int prevB2Color = buttonColors[b.row][b.col];
        int temp = prevB1Color;

        prevB1Color = prevB2Color;
        prevB2Color = temp;

        buttonColors[b.row][b.col] = prevB2Color;
        buttonColors[a.row][a.col] = prevB1Color;

        b1.btn.setBackgroundColor(buttonColors[a.row][a.col]);
        b2.btn.setBackgroundColor(buttonColors[b.row][b.col]);
    }

    void setClickListener(Button btn, int row, int col) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFirst) {
                    b1 = new MatchBtn(btn, row, col);
                    isFirst = false;
                } else {
                    b2 = new MatchBtn(btn, row, col);

                    if (b1.col == col && b1.row-1 == row ||
                        b1.col == col && b1.row+1 == row ||
                        b1.row == row && b1.col-1 == col ||
                        b1.row == row && b1.col+1 == col) {

                        swapColors(b1, b2);
                    }
                    isFirst = true;
                }
            }
        });

    }


    void generateRandomColors() {
        Random r = new Random();
        for (int row = 0; row < 5; row++) {

            for (int col = 0; col < 5; col++) {

                int curr = colors[r.nextInt(4)];

                if (col >= 2) {
                    while (true) {
                        if (curr == buttonColors[row][col-1] && curr == buttonColors[row][col-2]) {
                            curr = colors[r.nextInt(4)];
                        } else break;
                    }
                }

                if (row >= 2) {
                    while (true) {
                        if (curr == buttonColors[row-1][col] && curr == buttonColors[row-2][col]) {
                            curr = colors[r.nextInt(4)];
                        } else break;
                    }
                }
                buttonColors[row][col] = curr;
            }
        }
    }

    void setButtonColors() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                buttons[row][col].setBackgroundColor(buttonColors[row][col]);
            }
        }
    }

    void initButtons() {
        buttons[0][0] = (Button) findViewById(R.id.tile00);
        buttons[0][1] = (Button) findViewById(R.id.tile01);
        buttons[0][2] = (Button) findViewById(R.id.tile02);
        buttons[0][3] = (Button) findViewById(R.id.tile03);
        buttons[0][4] = (Button) findViewById(R.id.tile04);
        buttons[1][0] = (Button) findViewById(R.id.tile10);
        buttons[1][1] = (Button) findViewById(R.id.tile11);
        buttons[1][2] = (Button) findViewById(R.id.tile12);
        buttons[1][3] = (Button) findViewById(R.id.tile13);
        buttons[1][4] = (Button) findViewById(R.id.tile14);
        buttons[2][0] = (Button) findViewById(R.id.tile20);
        buttons[2][1] = (Button) findViewById(R.id.tile21);
        buttons[2][2] = (Button) findViewById(R.id.tile22);
        buttons[2][3] = (Button) findViewById(R.id.tile23);
        buttons[2][4] = (Button) findViewById(R.id.tile24);
        buttons[3][0] = (Button) findViewById(R.id.tile30);
        buttons[3][1] = (Button) findViewById(R.id.tile31);
        buttons[3][2] = (Button) findViewById(R.id.tile32);
        buttons[3][3] = (Button) findViewById(R.id.tile33);
        buttons[3][4] = (Button) findViewById(R.id.tile34);
        buttons[4][0] = (Button) findViewById(R.id.tile40);
        buttons[4][1] = (Button) findViewById(R.id.tile41);
        buttons[4][2] = (Button) findViewById(R.id.tile42);
        buttons[4][3] = (Button) findViewById(R.id.tile43);
        buttons[4][4] = (Button) findViewById(R.id.tile44);

        restartBtn = (Button) findViewById(R.id.btnRestartMatch3);

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initButtons();
            }
        });

        generateRandomColors();
        setButtonColors();


        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                setClickListener(buttons[row][col], row, col);
            }
        }

    }
}