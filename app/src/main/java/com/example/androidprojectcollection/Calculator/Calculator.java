package com.example.androidprojectcollection.Calculator;

import android.widget.TextView;

import java.util.Stack;

public class Calculator {

    TextView txtAnswer;
    TextView txtEquationString;

    private int pointer;
    private char currOp;
    private boolean isOperation;
    private boolean hasOperation;

    private boolean isDecimal;
    private boolean hasDecimal;
    private Stack<Double> sequentialStack;

    private StringBuilder nb;
    public StringBuilder sb;


    public Calculator(TextView txtAnswer, TextView txtEquationString) {
        this.txtAnswer = txtAnswer;
        this.txtEquationString = txtEquationString;
        nb = new StringBuilder();
        sb = new StringBuilder();
        sequentialStack = new Stack<>();

        pointer = 0;
        isOperation = false;
        hasOperation = false;
        isDecimal = false;
        hasDecimal = false;
    }

    private void calculateSequential() {
        Stack<Double> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < sb.length(); i++) {

            char c = sb.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                nb.append(c);
            } else {
                nums.push(Double.parseDouble(nb.toString()));
                nb.setLength(0);

                while (!ops.empty()) {
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
            txtEquationString.setText((int) top + "");
        }
        else {
            txtEquationString.setText(top + "");
        }

        nb.setLength(0);
    }

    public void handleNumberClick(char num) {
        if (isOperation) { isOperation = false; }

        isDecimal = false;

        if (sb.length() == 0 && num == '0') { return; }

        sb.append(num);

        if (hasOperation) {
            calculateSequential();
        }
        txtAnswer.setText(sb);
    }

    public void handleDecimalClick() {
        if (sb.length() == 0) {
            sb.append('0');
        }
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
        txtAnswer.setText(sb);
    }

    public void handleOperationClick(char op) {
        if (sb.length() == 0) { return; }

        if (isDecimal) { return; }

        hasDecimal = false;

        if (isOperation) {
//            if (sb.charAt(sb.length()-1) == op) {
//                sb.setLength(sb.length()-1);
//                isOperation = false;
//            }
//            else {
                sb.setCharAt(sb.length()-1, op);
//            }
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

        txtAnswer.setText(sb);
    }

    public int precedence(char op) {
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


    public Double calculate(Double a, Double b, char op) {

        System.out.println(a + " " + op + " " + b);

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

    public void calculateString() {

//        if (isOperation) { return; }
        if (isOperation) {
            sb.setLength(sb.length() - 1);
        }

        Stack<Double> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        StringBuilder nb = new StringBuilder();

        for (int i = 0; i < sb.length(); i++) {

            char c = sb.charAt(i);

//            nb = "23"

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
            txtEquationString.setText((int) top + "");
        }
        else {
            txtEquationString.setText(top + "");
        }

        RESET();
    }

    private void RESET() {
        pointer = 0;
        isOperation = false;
        hasOperation = false;
        hasDecimal = false;
        isDecimal = false;
        sequentialStack.clear();
        sb.setLength(0);
    }
}
