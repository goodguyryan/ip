package maltese;

import maltese.task.Deadline;
import maltese.task.Event;
import maltese.task.Todo;
import maltese.task.Task;

/**
 * Parses user input and dispatches task commands.
 * Errors are reported via printed messages.
 */
public class Parser {

    /**
     * Marks or unmarks the task at the given 1-based index.
     * Invalid or out-of-range indices print an error and do nothing.
     *
     * @param taskNumber 1-based index of the task.
     * @param doneStatus True to mark as done; false to unmark.
     * @param isSilent True to suppress output.
     */
    public static void processMark(String taskNumber, boolean doneStatus, boolean isSilent) {
        try {
            int actualNumber = Integer.parseInt(taskNumber);
            if (actualNumber - 1 >= TaskList.tasksLength) {
                System.out.println("my list does not have so many tasks");
            } else if (actualNumber - 1 < 0) {
                System.out.println("negative task??");
            } else {
                TaskList.tasks.get(actualNumber - 1).setDone(doneStatus);
                if (!isSilent) {
                    System.out.println(doneStatus ? "okie this task is done" : "okie this task is still doing");
                }
            }
        }
        catch (NumberFormatException e) {
            System.out.println("this task does not exist?");
        }
        Storage.updateFile();
    }

    /**
     * Adds a todo task from the given description.
     * Blank descriptions print an error and are ignored.
     *
     * @param todoTask Description of the todo.
     * @param isSilent True to suppress output.
     */
    public static void processTodo(String todoTask, boolean isSilent) {
        if (todoTask.isBlank()) {
            System.out.println("no todo found pls todo something");
            return;
        }

        Todo newTodo = new Todo(todoTask.strip());
        TaskList.addTask(newTodo);
        if (!isSilent) {
            System.out.println("Added the following todo:");
            System.out.println(newTodo.getTask());
        }
    }

    /**
     * Adds a deadline in the form "description /by when".
     * Missing delimiter or blank fields print an error and are ignored.
     *
     * @param deadlineTask Raw deadline string.
     * @param isSilent True to suppress output.
     */
    public static void processDeadline(String deadlineTask, boolean isSilent) {
        int byIndex= deadlineTask.indexOf(" /by ");
        if (byIndex == -1) {
            System.out.println("invalid deadline format pls do task /by deadline");
            return;
        }

        String task = deadlineTask.substring(0, byIndex);
        String deadline = deadlineTask.substring(byIndex + 5);
        if (task.isBlank() || deadline.isBlank()) {
            System.out.println("invalid deadline format pls do task /by deadline");
            return;
        }

        Deadline newDeadline = new Deadline(task.strip(), deadline.strip());
        TaskList.addTask(newDeadline);
        if (!isSilent) {
            System.out.println("Added the following deadline:");
            System.out.println(newDeadline.getTask());
        }
    }

    /**
     * Adds an event in the form "description /from start /to end".
     * Missing delimiters or blank fields print an error and are ignored.
     *
     * @param eventTask Raw event string.
     * @param isSilent True to suppress output.
     */
    public static void processEvent(String eventTask, boolean isSilent) {
        int fromIndex = eventTask.indexOf(" /from ");
        int toIndex = eventTask.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            System.out.println("fake event pls put event task /from from /to to");
            return;
        }

        String task =  eventTask.substring(0, fromIndex);
        String from = eventTask.substring(fromIndex + 7, toIndex);
        String to = eventTask.substring(toIndex + 5);
        if (task.isBlank() || from.isBlank() || to.isBlank()) {
            System.out.println("fake event pls put event task /from from /to to");
            return;
        }

        Event newEvent = new Event(task.strip(), from.strip(), to.strip());
        TaskList.addTask(newEvent);
        if (!isSilent) {
            System.out.println("Added the following event:");
            System.out.println(newEvent.getTask());
        }
    }

    /**
     * Deletes the task at the given 1-based index.
     * Non-numeric or out-of-range indices print an error and are ignored.
     *
     * @param deleteID 1-based index of the task.
     */
    public static void processDelete(String deleteID) {
        try{
            int deleteInt = Integer.parseInt(deleteID);
            if (deleteInt - 1 >= TaskList.tasksLength) {
                System.out.println("I do not have that many tasks!");
                return;
            }
            if (deleteInt - 1< 0) {
                System.out.println("Negative tasks!?");
                return;
            }

            System.out.println("Deleting the following task:");
            System.out.println(TaskList.tasks.get(deleteInt - 1).getTask());
            TaskList.tasks.remove(deleteInt - 1);
            TaskList.tasksLength--;
            System.out.println("You have " + TaskList.tasksLength + " tasks left");
            Storage.updateFile();
        } catch (NumberFormatException e) {
            System.out.println("That is not a number");
        }
    }

    /**
     * Prints tasks containing the given keyword.
     * Null or blank keywords print an error and are ignored.
     *
     * @param findKeyword Keyword to search for.
     */
    public static void processFind(String findKeyword) {
        if (findKeyword == null || findKeyword.isBlank()) {
            System.out.println("You have tof ind something");
            return;
        }

        boolean isFound = false;
        for (Task task: TaskList.tasks) {
            if (task.getDescription().contains(findKeyword)) {
                if (!isFound) {
                    System.out.println("Here are the matching tasks in the list:");
                    isFound = true;
                }
                System.out.println(task.getTask());
            }
        }

        if (!isFound) {
            System.out.println("No tasks in the list recognises this keyword");
        }
    }
    /**
     * Processes a single command line.
     * Null, blank, or unknown commands print an error and do nothing.
     *
     * @param command Raw user command.
     */
    public static void processCommand(String command) {
        if (command == null || command.isBlank()) {
            System.out.println("write something plssssss");
            return;
        }

        int firstSpace = command.indexOf(' ');
        String action = (firstSpace == -1) ? command : command.substring(0, firstSpace);
        String args   = (firstSpace == -1) ? "" : command.substring(firstSpace + 1);

        switch (action) {
        case "list":
            TaskList.printList();
            break;
        case "mark":
            processMark(args, true, false);
            break;
        case "unmark":
            processMark(args, false, false);
            break;
        case "todo":
            processTodo(args, false);
            break;
        case "deadline":
            processDeadline(args, false);
            break;
        case "event":
            processEvent(args, false);
            break;
        case "delete":
            processDelete(args);
            break;
        case "find":
            processFind(args);
            break;
        default:
            System.out.println("i don't recognise this at all");
        }
    }
}
