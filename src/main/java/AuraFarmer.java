import java.util.Scanner;

public class AuraFarmer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("    aura: hello! i'm aurafarmer");
        System.out.println("    aura: what can i do for you?");
        System.out.println("    ____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    aura: bye. hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            }

            System.out.println("    ____________________________________________________________");
            System.out.println("    aura: " + input);
            System.out.println("    ____________________________________________________________");
        }

        scanner.close();
    }
}
