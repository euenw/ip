import java.util.Scanner;

public class AuraFarmer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("    ____________________________________________________________");
        System.out.println("    aura: hello! i'm aurafarmer");
        System.out.println("    aura: what can i do for you?");
        System.out.println("    ____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: next time you return, return with more aura");
                System.out.println("    ____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("    aura: " + (i + 1) + "." + tasks[i]);
                }
                System.out.println("    ____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: nice! i've marked this task as done:");
                System.out.println("    aura:   " + tasks[taskIndex]);
                System.out.println("    ____________________________________________________________");
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: ok, i've marked this task as not done yet:");
                System.out.println("    aura:   " + tasks[taskIndex]);
                System.out.println("    ____________________________________________________________");
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                tasks[taskCount] = new Todo(description);
                taskCount++;
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: got it. i've added this task:");
                System.out.println("    aura:   " + tasks[taskCount - 1]);
                System.out.println("    aura: now you have " + taskCount + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else if (input.startsWith("deadline ")) {
                String rest = input.substring(9);
                int byIndex = rest.indexOf(" /by ");
                String description = rest.substring(0, byIndex);
                String by = rest.substring(byIndex + 5);
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: got it. i've added this task:");
                System.out.println("    aura:   " + tasks[taskCount - 1]);
                System.out.println("    aura: now you have " + taskCount + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else if (input.startsWith("event ")) {
                String rest = input.substring(6);
                int fromIndex = rest.indexOf(" /from ");
                int toIndex = rest.indexOf(" /to ");
                String description = rest.substring(0, fromIndex);
                String from = rest.substring(fromIndex + 7, toIndex);
                String to = rest.substring(toIndex + 5);
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: got it. i've added this task:");
                System.out.println("    aura:   " + tasks[taskCount - 1]);
                System.out.println("    aura: now you have " + taskCount + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: i don't understand that command");
                System.out.println("    ____________________________________________________________");
            }
        }

        scanner.close();
    }
}
