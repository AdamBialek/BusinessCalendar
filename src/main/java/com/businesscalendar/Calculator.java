package com.businesscalendar;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculator {
    private BigDecimal number1;

    private BigDecimal number2;

    private String operator;

    public BigDecimal getNumber1() {
        return number1;
    }

    public void setNumber1(BigDecimal number1) {
        this.number1 = number1;
    }

    public BigDecimal getNumber2() {
        return number2;
    }

    public void setNumber2(BigDecimal number2) {
        this.number2 = number2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public BigDecimal calcResult(){
        if(getOperator().equals("+")){
            setNumber1(getNumber1().add(getNumber2()));
            setNumber2(null);
            setOperator(null);
        } else if (getOperator().equals("-")){
            setNumber1(getNumber1().subtract(getNumber2()));
            setNumber2(null);
            setOperator(null);
        } else if (getOperator().equals("*")){
            setNumber1(getNumber1().multiply(getNumber2()));
            setNumber2(null);
            setOperator(null);
        } else if (getOperator().equals("/")){
            try {
                setNumber1(getNumber1().divide(getNumber2()));
            } catch (ArithmeticException e) {
                setNumber1(getNumber1().divide(getNumber2(),15, RoundingMode.HALF_DOWN));
            }
            setNumber2(null);
            setOperator(null);
        } else if (getOperator().equals("%")){
            BigDecimal percent = BigDecimal.valueOf(100);
            try {
                setNumber1(getNumber1().divide(getNumber2()).multiply(percent));
            } catch (ArithmeticException e) {
                MathContext mathContext = new MathContext(15);
                setNumber1(getNumber1().divide(getNumber2(),17, RoundingMode.HALF_DOWN).multiply(percent).round(mathContext));
            }
            setNumber2(null);
            setOperator(null);
        }


        return getNumber1();
    }

    public void clearAllValues() {
        setNumber1(BigDecimal.ZERO);
        setNumber2(null);
        setOperator(null);
    }
}
