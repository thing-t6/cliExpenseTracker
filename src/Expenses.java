import java.util.ArrayList;

public class Expenses {
    private String expenseName;
    private double expenseAmount;

    // constructor
    Expenses(String expenseName, double expenseAmount){
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
    }

    // getters
    String getName(){
        return expenseName;
    }

    // getters
    double getExpense(){
        return expenseAmount;
    }
}
