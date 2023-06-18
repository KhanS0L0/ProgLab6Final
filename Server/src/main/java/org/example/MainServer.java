package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.collection.CollectionManager;
import org.example.command.common.HelpCommand;
import org.example.command.common.InfoCommand;
import org.example.command.common.ExitCommand;
//import org.example.command.common.ExecuteScriptCommand;
import org.example.command.create.AddCommand;
import org.example.command.read.HeadCommand;
import org.example.command.read.ShowCommand;
import org.example.command.read.CountGreaterThanHeartCommand;
import org.example.command.read.FilterByNameCommand;
import org.example.command.delete.ClearCommand;
import org.example.command.delete.RemoveLowerCommand;
import org.example.command.delete.RemoveGreaterCommand;
import org.example.command.delete.RemoveByIdCommand;
import org.example.command.delete.RemoveAnyByChapterCommand;
import org.example.command.update.UpdateIdCommand;
import org.example.managers.CommandManager;
import org.example.managers.FileManager;
import org.example.utils.ReaderWriter;
import org.example.utils.BlankConsole;
import org.example.utils.LocalDateTimeAdapter;
import org.example.utils.RequestHandler;
import org.example.utils.Server;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class MainServer extends Thread {
    public static final int PORT = 6086;
    public static final String PATH_TO_DATA = "/Users/hansultan/Desktop/ProgLab6Final/Server/data.json";
    private static final ReaderWriter console = new BlankConsole();
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager(new ArrayList<>());
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        FileManager fileManager = new FileManager(PATH_TO_DATA, gson);
        try {
            System.out.println("Создание объектов");
            fileManager.loadCollectionFromFile(collectionManager);
            System.out.println("Создание объектов успешно завершено. Было добавлено " + collectionManager.getCollection().size() + " элементов");
        } catch (IOException e) {
            console.write("До свидания!");
            System.out.println("Ошибка во времени создания объектов");
        }

        CommandManager commandManager = new CommandManager(fileManager, collectionManager);
        commandManager.addCommand(List.of(
                new HelpCommand(commandManager),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager),
                new UpdateIdCommand(collectionManager),
                new RemoveByIdCommand(collectionManager),
                new ClearCommand(collectionManager),
//                new ExecuteScriptCommand(),
                new ExitCommand(),
                new HeadCommand(collectionManager),
                new RemoveGreaterCommand(collectionManager),
                new RemoveLowerCommand(collectionManager),
                new RemoveAnyByChapterCommand(collectionManager),
                new CountGreaterThanHeartCommand(collectionManager),
                new FilterByNameCommand(collectionManager)
        ));
        System.out.println("Создан объект менеджера команд");
        RequestHandler requestHandler = new RequestHandler(commandManager);
        System.out.println("Создан объект обработчика запросов");
        Server server = new Server(PORT, requestHandler, fileManager, collectionManager);
        System.out.println("Создан объект сервера");
        server.run();
    }
}
