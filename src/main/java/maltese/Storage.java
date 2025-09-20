package maltese;

import maltese.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    static String filePath = "src/main/java/maltese/tasks.txt";

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

    public static void loadTasksFromFile() {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                addTaskFromFile(s.nextLine());
            }
        } catch (FileNotFoundException e)  {
            System.out.println("File does not exist" + e.getMessage());
        }
    }
}
