package org.example.command.read;

import org.example.collection.CollectionManager;
import org.example.command.BaseCommand;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.IllegalArgumentsException;

public class HeadCommand extends BaseCommand {
    private final CollectionManager collectionManager;

    public HeadCommand(CollectionManager collectionManager) {
        super("head", "head: вывести первый элемент коллекции");
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
        if (collectionManager.getCollection() == null) {
            return new Response(ResponseStatus.ERROR, "Коллекция еще не инициализирована и не содержит каких либо элементов");
        }
        return new Response(ResponseStatus.OK, "Первый элемент: \n" + collectionManager.getCollection().get(0).toString() + "\n");
    }
}
