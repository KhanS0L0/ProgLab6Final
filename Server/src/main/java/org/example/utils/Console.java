package org.example.utils;

import java.util.Scanner;

public class Console implements ReaderWriter {

    @Override
    public Long readLong() {
        Scanner scanner = new Scanner(System.in);
        return Long.valueOf(scanner.nextLine().trim());
    }

    @Override
    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    @Override
    public void write(String text) {
        System.out.print(text);
    }

    @Override
    public String getNotEmptyValue(String message) {
        this.write(message);
        while (true) {
            String userPrint = readLine();
            if (!userPrint.isEmpty() && !userPrint.isBlank()) {
                return userPrint;
            }
        }
    }
}

