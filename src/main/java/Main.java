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
		int len = arrCmd.length;
                for (int i = 1; i < len; i++) {
                    System.out.print(arrCmd[i]);
		    if (len - 1 != i)
			    System.out.print(" ");
                }
                System.out.println();
                continue;
            }
            System.out.println(command + ": command not found");
        }
        scanner.close();
    }
}
