import java.util.Scanner;

public class Airy {
    public static void main(String[] args) {
        String name = "Airy";
        Scanner sc = new Scanner(System.in);
        String repeat;

        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");

        do {
            repeat = sc.nextLine();

            // Only repeat if it's not bye
            if (!repeat.toLowerCase().equals("bye")) {
                System.out.printf("%s\n\n", repeat);
            }
        } while(!repeat.toLowerCase().equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
