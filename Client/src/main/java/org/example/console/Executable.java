package org.example.console;

import org.example.exception.CommandRuntimeException;
import org.example.exception.ExitObligedException;
import org.example.exception.IllegalArgumentsException;

/**
 * Интерфейс для исполняемых команд
 */
public interface Executable {
    void execute(String args) throws CommandRuntimeException, ExitObligedException, IllegalArgumentsException;
}
