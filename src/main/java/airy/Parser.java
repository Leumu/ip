package airy;

/**
 * This class parses the input of the user
 */
public class Parser {

    /**
     * Parses the raw user input into a command and its arguments.
     *
     * @param input The full input string from the user.
     * @return A String array of size 2. The first element is the lowercase command,
     *         the second element is the remaining arguments string.
     */
    public String[] parse(String input) {
        String trimInput = input.trim();
        String args;

        // (\\s+) finds the first occurrence of whitespace and splits the string there
        String[] parts = trimInput.split("\\s+", 2);
        String command = parts[0].toLowerCase(); // First word is command
        if (parts.length == 2) {
            args = parts[1].trim();
        } else {
            args = "";
        }
        return new String[]{command, args};
    }

    /**
     * Parses the args to convert String to Int
     *
     * @param command The command being executed (e.g., "mark", "delete").
     * @param args The argument string provided by the user.
     * @return The parsed task number, converted to a zero-based index.
     */
    public int parseTaskNum(String command, String args) {
        if (args.isEmpty()) {
            throw new AiryException("Please enter a number after " + command + " to unmark a task");
        }

        // Gets the number after the command, -1 cause array is 0 indexed
        int taskNum = Integer.parseInt(args.trim()) - 1;
        if (taskNum < 0) {
            throw new AiryException("Task number must be at least 1.");
        }
        return taskNum;
    }

    /**
     * Ensures there is an argument present for Tasks such as To do, Deadline, Event
     *
     * @param command The command being executed (e.g., "to do", "deadline").
     * @param args The argument string provided by the user.
     * @throws AiryException If the argument string is empty.
     */
    public void checkArg(String command, String args) {
        if (args.isEmpty()) {
            throw new AiryException("Please enter a task name after " + command);
        }
    }

    /**
     * Parses the Deadline and Event tasks to split them into individual args
     *
     * @param command The command name, must be either "deadline" or "event".
     * @param args The full argument string for the command.
     * @return A String array containing the parsed components.
     * @throws AiryException If the arguments do not conform to the expected format with the required keywords.
     */
    public String[] parseDeadlineEvent(String command, String args) {
        if (command.equals("deadline")) {
            String[] parts = args.split("/by");
            if (parts.length != 2) {
                throw new AiryException(
                        "Please do /by before entering the due date. E.g. deadline return book /by Sunday");
            }
            parts[0] = parts[0].trim();
            parts[1] = parts[1].trim();
            return parts;
        } else { // (command.equals("event"))
            // Split string whenever u see /from or /to
            String[] parts = args.split("/from|/to");
            if (parts.length != 3) {
                throw new AiryException(
                        "Please do /from before entering the start date and /to before entering the end date"
                                + " E.g. event project meeting /from Mon 2pm /to 4pm");
            }
            parts[0] = parts[0].trim();
            parts[1] = parts[1].trim();
            parts[2] = parts[2].trim();
            return parts;
        }
    }
}
