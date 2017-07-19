package ru.otus.l61.atm.errors;

/**
 * Created by maximfirsov on 18/07/2017.
 */
public class AmountError extends Exception {
    public AmountError(String message) {
        super(message);
    }
}
