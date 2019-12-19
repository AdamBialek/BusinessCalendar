package com.businesscalendar.controllers;

import com.businesscalendar.Calculator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CalculatorController {

    private Calculator calculator;

    @FXML
    private TextField calculatorScreen;

    @FXML
    public void button1() {
        calculatorScreen.setText(calculator.assignButtonValue(1));
    }

    @FXML
    public void button2() {
        calculatorScreen.setText(calculator.assignButtonValue(2));
    }

    @FXML
    public void button3() {
        calculatorScreen.setText(calculator.assignButtonValue(3));
    }

    @FXML
    public void button4() {
        calculatorScreen.setText(calculator.assignButtonValue(4));
    }

    @FXML
    public void button5() {
        calculatorScreen.setText(calculator.assignButtonValue(5));
    }

    @FXML
    public void button6() {
        calculatorScreen.setText(calculator.assignButtonValue(6));
    }

    @FXML
    public void button7() {
        calculatorScreen.setText(calculator.assignButtonValue(7));
    }

    @FXML
    public void button8() {
        calculatorScreen.setText(calculator.assignButtonValue(8));
    }

    @FXML
    public void button9() {
        calculatorScreen.setText(calculator.assignButtonValue(9));
    }

    @FXML
    public void button0() {
        calculatorScreen.setText(calculator.assignButtonValue(0));
    }

    @FXML
    public void buttonPoint() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.numberToFraction(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonEquals() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.equals(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonAdd() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.addition(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonSubtract() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.subtraction(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonMultiply() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.multiplication(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonDivide() {
        String calcScrTxt=calculatorScreen.getText();
        String newCalcScrTxt=calculator.division(calcScrTxt);
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void buttonBackspace() {
        calculatorScreen.setText(calculator.erase());
    }

    @FXML
    public void buttonClear() {
        calculator.clearScreen();
        calculatorScreen.setText("");
    }

    @FXML
    public void plusMinus() {
        String newCalcScrTxt=calculator.negate();
        calculatorScreen.setText(newCalcScrTxt);
    }

    @FXML
    public void initialize(){
        calculator=new Calculator();
    }
}
