package ru.otus.l61;

import ru.otus.l61.atm.ATM;
import ru.otus.l61.atm.MyATM;
import ru.otus.l61.atm.errors.AmountError;
import ru.otus.l61.atm.errors.LimitException;

import java.io.IOException;

/**
 * Created by maximfirsov on 06/07/2017.
 *
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("--- run classes ---");

        ATM atm = new MyATM();
        atm.load(10, 1);
        atm.load(60, 1);
        atm.load(100, 1);

        System.out.println("limit: " + atm.getLimit());

        try {
            int[] result = atm.getCash(120);
            System.out.println("result : " + result);
        } catch (LimitException e) {
            e.printStackTrace();
        } catch (AmountError amountError) {
            amountError.printStackTrace();
        }
    }
}
