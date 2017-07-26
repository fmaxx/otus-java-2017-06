package ru.otus.l62.atm.state;

import ru.otus.l62.atm.ATM;

public interface ATMState {
    void apply(ATM atm);
}
