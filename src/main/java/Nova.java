import java.util.ArrayList;
import java.util.Scanner;

public class Nova {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm Nova");
        System.out.println("What can I do for you?");
        System.out.println(line);
        String input = scanner.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();

        // Loop until the user says "bye" or "Bye"
        while (!input.equals("bye") && !input.equals("Bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d.%s\n", i + 1, tasks.get(i));
                    }
                    System.out.println(line);
                } else if (input.equals("todo")) {
                    throw new NovaException("todo what? give description");
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new NovaException("todo what? give description");
                    }
                    Task task = new Todo(description);
                    tasks.add(task);

                    System.out.println(line);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (input.equals("deadline")) {
                    throw new NovaException("deadline for what? don't anyhow");
                } else if (input.startsWith("deadline ")) {
                    String rest = input.substring(9).trim();
                    int byIndex = rest.indexOf(" /by ");
                    if (byIndex == -1) {
                        throw new NovaException("when due? use /by");
                    }
                    String description = rest.substring(0, byIndex).trim();
                    String by = rest.substring(byIndex + 5).trim();
                    if (description.isEmpty()) {
                        throw new NovaException("deadline for what? don't anyhow");
                    }
                    if (by.isEmpty()) {
                        throw new NovaException("walao /by cannot be empty");
                    }
                    Task task = new Deadline(description, by);
                    tasks.add(task);

                    System.out.println(line);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (input.equals("event")) {
                    throw new NovaException("event what? tell me");
                } else if (input.startsWith("event ")) {
                    String rest = input.substring(6).trim();
                    int fromIndex = rest.indexOf(" /from ");
                    int toIndex = rest.indexOf(" /to ");
                    if (fromIndex == -1) {
                        throw new NovaException("eh where the /from?");
                    }
                    if (toIndex == -1) {
                        throw new NovaException("eh where the /to?");
                    }
                    String description = rest.substring(0, fromIndex).trim();
                    String from = rest.substring(fromIndex + 7, toIndex).trim();
                    String to = rest.substring(toIndex + 5).trim();
                    if (description.isEmpty()) {
                        throw new NovaException("event what? tell me");
                    }
                    if (from.isEmpty()) {
                        throw new NovaException("start time cannot be empty");
                    }
                    if (to.isEmpty()) {
                        throw new NovaException("end time cannot be empty");
                    }
                    Task task = new Event(description, from, to);
                    tasks.add(task);

                    System.out.println(line);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (input.equals("mark")) {
                    throw new NovaException("mark which one?");
                } else if (input.startsWith("mark ")) {
                    int taskNum = Integer.parseInt(input.substring(5)) - 1;
                    if (taskNum < 0 || taskNum >= tasks.size()) {
                        throw new NovaException("this task where got exist");
                    }
                    tasks.get(taskNum).markAsDone();

                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(taskNum));
                    System.out.println(line);
                } else if (input.equals("unmark")) {
                    throw new NovaException("unmark which one?");
                } else if (input.startsWith("unmark ")) {
                    int taskNum = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNum < 0 || taskNum >= tasks.size()) {
                        throw new NovaException("this task where got exist");
                    }
                    tasks.get(taskNum).unmarkAsDone();

                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(taskNum));
                    System.out.println(line);
                } else if (input.equals("delete")) {
                    throw new NovaException("delete which one?");
                } else if (input.startsWith("delete ")) {
                    int taskNum = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNum < 0 || taskNum >= tasks.size()) {
                        throw new NovaException("this task where got exist");
                    }
                    Task removed = tasks.remove(taskNum);

                    System.out.println(line);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else {
                    throw new NovaException("i don't understand");
                }
            } catch (NovaException e) {
                System.out.println(line);
                System.out.println("OOPS!!! " + e.getMessage());
                System.out.println(line);
            } catch (NumberFormatException e) {
                System.out.println(line);
                System.out.println("OOPS!!! that's not a number");
                System.out.println(line);
            }
            input = scanner.nextLine(); // read next input
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        scanner.close();
    }
}
