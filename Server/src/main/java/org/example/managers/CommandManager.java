package org.example.managers;

import org.example.collection.CollectionManager;
import org.example.command.BaseCommand;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.example.command.CollectionEditor;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exception.CommandRuntimeException;
import org.example.exception.ExitObligedException;
import org.example.exception.IllegalArgumentsException;
import org.example.exception.NoSuchCommandException;

/**
 * Командный менеджер.
 * Реализует паттерн программирования Command
 */
public class CommandManager{
    /**
     * Поле для хранения комманд в виде Имя-Комманда
     */
    private final HashMap<String, BaseCommand> commands = new HashMap<>();
    private final FileManager fileManager;
    private final CollectionManager collectionManager;

    public CommandManager(FileManager fileManager, CollectionManager collectionManager) {
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    public void addCommand(BaseCommand command){
        this.commands.put(command.getName(), command);
        System.out.println("Добавлена команда" + command);
    }
    public void addCommand(Collection<BaseCommand> commands){
        this.commands.putAll(commands.stream()
                .collect(Collectors.toMap(BaseCommand::getName, s -> s)));
        System.out.println("Добавлены команды:\n-----------------");
        commands.forEach(System.out::println);
        System.out.println("-----------------");
    }
    public Collection<BaseCommand> getCommands(){
        return commands.values();
    }

    /**
     * Выполняет команду
     * @param request - запрос клиента
     * @throws NoSuchCommandException такая команда отсутствует
     * @throws IllegalArgumentsException неверные аргументы команды
     * @throws CommandRuntimeException команда выдала ошибку при исполнении
     * @throws ExitObligedException команда вызвала выход из программы
     */
    public Response execute(Request request) throws NoSuchCommandException, IllegalArgumentsException, CommandRuntimeException, ExitObligedException {
        BaseCommand command = commands.get(request.getCommandName());
        if (command == null) {
            System.out.println("Нет такой команды" + request.getCommandName());
            throw new NoSuchCommandException();
        }
        Response response = command.execute(request);
        System.out.println("Выполнение команды\n" +  response);
        if (command instanceof CollectionEditor) {
            System.out.println("Файл обновлен");
            fileManager.saveCollectionToFile(collectionManager);
        }
        return response;
    }
}