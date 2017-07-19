package ru.otus.l61.atm.errors;

/**
 * Created by maximfirsov on 18/07/2017.
 */
public class LimitException extends Exception {
    public LimitException(String message) {
        super(message);
    }
}
