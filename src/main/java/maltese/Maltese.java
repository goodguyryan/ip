package maltese;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import maltese.task.Deadline;
import maltese.task.Event;
import maltese.task.Task;
import maltese.task.Todo;
import java.util.Scanner;

public class Maltese {
    static String filePath = "src/main/java/maltese/tasks.txt";
    static Task[] tasks = new Task[100];
    static int tasksLength = 0;

    public static void printBootupMessage() {
        System.out.println("Hello I'm Maltese, your personal chatbot!");
        System.out.println("What can I do for you");
    }

    public static void addTask(Task task) {
        tasks[tasksLength] = task;
        tasksLength++;
        updateFile();
    }

    public static void addTaskFromFile(String s) {
        char taskType = s.charAt(1);
        char taskIsDone = s.charAt(4);
        String taskDescription = s.substring(7);

        switch (taskType) {
        case 'T':
            processTodo(taskDescription);
            break;
        case 'D':
            processDeadline(taskDescription);
            break;
        case 'E':
            processEvent(taskDescription);
            break;
        default:
            return;
        }

        if (taskIsDone == 'X') {
            processMark(Integer.toString(tasksLength), true);
        }
    }

    public static void updateFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                if (task == null) {
                    break;
                }
                fw.write(task.getTask());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file" + e.getMessage());
        }
    }
    public static void processMark(String taskNumber, boolean doneStatus) {
        try {
            int actualNumber = Integer.parseInt(taskNumber);
            if (actualNumber - 1 >= tasksLength) {
                System.out.println("my list does not have so many tasks");
            } else if (actualNumber - 1 < 0) {
                System.out.println("negative task??");
            } else {
                tasks[actualNumber - 1].setDone(doneStatus);
                System.out.println(doneStatus ? "okie this task is done" : "okie this task is gone");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("this task does not exist?");
        }
        updateFile();
    }

    public static void printList() {
        if (tasksLength <= 0) {
            System.out.println("List is empty yippee");
        }
        for (int i = 0; i < tasksLength; i++) {
            System.out.println((i + 1) + "." + tasks[i].getTask());
        }
    }

    public static void processTodo(String todoTask) {
        if (todoTask.isBlank()) {
            System.out.println("no todo found pls todo something");
            return;
        }

        Todo newTodo = new Todo(todoTask);
        addTask(newTodo);
        System.out.println("Added the following todo:");
        System.out.println(newTodo.getTask());
    }

    public static void processDeadline(String deadlineTask) {
        int byIndex= deadlineTask.indexOf(" /by ");
        if (byIndex == -1) {
            System.out.println("invalid deadline format pls do task /by deadline");
            return;
        }

        String task = deadlineTask.substring(0, byIndex);
        String deadline = deadlineTask.substring(byIndex + 5);
        if (task.isBlank() || deadline.isBlank()) {
            System.out.println("invalid deadline format pls do task /by deadline");
            return;
        }

        Deadline newDeadline = new Deadline(task, deadline);
        addTask(newDeadline);
        System.out.println("Added the following deadline:");
        System.out.println(newDeadline.getTask());
    }

    public static void processEvent(String eventTask) {
        int fromIndex = eventTask.indexOf(" /from ");
        int toIndex = eventTask.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            System.out.println("fake event pls put event task /from from /to to");
            return;
        }

        String task =  eventTask.substring(0, fromIndex);
        String from = eventTask.substring(fromIndex + 7, toIndex);
        String to = eventTask.substring(toIndex + 5);
        if (task.isBlank() || from.isBlank() || to.isBlank()) {
            System.out.println("fake event pls put event task /from from /to to");
            return;
        }

        Event newEvent = new Event(task, from, to);
        addTask(newEvent);
        System.out.println("Added the following event:");
        System.out.println(newEvent.getTask());
    }

    public static void processCommand(String command) {
        if (command == null || command.isBlank()) {
            System.out.println("write something plssssss");
            return;
        }

        int firstSpace = command.indexOf(' ');
        String action = (firstSpace == -1) ? command : command.substring(0, firstSpace);
        String args   = (firstSpace == -1) ? "" : command.substring(firstSpace + 1);

        switch (action) {
        case "list":
            printList();
            break;
        case "mark":
            processMark(args, true);
            break;
        case "unmark":
            processMark(args, false);
            break;
        case "todo":
            processTodo(args);
            break;
        case "deadline":
            processDeadline(args);
            break;
        case "event":
            processEvent(args);
            break;
        default:
            System.out.println("i don't recognise this at all");
        }
    }

    public static void initProcessLoop() {
        String command;
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter: ");
            command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
             processCommand(command);
        }

        System.out.println("NOOOOOOOOOOOOO. See you soon o.0!");
    }

    public static void loadTasksFromFile() {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                addTaskFromFile(s.nextLine());
            }
            for (int i = 0; i < 20; i++) {
                System.out.println();
            }
        } catch (FileNotFoundException e)  {
            System.out.println("File does not exist" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        loadTasksFromFile();
        printBootupMessage();
        try {
            initProcessLoop();
        } catch (RuntimeException e) {
            System.out.println("Oh no i suddenly dieded :(");
        }
    }
}
