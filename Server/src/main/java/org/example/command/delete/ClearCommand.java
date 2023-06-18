package org.example.command.delete;

import org.example.collection.CollectionManager;
import org.example.command.BaseCommand;
import org.example.command.CollectionEditor;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.CommandRuntimeException;
import org.example.exception.IllegalArgumentsException;

public class ClearCommand extends BaseCommand implements CollectionEditor{
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "clear: очистить коллекцию");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) throws CommandRuntimeException, IllegalArgumentsException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        collectionManager.clear();
        return new Response(ResponseStatus.OK,"Элементы удалены\n");
    }
}
