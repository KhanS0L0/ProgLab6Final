package org.example.console;

public interface ReaderWriter {
    String readLine();
    void write(String text);
    void printError(String text);
    String getValidatedValue(String message);
}
