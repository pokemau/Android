package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Match3 extends AppCompatActivity {

//    Button t00, t01, t02, t03, t04;
//    Button t10, t11, t12, t13, t14;
//    Button t20, t21, t22, t23, t24;
//    Button t30, t31, t32, t33, t34;
//    Button t40, t41, t42, t43, t44;

    int[] colors = {Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN};

    int[][] buttonColors = new int[5][5];

    Button[][] buttons = new Button[5][5];



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
        int prevB1Color = buttonColors[b1.row][b1.col];
        int prevB2Color = buttonColors[b2.row][b2.col];
        int temp = prevB1Color;

        prevB1Color = prevB2Color;
        prevB2Color = temp;

        buttonColors[b2.row][b2.col] = prevB2Color;
        buttonColors[b1.row][b1.col] = prevB1Color;

        b1.btn.setBackgroundColor(buttonColors[b1.row][b2.col]);
        b2.btn.setBackgroundColor(buttonColors[b2.row][b2.col]);
    }

    void setClickListener(Button btn, int row, int col) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFirst) {
                    b1 = new MatchBtn(btn, row, col);
                    isFirst = false;
                } else {
                    // check adjacent
                    // check top
                    b2 = new MatchBtn(btn, row, col);

                    if (b1.col == col && b1.row-1 == row ) {

                        swapColors(b1, b2);

//                        int prevB1Color = buttonColors[b1.row][b1.col];
//                        int prevB2Color = buttonColors[b2.row][b2.col];
//                        int temp = prevB1Color;
//
//                        prevB1Color = prevB2Color;
//                        prevB2Color = temp;
//
//                        buttonColors[b2.row][b2.col] = prevB2Color;
//                        buttonColors[b1.row][b1.col] = prevB1Color;
//
//                        b1.btn.setBackgroundColor(buttonColors[b1.row][b2.col]);
//                        b2.btn.setBackgroundColor(buttonColors[b2.row][b2.col]);
                    }
//                    // check bot
                    if (b1.col == col && b1.row+1 == row) {
                        swapColors(b1, b2);
                    }
                    // check left
                    if (b1.row == row && b1.col-1 == col) {
                        swapColors(b1, b2);
                    }
//                    // check right
//                    if (b1.row == row && b1.col+1 == col) {
//                        b1.btn.setBackgroundColor(buttonColors[row][col]);
//                    }

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

        generateRandomColors();
        setButtonColors();


        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                setClickListener(buttons[row][col], row, col);
            }
        }
    }
}