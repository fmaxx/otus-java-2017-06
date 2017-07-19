package ru.otus.l61.atm;

import ru.otus.l61.atm.errors.AmountError;
import ru.otus.l61.atm.errors.LimitException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by maximfirsov on 18/07/2017.
 */
public class MyATM implements ATM {

    private HashMap<Integer, Long> map = new HashMap<>();
    private long limit = 0;

    @Override
    public long getLimit() {
        return limit;
    }

    @Override
    public int[] getCash(long value) throws LimitException, AmountError {
        if (value > limit) {
            throw new LimitException(
                    "requested value [" + value + "] more than ATM limit [" + limit + "]");
        }

        // getting sorted keys
        ArrayList<Integer> keys = new ArrayList<>();
        for (int key : map.keySet()) {
            keys.add(key);
        }
        Collections.sort(keys);

        // TODO: make the algorithm

        return null;
    }

    @Override
    public void load(int banknote, int count) {
        long currentAmount = banknote * count;
        if (map.containsKey(banknote)) {
            currentAmount += map.get(banknote);
        }
        map.put(banknote, currentAmount);
        limit = calculateLimit();
    }

    private long calculateLimit() {
        long result = 0;
        for (long amount : map.values()) {
            result += amount;
        }
        return result;
    }
}
