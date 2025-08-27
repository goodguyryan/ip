import java.util.Scanner;

public class Maltese {
    public static void bootup() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm Maltese, your personal chatbot!");
        System.out.println("What can I do for you");
        System.out.println("____________________________________________________________");
    }

    public static void echoLoop() {
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________________________");
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
