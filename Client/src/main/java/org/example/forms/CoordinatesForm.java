package org.example.forms;

import org.example.utils.ReadManager;
import org.example.console.ReaderWriter;
import org.example.console.BlankConsole;
import org.example.console.Console;
import org.example.entity.Coordinates;

public class CoordinatesForm extends Form<Coordinates> {
    private final ReaderWriter console;

    public CoordinatesForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
    }

    /**
     * Сконструировать новый элемент класса {@link Coordinates}
     *
     * @return объект класса {@link Coordinates}
     */
    @Override
    public Coordinates build() {
        ReadManager readManager = new ReadManager(console);
        return new Coordinates(
                readManager.readCoordinateX(),
                readManager.readCoordinateY()
        );
    }
}

