import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to store the data to the files and fetch data from files
 */
public class Storage {
    private static final Path DATA_FOLDER = Paths.get("data");
    private static final Path DATA_FILE = DATA_FOLDER.resolve("Airy.txt"); // Goes to data/Airy.txt path

    /**
     * Creates the file if it does not exist
     */
    private static void fileExists() {
        try {
            if (!Files.exists(DATA_FOLDER)) {
                Files.createDirectories(DATA_FOLDER);
            }
            if (!Files.exists(DATA_FILE)) {
                Files.createFile(DATA_FILE);
            }
        } catch (IOException e) {
            throw new AiryException("Error creating data file"); // Throws an error
        }
    }

    /**
     * Loads tasks from ./data/Airy.txt.
     * Create file if it doesn't exist it and returns empty list.
     */
    public static ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            fileExists();
            if (Files.size(DATA_FILE) > 0) { // So no error is throw if file is empty
                List<String> lines = Files.readAllLines(DATA_FILE); // If file is empty, it throws an error
                for (String line : lines) {
                    Task t = createTask(line); // Creates the task
                    if (t != null) {
                        tasks.add(t); // Stores the task in the ArrayList
                    }
                }
            }
        } catch (Exception e) {
            throw new AiryException("Something went wrong, starting with what we could read.");
        }
        return tasks;
    }

    /**
     * Creates task from the raw data fetched from the file
     */
    private static Task createTask(String line) {
        // Split into at most 4 pieces
        String[] parts = line.split("\\s*\\|\\s*", 5);
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0].trim();
        boolean isCompleted = parts[1].trim().equals("1");
        String name = parts[2].trim();

        // Switch statement to create respective tasks based on their data
        switch (type) {
        case "T": // To do Type
            Todo t = new Todo(name);
            if (isCompleted) {
                t.markCompleted();
            }
            return t;
        case "D": // Deadline Type
            if (parts.length == 4) {
                String dueDate = parts[3].trim();
                Deadline d = new Deadline(name, dueDate);
                if (isCompleted) {
                    d.markCompleted();
                }
                return d;
            }
        case "E": // Event Type
            if (parts.length == 5) {
                String startDate = parts[3].trim();
                String endDate = parts[4].trim();
                Event e = new Event(name, startDate, endDate);
                if (isCompleted) {
                    e.markCompleted();
                }
                return e;
            }
        default:
            return null;
        }
    }

    /**
     * Saves the current tasks list to ./data/Airy.txt
     * Note it overwrites the file
     */
    public static void save(ArrayList<Task> tasks) {
        List<String> data = new ArrayList<>();
        for (Task task : tasks) {
            data.add(serialize(task)); // Change formatting for every Task inside ArrayList tasks to a String
        }
        try {
            fileExists();
            // Truncate file before writing
            Files.write(DATA_FILE, data,
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println("Could not save tasks to storage.");
        }
    }

    /**
     * Saves the existing tasks into my file data/Airy.txt
     * File format:
     * T | 1 | read book
     * D | 0 | return book | Sunday
     * E | 0 | project meeting | Mon 2pm | 4pm
     */
    private static String serialize(Task t) {
        String isCompleted = t.getStatus().equals("X") ? "1" : "0";

        if (t instanceof Todo) {
            return String.format("T | %s | %s", isCompleted, t.getTaskName());
        } else if (t instanceof Deadline) {
            return String.format("D | %s | %s | %s", isCompleted, t.getTaskName(), t.getExtraDetailsForStorage());
        } else {
            return String.format("E | %s | %s | %s", isCompleted, t.getTaskName(), t.getExtraDetailsForStorage());
        }
    }
}
