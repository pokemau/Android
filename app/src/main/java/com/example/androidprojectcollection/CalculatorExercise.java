package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Stack;

public class CalculatorExercise extends AppCompatActivity {

    Button num1, num2, num3, num4, num5, num6, num7, num8, num9, num0;
    Button btnAdd, btnSubtract, btnDivide, btnMultiply, btnEquals;
    TextView txtInput;
    TextView txtAns;

    StringBuilder sb = new StringBuilder("");
    Stack<Character> ops = new Stack<>();
    Stack<Integer> nums = new Stack<>();

    // for sequential answer vars
    int tempAns = 0;
    int l = 0;
    char prevOp = '+';
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
        btnDivide = (Button) findViewById(R.id.btnDivide);
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

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempAns = 0;
                l = 0;
                isOperation = false;
                hasOperation = false;
                nums.clear();
                ops.clear();

                txtInput.setText("0");
                txtAns.setText("0");
                sb.setLength(0);
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
    }

    private void handleNumberClick(char num) {
        if (isOperation) { isOperation = false; }

        sb.append(num);

        int n = Integer.parseInt(sb.substring(l, sb.length()));


        if (hasOperation) {
            int n1 = nums.pop();
            int prevN = 0;

            if (sb.length() - l >= 2) {
                prevN = Integer.parseInt(sb.substring(l, sb.length()-1));
            }

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

            nums.push(n1);

            txtAns.setText(nums.peek() + "");
        }

        txtInput.setText(sb);
    }

    private void handleOperationClick(char op) {
        if (sb.length() == 0) { return; }

        sb.append(op);

        if (isOperation) {
            sb.setCharAt(sb.length()-1, op);
        }
        else {
            isOperation = true;

            currOp = op;

            int n = Integer.parseInt(sb.substring(l, sb.length()-1));

            if (!hasOperation) { hasOperation = true; }



            l = sb.length();

            if (nums.empty())
                nums.push(n);

            ops.push(op);
        }

        txtInput.setText(sb);
    }

    private void calculateString() {

        if (!hasOperation) { return; }

        float res = 0.0f;

        Stack<Float> tempNums = new Stack<>();

        int len = sb.length();

        for (int i = l; i < len; i++) {

            int currNum = 0;
            char currOp;

            for (int j = i; j < len; j++, i++) {

                char curr = sb.charAt(j);

                if (Character.isDigit(curr)) {


                    currNum = (currNum * 10) + (curr - '0');

                    if (hasOperation && !tempNums.empty()) {
                        float topNum = tempNums.pop();
                        topNum = (topNum * 10) + currNum;
                        tempNums.push(topNum);
                    }

                    txtAns.setText(Integer.toString(currNum));
                } else {

                    tempNums.push(currNum + 0.0f);

                    l = j;

                    System.out.println("-------------------------");
                    System.out.println("-------------------------");
                    System.out.println("-------------------------");
                    System.out.println(tempNums.peek());
                    System.out.println("SIZE: " + tempNums.size());
                    System.out.println("-------------------------");
                    System.out.println("-------------------------");
                    System.out.println("-------------------------");

                    break;

                }

            }

//            if (hasOperation)
//                txtAns.setText(res + "");
        }
    }


}