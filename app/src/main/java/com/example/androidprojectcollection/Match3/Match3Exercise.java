package com.example.androidprojectcollection.Match3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidprojectcollection.R;

import java.util.ArrayList;
import java.util.Random;

public class Match3Exercise extends AppCompatActivity {
    TextView tvScore;
    Button restartBtn;
    int score = 0;


    MatchBtn[][] buttons = new MatchBtn[5][5];
    int[] colors = {Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match3);

        initButtons();
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvScore.setText(score + "");
    }

    void setScore() {
        score++;
        tvScore.setText(score + "");
    }


    ////////////////// CLICK STUFF //////////////////
    MatchBtn b1;
    MatchBtn b2;
    boolean isFirst = true;


    boolean swapButtons(MatchBtn a, MatchBtn b) {
        if ((a.row == b.row && a.col == b.col) ||
                (a.color == b.color)) {
            return false;
        }
        MatchBtn prevA = new MatchBtn(a.btn, a.row, a.col, a.color);
        a.setColor(b.color);
        b.setColor(prevA.color);

        return true;
    }

    void setClickListener(MatchBtn btn) {
        btn.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFirst) {
                    b1 = btn;
                    isFirst = false;
                } else {
                    b2 = btn;

                    if (b1.col == b2.col && b1.row-1 == b2.row ||
                        b1.col == b2.col && b1.row+1 == b2.row ||
                        b1.row == b2.row && b1.col-1 == b2.col ||
                        b1.row == b2.row && b1.col+1 == b2.col) {

                        if(swapButtons(b1, b2)) {
                            checkWin(b1);
                            checkWin(b2);
                        }
                    }
                    isFirst = true;
                }

                printButtons();
            }
        });

    }

    int generateReplacementColor() {
        Random r = new Random();
        return colors[r.nextInt(4)];
    }

    void checkWin(MatchBtn a) {
//        String hexColor = String.format("#%06X", (0xFFFFFF & a.color));
//        System.out.println(hexColor + "(" + a.col + ", " + a.row + ")");
//        System.out.println("CHECKING COLOR: " + hexColor);

        ArrayList<MatchBtn> verticalRes = checkVertical(a);
        if (verticalRes.size() < 3) {
            ArrayList<MatchBtn> horizontalRes = checkHorizontal(a);
            if (horizontalRes.size() == 3) {
                setScore();
            }
        } else {
            setScore();
        }
    }

    ArrayList<MatchBtn> checkHorizontal(MatchBtn a) {
        int count = 1;
        int col = a.col-1;

        ArrayList<MatchBtn> match = new ArrayList<>();

        // check left
        while (col >= 0) {
            if (count == 3) { break; }
            MatchBtn curr = buttons[a.row][col];
            if (a.color == Color.BLACK) {
                col--;
                continue;
            }

            if (curr.color == a.color) {
                match.add(curr);
                count++;
            } else break;
            col--;
        }
        // check right
        col = a.col+1;
        while (col < 5) {
            if (count == 3) { break ;}
            MatchBtn curr = buttons[a.row][col];
            if (a.color == Color.BLACK) {
                col++;
                continue;
            }
            if (curr.color == a.color) {
                match.add(curr);
                count++;
            } else break;
            col++;
        }

        if (count == 3) {
            match.add(a);
            for (MatchBtn b : match) {
                int color = generateReplacementColor();
                b.setColor(color);
                checkWin(a);
            }
        }

        return match;
    }

    ArrayList<MatchBtn> checkVertical(MatchBtn a) {
        int count = 1;
        int row = a.row-1;

        ArrayList<MatchBtn> match = new ArrayList<>();

        // check top
        while (row >= 0) {
            if (count == 3) { break; }
            MatchBtn curr = buttons[row][a.col];
            if (a.color == Color.BLACK) {
                row--;
                continue;
            }

            if (curr.color == a.color) {
                match.add(curr);
                count++;
            } else break;
            row--;
        }
        // check bot
        row = a.row+1;
        while (row < 5) {
            if (count == 3) { break ;}
            MatchBtn curr = buttons[row][a.col];
            if (a.color == Color.BLACK) {
                row++;
                continue;
            }
            if (curr.color == a.color) {
                match.add(curr);
                count++;
            } else break;
            row++;
        }

        if (count == 3) {
            match.add(a);
            for (MatchBtn b : match) {
                int color = generateReplacementColor();
                b.setColor(color);
                checkWin(a);
            }
        }

        return match;
    }

    void setBlackColor(ArrayList<MatchBtn> match) {
        for (MatchBtn a : match) {
            a.setColor(Color.BLACK);
        }
    }


    void generateRandomColors() {
        Random r = new Random();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                int curr = colors[r.nextInt(4)];

                if (col >= 2) {
                    while (true) {
                        if (curr == buttons[row][col-1].color &&
                            curr == buttons[row][col-2].color) {
                            curr = colors[r.nextInt(4)];
                        } else break;
                    }
                }

                if (row >= 2) {
                    while (true) {
                        if (curr == buttons[row-1][col].color &&
                                curr == buttons[row-2][col].color) {
                            curr = colors[r.nextInt(4)];
                        } else break;
                    }
                }
                buttons[row][col].color = curr;
            }
        }
    }

    String getColorName(int colorVal) {
        if (colorVal == -16776961) { return "B"; }
        if (colorVal == -65536) { return "R"; }
        if (colorVal == -256) { return "Y"; }
        if (colorVal == -16711936) { return "G"; }
        return "_";
    }
    
    void printButtons() {
        // red      = #FF0000
        // green    = #00FF00
        // yellow   = #FFFF00
        // blue     = #0000FF

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                MatchBtn curr = buttons[row][col];
                String hexColor = String.format("#%06X", (0xFFFFFF & curr.color));


                System.out.print("("+curr.row+","+curr.col+")"+"-"+""+ getColorName(curr.color)+ "\t");
            }
            System.out.println();
        }
    }

    void setButtonColors() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                MatchBtn curr = buttons[row][col];
                curr.btn.setBackgroundColor(curr.color);
            }
        }
    }

    void initButtons() {
        buttons[0][0] = new MatchBtn((Button) findViewById(R.id.tile00), 0, 0);
        buttons[0][1] = new MatchBtn((Button) findViewById(R.id.tile01), 0, 1);
        buttons[0][2] = new MatchBtn((Button) findViewById(R.id.tile02), 0, 2);
        buttons[0][3] = new MatchBtn((Button) findViewById(R.id.tile03), 0, 3);
        buttons[0][4] = new MatchBtn((Button) findViewById(R.id.tile04), 0, 4);
        buttons[1][0] = new MatchBtn((Button) findViewById(R.id.tile10), 1, 0);
        buttons[1][1] = new MatchBtn((Button) findViewById(R.id.tile11), 1, 1);
        buttons[1][2] = new MatchBtn((Button) findViewById(R.id.tile12), 1, 2);
        buttons[1][3] = new MatchBtn((Button) findViewById(R.id.tile13), 1, 3);
        buttons[1][4] = new MatchBtn((Button) findViewById(R.id.tile14), 1, 4);
        buttons[2][0] = new MatchBtn((Button) findViewById(R.id.tile20), 2, 0);
        buttons[2][1] = new MatchBtn((Button) findViewById(R.id.tile21), 2, 1);
        buttons[2][2] = new MatchBtn((Button) findViewById(R.id.tile22), 2, 2);
        buttons[2][3] = new MatchBtn((Button) findViewById(R.id.tile23), 2, 3);
        buttons[2][4] = new MatchBtn((Button) findViewById(R.id.tile24), 2, 4);
        buttons[3][0] = new MatchBtn((Button) findViewById(R.id.tile30), 3, 0);
        buttons[3][1] = new MatchBtn((Button) findViewById(R.id.tile31), 3, 1);
        buttons[3][2] = new MatchBtn((Button) findViewById(R.id.tile32), 3, 2);
        buttons[3][3] = new MatchBtn((Button) findViewById(R.id.tile33), 3, 3);
        buttons[3][4] = new MatchBtn((Button) findViewById(R.id.tile34), 3, 4);
        buttons[4][0] = new MatchBtn((Button) findViewById(R.id.tile40), 4, 0);
        buttons[4][1] = new MatchBtn((Button) findViewById(R.id.tile41), 4, 1);
        buttons[4][2] = new MatchBtn((Button) findViewById(R.id.tile42), 4, 2);
        buttons[4][3] = new MatchBtn((Button) findViewById(R.id.tile43), 4, 3);
        buttons[4][4] = new MatchBtn((Button) findViewById(R.id.tile44), 4, 4);

        restartBtn = (Button) findViewById(R.id.btnRestartMatch3);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initButtons();
                b1 = null;
                b2 = null;
                isFirst = true;
                score = 0;
                tvScore.setText(score + "");
            }
        });

        generateRandomColors();
        setButtonColors();


        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                setClickListener(buttons[row][col]);
            }
        }

    }
}