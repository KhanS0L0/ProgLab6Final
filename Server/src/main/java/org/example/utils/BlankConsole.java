package org.example.utils;
public class BlankConsole implements ReaderWriter{

    @Override
    public Long readLong() {
        return null;
    }

    @Override
    public String readLine() {
        return null;
    }

    @Override
    public void write(String text) {}

    @Override
    public String getNotEmptyValue(String message) {
        return null;
    }
}

