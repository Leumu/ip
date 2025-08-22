import java.util.Scanner;

public class Airy {
    public static void main(String[] args) {
        String name = "Airy";
        Scanner sc = new Scanner(System.in);
        String repeat;
        String lower;
        // To store tasks
        String[] tasks = new String[100];
        // To take note of increment for the tasks array
        int increment = 0;

        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");

        do {
            repeat = sc.nextLine();
            lower = repeat.toLowerCase();

            // Only repeat if it's not bye
            if (lower.equals("bye")) {
                break;
            } else if (lower.equals("list")) {
                // Print out saved tasks
                for (int i = 0; i < increment; i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks[i]);
                }
                System.out.println("\n");
            } else {
                // Save tasks into array
                tasks[increment] = repeat;
                increment++;
                // Show user task has been added
                System.out.printf("added: %s\n\n", repeat);
            }
        } while (true);

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
