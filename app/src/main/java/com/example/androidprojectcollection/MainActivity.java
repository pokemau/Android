package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidprojectcollection.Calculator.CalculatorExercise;
import com.example.androidprojectcollection.Match3.Match3Exercise;

public class MainActivity extends AppCompatActivity {

    Button layoutExerBtn;
    Button btnExerBtn;
    Button calculatorExerBtn;
    Button btnMatch3;
    Button btnIntentsExer;
    Button menuExer;
    Button btnOpeningMaps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        btnMatch3 = (Button) findViewById(R.id.btnMatch3);
        btnMatch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, Match3Exercise.class
                );
                startActivity(intent1);
            }
        });

        btnIntentsExer = (Button) findViewById(R.id.btnPassingIntents);
        btnIntentsExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, PassingIntentsExercise.class
                );
                startActivity(intent1);
            }
        });

        menuExer = (Button) findViewById(R.id.btnMenus);
        menuExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, MenuExcercise.class
                );
                startActivity(intent1);
            }
        });

        btnOpeningMaps = (Button) findViewById(R.id.btnOpeningMaps);
        btnOpeningMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, MapsExercise.class
                );
                startActivity(intent1);
            }
        });
    }
}