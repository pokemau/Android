package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidprojectcollection.Calculator.CalculatorExercise;

public class MainActivity extends AppCompatActivity {

    Button layoutExerBtn;
    Button btnExerBtn;
    Button calculatorExerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Close
        Toast
        Change BG
        Change Button BG
        Disappear
         */


        layoutExerBtn = (Button) findViewById(R.id.btnLayoutExercise);
        layoutExerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, LayoutExercise.class
                );

                startActivity(intent1);
            }
        });

        btnExerBtn = (Button) findViewById(R.id.btnButtonExercise);
        btnExerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, ButtonExercise.class
                );

                startActivity(intent1);
            }
        });

        calculatorExerBtn = (Button) findViewById(R.id.btnCalculatorExer);
        calculatorExerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, CalculatorExercise.class
                );
                startActivity(intent1);
            }
        });
    }
}