package ru.progwards.lesson1.task.javabaseconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ru.progwards.lesson1.task.javabaseconfig")
public class TaskConfig {

    @Bean
    public FileTaskRepository getFileTaskRepository() {
        return new FileTaskRepository();
    }

    @Bean
    public RunTask getRunTask() {
        return new RunTask();
    }
}
