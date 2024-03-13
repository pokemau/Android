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

        txtInput    = (TextView) findViewById(R.id.txtInput);
        txtAns      = (TextView) findViewById(R.id.textAnswer);
        num0        = (Button) findViewById(R.id.btnZero);
        num1        = (Button) findViewById(R.id.btnOne);
        num2        = (Button) findViewById(R.id.btnTwo);
        num3        = (Button) findViewById(R.id.btnThree);
        num4        = (Button) findViewById(R.id.btnFour);
        num5        = (Button) findViewById(R.id.btnFive);
        num6        = (Button) findViewById(R.id.btnSix);
        num7        = (Button) findViewById(R.id.btnSeven);
        num8        = (Button) findViewById(R.id.btnEight);
        num9        = (Button) findViewById(R.id.btnNine);

        btnEquals   = (Button) findViewById(R.id.btnEquals);
        btnAdd      = (Button) findViewById(R.id.btnPlus);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnDivide   = (Button) findViewById(R.id.btnDivide);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNumberClick('1');
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNumberClick('2');
            }
        });



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperationClick('+');
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperationClick('-');
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperationClick('*');
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperationClick('/');
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculateString(sb.toString());

                l = 0;
                isOperation = false;
                hasOperation = false;
                nums_sequential.clear();
                txtInput.setText("0");
//                txtAns.setText("0");
                sb.setLength(0);
            }
        });
    }

    private void calculateString(String str) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        List<Integer> opsOrder = new ArrayList<>();

        int res = 0;
        int currNum = 0;

        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);

            if (Character.isDigit(curr))
                currNum = (currNum * 10) + (curr - '0');
            else {
                nums.push(currNum);
                currNum = 0;
                ops.push(curr);
            }
        }
        if (currNum != 0)
            nums.push(currNum);


        // if string is an invalid equation
        if (    (nums.size() % 2 == 0 && ops.size() % 2 == 0) ||
                (nums.size() == 1 && ops.size() == 1)) {
            return;
        }

        txtAns.setText(res + "");

        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println(Arrays.toString(nums.toArray()));
        System.out.println(Arrays.toString(ops.toArray()));
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
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
}