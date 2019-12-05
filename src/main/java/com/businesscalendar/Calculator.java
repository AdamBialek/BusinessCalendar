package com.businesscalendar;

import com.businesscalendar.controllers.MenuScreenController;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculator {

    private MenuScreenController menuScreenController;

    private String calculatorNumber1="";

    private String operation="";

    private BigDecimal number1;

    public String getCalculatorNumber1() {
        return calculatorNumber1;
    }

    public void setCalculatorNumber1(String calculatorNumber1) {
        this.calculatorNumber1 = calculatorNumber1;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public BigDecimal getNumber1() {
        return number1;
    }

    public void setNumber1(BigDecimal number1) {
        this.number1 = number1;
    }

    public String assignButtonValue(int value) {
        return calculatorNumber1=getCalculatorNumber1()+String.valueOf(value);
    }

    public String numberToFraction(String scrTxt) {
        if (!getCalculatorNumber1().contains(".")){
            return calculatorNumber1=getCalculatorNumber1()+".";
        }
        return scrTxt;
    }

    public String addition(String scrTxt) {
        if(operation.length()<1){
            operation="+";
            number1=new BigDecimal(getCalculatorNumber1());
            setCalculatorNumber1("");
            return "";
        } else if(!operation.isEmpty()&&!calculatorNumber1.isEmpty()){
            String calcResult=result(scrTxt);
            operation="+";
            setCalculatorNumber1("");
            return calcResult;
        }
        return scrTxt;
    }

    public String subtraction(String scrTxt) {
        if(operation.length()<1) {
            operation = "-";
            number1 = new BigDecimal(getCalculatorNumber1());
            setCalculatorNumber1("");
            return "";
        } else if(!operation.isEmpty()&&!calculatorNumber1.isEmpty()){
            String calcResult=result(scrTxt);
            operation="-";
            setCalculatorNumber1("");
            return calcResult;
        }
        return scrTxt;
    }

    public String multiplication(String scrTxt) {
        if(operation.length()<1) {
            operation = "*";
            number1 = new BigDecimal(getCalculatorNumber1());
            setCalculatorNumber1("");
            return "";
        } else if(!operation.isEmpty()&&!calculatorNumber1.isEmpty()){
            String calcResult=result(scrTxt);
            operation="*";
            setCalculatorNumber1("");
            return calcResult;
        }
        return scrTxt;
    }

    public String division(String scrTxt) {
        if(operation.length()<1) {
            operation = "/";
            number1 = new BigDecimal(getCalculatorNumber1());
            setCalculatorNumber1("");
            return "";
        } else if(!operation.isEmpty()&&!calculatorNumber1.isEmpty()){
            String calcResult=result(scrTxt);
            operation="/";
            setCalculatorNumber1("");
            return calcResult;
        }
        return scrTxt;
    }

    public String erase() {
        setCalculatorNumber1(getCalculatorNumber1().substring(0,calculatorNumber1.length()-1));
        return getCalculatorNumber1();
    }

    public String negate() {
        if (getCalculatorNumber1().lastIndexOf("-")==0){
            setCalculatorNumber1(getCalculatorNumber1().replace("-",""));
            return calculatorNumber1;
        } else if(getCalculatorNumber1().lastIndexOf("-")==-1){
            setCalculatorNumber1("-"+getCalculatorNumber1());
            return calculatorNumber1;
        }
        return null;
    }

    public void clearScreen() {
        number1=BigDecimal.ZERO;
        calculatorNumber1="";
        operation="";
    }

    public String equals(String calculatorScreenText) {
        if(!getCalculatorNumber1().equals("")){
            return result(calculatorScreenText);
        } else {
            setCalculatorNumber1(number1.toString());
            number1=BigDecimal.ZERO;
            operation="";
            return getCalculatorNumber1();
        }
    }

    public String result(String calculatorScreenText){
        if (!getCalculatorNumber1().equals("")) {
            if (operation.equals("+")) {
                BigDecimal bigDecimal = new BigDecimal(getCalculatorNumber1());
                number1 = number1.add(bigDecimal);
                setCalculatorNumber1(number1.toString());
                operation = "";
                return number1.toString();
            } else if (operation.equals("-")) {
                BigDecimal bigDecimal = new BigDecimal(getCalculatorNumber1());
                number1 = number1.subtract(bigDecimal);
                setCalculatorNumber1(number1.toString());
                operation = "";
                return number1.toString();
            } else if (operation.equals("*")) {
                BigDecimal bigDecimal = new BigDecimal(getCalculatorNumber1());
                number1 = number1.multiply(bigDecimal);
                setCalculatorNumber1(number1.toString());
                operation = "";
                return number1.toString();
            } else if (operation.equals("/")) {
                BigDecimal bigDecimal = new BigDecimal(getCalculatorNumber1());
                try {
                    number1 = number1.divide(bigDecimal);
                } catch (ArithmeticException e) {
                    number1 = number1.divide(bigDecimal, 15, RoundingMode.HALF_DOWN);
                }
                setCalculatorNumber1(number1.toString());
                operation = "";
                return number1.toString();
            } else if (operation.equals("%")) {
                BigDecimal bigDecimal = new BigDecimal(getCalculatorNumber1());
                BigDecimal percent = BigDecimal.valueOf(100);
                try {
                    number1 = number1.divide(bigDecimal).multiply(percent);
                } catch (ArithmeticException e) {
                    MathContext mathContext = new MathContext(15);
                    number1 = number1.divide(bigDecimal, 17, RoundingMode.HALF_DOWN).multiply(percent).round(mathContext);
                }
                setCalculatorNumber1(number1.toString());
                operation = "";
                return number1.toString();
            }
        } else {
            operation="";
        }
        return calculatorScreenText;
    }
}