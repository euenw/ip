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
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: added: " + input);
                System.out.println("    ____________________________________________________________");
            }
        }

        scanner.close();
    }
}
