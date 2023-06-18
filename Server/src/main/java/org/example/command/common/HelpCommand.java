package org.example.command.common;

import org.example.command.BaseCommand;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.dto.ResponseStatus;
import org.example.exception.IllegalArgumentsException;
import org.example.managers.CommandManager;

public class HelpCommand extends BaseCommand {
    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        super("help", "help: вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        var helpInfo = new StringBuilder();
        commandManager.getCommands().forEach(it -> helpInfo.append(it.getDescription()).append("\n"));
        return new Response(
                ResponseStatus.OK,
                helpInfo.append("\n").toString()
        );
    }
}
