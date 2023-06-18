package org.example.command.delete;

import org.example.collection.CollectionManager;
import org.example.command.BaseCommand;
import org.example.command.CollectionEditor;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.IllegalArgumentsException;

import java.util.Objects;

public class RemoveAnyByChapterCommand extends BaseCommand implements CollectionEditor {
    private final CollectionManager collectionManager;

    public RemoveAnyByChapterCommand(CollectionManager collectionManager) {
        super("remove_any_by_chapter", "remove_any_by_chapter {element} : удалить из коллекции один элемент, значение поля chapter которого эквивалентно заданному");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнить команду
     *
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        if (Objects.isNull(request.getObject())){
            return new Response(ResponseStatus.ASK_OBJECT, "Для команды " + this.getName() + "требуется объект");
        } else {
            collectionManager.removeAnyByChapter(request.getObject().getChapter());
            return new Response(ResponseStatus.OK, "Объект успешно удален\n");
        }
    }
}


