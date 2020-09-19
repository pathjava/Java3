package ru.progwards.lesson1.calc.javabaseconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ru.progwards.lesson1.calc.javabaseconfig")
public class CalcConfig {

    @Bean
    public ICalculator getAdvancedCalc(){
        return new AdvancedCalculator();
    }

    @Bean
    public ICalculator getSimpleCalc(){
        return new SimpleCalculator();
    }

    @Bean
    public Calculator getCalc(){
        return new Calculator(getAdvancedCalc());
    }

    @Bean
    public RunCalculator getRunner(){
        return new RunCalculator();
    }
}
