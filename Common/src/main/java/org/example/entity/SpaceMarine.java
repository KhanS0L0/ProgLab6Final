package org.example.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class SpaceMarine implements Serializable {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private float health;
    private Integer heartCount;
    private AstartesCategory category;
    private Weapon weaponType;
    private Chapter chapter;

    public SpaceMarine(){}

    public SpaceMarine(String name, Coordinates coordinates, LocalDateTime creationDate,
                       Float health, Integer heartCount, AstartesCategory category, Weapon weaponType, Chapter chapter) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.heartCount = heartCount;
        this.category = category;
        this.weaponType = weaponType;
        this.chapter = chapter;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public float getHealth() {
        return health;
    }

    public Integer getHeartCount() {
        return heartCount;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public SpaceMarine setId(Integer id) {
        this.id = id;
        return this;
    }

    public SpaceMarine setName(String name) {
        this.name = name;
        return this;
    }

    public SpaceMarine setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public SpaceMarine setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public SpaceMarine setHealth(float health) {
        this.health = health;
        return this;
    }

    public SpaceMarine setHeartCount(Integer heartCount) {
        this.heartCount = heartCount;
        return this;
    }

    public SpaceMarine setCategory(AstartesCategory category) {
        this.category = category;
        return this;
    }

    public SpaceMarine setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    public SpaceMarine setChapter(Chapter chapter) {
        this.chapter = chapter;
        return this;
    }

    @Override
    public String toString() {
        return "SpaceMarine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", health=" + health +
                ", heartCount=" + heartCount +
                ", category=" + category +
                ", weaponType=" + weaponType +
                ", chapter=" + chapter +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

//    public boolean validate() {
//        if (this.id == null ||
//                this.id < 0 ||
//                this.name == null ||
//                this.name.isEmpty() ||
//                this.coordinates == null ||
//                this.creationDate == null ||
//                this.chapter == null) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}
