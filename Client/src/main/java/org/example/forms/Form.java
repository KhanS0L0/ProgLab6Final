package org.example.forms;

import org.example.exception.InvalidFormException;

/**
 * Абстрактный класс для пользовательских форм ввода
 * @param <T> класс формы
 */
public abstract class Form<T>{
    public abstract T build() throws InvalidFormException;
}
