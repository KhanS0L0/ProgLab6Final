package org.example.utils;

import org.example.collection.CollectionManager;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exception.ConnectionErrorException;
import org.example.exception.OpeningServerException;
import org.example.managers.FileManager;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class Server {
    private final int port;
    private final ReaderWriter console;
    private final RequestHandler requestHandler;
    private final FileManager fileManager;
    private final CollectionManager collectionManager;
    private ServerSocketChannel serverSocketChannel;
    private SocketChannel socketChannel;

    BufferedInputStream bf = new BufferedInputStream(System.in);
    BufferedReader scanner = new BufferedReader(new InputStreamReader(bf));

    public Server(int port, RequestHandler handler, FileManager fileManager, CollectionManager collectionManager) {
        this.port = port;
        this.collectionManager = collectionManager;
        this.console = new BlankConsole();
        this.requestHandler = handler;
        this.fileManager = fileManager;
    }

    public void run() {
        try {
            openServerSocket();
            while (true) {
                try {
                    if (scanner.ready()) {
                        String line = scanner.readLine();
                        if (line.equals("save")) {
                            fileManager.saveCollectionToFile(collectionManager);
                            System.out.println("Коллекция сохранена");
                        }
                    }
                } catch (IOException ignored) {
                }

                try (SocketChannel clientSocket = connectToClient()) {
                    if (!processClientRequest(clientSocket)) break;
                } catch (ConnectionErrorException | SocketTimeoutException ignored) {
                } catch (IOException exception) {
                    console.write("Произошла ошибка при попытке завершить соединение с клиентом!");
                    System.out.println("Произошла ошибка при попытке завершить соединение с клиентом!");
                }
            }
            stop();
            System.out.println("Соединение закрыто");
        } catch (OpeningServerException e) {
            console.write("Сервер не может быть запущен");
            System.out.println("Сервер не может быть запущен");
        } catch (NullPointerException ex) {
            System.out.println("OMG!!! SEEMS LIKE WE FUCKED UP ^_^");
        }
    }

    private void openServerSocket() throws OpeningServerException {
        try {
            SocketAddress socketAddress = new InetSocketAddress(port);
            System.out.println("Создан сокет");
            serverSocketChannel = ServerSocketChannel.open();
            System.out.println("Создан канал");
            serverSocketChannel.bind(socketAddress);
            System.out.println("Открыт канал сокета");
        } catch (IllegalArgumentException exception) {
            console.write("Порт '" + port + "' находится за пределами возможных значений!");
            System.out.println("Порт находится за пределами возможных значений");
            throw new OpeningServerException();
        } catch (IOException exception) {
            System.out.println("Произошла ошибка при попытке использовать порт");
            console.write("Произошла ошибка при попытке использовать порт '" + port + "'!");
            throw new OpeningServerException();
        }
    }

    private SocketChannel connectToClient() throws ConnectionErrorException, SocketTimeoutException {
        try {
            console.write("Прослушивание порта '" + port + "'...");
            System.out.println("Прослушивание порта '" + port + "'...");
            socketChannel = serverSocketChannel.socket().accept().getChannel();
            console.write("Соединение с клиентом успешно установлено.");
            System.out.println("Соединение с клиентом успешно установлено.");
            return socketChannel;
        } catch (SocketTimeoutException exception) {
            throw new SocketTimeoutException();
        } catch (IOException exception) {
            System.out.println("Произошла ошибка при соединении с клиентом!");
            throw new ConnectionErrorException();
        }
    }

    private boolean processClientRequest(SocketChannel clientSocket) {
        Request userRequest = null;
        Response responseToUser = null;
        try (
                ObjectInputStream clientReader = new ObjectInputStream(clientSocket.socket().getInputStream());
                ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.socket().getOutputStream())
        ){
            System.out.println("Открыты потоки ввода вывода");
            do {
                userRequest = (Request) clientReader.readObject();
                System.out.println("Получен запрос с командой" + userRequest.getCommandName() + " " + userRequest);
                console.write(userRequest.toString());
                responseToUser = requestHandler.handle(userRequest);
                clientWriter.writeObject(responseToUser);
                System.out.println("Отправлен ответ" + responseToUser);
                clientWriter.flush();
            } while (true);
        } catch (ClassNotFoundException exception) {
            console.write("Произошла ошибка при чтении полученных данных!");
            System.out.println("Произошла ошибка при чтении полученных данных!");
        } catch (InvalidClassException | NotSerializableException exception) {
            console.write("Произошла ошибка при отправке данных на клиент!");
            System.out.println("Произошла ошибка при отправке данных на клиент!");
        } catch (IOException exception) {
            if (userRequest == null) {
                console.write("Непредвиденный разрыв соединения с клиентом!");
                System.out.println("Непредвиденный разрыв соединения с клиентом!");
            } else {
                console.write("Клиент успешно отключен от сервера!");
                System.out.println("Клиент успешно отключен от сервера!");
            }
        }
        return true;
    }

    private void stop() {
        class ClosingSocketException extends Exception {
        }
        try {
            if (socketChannel == null) throw new ClosingSocketException();
            socketChannel.close();
            serverSocketChannel.close();
            System.out.println("все соединения закрыты");
        } catch (ClosingSocketException exception) {
            console.write("Невозможно завершить работу еще не запущенного сервера!");
            System.out.println("Невозможно завершить работу еще не запущенного сервера!");
        } catch (IOException exception) {
            console.write("Произошла ошибка при завершении работы сервера!");
            System.out.println("Произошла ошибка при завершении работы сервера!");
        }
    }
}
