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

            try {
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
                    if (taskIndex < 0 || taskIndex >= taskCount) {
                        throw new AuraFarmerException("that task doesn't exist, no aura for you");
                    }
                    tasks[taskIndex].markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    aura: nice! i've marked this task as done:");
                    System.out.println("    aura:   " + tasks[taskIndex]);
                    System.out.println("    ____________________________________________________________");
                } else if (input.equals("mark")) {
                    throw new AuraFarmerException("mark what? give me a task number");
                } else if (input.startsWith("unmark ")) {
                    int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                    if (taskIndex < 0 || taskIndex >= taskCount) {
                        throw new AuraFarmerException("that task doesn't exist, no aura for you");
                    }
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    aura: ok, i've marked this task as not done yet:");
                    System.out.println("    aura:   " + tasks[taskIndex]);
                    System.out.println("    ____________________________________________________________");
                } else if (input.equals("unmark")) {
                    throw new AuraFarmerException("unmark what? give me a task number");
                } else if (input.startsWith("todo")) {
                    if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
                        throw new AuraFarmerException("the description of a todo cannot be empty, that's negative aura");
                    }
                    String description = input.substring(5);
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    aura: got it. i've added this task:");
                    System.out.println("    aura:   " + tasks[taskCount - 1]);
                    System.out.println("    aura: now you have " + taskCount + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                } else if (input.startsWith("deadline")) {
                    if (input.length() <= 9 || !input.contains(" /by ")) {
                        throw new AuraFarmerException("deadline needs a description and /by time, that's how you farm aura");
                    }
                    String rest = input.substring(9);
                    int byIndex = rest.indexOf(" /by ");
                    if (byIndex <= 0) {
                        throw new AuraFarmerException("deadline needs a description before /by");
                    }
                    String description = rest.substring(0, byIndex);
                    String by = rest.substring(byIndex + 5);
                    if (by.trim().isEmpty()) {
                        throw new AuraFarmerException("when is the deadline? give me the /by time");
                    }
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    aura: got it. i've added this task:");
                    System.out.println("    aura:   " + tasks[taskCount - 1]);
                    System.out.println("    aura: now you have " + taskCount + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                } else if (input.startsWith("event")) {
                    if (input.length() <= 6 || !input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new AuraFarmerException("event needs a description, /from and /to times");
                    }
                    String rest = input.substring(6);
                    int fromIndex = rest.indexOf(" /from ");
                    int toIndex = rest.indexOf(" /to ");
                    if (fromIndex <= 0) {
                        throw new AuraFarmerException("event needs a description before /from");
                    }
                    String description = rest.substring(0, fromIndex);
                    String from = rest.substring(fromIndex + 7, toIndex);
                    String to = rest.substring(toIndex + 5);
                    if (from.trim().isEmpty() || to.trim().isEmpty()) {
                        throw new AuraFarmerException("event needs both /from and /to times filled in");
                    }
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    aura: got it. i've added this task:");
                    System.out.println("    aura:   " + tasks[taskCount - 1]);
                    System.out.println("    aura: now you have " + taskCount + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                } else {
                    throw new AuraFarmerException("i don't know what that means, you're losing aura");
                }
            } catch (AuraFarmerException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: oops! " + e.getMessage());
                System.out.println("    ____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: oops! that's not a valid number, no aura for you");
                System.out.println("    ____________________________________________________________");
            }
        }

        scanner.close();
    }
}
