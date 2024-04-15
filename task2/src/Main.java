import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private double balance;

    public User(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

class SimpleBankingApplicationWithLogin {
    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("Welcome to Simple Banking Application!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for using Simple Banking Application!");
        scanner.close();
    }

    private static void register() {
        boolean isValidUsername = false;
        String username;
        do {
            System.out.print("Enter username (must contain at least one number): ");
            username = scanner.next();
            if (username.matches(".*\\d.*")) {
                isValidUsername = true;
            } else {
                System.out.println("Username must contain at least one number.");
            }
        } while (!isValidUsername);

        boolean isValidPassword = false;
        String password;
        do {
            System.out.print("Enter password (must be strong): ");
            password = scanner.next();
            if (isStrongPassword(password)) {
                isValidPassword = true;
            } else {
                System.out.println("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
            }
        } while (!isValidPassword);

        users.add(new User(username, password, 0));
        System.out.println("Registration successful.");
    }

    private static boolean isStrongPassword(String password) {
        // Password must be at least 8 characters long and contain at least one uppercase letter,
        // one lowercase letter, one digit, and one special character
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*")
                && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()].*");
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                bankingOperations();
                return;
            }
        }
        System.out.println("Invalid username or password.");
    }

    private static void bankingOperations() {
        System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");

        boolean logout = false;
        while (!logout) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    logout = true;
                    currentUser = null;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            currentUser.setBalance(currentUser.getBalance() + amount);
            System.out.println("Deposit of $" + amount + " successful.");
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= currentUser.getBalance()) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            System.out.println("Withdrawal of $" + amount + " successful.");
        } else if (amount > currentUser.getBalance()) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    private static void checkBalance() {
        System.out.println("Current Balance: $" + currentUser.getBalance());
    }
}
