/**
 * This class parses the input of the user
 */
public class Parser {

    /**
     * Parses the input of user
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
     */
    public void checkArg(String command, String args) {
        if (args.isEmpty()) {
            throw new AiryException("Please enter a task name after " + command);
        }
    }

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
                        "Please do /from before entering the start date and /to before entering the end date" +
                                " E.g. event project meeting /from Mon 2pm /to 4pm");
            }
            parts[0] = parts[0].trim();
            parts[1] = parts[1].trim();
            parts[2] = parts[2].trim();
            return parts;
        }
    }
}
