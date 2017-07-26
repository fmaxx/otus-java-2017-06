package ru.otus.l62;

import ru.otus.l62.atm.ATM;
import ru.otus.l62.atm.ATMDepartment;
import ru.otus.l62.atm.Cell;
import ru.otus.l62.atm.state.ATMState;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static ATMDepartment atmDepartment;
    public static void main(String[] args) {

        // atm 1
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(20, 3));
        cells.add(new Cell(50, 1));
        ATMState workingState = new ATM.State(cells);

        cells = new ArrayList<>();
        cells.add(new Cell(20, 3));
        cells.add(new Cell(50, 1));
        ATMState defaultStateState = new ATM.State(cells);

        ATM atm_1 = new ATM(workingState, defaultStateState);


        // atm 2
        cells = new ArrayList<>();
        cells.add(new Cell(1, 10));
        cells.add(new Cell(5, 10));
        cells.add(new Cell(10, 10));
        cells.add(new Cell(50, 10));
        cells.add(new Cell(100, 10));
        workingState = new ATM.State(cells);

        cells = new ArrayList<>();
        cells.add(new Cell(1, 10));
        cells.add(new Cell(5, 10));
        cells.add(new Cell(10, 10));
        cells.add(new Cell(50, 10));
        cells.add(new Cell(100, 10));
        defaultStateState = new ATM.State(cells);

        ATM atm_2 = new ATM(workingState, defaultStateState);

        // atm department
        List<ATM> atms = new ArrayList<>();
        atms.add(atm_1);
        atms.add(atm_2);
        atmDepartment = new ATMDepartment(atms);

        // get test 100$
        atmDepartment.getCashFromFirsrtForTestsing(20);

        atmDepartment.getBalance(new ATMDepartment.BalanceHandler() {
            @Override
            public void onResult(int balance) {
                System.out.println("new balance: " + balance);

                // reset to default state
                atmDepartment.reset();

                atmDepartment.getBalance(new ATMDepartment.BalanceHandler() {
                    @Override
                    public void onResult(int balance) {
                        System.out.println("default balance: " + balance);
//                        reset();
                    }
                });
            }
        });
    }
}
