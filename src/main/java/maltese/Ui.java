package maltese;

import java.util.Scanner;

/**
 * Provides a simple console UI for the Maltese chatbot.
 */
public class Ui {

    /**
     * Prints the boot-up greeting and prompt.
     */
    public void printBootupMessage() {
        System.out.println("Hello I'm Maltese, your personal chatbot!");
        System.out.println("What can I do for you");
    }

    /**
     * Runs the interactive command loop until the user enters "bye".
     * Input errors are handled by the command parser.
     */
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

    /**
     * Prints a generic error message for failures in the process loop.
     */
    public void printProcessLoopErrorMessage() {
        System.out.println("Oh no i suddenly dieded :(");
    }
}
