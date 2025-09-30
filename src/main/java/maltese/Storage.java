package maltese;

import maltese.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles persistence of tasks to and from a text file.
 * Errors are reported via printed messages; in
 */
public class Storage {
    static String filePath = "tasks.txt";

    /**
     * Adds a task parsed from a serialized line.
     *
     * @param s Serialized task line.
     */
    public static void addTaskFromFile(String s) {
        char taskType = s.charAt(1);
        char taskIsDone = s.charAt(4);
        String taskDescription = s.substring(7);

        switch (taskType) {
        case 'T':
            Parser.processTodo(taskDescription, true);
            break;
        case 'D':
            Parser.processDeadline(taskDescription, true);
            break;
        case 'E':
            Parser.processEvent(taskDescription, true);
            break;
        default:
            return;
        }

        if (taskIsDone == 'X') {
            Parser.processMark(Integer.toString(TaskList.tasksLength), true, true);
        }
    }

    /**
     * Writes all tasks to the tasks file.
     * I/O errors print an error message.
     */
    public static void updateFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : TaskList.tasks) {
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

    /**
     * Loads tasks from the tasks file.
     * Missing file prints an error message.
     */
    public void loadTasksFromFile() {
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                boolean created = f.createNewFile();
                if (!created) {
                    System.out.println("failed to create a new file T-T");
                    return;
                }
                System.out.println("created new file yippee");
            } catch (IOException e) {
                System.out.println("this file cant be created" + e.getMessage());
            }
        }

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                addTaskFromFile(s.nextLine());
            }
        } catch (FileNotFoundException e)  {
            System.out.println("File does not exist" + e.getMessage());
        }
    }
}
