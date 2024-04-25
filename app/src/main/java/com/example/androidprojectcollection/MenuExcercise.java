package com.example.androidprojectcollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MenuExcercise extends AppCompatActivity {


    Button btnChanger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_excercise);

        btnChanger = findViewById(R.id.btnTransformingButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menuexercise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.mItemChange) {

        } else if (item.getItemId() == R.id.mItemBGToYellow) {
            btnChanger.setBackgroundColor(Color.YELLOW);

        } else if (item.getItemId() == R.id.mItemChangeSize) {
            int dimen= (int)getResources().getDimension(R.dimen.transformBtnWidth);

            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) btnChanger.getLayoutParams();

            layoutParams.width = dimen;
            layoutParams.height = dimen;
            btnChanger.setLayoutParams(layoutParams);

        } else if (item.getItemId() == R.id.mItemTxtToRed) {
            btnChanger.setTextColor(Color.RED);

        } else if (item.getItemId() == R.id.mItemFontToBold) {
            btnChanger.setTypeface(null, Typeface.BOLD);

        } else if (item.getItemId() == R.id.mItemChangeFontSize) {
            btnChanger.setTextSize(50);

        } else if (item.getItemId() == R.id.mItemReset) {
            int dimen= (int)getResources().getDimension(R.dimen.transformBtnOrigWidth);

            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) btnChanger.getLayoutParams();
            layoutParams.width = dimen;
            layoutParams.height = dimen;
            btnChanger.setLayoutParams(layoutParams);

            btnChanger.setBackgroundColor(Color.parseColor("#8C3434"));
            btnChanger.setTextColor(Color.WHITE);
            btnChanger.setTypeface(null, Typeface.NORMAL);
            btnChanger.setTextSize(20);



        } else if (item.getItemId() == R.id.mItemExit) {
            finish();
        }

        return true;
    }
}