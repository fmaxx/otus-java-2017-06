package ru.otus.l62.atm;

import ru.otus.l62.atm.commands.BalanceCommand;
import ru.otus.l62.atm.commands.Command;

import java.util.Iterator;
import java.util.List;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ATMDepartment {

    private Queue<Command> commandQueue = new ConcurrentLinkedQueue<>();

    private final ATM first;
    private final List<ATM> atms;


    public ATMDepartment(List<ATM> atms) {
        this.atms = atms;
        first = atms.get(0);
        chain(atms);
    }

    private void chain(List<ATM> atms) {
        Iterator<ATM> iterator = atms.iterator();
        ATM a1 = iterator.next();
        while (iterator.hasNext()){
            ATM a2 = iterator.next();
            a1.setNext(a2);
            a1 = a2;
        }
    }

    public boolean getCashFromFirsrtForTestsing(int cash){
        if(first != null){
            return first.withdraw(cash);
        }
        return  false;
    }

    public void getBalance(BalanceHandler handler){
        addCommand(new BalanceCommand(first, handler));
        processCommands();
    }

    private void processCommands() {
        Command command = commandQueue.poll();
        if(command != null){
            command.execute();
        }
    }

    private void addCommand(Command command) {
        commandQueue.add(command);
    }

    public void reset() {
        for (ATM atm : atms) {
            atm.reset();
        }
    }

    public interface BalanceHandler {
        void onResult(int balance);
    }
}
