import java.sql.*;
import java.util.Scanner;

public class Model {

    private static final String url = "jdbc:postgresql://localhost:5432/Project V2";
    private static final String user = "postgres";
    private static final String password = "admin";
    private Member member = new Member();
    private Trainer trainer = new Trainer();
    private Staff staff = new Staff();
    Scanner scanner = new Scanner(System.in);

    public Model(){
    }

    public void start(){
        System.out.println("Welcome to our Health and Fitness Club Management System!");
        System.out.println("At any point, enter a 0 to exit system.\n");
        System.out.println("Select user:");
        System.out.println("1. Member");
        System.out.println("2. Trainer");
        System.out.println("3. Staff");
        System.out.print("Enter (1, 2, 3, 0): ");
        int choice = scanner.nextInt();
        switch(choice){
            case(1):
                handleMemberLogin();
            case(2):
                handleTrainerLogin();
            case(3):
                handleStaffLogin();
            case(0):
                System.exit(1);
        }
        System.out.println("\n*-------------------------------------------------------------*\n");
    }

    public void handleMemberLogin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello Member.\n");
        System.out.println("Select option:");
        System.out.println("1. Login");
        System.out.println("2. Register (For new users)");
        System.out.print("Enter (1, 2, 0): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case(1):
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String pass = scanner.nextLine();
                if(!member.memberLogin(email,pass)){
                    System.out.println("Incorrect email or password.");
                    handleMemberLogin();
                }
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleMemberFuncions();
            case(2):

            case(0):
                System.exit(1);
        }

    }

    public void handleTrainerLogin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello Trainer.\n");
        System.out.println("Select option:");
        System.out.println("1. Login");
        System.out.println("2. Register (For new users)");
        System.out.print("Enter (1, 2, 0): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case(1):
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String pass = scanner.nextLine();
                if(!trainer.trainerLogin(email,pass)){
                    System.out.println("Incorrect email or password.");
                    handleMemberLogin();
                }
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleTrainerFuncions();
            case(2):

            case(0):
                System.exit(1);
        }
    }

    public void handleStaffLogin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello Staff.\n");
        System.out.println("Select option:");
        System.out.println("1. Login");
        System.out.println("2. Register (For new users)");
        System.out.print("Enter (1, 2, 0): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case(1):
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String pass = scanner.nextLine();
                if(!staff.staffLogin(email,pass)){
                    System.out.println("Incorrect email or password.");
                    handleMemberLogin();
                }
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleStaffFuncions();
            case(2):

            case(0):
                System.exit(1);
        }
    }

    public void handleMemberFuncions(){

    }

    public void handleTrainerFuncions(){

    }

    public void handleStaffFuncions(){

    }


}
