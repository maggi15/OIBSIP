import java.util.Scanner;

class BankAccount {
    
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 10000f;
    int transactions = 0;
    String transactionHistory = "";
    
    public void register(Scanner sc) {
        System.out.println("\nEnter your Name: ");
        this.name = sc.nextLine();
        System.out.println("\nEnter your Username: ");
        this.userName = sc.nextLine();
        System.out.println("\nEnter your Password: ");
        this.password = sc.nextLine();
        System.out.println("\nEnter your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration Successful. Please Log in to your Bank Account");
    }

    public boolean login(Scanner sc) {
        boolean isLogin = false;
        while (!isLogin) {
            System.out.println("\nEnter your username: ");
            String Username = sc.nextLine();
            if (Username.equals(userName)) {
                while (!isLogin) {
                    System.out.println("\nEnter your password: ");
                    String Password = sc.nextLine();
                    if (Password.equals(password)) {
                        System.out.println("\nLogin Successful");
                        isLogin = true;
                    } else {
                        System.out.println("\nIncorrect Password");
                    }
                }
            } else {
                System.out.println("\nUsername not found");
            }
        }
        return isLogin;
    }
    
    public void withdraw(Scanner sc) {
        System.out.println("\nEnter Amount to Withdraw: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Consume the leftover newline
        try {
            if (balance >= amount) {
                transactions++;
                balance -= amount;
                System.out.println("\nWithdrawal Successful.");
                String str = amount + " Rs Withdrawn\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nInsufficient Balance.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during withdrawal.");
        }
    }
    
    public void deposit(Scanner sc) {
        System.out.println("\nEnter Amount to Deposit: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Consume the leftover newline
        try {
            if (amount <= 10000f) {
                transactions++;
                balance += amount;
                System.out.println("\nDeposit Successful.");
                String str = amount + " Rs deposited\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nSorry. The limit is 10000.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during deposit.");
        }
    }
    
    public void transfer(Scanner sc) {
        System.out.println("\nEnter Recipient's Name: ");
        String recipient = sc.nextLine();
        System.out.println("\nEnter Amount to transfer: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Consume the leftover newline
        try {
            if (balance >= amount) {
                if (amount <= 50000f) {
                    transactions++;
                    balance -= amount;
                    System.out.println("\nSuccessfully Transferred to " + recipient);
                    String str = amount + " Rs transferred to " + recipient + "\n";
                    transactionHistory = transactionHistory.concat(str);
                } else {
                    System.out.println("\nSorry. The limit is 50000.");
                }
            } else {
                System.out.println("\nInsufficient Balance.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during transfer.");
        }
    }
    
    public void checkBalance() {
        System.out.println("\n" + balance + " Rs");
    }
    
    public void transHistory() {
        if (transactions == 0) {
            System.out.println("No Transactions happened");
        } else {
            System.out.print("\n" + transactionHistory);
        }
    }
}

public class ATMInterface {
    
    public static int takeIntegerInput(int limit, Scanner sc) {
        int input = 0;
        boolean flag = false;
        
        while (!flag) {
            try {
                input = sc.nextInt();
                sc.nextLine(); // Consume the leftover newline
                flag = true;
                
                if (flag && (input > limit || input < 1)) {
                    System.out.println("Choose a number between 1 to " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only integer value.");
                sc.next(); // Clear the invalid input
                flag = false;
            }
        }
        return input;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n********************WELCOME TO GOVARDHAN ATM INTERFACE*******************");
        System.out.println("\n1. Register \n2. Exit");
        System.out.println("Choose one option: ");
        int choose = takeIntegerInput(2, sc);
        
        if (choose == 1) {
            BankAccount b = new BankAccount();
            b.register(sc);
            while (true) {
                System.out.println("\n1. Login \n2. Exit");
                System.out.println("Enter your choice: ");
                int ch = takeIntegerInput(2, sc);
                if (ch == 1) {
                    if (b.login(sc)) {
                        System.out.println("\n********************WELCOME BACK " + b.name + "*******************");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Exit");
                            System.out.println("Enter your choice: ");
                            int c = takeIntegerInput(6, sc);
                            switch (c) {
                                case 1:
                                    b.withdraw(sc);
                                    break;
                                case 2:
                                    b.deposit(sc);
                                    break;
                                case 3:
                                    b.transfer(sc);
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transHistory();
                                    break;
                                case 6:
                                    isFinished
									= true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}