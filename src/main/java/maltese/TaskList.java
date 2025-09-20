package maltese;

import java.util.ArrayList;
import maltese.task.Task;

public class TaskList {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    static int tasksLength = 0;

    static void printList() {
        if (tasksLength <= 0) {
            System.out.println("List is empty yippee");
        }
        for (int i = 0; i < tasksLength; i++) {
            System.out.println((i + 1) + "." + tasks.get(i).getTask());
        }
    }

    static void addTask(Task task) {
        tasks.add(task);
        tasksLength++;
        Storage.updateFile();
    }
}
