package maltese;

public class Maltese {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage.loadTasksFromFile();
        ui.printBootupMessage();
        try {
            ui.initProcessLoop();
        } catch (RuntimeException e) {
            ui.printProcessLoopErrorMessage();
        }
    }
}
