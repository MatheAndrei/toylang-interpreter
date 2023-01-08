package view;

import view.command.Command;

import java.util.*;

public class TextMenu {
    private final Map<String, Command> commands;

    public TextMenu() {
        this.commands = new HashMap<>();
    }

    private void printMenu() {
        List<Command> allCommands = new ArrayList<>(commands.values());
        allCommands.sort(Comparator.comparingInt(c -> Integer.parseInt(c.getKey())));
        System.out.println("Menu:");
        for (Command c : allCommands) {
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
