package org.example.utils;

import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.CommandRuntimeException;
import org.example.exception.ExitObligedException;
import org.example.exception.IllegalArgumentsException;
import org.example.exception.NoSuchCommandException;
import org.example.managers.CommandManager;

public class RequestHandler {
    private final CommandManager commandManager;

    public RequestHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response handle(Request request) {
        try {
            return commandManager.execute(request);
        } catch (IllegalArgumentsException e) {
            return new Response(ResponseStatus.WRONG_ARGUMENTS,
                    "Неверное использование аргументов команды");
        } catch (CommandRuntimeException e) {
            return new Response(ResponseStatus.ERROR,
                    "Ошибка при исполнении программы");
        } catch (NoSuchCommandException e) {
            return new Response(ResponseStatus.ERROR, "Такой команды нет в списке");
        } catch (ExitObligedException e) {
            return new Response(ResponseStatus.EXIT);
        }
    }
}