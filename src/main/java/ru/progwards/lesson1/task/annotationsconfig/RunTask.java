package ru.progwards.lesson1.task.annotationsconfig;

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
            case "2":
                updateTask();
                break;
            case "3":
                viewAllTasks(fileTask.getAll());
                break;
            case "4":
                viewTask();
                break;
            case "5":
                deleteTask();
            default:
                System.out.println("Вы не выбрали номер задачи");
        }
    }

    private static void deleteTask() {
        System.out.println("Введите id задачи:");
        Scanner input = new Scanner(System.in);
        fileTask.delete(getTask(input).getId());
        System.out.println("Задача успешно удалена!\n");
    }

    private static void viewTask() {
        System.out.println("Введите id задачи:");
        Scanner input = new Scanner(System.in);
        List<Task> list = List.of(getTask(input));
        viewAllTasks(list);
    }

    private static void viewAllTasks(List<Task> taskList) {
        taskList.stream().map(task -> "Task Id: " + task.getId() + "\n"
                + "description: " + task.getDescription() + "\n"
                + "author: " + task.getAuthor() + "\n"
                + "name: " + task.getName() + "\n"
                + "storyPoint: " + task.getStoryPoint() + "\n").forEach(System.out::println);
    }

    private static void updateTask() {
        System.out.println("Введите id задачи:");
        Scanner input = new Scanner(System.in);
        Task task = getTask(input);
        List<String> commands = List.of("Введите новое описание задачи (текущее: " + task.getDescription() + ")",
                "Введите нового автора задачи (текущийЖ " + task.getAuthor() + ")",
                "Введите новое имя задачи (текущее: " + task.getName() + ")",
                "Введите новую сюжетную точку (текущая: " + task.getStoryPoint() + ")");

        List<String> newTask = new ArrayList<>(List.of(task.getId()));
        System.out.println(commands.get(0) + " ");
        doCommands(input, commands, newTask);

        fileTask.update(new Task(newTask.get(0), newTask.get(1), newTask.get(2),
                newTask.get(3), Integer.parseInt(newTask.get(4))));
        System.out.println("Задача успешно обновлена!\n");
    }

    private static void createTask() {
        List<String> commands = List.of("Введите id(" + (fileTask.getSize() + 1) + "):",
                "Введите описание задачи:", "Введите автора задачи:",
                "Введите имя задачи:", "Введите сюжетную точку:");

        List<String> newTask = new ArrayList<>();
        System.out.println(commands.get(0) + " ");
        Scanner input = new Scanner(System.in);
        doCommands(input, commands, newTask);

        fileTask.save(new Task(newTask.get(0), newTask.get(1), newTask.get(2),
                newTask.get(3), Integer.parseInt(newTask.get(4))));
        System.out.println("Новая задача добавлена!\n");
    }

    private static void doCommands(Scanner input, List<String> commands, List<String> newTask) {
        int index = 1;
        while (input.hasNextLine()) {
            String newLine = input.nextLine();
            if (newLine.isEmpty())
                break;
            newTask.add(newLine);
            if (newTask.size() == 5)
                break;
            System.out.println(commands.get(index) + " ");
            index++;
        }
    }

    private static Task getTask(Scanner input) {
        Task task = null;
        while (input.hasNextLine()) {
            String newLine = input.nextLine();
            if (newLine.isEmpty())
                System.out.println("Вы не ввели id задачи");
            else if ((task = fileTask.get(newLine)) == null)
                System.out.println("Задачи с id " + newLine + " не существует");
            else
                break;
        }
        return task;
    }

    public static void main(String[] args) {
        List<String> list = List.of("Введите номер требуемой задачи:\n" +
                "* создать задачу - введите: 1\n" +
                "* изменить задачу - введите: 2\n" +
                "* вывести список задач - введите: 3\n" +
                "* вывести задачу по ID - введите: 4\n" +
                "* удалить задачу по ID - введите: 5\n" +
                "* остановить программу - stop");
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
