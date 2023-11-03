package tim.tasks;
import tim.body.DateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents as an Event object.
 * This class is a subclass of Task.
 * It has a description, a fromDate and a toDate.
 */
public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Creates an Event object.
     *
     * @param description the description of the task
     * @param fromDate the date of the event
     * @param toDate the date of the event
     * @throws DateException if the fromDate is after toDate
     */
    public Event(String description, String fromDate, String toDate) throws DateException {
        super(description);
        this.setType('E');
        this.fromDate = LocalDate.parse(fromDate);
        this.toDate = LocalDate.parse(toDate);
        if(this.fromDate.isAfter(this.toDate)){
            throw new DateException("improper date");
        }
    }

    /**
     * @inheritDoc
     *
     * @return description of the task with the fromDate and toDate.
     */
    @Override
    public String getDescription(){
        return (super.getDescription() + "(from: " + fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +  ")");
    }


}
