package org.example.console;

public class BlankConsole implements ReaderWriter {

    @Override
    public String readLine() {
        return null;
    }

    @Override
    public void write(String text) {

    }

    @Override
    public void printError(String a) {

    }

    @Override
    public String getValidatedValue(String message) {
        return null;
    }
}
