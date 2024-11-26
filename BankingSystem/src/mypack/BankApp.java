package mypack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        Bank b = new Bank();

        while (true) {
            System.out.println("Enter 1 for account creation");
            System.out.println("Enter 2 to check balance");
            System.out.println("Enter 3 for deposit");
            System.out.println("Enter 4 for Withdraw");
            System.out.println("Enter 5 to change PIN");
            System.out.println("Enter 6 to Search Account");
            System.out.println("Enter 7 to exit");

            int option = sc.nextInt();
            sc.nextLine(); 

            switch (option) {
                case 1:
                    createAccount(sc, b);
                    break;
                case 2:
                    checkBalance(sc, b);
                    break;
                case 3:
                	System.out.println("Enter the account number");
                	int acc = sc.nextInt();
                	System.out.println("Enter the amount to deposit");
                	int bal = sc.nextInt();
                	int oldBalance = BankDAO.getBal(acc);
                	int newBalance = oldBalance + bal;
                	int upbal = BankDAO.updateBalance(acc,newBalance);
                	if(upbal>0) {
                		System.out.println("deposit added and Updated balance");
                		
                	}
                    break;
                case 4:
                	System.out.println("Enter the account number");
                	int ac = sc.nextInt();
                	System.out.println("Enter the amount to withdraw");
                	int balance = sc.nextInt();
                	int old = BankDAO.getBal(ac);
                	int with = old - balance;
                	int withdraw = BankDAO.withdraw(ac,with);
                	
                	if(withdraw>0) {
                		System.out.println("The amount is debited");
                	}else {
                		System.out.println("You dont have sufficent balance");
                	}
                	
                	break;
                case 5:
                    changePin(sc, b);
                    break;
                case 6:
                	System.out.println("Enter the Account Number");
                	int account = sc.nextInt();
                	 String state = BankDAO.searchacc(account);
                	sc.nextLine();
                	System.out.println(state);
                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner sc, Bank b) throws SQLException, ClassNotFoundException {
        System.out.println("Enter the account number:");
        int acc = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.println("Enter the name:");
        String name = sc.nextLine();

        System.out.println("Enter the PIN:");
        String pin = sc.nextLine();

        System.out.println("Enter the balance:");
        int bal = sc.nextInt();
        sc.nextLine(); // Consume newline

        b.setAccno(acc);
        b.setName(name);
        b.setBalance(bal);  // Corrected spelling to `balance`
        b.setPin(pin);

        boolean res = BankDAO.createAccount(b);
        if (res) {
            System.out.println("Hi " + name + ", your account has been created successfully.");
        } else {
            System.out.println("Account creation failed. Please try again.");
        }
    }

    private static void checkBalance(Scanner sc, Bank b) throws SQLException, ClassNotFoundException {
        System.out.println("Enter the account number:");
        int acc = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.println("Enter the account PIN:");
        String pin = sc.nextLine();

        b.setAccno(acc);
        b.setPin(pin);
        
        Bank user = BankDAO.getDetails(b);
        
        if (user != null) {
            System.out.println("Name: " + user.getName());
            System.out.println("Balance: " + user.getBalance());
        } else {
            System.out.println("Invalid account details. Please try again.");
        }
    }
   
    private static void changePin(Scanner sc, Bank b) throws SQLException, ClassNotFoundException {
        System.out.println("Enter the account number:");
        int accNo = sc.nextInt();
        sc.nextLine(); 

        System.out.println("Enter the current PIN:");
        String currentPin = sc.nextLine();

        b.setAccno(accNo);
        b.setPin(currentPin);

        Bank user = BankDAO.getDetails(b);
        if (user != null) {
            System.out.println("Enter the new PIN:");
            String newPin = sc.nextLine();
            int res = BankDAO.updatePin(accNo, newPin);
            if (res > 0) {
                System.out.println("PIN updated successfully.");
            } else {
                System.out.println("PIN update failed.");
            }
        } else {
            System.out.println("No account found with the given details.");
        }
    }
}
