package org.example.forms;

import org.example.utils.ReadManager;
import org.example.console.ReaderWriter;
import org.example.console.BlankConsole;
import org.example.console.Console;
import org.example.entity.Chapter;

public class ChapterForm extends Form<Chapter>{
    private final ReaderWriter console;

    public ChapterForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
    }

    /**
     * Сконструировать новый элемент класса {@link Chapter}
     * @return объект класса {@link Chapter}
     */
    @Override
    public Chapter build() {
        ReadManager readManager = new ReadManager(console);
        return new Chapter(
                readManager.readChapterName(),
                readManager.readChapterParentLegion(),
                readManager.readChapterMarinesCount(),
                readManager.readChapterWorld()
        );
    }
}
