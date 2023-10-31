package tim.body;
import tim.tasks.Task;
import tim.tasks.ToDo;
import tim.tasks.Event;
import tim.tasks.Deadline;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ListManager {
    /**
     * This method adds a task to the back of the list.
     * inputEntry is parsed into description.
     * 
     * @param inputEntry the input string from the user
     * @param list the list of tasks
     */
    static void addToDo (String inputEntry, ArrayList<Task> list){
        list.add(new ToDo(inputEntry));
        System.out.println("Gotcha! Added this task:");
        Messenger.printSingle(list.size(),list);
        System.out.println("now there is: "+ list.size() + " item(s)");
        Messenger.printDashes();
    }

    /**
     * This method adds a deadline to the back of the list.
     * inputEntry is parsed into description and byDate.
     * 
     * @param inputEntry the input string from the user
     * @param list the list of tasks
     */
    static void addDeadline (String inputEntry, ArrayList<Task> list){
        String[] inputTokenized;
        String byDate;
        try{
            inputTokenized = inputEntry.split("/by ",2);
            byDate = inputTokenized[1];
            String description = inputTokenized[0];
            if(description.isBlank()){
                throw new BlankInputException("missing description");
            }
            list.add(new Deadline(description,byDate));
            System.out.println("Gotcha! Added this task:");
            Messenger.printSingle(list.size(),list);
            System.out.println("now there is: "+ list.size() + " item(s)");
            Messenger.printDashes();
        } catch (ArrayIndexOutOfBoundsException AIO) {
            System.err.println("you've missed out [/by] !");
        } catch(DateTimeParseException DTPE ){
            System.err.println("that's not a valid date, please enter << /by yyyy-mm-dd >>");
        } catch (BlankInputException BIE) {
            System.err.println("you've missed out the description");
        }

    }
    
    /** 
     * This method adds an event to the back of the list.
     * inputEntry is parsed into description, fromDate and toDate.
     * 
     * @param inputEntry the input string from the user
     * @param list the list of tasks
     */
    static void addEvent (String inputEntry,ArrayList<Task> list){
        String[] inputTokenized;
        String fromDate;
        String toDate;
        try {
            inputTokenized =  inputEntry.split("/from ",2);
            String description = inputTokenized[0];
            if(description.isBlank()){
                throw new BlankInputException("missing description");
            }
            toDate = inputTokenized[1].split(" /to ", 2)[1];
            fromDate = inputTokenized[1].split(" /to ", 2)[0];
            list.add(new Event(description,fromDate,toDate));
            System.out.println("Gotcha! Added this task:");
            Messenger.printSingle(list.size(),list);
            System.out.println("now there is: "+ list.size() + " item(s)");
            Messenger.printDashes();
        } catch (ArrayIndexOutOfBoundsException AIO) {
            System.err.println("you've missed out [/fromDate] or [/toDate] !");
        } catch(DateTimeParseException DTPE ){
            System.err.println("that's not a valid date, please enter << /fromDate yyyy-mm-dd /toDate yyyy-mm-dd>>");
        } catch (BlankInputException BIE) {
            System.err.println("you've missed out the description");
        } catch (DateException DE){
            System.err.println("/fromDate date shouldn't be later than /toDate date");
        }

    }

    /**
     * This method deletes the task at the given index from the list.
     *
     * @param deleteIndex the index of the task to be deleted
     * @param list the list of tasks
     */
    static void deleteFromList (int deleteIndex,ArrayList<Task> list){
        System.out.print("Deleting: ");
        Messenger.printSingle(deleteIndex,list);
        Messenger.printDashes();
        list.remove(deleteIndex-1);
        Messenger.printList(list);
    }

    /**
     * This method marks or unmarks the task at the given index from the list.
     *
     * @param index the index of the task to be marked or unmarked
     * @param markUnmark true if the task is to be marked, false if the task is to be unmarked
     * @param list the list of tasks
     */
    static void markUnmarkTask(int index, boolean markUnmark, ArrayList<Task> list){
        Task target = list.get(index-1);

        if((target.getIsDone().equals("x")) != markUnmark){
            if(markUnmark){
                System.out.println("Nice! I've marked this task as done:");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
            target.setIsDone(markUnmark);
            Messenger.printSingle(index,list);
        } else {
            System.out.print("Task is already " + (markUnmark ? "marked" : "unmarked") + ".");

        }
        Messenger.printDashes();

    }
}
