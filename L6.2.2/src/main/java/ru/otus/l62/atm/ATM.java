package ru.otus.l62.atm;

import ru.otus.l62.atm.state.ATMState;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ATM implements Iterable<ATM> {
    private Cell first;
    private ATM nextATM;
    private ATMState defaultState;

    public ATM(ATMState workingState, ATMState defaultState) {
        this.defaultState = defaultState;
        setState(workingState);
    }

    public void reset() {
        defaultState.apply(this);
    }

    public void setState(ATMState state){
        state.apply(this);
    }

    private void setCells(List<Cell> cells){
        //unlink old cells
        if(first != null){
            Iterator<Cell> iterator = cells.iterator();
            Cell cell_1 = iterator.next();
            while (iterator.hasNext()){
                Cell cell_2 = iterator.next();
                cell_1.setNext(null);
                cell_1 = cell_2;
            }
        }

        Collections.sort(cells);
        first = cells.get(0);
        link(cells);
    }

    public boolean withdraw(int requested){
        return first.withdraw(requested);
    }

    public int getBalance(){
        Iterator<Cell> iterator = first.iterator();
        int balance = 0;
        while (iterator.hasNext()){
            balance += iterator.next().getBalance();
        }
        return balance;
    }

    public void setNext(ATM next) {
        nextATM = next;
    }

    private void link(List<Cell> cells) {
        Iterator<Cell> iterator = cells.iterator();
        Cell cell_1 = iterator.next();
        while (iterator.hasNext()){
            Cell cell_2 = iterator.next();
            cell_1.setNext(cell_2);
            cell_1 = cell_2;
        }
    }

    @Override
    public Iterator<ATM> iterator() {
        return new Iterator<ATM>() {

            ATM current = ATM.this;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public ATM next() {
                ATM before = current;
                current = current.nextATM;
                return before;
            }
        };
    }



    public static class State implements ATMState {

        private List<Cell> cells;

        public State(List<Cell> cells ) {
            this.cells = cells;
        }

        @Override
        public void apply(ATM atm) {
            atm.setCells(cells);
        }
    }
}
