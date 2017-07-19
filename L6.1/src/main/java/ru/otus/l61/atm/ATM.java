package ru.otus.l61.atm;

import ru.otus.l61.atm.errors.AmountError;
import ru.otus.l61.atm.errors.LimitException;

/**
 * Created by maximfirsov on 18/07/2017.
 */
public interface ATM {

    long getLimit();

    int[] getCash(long value) throws LimitException, AmountError;

    void load(int banknote, int count);
}
