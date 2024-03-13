package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CalculatorExercise extends AppCompatActivity {

    Button num1, num2, num3, num4, num5, num6, num7, num8, num9, num0;
    Button[] buttons = {num0, num1, num2, num3, num4, num5, num6, num7, num8, num9};
    Button btnAdd, btnSubtract, btnDivide, btnMultiply, btnEquals;
    TextView txtInput;
    TextView txtAns;

    StringBuilder sb = new StringBuilder("");
    Stack<Integer> nums_sequential = new Stack<>();

    // for sequential answer vars
    int l = 0;
    char currOp;
    boolean isOperation = false;
    boolean hasOperation = false;

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

        // operation buttons
        btnEquals   = (Button) findViewById(R.id.btnEquals);
        btnAdd      = (Button) findViewById(R.id.btnPlus);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnDivide   = (Button) findViewById(R.id.btnDivide);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);

        for (int i = 0; i < 10; i++) {
            setNumClickListener(buttons[i], (char)(i + '0'));
        }

        setOperationClickListener(btnAdd, '+');
        setOperationClickListener(btnSubtract, '-');
        setOperationClickListener(btnMultiply, '*');
        setOperationClickListener(btnDivide, '/');

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculateString(sb.toString());

                l = 0;
                isOperation = false;
                hasOperation = false;
                nums_sequential.clear();
                txtInput.setText("0");
                sb.setLength(0);
            }
        });
    }

    private int calculate(int a, int b, char op) {
        switch(op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) return 0;
                return a / b;
        }
        return 0;
    }

    private void calculateString(String str) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        int res = 0;
        int currNum = 0;

        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);

            if (Character.isDigit(curr))
                currNum = (currNum * 10) + (curr - '0');
            else {
                nums.push(currNum);
                currNum = 0;

                while (!ops.empty() && precedence(ops.peek()) >= precedence(curr)) {
                    char currOp = ops.pop();
                    int a = nums.pop();
                    int b = nums.pop();

                    nums.push(calculate(b, a, currOp));
                }
                ops.push(curr);
            }
        }
        if (currNum != 0)
            nums.push(currNum);

        while(!ops.empty()) {
            char currOp = ops.pop();
            int a = nums.pop();
            int b = nums.pop();
            nums.push(calculate(b, a, currOp));
        }

        // if string is an invalid equation
        // [invalid number of operands or operator]
        if ( (nums.size() % 2 == 0 && ops.size() % 2 == 0) ||
             (nums.size()     == 1 && ops.size()     == 1) ) {
            return;
        }

        txtAns.setText(nums.peek()+"");
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

    private void handleNumberClick(char num) {
        if (isOperation) { isOperation = false; }

        sb.append(num);

        int n = Integer.parseInt(sb.substring(l, sb.length()));

        if (hasOperation) {
            int n1 = nums_sequential.pop();
            int prevN = 0;

            if (sb.length() - l >= 2)
                prevN = Integer.parseInt(sb.substring(l, sb.length()-1));

            int newN = n-prevN;

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
                    if (newN == 0) { break; }
                    n1 /= newN;
                    break;
            }

            nums_sequential.push(n1);

            txtAns.setText(nums_sequential.peek() + "");
        }
        txtInput.setText(sb);
    }



    private void handleOperationClick(char op) {
        if (sb.length() == 0) { return; }

        if (isOperation) {
            sb.setCharAt(sb.length()-1, op);
        }
        else {
            sb.append(op);
            isOperation = true;
            currOp = op;

            int n = Integer.parseInt(sb.substring(l, sb.length()-1));

            if (!hasOperation) { hasOperation = true; }

            l = sb.length();

            if (nums_sequential.empty())
                nums_sequential.push(n);
        }

        txtInput.setText(sb);
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
                System.out.println(num);

                handleNumberClick(num);
            }
        });
    }
}

