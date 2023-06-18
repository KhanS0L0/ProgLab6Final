package org.example.command.common;

import org.example.command.BaseCommand;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.IllegalArgumentsException;

/**
 * exit : terminate the program (without saving to a file)
 */
public class ExitCommand extends BaseCommand {
    public ExitCommand() {
        super("exit", "exit: завершить программу (без сохранения в файл)");
    }
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        return new Response(ResponseStatus.EXIT);
    }
}



