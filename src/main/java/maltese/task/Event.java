package maltese.task;

/**
 * Represents an event with a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an event with the given description and time range.
     *
     * @param description Task description.
     * @param from Start time.
     * @param to End time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the status icon with the event type prefix.
     *
     * @return [E] followed by the base status icon.
     */
    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    /**
     * Returns the serialized event string.
     *
     * @return Status icon, description, start time, and end time.
     */
    @Override
    public String getTask() {
        return super.getTask() + " /from " + this.from + " /to " + this.to;
    }
}
