import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
	Scanner scanner = new Scanner(System.in);
	String pathEnv = System.getenv("PATH");

	if (pathEnv == null) {
	    pathEnv = "";
	}

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
		if (len == 1) {
			continue;
		}

                for (int i = 1; i < len; i++) {
                    System.out.print(arrCmd[i]);
		    if (len - 1 != i)
			    System.out.print(" ");
                }
                System.out.println();
                continue;
            }

	    if (arrCmd[0].equals("type")) {
		int len = arrCmd.length;
		if (len == 1) {
			continue;
		}
		String[] totalBuiltin = {"type", "echo", "exit"};
		int tbLen = totalBuiltin.length;
		for (int i = 1; i < len; i++) {
			int indicator = 0;
			for (int j = 0; j < tbLen; j++) {
				if (arrCmd[i].equals(totalBuiltin[j])) {
					System.out.println(arrCmd[i] + " is a shell builtin");
					indicator++;
				}
			}
			if (indicator == 0) {
				int binIndicator = 0;
				String[] paths = pathEnv.split(File.pathSeparator);
				for (String path : paths) {
					File file = new File(path,arrCmd[i]);

					if (file.exists() && file.canExecute()) {
						System.out.println(arrCmd[i] + " is " + file.getAbsolutePath());
						binIndicator = 1;
						break;
					}
				}
				if (binIndicator == 0) {
					System.out.println(arrCmd[i] + ": not found");
				}
			}
		}
                continue;
	}

	int execIndicator = 0;

	String[] paths = pathEnv.split(File.pathSeparator);

	for (String path : paths) {

	    File file = new File(path, arrCmd[0]);

	    if (file.exists() && file.canExecute()) {

		execIndicator = 1;
		break;
		}
	}

	    if (execIndicator == 1) {
		ProcessBuilder pb = new ProcessBuilder(arrCmd);
		pb.inheritIO();
		Process p = pb.start();
		p.waitFor();
	    } else {
            System.out.println(command + ": command not found");
	    }
        }
        scanner.close();
    }
}
