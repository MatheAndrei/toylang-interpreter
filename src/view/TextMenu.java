package view;

import view.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private final Map<String, Command> commands;

    public TextMenu() {
        this.commands = new HashMap<>();
    }

    private void printMenu() {
        System.out.println("Menu:");
        for (Command c : commands.values()) {
            String entry = String.format("%4s:%s", c.getKey(), c.getDescription());
            System.out.println(entry);
        }
    }

    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("\nOption: ");
            String key = scanner.nextLine();
            Command c = commands.get(key);
            if (c == null) {
                System.out.println("Invalid command! Try again!\n");
                continue;
            }
            c.execute();
        }
    }
}
