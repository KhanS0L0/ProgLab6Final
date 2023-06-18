package org.example.command.create;

import org.example.collection.CollectionManager;
import org.example.command.BaseCommand;
import org.example.command.CollectionEditor;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.IllegalArgumentsException;

import java.util.Objects;

/**
 * Command add {element}
 */
public class AddCommand extends BaseCommand implements CollectionEditor {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        super("add", "add {element}: добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        if (Objects.isNull(request.getObject())) {
            return new Response(ResponseStatus.ASK_OBJECT, "Для команды " + this.getName() + " требуется объект");
        } else {
            collectionManager.addSpaceMarine(request.getObject());
            return new Response(ResponseStatus.OK, "Объект успешно добавлен\n");
        }
    }

}