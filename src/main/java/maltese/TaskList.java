package maltese;

import java.util.ArrayList;
import maltese.task.Task;

/**
 * Maintains the in-memory list of tasks.
 * Errors are reported via printed messages.
 */
public class TaskList {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    static int tasksLength = 0;

    /**
     * Prints the task list with 1-based indices.
     * Empty lists print a message.
     */
    static void printList() {
        if (tasksLength <= 0) {
            System.out.println("List is empty yippee");
        }
        for (int i = 0; i < tasksLength; i++) {
            System.out.println((i + 1) + "." + tasks.get(i).getTask());
        }
    }

    /**
     * Appends a task and persists the list.
     *
     * @param task Task to add.
     */
    static void addTask(Task task) {
        tasks.add(task);
        tasksLength++;
        Storage.updateFile();
    }
}
