package maltese;

import java.util.Scanner;

public class Ui {

    public void printBootupMessage() {
        System.out.println("Hello I'm Maltese, your personal chatbot!");
        System.out.println("What can I do for you");
    }

    public void initProcessLoop() {
        String command;
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter: ");
            command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
            Parser.processCommand(command);
        }

        System.out.println("NOOOOOOOOOOOOO. See you soon o.0!");
    }

    public void printProcessLoopErrorMessage() {
        System.out.println("Oh no i suddenly dieded :(");
    }
}
