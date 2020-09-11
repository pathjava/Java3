package task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTask {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("task-context.xml");
        FileTaskRepository fileTask = context.getBean("task", FileTaskRepository.class);
    }

}
