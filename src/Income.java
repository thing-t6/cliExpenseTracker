import java.util.InputMismatchException;
import java.util.Scanner;

public class Income{
    private double initialIncome;
    private double finalAmount;

    // sets income
    void setInitialIncome(Scanner scanner){
        try{
            System.out.print("Whats your total income today :$ ");
            this.initialIncome = scanner.nextDouble();
            scanner.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Please enter valid amount");
        }
    }

    // method to get the decremented total income after expense
    void incomeAfterExpense(double totalExpense){
        this.finalAmount = initialIncome - totalExpense;
    }

    // getters
    double getFinalAmount(){
        return this.finalAmount;
    }

    // getters
    double getInitialIncome(){
        return this.initialIncome;
    }

}
