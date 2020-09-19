package ru.progwards.lesson1.calc.annotationsconfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("calculator")
public class Calculator {

    private ICalculator iCalculator;

    @Autowired
    @Qualifier("advancedCalc")
    public void setCalculator(ICalculator iCalculator) {
        this.iCalculator = iCalculator;
    }

    public ICalculator getICalc(){
        return iCalculator;
    }
}
