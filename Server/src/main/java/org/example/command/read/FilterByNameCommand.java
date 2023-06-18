package org.example.command.read;

import org.example.collection.CollectionManager;
import org.example.command.BaseCommand;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.IllegalArgumentsException;


import java.util.Arrays;

public class FilterByNameCommand extends BaseCommand {
    private CollectionManager collectionManager;

    public FilterByNameCommand(CollectionManager collectionManager) {
        super("filter_contains_name", "filter_contains_name {element} : вывести элементы, значение поля name которых содержит заданную подстроку");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (request.getArgs().isBlank()) throw new IllegalArgumentsException();
        String name = request.getArgs().trim().toLowerCase();
        return new Response(ResponseStatus.OK, "Элементы коллекции с заданным name: \n" + collectionManager.filterByName(name) + "\n");
    }
}
