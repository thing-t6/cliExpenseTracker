import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int numberOfExpense = 0;
        double totalExpense = 0;
        LocalDate date = LocalDate.now();
        DecimalFormat df = new DecimalFormat("0.00");

        // Created an array list of expenses class
        ArrayList<Expenses> expenseList = new ArrayList<>();

        // initialized an object of income
        Income income = new Income();

        // method calling to initialize income
        System.out.println("--------Details of income and expenses!--------");
        income.setInitialIncome(scanner);
        double initialIncome = income.getInitialIncome();

        // get number of expenses a user wants to add
        while (true) {
            try {
                System.out.print("How many expense details are you going to enter: ");
                numberOfExpense = scanner.nextInt();
                scanner.nextLine();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Please enter valid details");
                scanner.nextLine();
            }
        }

        // add the name and expense to the arraylist we created but we assigned object rather than a simple value
        for(int i = 0; i < numberOfExpense; i++){
            try{
                System.out.printf("Enter the title of your expenses no-%d : ", i + 1);
                String expenseName = scanner.nextLine();
                System.out.print("Enter the amount spent :$ ");
                double expenseAmount = scanner.nextDouble();
                scanner.nextLine();
                expenseList.add(new Expenses(expenseName, expenseAmount));

            }catch (InputMismatchException e){
                System.out.println("Please enter valid details");
                scanner.nextLine();
                i--;

            }
        }

        // get the total expense by iterating over expenses of expenses arraylist of object
        for (Expenses expenses : expenseList) {
            totalExpense += expenses.getExpense();
        }

        // print details of each object inside expenseList
        System.out.println("");
        for(int i = 0; i < expenseList.size(); i++){
            System.out.println("Expense no " +  (i + 1) + ": " + expenseList.get(i).getName());
            System.out.println("Expense amount: $" + expenseList.get(i).getExpense());
        }
        System.out.println("");

        // method to get income after all expenses decremente
        income.incomeAfterExpense(totalExpense);
        System.out.println("Total expense: $" + df.format(totalExpense));
        double finalAmount = income.getFinalAmount();
        System.out.println("Final amount after decrement of expenses is: $" + df.format(finalAmount));
        System.out.println("");

        String filePath = "src/test.txt";

        while(true) {
            System.out.print("Do you want to add these expenses to a file? (Y/N): ");
            String decisionWriteFile = scanner.nextLine().toUpperCase();
            try {
                if (decisionWriteFile.equals("Y")) {
                    writeFile(expenseList, totalExpense, finalAmount, initialIncome, filePath, date);
                    break;
                } else if (decisionWriteFile.equals("N")) {
                    System.out.println("File not written");
                    break;
                } else {
                    System.out.println("Please enter valid ans");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter valid ans");
            }
        }

        while (true) {
            System.out.print("Do you want to see the content of the file? (Y/N): ");
            String decisionShowFileContent = scanner.nextLine().toUpperCase();

            try {
                if (decisionShowFileContent.equals("Y")) {
                    showFile(filePath);
                    break;
                } else if (decisionShowFileContent.equals("N")) {
                    System.out.println("Ok!");
                    break;
                } else {
                    System.out.println("Please enter valid ans");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter valid ans");
            }
        }

    }

    public static void writeFile(ArrayList<Expenses> expenseList,double totalExpense, double finalAmount, double income,String filePath,LocalDate date){
            try(FileWriter writer = new FileWriter(filePath, true)) {
                writer.write("--------Date : " + date + "--------\n");
                for(int i = 0; i < expenseList.size(); i++) {
                    String textContent = expenseList.get(i).getName() + " -> $" + expenseList.get(i).getExpense();
                    writer.write(textContent + "\n");
                    System.out.println(textContent);
                }
                writer.write("Income :$" + income + "\n"
                        + "Total Expense :$" + totalExpense + "\n" + "Final amount :$" + finalAmount + "\n");
                writer.write("\n");
                System.out.println("File has been written");
            } catch (FileNotFoundException e){
                System.out.println("Could not locate file location");
            }
            catch (IOException e) {
                System.out.println("Could not write file");
            }

        }

    public static void showFile(String filePath){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            System.out.println("");
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }catch (FileNotFoundException e){
            System.out.println("Could not locate file");
        }catch (IOException e){
            System.out.println("Something went wrong");
        }
    }
}
