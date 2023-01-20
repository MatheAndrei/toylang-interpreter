import gui.GUI;
import model.statement.IStatement;
import view.TextMenu;
import view.command.ExitCommand;
import view.command.RunExample;

import java.util.List;
import java.util.Scanner;

public class Interpreter {
    public static void main(String[] args) {
        // choose interface
        String option = chooseInterface();

        if (option.equals("x"))
            return;

        // clear screen
//        try {
//            String os = System.getProperty("os.name");
//            ProcessBuilder clearProcess;
//            if (os.contains("Windows"))
//                clearProcess = new ProcessBuilder("cmd", "/c", "cls");
//            else
//                clearProcess = new ProcessBuilder("clear");
//            clearProcess.inheritIO().start().waitFor();
//        } catch (InterruptedException | IOException e) {
//            throw new RuntimeException(e);
//        }

        // init interface
        if (option.equals("1")) {
            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunExample("1", Programs.ex1.toString(), Programs.ex1));
            menu.addCommand(new RunExample("2", Programs.ex2.toString(), Programs.ex2));
            menu.addCommand(new RunExample("3", Programs.ex3.toString(), Programs.ex3));
            menu.addCommand(new RunExample("4", Programs.ex4.toString(), Programs.ex4));
            menu.addCommand(new RunExample("5", Programs.ex5.toString(), Programs.ex5));
            menu.addCommand(new RunExample("6", Programs.ex6.toString(), Programs.ex6));
            menu.addCommand(new RunExample("7", Programs.ex7.toString(), Programs.ex7));
            menu.addCommand(new RunExample("8", Programs.ex8.toString(), Programs.ex8));
            menu.addCommand(new RunExample("9", Programs.ex9.toString(), Programs.ex9));
            menu.addCommand(new RunExample("10", Programs.ex10.toString(), Programs.ex10));
            menu.addCommand(new RunExample("11", Programs.ex11.toString(), Programs.ex11));
            menu.addCommand(new RunExample("12", Programs.ex12.toString(), Programs.ex12));
            menu.show();
        } else if (option.equals("2")) {
            List<IStatement> programs = Programs.getAllPrograms();
            GUI.launchApp(args, programs);
        }
    }

    private static void printMenu() {
        System.out.println("Choose an interface:");
        System.out.println("   1 console");
        System.out.println("   2 gui");
        System.out.println("   x exit");
    }

    private static String chooseInterface() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("\nOption: ");
            String option = scanner.nextLine();
            if (!option.equals("1") && !option.equals("2") && !option.equals("x")) {
                System.out.println("Invalid option! Try again!\n");
                continue;
            }
            return option;
        }
    }
}
