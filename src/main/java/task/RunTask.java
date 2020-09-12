package task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTask {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("task-context.xml");
        FileTaskRepository fileTask = context.getBean("task", FileTaskRepository.class);

        fileTask.save(new Task("01", "desc", "Dev", "Oleg", 1));
        fileTask.save(new Task("02", "desc", "Dev", "Oleg", 1));
        fileTask.getAll().forEach(System.out::println);
    }

}
