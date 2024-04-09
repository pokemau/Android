package com.example.androidprojectcollection.Match3;

import android.graphics.Color;
import android.widget.Button;

public class MatchBtn {
    Button btn;
    int row, col;
    int color;

    public MatchBtn(Button btn, int row, int col) {
        this.btn = btn;
        this.row = row;
        this.col = col;
        color = Color.BLACK;
    }

    public MatchBtn(Button btn, int row, int col, int color) {
        this.btn = btn;
        this.row = row;
        this.col = col;
        this.color = color;
    }

    public void setColor(int color) {
        this.color = color;
        btn.setBackgroundColor(color);
    }
}
