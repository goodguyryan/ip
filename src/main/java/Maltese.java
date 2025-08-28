import java.util.Scanner;

public class Maltese {
    static String[] tasks = new String[100];
    static int tasksLength = 0;

    public static void bootup() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm Maltese, your personal chatbot!");
        System.out.println("What can I do for you");
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String task) {
        tasks[tasksLength] = task;
        tasksLength++;
    }

    public static void printList() {
        for (int i = 0; i < tasksLength; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
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
            } else {
                System.out.println("____________________________________________________________");
                addTask(line);
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("NOOOOOOOOOOOOO. See you soon o.0!");
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        bootup();
        echoLoop();
    }
}
