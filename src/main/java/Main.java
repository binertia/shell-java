import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
	     Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");

            String command = scanner.nextLine().trim();

            if (command.isEmpty()) {
                continue;
            }

            String[] arrCmd = command.split("\\s+");

            if (arrCmd[0].equals("exit")) {
                break;
            }

            if (arrCmd[0].equals("echo")) {
                for (int i = 1; i < arrCmd.length; i++) {
                    System.out.print(arrCmd[i] + " ");
                }
                System.out.println();
                continue;
            }
            System.out.println(command + ": command not found");
        }
        scanner.close();
    }
}
