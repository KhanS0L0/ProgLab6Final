package org.example.command.delete;

import org.example.collection.CollectionManager;
import org.example.command.BaseCommand;
import org.example.command.CollectionEditor;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.FileModeException;
import org.example.exception.IllegalArgumentsException;

import java.util.NoSuchElementException;

public class RemoveLowerCommand extends BaseCommand implements CollectionEditor {
    private final CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("remove_lower", "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
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
        if (request.getArgs().isBlank()) throw new IllegalArgumentsException();
        try {
            int health = Integer.parseInt(request.getArgs().trim());
            if (health > 0) {
                collectionManager.removeLower(health);
                return new Response(ResponseStatus.OK, "Удалены элементы меньше чем заданный\n");
            } else {
                System.out.println("Уровень здоровье должен быть больше 0");
                return new Response(ResponseStatus.WRONG_ARGUMENTS, "Упс, что-то пошло не так");
            }
        } catch (NoSuchElementException e) {
            return new Response(ResponseStatus.ERROR, "В коллекции нет элементов");
        } catch (FileModeException e) {
            return new Response(ResponseStatus.ERROR, "Поля в файле не валидны! Объект не создан");
        } catch (NumberFormatException e) {
            return new Response(ResponseStatus.ERROR, "Число введено неверно");
        }
    }
}


