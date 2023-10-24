import java.util.Scanner;
import java.util.InputMismatchException;

interface ATM {
    void menu();

    void withdraw();

    void deposit();

    void transfer();
    
    void checkbalance();

    void quit();

}

class AtmMachine implements ATM {
    private int userId;
    private float balance = 0;
    private int PIN = 1234;
    private int pin;
    Scanner input = new Scanner(System.in);
    AtmMachine(int userId, int pin) {
        this.userId = userId;
        this.pin = pin;
    }

    @Override
    public void menu() {
        if (pin == PIN) {
            System.out.println("Select An Option");
            System.out.println("A: Withdraw");
            System.out.println("B: Deposit");
            System.out.println("C: Transfer");
            System.out.println("D: checkBalance");
            System.out.println("E: Quit");
            char enteredOption = input.next().charAt(0);
            if (enteredOption == 'A') {
                withdraw();
            } else if (enteredOption == 'B') {
                deposit();
            } else if (enteredOption == 'C') {
                transfer();
            } else if (enteredOption == 'D') {
                checkbalance();
            }else if(enteredOption=='E'){
                quit();

            }
            else {
                System.out.println("Enter a valid Input");
            }

        }
        else{
            System.out.println("Incorrect Pin. Please Enter your pin correctly.");
        }

    }

    @Override
    public void withdraw() {
        System.out.println("Enter the amount to withdraw: ");
        int amount = input.nextInt();
        if (balance >= amount) {
            balance -= amount;
            System.out.println(" successfully from "+userId);
        } else {
            System.out.println("Insufficient Funds");
        }
        menu();
    }

    @Override
    public void deposit() {
        System.out.println("Enter the amount to Deposit: ");
        int amount = input.nextInt();
        balance += amount;
        System.out.println("Amount deposited successfully to the "+userId);
        menu();

    }

    @Override
    public void transfer() {
        System.out.println("Amount to be Transfered to the Account Number: ");
        int accountNumber2 = input.nextInt();
        System.out.println("Enter the amount to transfer: ");
        int amount = input.nextInt();
        if (balance >= amount) {
            balance = balance - amount;
            String msg = String.format("%d Transferred from %d to %d successfully", amount,userId ,accountNumber2);
            System.out.println(msg);
            System.out.println("Remaining Balance: "+balance);
        } else {
            System.out.println("Insufficient funds");
            System.out.println("Transaction failed");
        }menu();
    }
    @Override
    public void checkbalance(){
        System.out.println("Remaining balance: "+balance);
        menu();
    }

    @Override
    public void quit() {
        System.out.println("Are you sure you want to cancel the transaction(yes/no)");
        String option = input.next();
        if (option.equalsIgnoreCase("yes")) {

            System.out.println("Transaction is cancelled");
            System.exit(0);
        } else {
            menu();
        }
    }

}

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int        userId     = 0;
        int        pin        = 0;
        try{
            System.out.println("Enter UserId: ");
            userId = input.nextInt();
            System.out.println("Enter Your Pin");
            pin        = input.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Please Enter a valid UserId and Pin");
            input.close();
            return;
        }
        AtmMachine atmMachine = new AtmMachine(userId, pin);
            atmMachine.menu();
            input.close();
    }
}



