package org.example.command.read;

import org.example.collection.CollectionManager;
import org.example.command.BaseCommand;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.IllegalArgumentsException;

public class ShowCommand extends BaseCommand {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
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
            return new Response(ResponseStatus.ERROR, "Коллекция еще не инициализирована или содержит ноль элементов");
        }
        return new Response(ResponseStatus.OK, "Коллекция: \n" + collectionManager.getAllElementsAsString() + "\n");
    }
}