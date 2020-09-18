package ru.progwards.lesson1.calc.xmlconfig;


public class Calculator {

    private final ICalculator iCalculator;

    public Calculator(ICalculator iCalculator) {
        this.iCalculator = iCalculator;
    }

    public ICalculator getICalc(){
        return iCalculator;
    }
}
