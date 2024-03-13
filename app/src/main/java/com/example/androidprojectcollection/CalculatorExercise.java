package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Stack;


public class CalculatorExercise extends AppCompatActivity {

    Button num1, num2, num3, num4, num5, num6, num7, num8, num9, num0;
    Button decimal;
    Button[] buttons = {num0, num1, num2, num3, num4, num5, num6, num7, num8, num9};

    Button btnAdd, btnSubtract, btnDivide, btnMultiply, btnEquals;
    TextView txtInput;
    TextView txtAns;

    StringBuilder sb = new StringBuilder("");

    // for sequential answer vars
    int pointer = 0;
    char currOp;
    boolean isOperation = false;
    boolean hasOperation = false;

    boolean isDecimal = false;
    boolean hasDecimal = false;
    Stack<Double> sequentialStack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_exercise);

        // text views
        txtInput    = (TextView) findViewById(R.id.txtInput);
        txtAns      = (TextView) findViewById(R.id.textAnswer);

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

        for (int i = 0; i < 10; i++) {
            setNumClickListener(buttons[i], (char)(i + '0'));
        }


        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sb.length() == 0) { return; }
                if (isOperation) { return; }
                if (!Character.isDigit(sb.charAt(pointer))) { return; }

                if (!isDecimal) {
                    if (!hasDecimal) {
                        sb.append('.');
                        hasDecimal = true;
                        isDecimal = true;
                    }

                } else {
                    sb.setLength(sb.length() - 1);
                    hasDecimal = !hasDecimal;
                    isDecimal = false;
                }


                txtInput.setText(sb);
            }
        });

        setOperationClickListener(btnAdd, '+');
        setOperationClickListener(btnSubtract, '-');
        setOperationClickListener(btnMultiply, '*');
        setOperationClickListener(btnDivide, '/');

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOperation) { return; }

                calculateString(sb.toString());

                pointer = 0;
                isOperation = false;
                hasOperation = false;
                hasDecimal = false;
                isDecimal = false;
                sequentialStack.clear();
//                txtInput.setText("0");
                sb.setLength(0);
            }
        });
    }

    private void calculateString(String str) {

        Stack<Double> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        StringBuilder nb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                nb.append(c);
            } else {
                nums.push(Double.parseDouble(nb.toString()));
                nb.setLength(0);

                while (!ops.empty() && precedence(ops.peek()) >= precedence(c)) {
                    char currOp = ops.pop();
                    double a = nums.pop();
                    double b = nums.pop();

                    nums.push(calculate(b, a, currOp));
                }
                ops.push(c);
            }
        }

        if (nb.length() != 0) {
            nums.push(Double.parseDouble(nb.toString()));
            nb.setLength(0);
        }

        while(!ops.empty()) {
            char currOp = ops.pop();
            double a = nums.pop();
            double b = nums.pop();
            nums.push(calculate(b, a, currOp));
        }

        // if string is an invalid equation
        // [invalid number of operands or operator]
        if ( (nums.size() % 2 == 0 && ops.size() % 2 == 0) ||
             (nums.size()     == 1 && ops.size()     == 1) ) {
            return;
        }

        double top = nums.peek();

        if (top % 1 == 0) {
            txtAns.setText((int) top + "");
        }
        else {
            txtAns.setText(top + "");
        }
    }


    private void handleNumberClick(char num) {
        if (isOperation) { isOperation = false; }

        isDecimal = false;

        if (sb.length() == 0 && num == '0') { return; }

        sb.append(num);


        double n = Double.parseDouble(sb.substring(pointer, sb.length()));


        if (hasOperation) {
            double n1 = sequentialStack.pop();
            double prevN = 0.0;

            if (sb.length() - pointer >= 2)
                prevN = Double.parseDouble(sb.substring(pointer, sb.length()-1));

            double newN = n-prevN;

            switch (currOp) {
                case '+':
                    n1 += newN;
                    break;
                case '-':
                    n1 -= newN;
                    break;
                case '*':
                    n1 *= newN;
                    break;
                case '/':
                    if (newN == 0.0) { break; }
                    n1 /= newN;
                    break;
            }

            sequentialStack.push(n1);

            double top = sequentialStack.peek();

            if (top % 1 == 0)
                txtAns.setText((int) top + "");
            else {
                String ans = String.format("%.2f", top);
                txtAns.setText(ans);
            }

        }
        txtInput.setText(sb);
    }

    private void handleOperationClick(char op) {
        if (sb.length() == 0) { return; }

        if (isDecimal) { return; }

        hasDecimal = false;

        if (isOperation) {
            sb.setCharAt(sb.length()-1, op);
        }
        else {
            sb.append(op);
            isOperation = true;
            currOp = op;

            double n = Double.parseDouble(sb.substring(pointer, sb.length()-1));

            if (!hasOperation) { hasOperation = true; }

            pointer = sb.length();

            if (sequentialStack.empty())
                sequentialStack.push(n);
        }

        txtInput.setText(sb);
    }

    private int precedence(char op) {
        switch(op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    private Double calculate(Double a, Double b, char op) {
        switch(op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) return 0.0;
                return a / b;
        }
        return 0.0;
    }

    private void setOperationClickListener(Button btn, char op) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperationClick(op);
            }
        });
    }

    private void setNumClickListener(Button btn, char num) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNumberClick(num);
            }
        });
    }
}

