package org.example.command;

import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exception.CommandRuntimeException;
import org.example.exception.ExitObligedException;
import org.example.exception.IllegalArgumentsException;

/**
 * Интерфейс для исполняемых команд
 */
public interface Executable {
    Response execute(Request request) throws CommandRuntimeException, ExitObligedException, IllegalArgumentsException;
}
