package org.example.command.read;

import org.example.collection.CollectionManager;
import org.example.command.BaseCommand;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.IllegalArgumentsException;

public class CountGreaterThanHeartCommand extends BaseCommand {
    private final CollectionManager collectionManager;

    public CountGreaterThanHeartCommand(CollectionManager collectionManager) {
        super("count_greater_than_heart_count", "count_greater_than_heart_count {element} : вывести количество элементов, значение поля heartCount которых больше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (request.getArgs().isBlank()) throw new IllegalArgumentsException();
        if (collectionManager.getCollection() == null) {
            return new Response(ResponseStatus.ERROR, "Коллекция еще не инициализирована и не содержит каких либо элементов");
        }
        try {
            int heartCount = Integer.parseInt(request.getArgs().trim());
            return new Response(ResponseStatus.OK, "Количество элементов: " + collectionManager.countByHeartCount(heartCount));
        } catch (NumberFormatException exception) {
            return new Response(ResponseStatus.ERROR, "heartCount должен быть числом типа integer");
        }
    }
}