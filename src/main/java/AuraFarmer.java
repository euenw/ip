import java.util.Scanner;

public class AuraFarmer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("    aura: " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("    ____________________________________________________________");
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: added: " + input);
                System.out.println("    ____________________________________________________________");
            }
        }

        scanner.close();
    }
}
