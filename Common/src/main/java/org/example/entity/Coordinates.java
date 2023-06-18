package org.example.entity;

import java.io.Serializable;
import java.util.Objects;

public record Coordinates(double x, double y) implements Serializable {

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x(), x()) == 0 && Double.compare(that.y(), y()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x(), y());
    }
}
