import java.util.ArrayList;
import java.util.Scanner;

public class AuraFarmer {
    private static final String FILE_PATH = "data/aurafarmer.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> tasks;

        // Load tasks from file
        try {
            tasks = storage.load();
        } catch (AuraFarmerException e) {
            System.out.println("    aura: " + e.getMessage());
            tasks = new ArrayList<>();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    aura: hello! i'm aurafarmer");
        System.out.println("    aura: what can i do for you?");
        System.out.println("    ____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();

            try {
                Command command = parseCommand(input);

                switch (command) {
                case BYE:
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    aura: next time you return, return with more aura");
                    System.out.println("    ____________________________________________________________");
                    scanner.close();
                    return;
                case LIST:
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    aura: here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("    aura: " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println("    ____________________________________________________________");
                    break;
                case MARK:
                    handleMark(input, tasks);
                    storage.save(tasks);
                    break;
                case UNMARK:
                    handleUnmark(input, tasks);
                    storage.save(tasks);
                    break;
                case TODO:
                    handleTodo(input, tasks);
                    storage.save(tasks);
                    break;
                case DEADLINE:
                    handleDeadline(input, tasks);
                    storage.save(tasks);
                    break;
                case EVENT:
                    handleEvent(input, tasks);
                    storage.save(tasks);
                    break;
                case DELETE:
                    handleDelete(input, tasks);
                    storage.save(tasks);
                    break;
                case UNKNOWN:
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
    }

    private static Command parseCommand(String input) {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else {
            return Command.UNKNOWN;
        }
    }

    private static void handleMark(String input, ArrayList<Task> tasks) throws AuraFarmerException {
        if (input.length() <= 5) {
            throw new AuraFarmerException("mark what? give me a task number");
        }
        int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new AuraFarmerException("that task doesn't exist, no aura for you");
        }
        tasks.get(taskIndex).markAsDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("    aura: nice! i've marked this task as done:");
        System.out.println("    aura:   " + tasks.get(taskIndex));
        System.out.println("    ____________________________________________________________");
    }

    private static void handleUnmark(String input, ArrayList<Task> tasks) throws AuraFarmerException {
        if (input.length() <= 7) {
            throw new AuraFarmerException("unmark what? give me a task number");
        }
        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new AuraFarmerException("that task doesn't exist, no aura for you");
        }
        tasks.get(taskIndex).markAsNotDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("    aura: ok, i've marked this task as not done yet:");
        System.out.println("    aura:   " + tasks.get(taskIndex));
        System.out.println("    ____________________________________________________________");
    }

    private static void handleTodo(String input, ArrayList<Task> tasks) throws AuraFarmerException {
        if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
            throw new AuraFarmerException("the description of a todo cannot be empty, that's negative aura");
        }
        String description = input.substring(5);
        tasks.add(new Todo(description));
        System.out.println("    ____________________________________________________________");
        System.out.println("    aura: got it. i've added this task:");
        System.out.println("    aura:   " + tasks.get(tasks.size() - 1));
        System.out.println("    aura: now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    private static void handleDeadline(String input, ArrayList<Task> tasks) throws AuraFarmerException {
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
        tasks.add(new Deadline(description, by));
        System.out.println("    ____________________________________________________________");
        System.out.println("    aura: got it. i've added this task:");
        System.out.println("    aura:   " + tasks.get(tasks.size() - 1));
        System.out.println("    aura: now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    private static void handleEvent(String input, ArrayList<Task> tasks) throws AuraFarmerException {
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
        tasks.add(new Event(description, from, to));
        System.out.println("    ____________________________________________________________");
        System.out.println("    aura: got it. i've added this task:");
        System.out.println("    aura:   " + tasks.get(tasks.size() - 1));
        System.out.println("    aura: now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    private static void handleDelete(String input, ArrayList<Task> tasks) throws AuraFarmerException {
        if (input.length() <= 7) {
            throw new AuraFarmerException("delete what? give me a task number");
        }
        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new AuraFarmerException("that task doesn't exist, no aura for you");
        }
        Task removedTask = tasks.remove(taskIndex);
        System.out.println("    ____________________________________________________________");
        System.out.println("    aura: noted. i've removed this task:");
        System.out.println("    aura:   " + removedTask);
        System.out.println("    aura: now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }
}
