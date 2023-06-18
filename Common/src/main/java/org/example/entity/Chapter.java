package org.example.entity;

import java.io.Serializable;
import java.util.Objects;

public class Chapter implements Serializable {

    private String name;
    private String parentLegion;
    private Long marinesCount;
    private String world;

    public Chapter(){}

    public Chapter(String name, String parentLegion, Long marinesCount, String world) {
        this.name = name;
        this.parentLegion = parentLegion;
        this.marinesCount = marinesCount;
        this.world = world;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "name='" + name + '\'' +
                ", parentLegion='" + parentLegion + '\'' +
                ", marinesCount=" + marinesCount +
                ", world='" + world + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Chapter other = (Chapter) obj;
        return Objects.equals(name, other.name) &&
                Objects.equals(world, other.world) &&
                Objects.equals(marinesCount, other.marinesCount) &&
                Objects.equals(parentLegion, other.parentLegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getParentLegion(), getMarinesCount(), getWorld());
    }

    public String getName() {
        return name;
    }

    public String getParentLegion() {
        return parentLegion;
    }

    public int getMarinesCount() {
        return Math.toIntExact(marinesCount);
    }

    public String getWorld() {
        return world;
    }

    public Chapter setName(String name) {
        this.name = name;
        return this;
    }

    public Chapter setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
        return this;
    }

    public Chapter setMarinesCount(Long marinesCount) {
        this.marinesCount = marinesCount;
        return this;
    }

    public Chapter setWorld(String world) {
        this.world = world;
        return this;
    }
}

