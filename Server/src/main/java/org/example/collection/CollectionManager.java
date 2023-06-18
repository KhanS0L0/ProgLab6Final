package org.example.collection;

import org.example.entity.SpaceMarine;
import org.example.entity.Chapter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The class that implements collection related methods
 */
public class CollectionManager implements Serializable {
    private List<SpaceMarine> collection;
    private transient final LocalDateTime initDateTime = LocalDateTime.now();

    public CollectionManager() {
    }

    public CollectionManager(List<SpaceMarine> collection) {
        this.collection = collection;
    }

    public List<SpaceMarine> getCollection() {
        return collection;
    }

    public void setCollection(List<SpaceMarine> spaceMarineCollection) {
        this.collection = spaceMarineCollection;
    }

    public SpaceMarine getById(int id) {
        return getCollection().stream()
                .filter(it -> it.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public String info() {
        return "Тип коллекции:" + collection.getClass().getSimpleName() + "\n"
                + "Время инициализации:" + initDateTime + "\n"
                + "Количество элементов:" + collection.size() + "\n";
    }

    public boolean containsById(int id) {
        return collection.stream().filter(it -> it.getId().equals(id)).findFirst().orElse(null) != null;
    }

    public String getAllElementsAsString() {
        if (collection.isEmpty()) {
            System.out.println("В коллекции нет объектов, доступных для просмотра");
        }
        StringBuilder information = new StringBuilder();
        for (SpaceMarine spaceMarine : collection) {
            information.append(spaceMarine.toString()).append("\n");
        }
        return information.toString();
    }

    public void addSpaceMarine(SpaceMarine spaceMarine) {
        spaceMarine.setId(generateId(this.getCollection()));
        collection.add(spaceMarine);
        System.out.println("Добавлен объект в коллекцию " + spaceMarine);
    }

    public void updateById(SpaceMarine newSpaceMarine, int id) {
        for (SpaceMarine spaceMarine : collection) {
            if (spaceMarine.getId().equals(id)) {
                spaceMarine.setName(newSpaceMarine.getName());
                spaceMarine.setCoordinates(newSpaceMarine.getCoordinates());
                spaceMarine.setHealth(newSpaceMarine.getHealth());
                spaceMarine.setHeartCount(newSpaceMarine.getHeartCount());
                spaceMarine.setChapter(newSpaceMarine.getChapter());
                spaceMarine.setCategory(newSpaceMarine.getCategory());
                spaceMarine.setWeaponType(newSpaceMarine.getWeaponType());
            }
        }
    }

    public void removeSpaceMarine(SpaceMarine spaceMarine) {
        collection.remove(spaceMarine);
        System.out.println("Элемент c id " + spaceMarine.getId() + " удалён");
    }

    public void removeSpaceMarines(Collection<SpaceMarine> collection) {
        this.collection.removeAll(collection);
    }

    public void clear() {
        collection.clear();
        System.out.println("Коллекция очищена");
    }

    public void removeGreater(Integer heartCount) {
        Iterator<SpaceMarine> iterator = collection.iterator();
        while (iterator.hasNext()) {
            SpaceMarine spaceMarine = iterator.next();
            if (spaceMarine.getHeartCount() > heartCount) {
                iterator.remove();
                System.out.println("Элемент удален из коллекции: " + spaceMarine.getName());
            } else if (!iterator.hasNext()) {
                System.out.println("Нет элементов с таким же уровнем здоровья");
            }
        }
    }

    public void removeLower(Integer heartCount) {
        Iterator<SpaceMarine> iterator = collection.iterator();
        while (iterator.hasNext()) {
            SpaceMarine spaceMarine = iterator.next();
            if (spaceMarine.getHeartCount() < heartCount) {
                iterator.remove();
                System.out.println("Элемент удален из коллекции: " + spaceMarine.getName());
            } else if (!iterator.hasNext()) {
                System.out.println("Нет элементов с таким же уровнем здоровья");
            }
        }
    }

    public void removeById(int ID) {
        Iterator<SpaceMarine> iterator = collection.iterator();
        while (iterator.hasNext()) {
            SpaceMarine spaceMarine = iterator.next();
            if (spaceMarine.getId().equals(ID)) {
                iterator.remove();
                System.out.println("Элемент удален из коллекции");
            } else if (!iterator.hasNext()) {
                System.out.println("Элемента с таким ID не существует");
            }
        }
    }

    public void removeAnyByChapter(Chapter chapter) {
        Iterator<SpaceMarine> iterator = collection.iterator();
        while (iterator.hasNext()) {
            SpaceMarine spaceMarine = iterator.next();
            if (spaceMarine.getChapter().equals(chapter)) {
                iterator.remove();
                System.out.println("Элемент удален из коллекции");
            } else if (!iterator.hasNext()) {
                System.out.println("Элемента с таким Chapter: " + chapter.toString() + " не существует");
            }
        }
    }

    public int countByHeartCount(int heartCount){
        int result = 0;
        for(SpaceMarine spaceMarine : collection){
            if (spaceMarine.getHeartCount() > heartCount)
                result++;
        }
        return result;
    }

    public String filterByName(String name) {
        StringBuilder filterObjects = new StringBuilder();
        for (SpaceMarine spaceMarine : collection) {
            var one = spaceMarine.getName().trim().toLowerCase();
            var two = name.trim().toLowerCase();
            if (one.contains(two)) {
                filterObjects.append(spaceMarine).append("\n");
            }
        }
        if (filterObjects.length() == 0) {
            System.out.println("Нет ни одного экземпляра с таким полем");
        }
        return filterObjects.toString();
    }

    private int generateId(List<SpaceMarine> spaceMarines) {
        int maxId = 0;
        for (SpaceMarine spaceMarine : spaceMarines) {
            if (spaceMarine.getId() > maxId) {
                maxId = spaceMarine.getId();
            }
        }
        return maxId + 1;
    }

}





