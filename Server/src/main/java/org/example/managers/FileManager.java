package org.example.managers;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.example.collection.CollectionManager;
import org.example.entity.SpaceMarine;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class FileManager {

    private String fileName;

    private Gson gsonConverter;

    public FileManager() {}

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public FileManager(String fileName, Gson gsonConverter) {
        this.fileName = fileName;
        this.gsonConverter = gsonConverter;
    }

    public void saveCollectionToFile(CollectionManager collectionManager) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName))) {
            var string = gsonConverter.toJson(collectionManager.getCollection());
            writer.write(string);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCollectionFromFile(CollectionManager collectionManager) throws IOException {
        // загрузка коллекции из файла
        try {
            // Открываем файл и считываем его содержимое в строку
            String json = Files.readString(Path.of(this.fileName), StandardCharsets.UTF_8);

            // Преобразуем JSON-строку в список объектов SpaceMarine
            List<SpaceMarine> spaceMarines = gsonConverter.fromJson(json, new TypeToken<List<SpaceMarine>>() {
            }.getType());

            // Очищаем текущую коллекцию и добавляем загруженные объекты SpaceMarine
            for (SpaceMarine spaceMarine : spaceMarines) {
                collectionManager.getCollection().add(spaceMarine);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка при чтении JSON: " + e.getMessage());
        }
    }
}



