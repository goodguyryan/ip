package maltese.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Creates a todo with the given description.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }


    /**
     * Returns the status icon with the todo type prefix.
     *
     * @return [T] followed by the base status icon.
     */
    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    /**
     * Returns the serialized todo string.
     *
     * @return Status icon and description.
     */
    @Override
    public String getTask() {
        return super.getTask();
    }
}
