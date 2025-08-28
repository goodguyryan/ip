import java.util.Scanner;

public class Maltese {
    static Task[] tasks = new Task[100];
    static int tasksLength = 0;

    public static void bootup() {
        System.out.println("Hello I'm Maltese, your personal chatbot!");
        System.out.println("What can I do for you");
    }

    public static void addTask(String task) {
        tasks[tasksLength] = new Task(task);
        tasksLength++;
    }

    public static void processMark(String taskNumber, boolean doneStatus) {
        if (taskNumber.matches("[0-9]+")) {
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
        else {
            System.out.println("this task does not exist?");
        }
    }

    public static void printList() {
        if (tasksLength <= 0) {
            System.out.println("List is empty yippee");
        }
        for (int i = 0; i < tasksLength; i++) {
            System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
        }
    }

    public static void echoLoop() {
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }

            if (line.equals("list")) {
                printList();
            } else if (line.length() > 5 && line.startsWith("mark ")) {
                processMark(line.substring(5), true);
            } else if (line.length() > 7 && line.startsWith("unmark ")) {
                processMark(line.substring(7), false);
            } else {
                System.out.println("added: " + line);
                addTask(line);
            }
        }

        System.out.println("NOOOOOOOOOOOOO. See you soon o.0!");
    }

    public static void main(String[] args) {
        bootup();
        echoLoop();
    }
}
