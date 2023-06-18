package org.example.command.delete;

import org.example.collection.CollectionManager;
import org.example.command.BaseCommand;
import org.example.command.CollectionEditor;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.IllegalArgumentsException;
import org.example.exception.NoSuchIDException;

public class RemoveByIdCommand extends BaseCommand implements CollectionEditor {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "remove_by_id id: удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (request.getArgs().isBlank()) throw new IllegalArgumentsException();
        try {
            int ID = Integer.parseInt(request.getArgs().trim());
            if (!collectionManager.containsById(ID)) throw new NoSuchIDException();
            collectionManager.removeById(ID);
            return new Response(ResponseStatus.OK, "Объект удален успешно\n");
        } catch (NoSuchIDException err) {
            return new Response(ResponseStatus.ERROR, "В коллекции нет элемента с таким id\n");
        } catch (NumberFormatException exception) {
            return new Response(ResponseStatus.WRONG_ARGUMENTS, "id должно быть числом типа long\n");
        }
    }
}

