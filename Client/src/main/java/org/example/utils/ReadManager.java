package org.example.utils;

import org.example.console.ReaderWriter;
import org.example.console.UserInput;
import org.example.console.BlankConsole;
import org.example.console.Console;
import org.example.console.ConsoleInput;
import org.example.entity.AstartesCategory;
import org.example.entity.Weapon;
import org.example.exception.FileModeException;

import java.util.Arrays;

public class ReadManager implements Readable {
    private final ReaderWriter console;
    private final UserInput scanner;

    public ReadManager(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    @Override
    public String readName() {
        String name;
        while (true) {
            console.write("Введите имя/название отряда:");
            name = scanner.nextLine().trim();
            if (name.equals("") || !name.matches("^[a-zA-Z-А-Яа-я]*$")) {
                console.printError("Имя не может быть пустой строкой/иными знаками, кроме букв");
                if (Console.isFileMode()) throw new FileModeException();
            } else {
                return name;
            }
        }
    }

    @Override
    public Double readCoordinateX() {
        while (true) {
            console.write("Введите координату X:");
            String input = scanner.nextLine().trim();
            try {
                double x = Double.parseDouble(input);
                if (x <= -595) {
                    console.printError("Значение поля должно быть больше -595");
                }
                if (input.isEmpty() || input.isBlank()) {
                    console.printError("Введите число, а не пустую строку");
                } else {
                    return x;
                }
            } catch (NumberFormatException exception) {
                console.printError("Число введено неверно");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * checks if the entered coordinate is correct, whether a number is entered or not
     *
     * @return Y
     */
    @Override
    public Double readCoordinateY() {
        while (true) {
            console.write("Введите координату Y:");
            String input = scanner.nextLine().trim();
            try {
                double y = Double.parseDouble(input);
                if (input.isEmpty()) {
                    console.printError("Введите число, а не пустую строку");
                } else {
                    return y;
                }
            } catch (NumberFormatException exception) {
                console.printError("Число введено неверно");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    @Override
    public Float readHealth() {
        while (true) {
            console.write("ведите уровень здоровья бойца:");
            String input = scanner.nextLine().trim();
            try {
                float health = Float.parseFloat(input);
                if (input.isEmpty() || input.isBlank()) {
                    console.printError("Введите число, а не пустую строку");
                } else {
                    return health;
                }
            } catch (NumberFormatException exception) {
                console.printError("Число введено неверно");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }


    @Override
    public Integer readHeartCount() {
        while (true) {
            console.write("ведите сердечный уровень бойца:");
            String input = scanner.nextLine().trim();
            try {
                int health = Integer.parseInt(input);
                if (input.isEmpty() || input.isBlank()) {
                    console.printError("Введите число, а не пустую строку");
                } else {
                    return health;
                }
            } catch (NumberFormatException exception) {
                console.printError("Число введено неверно");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    @Override
    public AstartesCategory readCategory() {
        console.write("Вы должны ввести одно из перечисленных видов оружия:" + Arrays.toString(AstartesCategory.values()));
        AstartesCategory astartesCategory;
        try {
            astartesCategory = AstartesCategory.valueOf(console.getValidatedValue("\nВведите вид оружия:").toUpperCase());
        } catch (IllegalArgumentException e) {
            astartesCategory = readCategory();
            if (Console.isFileMode()) throw new FileModeException();
        }
        return astartesCategory;
    }

    @Override
    public Weapon readWeapon() {
        console.write("Вы должны ввести одно из перечисленных видов оружия:" + Arrays.toString(Weapon.values()));
        Weapon weapon;
        try {
            weapon = Weapon.valueOf(console.getValidatedValue("\nВведите вид оружия:").toUpperCase());
        } catch (IllegalArgumentException e) {
            weapon = readWeapon();
            if (Console.isFileMode()) throw new FileModeException();
        }
        return weapon;
    }

    @Override
    public Long readChapterMarinesCount() {
        while (true) {
            console.write("Введите количество бойцов дивизиона:");
            String input = scanner.nextLine().trim();
            try {
                long marinesCount = Long.parseLong(input);
                if (input.isEmpty()) {
                    console.printError("Введите число, а не пустую строку");
                } else {
                    return marinesCount;
                }
            } catch (NumberFormatException exception) {
                console.printError("Число введено неверно");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    @Override
    public String readChapterName() {
        String chapterName;
        while (true) {
            console.write("Введите имя/название главы:");
            chapterName = scanner.nextLine().trim();
            if (chapterName.equals("") || !chapterName.matches("^[a-zA-Z-А-Яа-я]*$")) {
                console.printError("Имя/название главы не может быть пустой строкой/иными знаками, кроме букв");
                if (Console.isFileMode()) throw new FileModeException();
            } else {
                return chapterName;
            }
        }    }

    @Override
    public String readChapterParentLegion() {
        String chapterParentLegion;
        while (true) {
            console.write("Введите родительское имя/название главы:");
            chapterParentLegion = scanner.nextLine().trim();
            if (chapterParentLegion.equals("") || !chapterParentLegion.matches("^[a-zA-Z-А-Яа-я]*$")) {
                console.printError("Родительское имя/называние не может быть пустой строкой/иными знаками, кроме букв");
                if (Console.isFileMode()) throw new FileModeException();
            } else {
                return chapterParentLegion;
            }
        }
    }

    @Override
    public String readChapterWorld() {
        String chapterWorldName;
        while (true) {
            console.write("Введите имя/название мира главы:");
            chapterWorldName = scanner.nextLine().trim();
            if (chapterWorldName.equals("") || !chapterWorldName.matches("^[a-zA-Z-А-Яа-я]*$")) {
                console.printError("Имя мира не может быть пустой строкой/иными знаками, кроме букв");
                if (Console.isFileMode()) throw new FileModeException();
            } else {
                return chapterWorldName;
            }
        }
    }
}

