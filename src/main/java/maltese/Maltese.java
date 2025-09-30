package maltese;

public class Maltese {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printBootupMessage();
        Storage storage = new Storage();
        storage.loadTasksFromFile();
        try {
            ui.initProcessLoop();
        } catch (RuntimeException e) {
            ui.printProcessLoopErrorMessage();
        }
    }
}
