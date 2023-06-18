package org.example.command.update;

import org.example.collection.CollectionManager;
import org.example.command.BaseCommand;
import org.example.command.CollectionEditor;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.IllegalArgumentsException;
import org.example.exception.NoSuchIDException;

import java.util.Objects;


/**
 * Command update id {element} : update the value of the collection item whose id is equal to the given one
 */
public class UpdateIdCommand extends BaseCommand implements CollectionEditor{
    private final CollectionManager collectionManager;

    public UpdateIdCommand(CollectionManager collectionManager) {
        super("update_id", "update_id {element}: обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }
    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException{
        if (request.getArgs().isBlank()) throw new IllegalArgumentsException();
        try {
            int ID = Integer.parseInt(request.getArgs().trim());
            if (!collectionManager.containsById(ID)) throw new NoSuchIDException();
            if (Objects.isNull(request.getObject())){
                return new Response(ResponseStatus.ASK_OBJECT, "Для команды " + this.getName() + " требуется объект");
            }
            collectionManager.updateById(request.getObject(), ID);
            return new Response(ResponseStatus.OK, "Объект успешно обновлен\n");
        } catch (NoSuchIDException err) {
            return new Response(ResponseStatus.ERROR,"В коллекции нет элемента с таким id");
        } catch (NumberFormatException exception) {
            return new Response(ResponseStatus.ERROR,"id должно быть числом типа int");
        }
    }
}




