import java.util.Scanner;
import java.util.ArrayList;

/**
 * This is my main class which implements the logic for handling
 * the different tasks and commands
 */
public class Airy {
    private final Ui ui;
    private TaskList tasks;

    /**
     * This is my Airy constructor which initializes the ArrayList for Task and Ui
     */
    public Airy() {
        // Initializes the Ui object
        this.ui = new Ui();
        // Load tasks from disk instead of starting empty
        // If no data, create fie and return empty ArrayList
        this.tasks = new TaskList(Storage.load());
    }

    /**
     * The chatbot logic is handled here
     */
    public void run() {
        ui.welcomeMessage();
        String repeat;
        String lower;

        // Detect input
        Scanner sc = new Scanner(System.in);

        do {
            try {
                repeat = sc.nextLine();
                if (repeat.isEmpty()) {
                    throw new AiryException("Please enter a keyword e.g."
                            + " todo, deadline, event, list, mark & unmark");
                }
                lower = repeat.toLowerCase();

                // Only repeat if it's not bye
                if (lower.equals("bye")) {
                    break;
                } else if (lower.equals("list")) {
                    ui.showTaskList(tasks.getTasks());
                } else if (lower.startsWith("mark")) {
                    if (repeat.length() <= 5) {
                        throw new AiryException("Please enter a number after mark to mark a task");
                    }
                    // Gets the number after mark, -1 cause array is 0 indexed
                    int taskNum = Integer.parseInt(lower.substring(5)) - 1;
                    Task taskObj = tasks.markTask(taskNum);
                    ui.showMark(taskObj);

                    // Save after change
                    Storage.save(tasks.getTasks());
                } else if (lower.startsWith("unmark")) {
                    if (repeat.length() <= 7) {
                        throw new AiryException("Please enter a number after unmark to unmark a task");
                    }
                    // Gets the number after unmark, -1 cause array is 0 indexed
                    int taskNum = Integer.parseInt(lower.substring(7)) - 1;
                    Task taskObj = tasks.unmarkTask(taskNum);
                    ui.showUnmark(taskObj);

                    // Save after change
                    Storage.save(tasks.getTasks());
                } else if (lower.startsWith("todo")) {
                    if (repeat.length() <= 5) {
                        throw new AiryException("Please enter a task after todo");
                    }
                    String taskName = repeat.substring(5);
                    // New to do object
                    Todo todoTask = new Todo(taskName);
                    // Save to do object into array
                    tasks.addTask(todoTask);
                    // Show user task has been added
                    ui.showTaskAdded(todoTask, tasks.getSize());

                    // Save after change
                    Storage.save(tasks.getTasks());
                } else if (lower.startsWith("deadline")) {
                    if (repeat.length() <= 9) {
                        throw new AiryException("Please enter a task after deadline");
                    }
                    String[] parts = repeat.substring(9).split("/by");
                    if (parts.length != 2) {
                        throw new AiryException(
                                "Please do /by before entering the due date. E.g. deadline return book /by Sunday");
                    }
                    String taskName = parts[0].trim();
                    String dueDate = parts[1].trim();
                    // New deadline object
                    Deadline deadlineTask = new Deadline(taskName, dueDate);
                    // Save Deadline object into array
                    tasks.addTask(deadlineTask);
                    // Show user task has been added
                    ui.showTaskAdded(deadlineTask, tasks.getSize());

                    // Save after change
                    Storage.save(tasks.getTasks());
                } else if (lower.startsWith("event")) {
                    if (repeat.length() <= 6) {
                        throw new AiryException("Please enter a task after event");
                    }
                    // Split string whenever u see /from or /to
                    String[] parts = repeat.substring(6).split("/from|/to");
                    if (parts.length != 3) {
                        throw new AiryException(
                                "Please do /from before entering the start date and /to before entering the end date" +
                                        " E.g. event project meeting /from Mon 2pm /to 4pm");
                    }
                    String taskName = parts[0].trim();
                    String starting = parts[1].trim();
                    String end = parts[2].trim();
                    // New event object
                    Event eventTask = new Event(taskName, starting, end);
                    // Save Event object into array
                    tasks.addTask(eventTask);
                    // Show user task has been added
                    ui.showTaskAdded(eventTask, tasks.getSize());

                    // Save after change
                    Storage.save(tasks.getTasks());
                } else if (lower.startsWith("delete")) {
                    if (repeat.length() <= 7) {
                        throw new AiryException("Please enter a number after delete to delete a task");
                    }
                    int taskNum = Integer.parseInt(lower.substring(7)) - 1;
                    // ArrayList auto reduces the index of elements after deleted element by 1
                    Task removedTask = tasks.deleteTask(taskNum);
                    ui.showTaskRemoved(removedTask, tasks.getSize());

                    // Save after change
                    Storage.save(tasks.getTasks());
                } else {
                    // Unrecognized command
                    throw new AiryException("Unrecognized command");
                }
            } catch (AiryException e) { // Catch exceptions
                System.out.println(e.getMessage() + "\n");
            }
        } while (true);

        ui.byeMessage();
        sc.close();
    }

    /**
     * Main method used to call run()
     */
    public static void main(String[] args) {
        new Airy().run();
    }
}
