package maltese.task;

/**
 * Represents a deadline task with a due time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a deadline with the given description and due time.
     *
     * @param description Task description.
     * @param by Due time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the status icon with the deadline type prefix.
     *
     * @return {@code "[D]"} followed by the base status icon.
     */
    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    /**
     * Returns the serialized deadline string.
     *
     * @return Status icon, description, and due time.
     */
    @Override
    public String getTask() {
        return super.getTask() + " /by " + this.by;
    }
}
