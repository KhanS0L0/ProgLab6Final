package org.example.utils;

import org.example.entity.AstartesCategory;
import org.example.entity.Weapon;

public interface Readable {
    String readName();
    Double readCoordinateX();
    Double readCoordinateY();
    Float readHealth();
    Integer readHeartCount();
    AstartesCategory readCategory();
    Weapon readWeapon();
    Long readChapterMarinesCount();
    String readChapterName();
    String readChapterParentLegion();
    String readChapterWorld();
}
