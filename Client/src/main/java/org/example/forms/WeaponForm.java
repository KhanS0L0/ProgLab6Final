package org.example.forms;

import org.example.console.Console;
import org.example.console.ReaderWriter;
import org.example.console.UserInput;
import org.example.console.BlankConsole;
import org.example.console.ConsoleInput;
import org.example.entity.Weapon;
import org.example.exception.FileModeException;
import org.example.utils.ExecuteFileManager;

import java.util.Arrays;

public class WeaponForm extends Form<Weapon>{
    private final ReaderWriter console;
    private final UserInput scanner;
    public WeaponForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    @Override
    public Weapon build() {
        console.write("Возможные типы оружия бойца: ");
        console.write(Arrays.toString(Weapon.values()));
        while (true){
            console.write("Введите тип оружия ");
            String input = scanner.nextLine().trim();
            try{
                return Weapon.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException exception){
                console.printError("Такого оружия нет в списке");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }
}
