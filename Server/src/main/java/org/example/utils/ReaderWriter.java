package org.example.utils;

public interface ReaderWriter {
    Long readLong();
    String readLine();
    void write(String text);
    String getNotEmptyValue(String message);
}
