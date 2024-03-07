package com.example.androidprojectcollection;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ButtonExercise extends AppCompatActivity {

    Button closeBtn;
    Button toastBtn;
    Button changeBgBtn;
    Button changeBtnBgBtn;
    Button disappearBtn;

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_exercise);


        /*
            A button that opens another empty activity that has another button( return button ) that,
            when clicked, returns to the activity_button_exercise.
         */
        closeBtn = (Button) findViewById(R.id.btnClose);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        ButtonExercise.this, ButtonExerciseEmptyActivity.class
                );
                startActivity(intent1);
            }
        });

        toastBtn = (Button) findViewById(R.id.btnToast);
        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = createToast();
                t.show();
            }
        });


        changeBgBtn = (Button) findViewById(R.id.btnChangeBG);
        changeBgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constraintLayout = (ConstraintLayout) findViewById(R.id.LayoutbtnActivityConstraint);
                constraintLayout.setBackgroundColor(Color.GREEN);
            }
        });

        changeBtnBgBtn = (Button) findViewById(R.id.btnChangeBtnBG);
        changeBtnBgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBtnBgBtn.setBackgroundColor(Color.RED);
                changeBtnBgBtn.setTextColor(Color.WHITE);
            }
        });


        disappearBtn = (Button) findViewById(R.id.btnDisappear);
        disappearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disappearBtn.setVisibility(View.GONE);
            }
        });
    }

    Toast createToast() {
        CharSequence toastText = "Slamm LUAB";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, toastText, duration);
        return toast;
    }
}