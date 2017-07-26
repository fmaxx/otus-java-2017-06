package ru.otus.l62.atm.commands;

import ru.otus.l62.atm.ATM;
import ru.otus.l62.atm.ATMDepartment;

import java.util.Iterator;

public class BalanceCommand implements Command {

    private ATM first;
    private ATMDepartment.BalanceHandler handler;

    public BalanceCommand(ATM first, ATMDepartment.BalanceHandler handler) {
        this.first = first;
        this.handler = handler;
    }

    @Override
    public void execute() {
        if(handler != null){
            Iterator<ATM> iterator = first.iterator();
            int balance = 0;
            while (iterator.hasNext()){
                balance += iterator.next().getBalance();
            }
            handler.onResult(balance);
        }
        handler = null;
        first = null;

    }
}
