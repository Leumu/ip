import java.util.Scanner;

/**
 * This is my main class which implements the logic for handling
 * the different tasks and commands
 */
public class Airy {
    private final Ui ui;
    private final TaskList tasks;
    private Parser parser;

    /**
     * This is my Airy constructor which initializes the ArrayList for Task and Ui
     */
    public Airy() {
        // Initializes the Ui object
        this.ui = new Ui();
        // Initializes the Parser object
        this.parser = new Parser();
        // Load tasks from disk instead of starting empty
        // If no data, create fie and return empty ArrayList
        this.tasks = new TaskList(Storage.load());
    }

    /**
     * The chatbot logic is handled here
     */
    public void run() {
        String repeat;
        ui.welcomeMessage();
        // Detect input
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                repeat = sc.nextLine();
                if (repeat.isEmpty()) {
                    throw new AiryException("Please enter a keyword e.g."
                            + " todo, deadline, event, list, mark & unmark");
                }

                String[] parsed = parser.parse(repeat);
                String command = parsed[0];
                String args = parsed[1];

                // Only repeat if it's not bye
                if (command.equals("bye")) {
                    break;
                }

                switch (command) {
                case "list": {
                    ui.showTaskList(tasks.getTasks());
                    break;
                }
                case "mark": {
                    int taskNum = parser.parseTaskNum(command, args);
                    Task taskObj = tasks.markTask(taskNum);
                    ui.showMark(taskObj);

                    // Save after change
                    Storage.save(tasks.getTasks());
                    break;
                }
                case "unmark": {
                    int taskNum = parser.parseTaskNum(command, args);
                    Task taskObj = tasks.unmarkTask(taskNum);
                    ui.showUnmark(taskObj);

                    // Save after change
                    Storage.save(tasks.getTasks());
                    break;
                }
                case "todo": {
                    parser.checkArg(command, args);
                    // New to do object
                    Task todoTask = new Todo(args);
                    // Save to do object into array
                    tasks.addTask(todoTask);
                    // Show user task has been added
                    ui.showTaskAdded(todoTask, tasks.getSize());

                    // Save after change
                    Storage.save(tasks.getTasks());
                    break;
                }
                case "deadline": {
                    parser.checkArg(command, args);
                    String[] parts = parser.parseDeadlineEvent(command, args);
                    // New deadline object, parts[0] = taskName, parts[1] = dueDate
                    Deadline deadlineTask = new Deadline(parts[0], parts[1]);
                    // Save to do object into array
                    tasks.addTask(deadlineTask);
                    // Show user task has been added
                    ui.showTaskAdded(deadlineTask, tasks.getSize());

                    // Save after change
                    Storage.save(tasks.getTasks());
                    break;
                }
                case "event": {
                    parser.checkArg(command, args);
                    String[] parts = parser.parseDeadlineEvent(command, args);
                    // New event object, parts[0] = taskName, parts[1] = startDate, parts[2] = endDate
                    Event eventTask = new Event(parts[0], parts[1], parts[2]);
                    // Save to do object into array
                    tasks.addTask(eventTask);
                    // Show user task has been added
                    ui.showTaskAdded(eventTask, tasks.getSize());

                    // Save after change
                    Storage.save(tasks.getTasks());
                    break;
                }
                case "delete": {
                    int taskNum = parser.parseTaskNum(command, args);
                    // ArrayList auto reduces the index of elements after deleted element by 1
                    Task removedTask = tasks.deleteTask(taskNum);
                    ui.showTaskRemoved(removedTask, tasks.getSize());

                    // Save after change
                    Storage.save(tasks.getTasks());
                    break;
                }
                default: {
                    // Unrecognized command
                    throw new AiryException("Unrecognized command");
                }
                }
            } catch (AiryException e) { // Catch exceptions
                System.out.println(e.getMessage() + "\n");
            }
        }

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
