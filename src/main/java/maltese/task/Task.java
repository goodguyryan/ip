package maltese.task;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon.
     *
     * @return "[X]" if done, otherwise "[ ]".
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Sets the completion status.
     *
     * @param isDone true to mark as done; false to mark as not done.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the serialized task string.
     *
     * @return Status icon and description.
     */
    public String getTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
