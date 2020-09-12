package task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunTask {

    private static final ApplicationContext context
            = new ClassPathXmlApplicationContext("task-context.xml");
    private static final FileTaskRepository fileTask
            = context.getBean("task", FileTaskRepository.class);

    private static void runSelectedTask(String str) {
        switch (str) {
            case "1":
                createTask();
                break;
            case "3":
                System.out.println(fileTask.getAll());
                break;
            case "4":
                System.out.println(fileTask.get("01"));
                break;
            case "5":
                fileTask.delete("01");
            default:
                System.out.println("Вы не выбрали номер задачи");
        }
    }

    private static void createTask() {
        List<String> commands = List.of("Введите id(" + (fileTask.getSize() + 1) + "):",
                "Введите описание задачи:", "Введите автора задачи:",
                "Введите имя:", "Введите сюжетную точку:");
        List<String> lines = new ArrayList<>();

        System.out.print(commands.get(0) + " ");
        Scanner input = new Scanner(System.in);
        int index = 1;
        while (input.hasNextLine()) {
            String newLine = input.nextLine();
            if (newLine.isEmpty())
                break;
            lines.add(newLine);
            if (lines.size() == 5)
                break;
            System.out.print(commands.get(index) + " ");
            index++;
        }
        fileTask.save(new Task(lines.get(0), lines.get(1), lines.get(2),
                lines.get(3), Integer.parseInt(lines.get(4))));
        System.out.println("Новая задача добавлена!\n");
    }

    public static void main(String[] args) {
        List<String> list = List.of("Введите номер требуемой задачи:\n" +
                "создать задачу - введите: 1\n" +
                "изменить задачу - введите: 2\n" +
                "вывести список задач - введите: 3\n" +
                "вывести задачу по ID - введите: 4\n" +
                "удалить задачу по ID - введите: 5\n" +
                "или остановите программу - stop");
        while (true) {
            list.forEach(System.out::println);
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                if (str.isEmpty())
                    throw new IllegalArgumentException("Вы не ввели условие");
                if (str.toLowerCase().equals("stop"))
                    return;
                runSelectedTask(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
