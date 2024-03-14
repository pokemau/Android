package com.example.androidprojectcollection.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidprojectcollection.R;


public class CalculatorExercise extends AppCompatActivity {

    Calculator calc;

    Button num1, num2, num3, num4, num5, num6, num7, num8, num9, num0;
    Button decimal;
    Button[] buttons = {num0, num1, num2, num3, num4, num5, num6, num7, num8, num9};

    Button btnAdd, btnSubtract, btnDivide, btnMultiply, btnEquals;
    TextView txtAnswer;
    TextView txtEquationString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_exercise);

        // text views
        txtAnswer = (TextView) findViewById(R.id.textAnswer);
        txtEquationString = (TextView) findViewById(R.id.txtInput);

        // number buttons
        buttons[0]  = (Button) findViewById(R.id.btnZero);
        buttons[1]  = (Button) findViewById(R.id.btnOne);
        buttons[2]  = (Button) findViewById(R.id.btnTwo);
        buttons[3]  = (Button) findViewById(R.id.btnThree);
        buttons[4]  = (Button) findViewById(R.id.btnFour);
        buttons[5]  = (Button) findViewById(R.id.btnFive);
        buttons[6]  = (Button) findViewById(R.id.btnSix);
        buttons[7]  = (Button) findViewById(R.id.btnSeven);
        buttons[8]  = (Button) findViewById(R.id.btnEight);
        buttons[9]  = (Button) findViewById(R.id.btnNine);

        // decimal
        decimal     = (Button) findViewById(R.id.btnDecimal);

        // operation buttons
        btnEquals   = (Button) findViewById(R.id.btnEquals);
        btnAdd      = (Button) findViewById(R.id.btnPlus);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnDivide   = (Button) findViewById(R.id.btnDivide);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);

        /////////////// CALCULATOR CLASS //////////////////
        calc = new Calculator(txtAnswer, txtEquationString);
        /////////////// CALCULATOR CLASS //////////////////

        for (int i = 0; i < 10; i++) {
            setNumClickListener(buttons[i], (char)(i + '0'));
        }


        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.handleDecimalClick();
            }
        });


        setOperationClickListener(btnAdd, '+');
        setOperationClickListener(btnSubtract, '-');
        setOperationClickListener(btnMultiply, '*');
        setOperationClickListener(btnDivide, '/');

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.calculateString();
            }
        });
    }

    private void setOperationClickListener(Button btn, char op) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.handleOperationClick(op);
            }
        });
    }

    private void setNumClickListener(Button btn, char num) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.handleNumberClick(num);
            }
        });
    }
}

