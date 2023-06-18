package org.example.forms;

import org.example.entity.SpaceMarine;
import org.example.entity.Coordinates;
import org.example.entity.Chapter;
import org.example.entity.AstartesCategory;
import org.example.entity.Weapon;
import org.example.utils.ReadManager;
import org.example.console.ReaderWriter;
import org.example.console.BlankConsole;
import org.example.console.Console;

import java.time.LocalDateTime;

public class SpaceMarineForm extends Form<SpaceMarine> {
    private final ReaderWriter console;
    public SpaceMarineForm(Console console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
    }

    /**
     * Сконструировать новый элемент класса {@link SpaceMarine}
     *
     * @return объект класса {@link SpaceMarine}
     */
    @Override
    public SpaceMarine build() {
        ReadManager readManager = new ReadManager(console);
        return new SpaceMarine(
                        readManager.readName(),
                        readCoordinates(),
                        LocalDateTime.now(),
                        readManager.readHealth(),
                        readManager.readHeartCount(),
                        readAstartesCategory(),
                        readWeapon(),
                        readChapter()
                );
    }
    private Coordinates readCoordinates(){
        return new CoordinatesForm(console).build();
    }
    private Chapter readChapter(){
        return new ChapterForm(console).build();
    }
    private AstartesCategory readAstartesCategory(){
        return new AstartesCategoryForm(console).build();
    }
    private Weapon readWeapon(){
        return new WeaponForm(console).build();
    }


}
