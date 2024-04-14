import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.text.ParseException;
import java.util.Date;

public class Model {

    private static final String url = "jdbc:postgresql://localhost:5432/Project V2";
    private static final String user = "postgres";
    private static final String password = "admin";
    private Member member = new Member();
    private Trainer trainer = new Trainer();
    private Staff staff = new Staff();
    Scanner scanner = new Scanner(System.in);
    private int currentMemberId;
    private int currentStaffId;
    private int currentTrainerId;

    public Model(){
    }

    public void start(){
        System.out.println("\nWelcome to our Health and Fitness Club Management System!");
        System.out.println("At any point, enter a 0 to exit system.\n");
        System.out.println("Select user:");
        System.out.println("1. Member");
        System.out.println("2. Trainer");
        System.out.println("3. Staff");
        System.out.print("Enter (1, 2, 3, 0): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case(1):
                handleMemberLogin();
                break;
            case(2):
                handleTrainerLogin();
                break;
            case(3):
                handleStaffLogin();
                break;
            case(0):
                System.exit(1);
            default:
                System.out.println("Invalid choice");
                start();
                break;
        }
        System.out.println("\n*-------------------------------------------------------------*\n");
    }

    public void handleMemberLogin(){
        System.out.println("Hello Member.\n");
        System.out.println("Select option:");
        System.out.println("1. Login");
        System.out.println("2. Register (For new users)");
        System.out.print("Enter (1, 2, 0): ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n*-------------------------------------------------------------*\n");
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

                currentMemberId = getMemberId(email);
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleMemberFunctions();
                break;
            case(2):
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();

                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.nextLine();

                System.out.print("Enter email: ");
                String email2 = scanner.nextLine();

                System.out.print("Enter birthday (YYYY-MM-DD): ");
                String birthday = scanner.nextLine();
                java.sql.Date formattedBirthday = convertDate(birthday);

                System.out.print("Enter join date (YYYY-MM-DD): ");
                String joinDate = scanner.nextLine();
                java.sql.Date formattedJointDate = convertDate(joinDate);

                System.out.print("Create password: ");
                String pass2 = scanner.nextLine();

                member.createMember(firstName, lastName, phoneNumber, email2, formattedBirthday, formattedJointDate, pass2);
                currentMemberId = getMemberId(email2);
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleMemberFunctions();
                break;
            case(0):
                System.exit(1);
            default:
                System.out.println("Invalid choice");
                handleMemberLogin();
                break;
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
        System.out.println("\n*-------------------------------------------------------------*\n");
        switch(choice){
            case(1):
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String pass = scanner.nextLine();
                if(!trainer.trainerLogin(email,pass)){
                    System.out.println("Incorrect email or password.");
                    handleTrainerLogin();
                }
                currentTrainerId = getTrainerId(email);
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleTrainerFunctions();
                break;
            case(2):
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();

                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.nextLine();

                System.out.print("Enter email: ");
                String email2 = scanner.nextLine();

                System.out.print("Create password: ");
                String pass2 = scanner.nextLine();

                trainer.createTrainer(firstName, lastName, phoneNumber, email2, pass2);
                currentTrainerId = getTrainerId(email2);
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleTrainerFunctions();
                break;
            case(0):
                System.exit(1);
            default:
                System.out.println("Invalid choice");
                handleTrainerLogin();
                break;
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
        System.out.println("\n*-------------------------------------------------------------*\n");
        switch(choice){
            case(1):
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String pass = scanner.nextLine();
                if(!staff.staffLogin(email,pass)){
                    System.out.println("Incorrect email or password.");
                    handleStaffLogin();
                }
                currentStaffId = getStaffId(email);
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleStaffFunctions();
                break;
            case(2):
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();

                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.nextLine();

                System.out.print("Enter email: ");
                String email2 = scanner.nextLine();

                System.out.print("Create password: ");
                String pass2 = scanner.nextLine();

                staff.createStaff(firstName, lastName, phoneNumber, email2, pass2);
                currentStaffId = getStaffId(email2);
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleStaffFunctions();
                break;
            case(0):
                System.exit(1);
            default:
                System.out.println("Invalid choice");
                handleStaffLogin();
                break;
        }
    }

    public void handleMemberFunctions(){
        System.out.println("Select option:");
        System.out.println("1. View Dashboard");
        System.out.println("2. Health Metrics");
        System.out.println("3. Goals");
        System.out.println("4. Achievements");
        System.out.println("5. Routines");
        System.out.println("6. Training Sessions");
        System.out.println("7. Classes");
        System.out.println("8. Update Personal Info");
        System.out.println("9. Bills");
        System.out.println("10. Rooms");
        System.out.print("Enter (1, 2, 3, 4, 5, 6, 7, 8, 9, 0): ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n*-------------------------------------------------------------*\n");
        switch (choice) {
            case 0:
                System.exit(1);
                break;
            case 1:
                member.displayHealthMetrics(currentMemberId);
                member.displayGoals(currentMemberId);
                member.displayAchievements(currentMemberId);
                System.out.println("Enrolled Sessions: ");
                member.displayEnrolledSessions(currentMemberId);
                System.out.println("Enrolled Classes: ");
                member.displayMemberClasses(currentMemberId);
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleMemberFunctions();
                break;
            case 2:
                System.out.println("Select option:");
                System.out.println("1. Create Health Metrics");
                System.out.println("2. Update Health Metrics");
                System.out.println("3. Display Health Metrics");
                System.out.print("Enter (1, 2, 3, 0): ");

                int choice2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch (choice2){
                    case 1:
                        System.out.print("Enter weight (kg): ");
                        int weight = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter height (cm): ");
                        int height = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter resting heart rate (bpm): ");
                        int heart_rate = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter bmi: ");
                        int bmi = scanner.nextInt();
                        scanner.nextLine();

                        member.inputHealthMetrics(currentMemberId, weight, height, heart_rate, bmi);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 2:
                        System.out.print("Enter weight (kg): ");
                        int weight2 = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter height (cm): ");
                        int height2 = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter resting heart rate (bpm): ");
                        int heart_rate2 = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter bmi: ");
                        int bmi2 = scanner.nextInt();
                        scanner.nextLine();

                        member.updateHealthMetrics(currentMemberId, weight2, height2, heart_rate2, bmi2);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 3:
                        member.displayHealthMetrics(currentMemberId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleMemberFunctions();
                        break;
                }
                break;
            case 3:
                System.out.println("Select option:");
                System.out.println("1. Create Goal");
                System.out.println("2. Remove Goal");
                System.out.println("3. Display Goals");
                System.out.print("Enter (1, 2, 3, 0): ");
                int choice3 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice3){
                    case 1:
                        System.out.print("Enter goal: ");
                        String goal = scanner.nextLine();
                        member.addGoal(currentMemberId, goal);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 2:
                        member.displayGoals(currentMemberId);
                        System.out.print("Enter the id of the goal to remove: ");
                        int goalId = scanner.nextInt();
                        scanner.nextLine();
                        member.removeGoal(goalId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 3:
                        member.displayGoals(currentMemberId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleMemberFunctions();
                        break;
                }
                break;
            case 4:
                System.out.println("Select option:");
                System.out.println("1. Create Achievement");
                System.out.println("2. Display Achievements");
                System.out.print("Enter (1, 2, 0): ");
                int choice4 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice4){
                    case 1:
                        System.out.print("Enter Achievement: ");
                        String achievement = scanner.nextLine();
                        member.addAchievement(currentMemberId, achievement);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 2:
                        member.displayAchievements(currentMemberId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleMemberFunctions();
                        break;
                }
                break;
            case 5:
                System.out.println("Select option:");
                System.out.println("1. Create Routine");
                System.out.println("2. Display Routines");
                System.out.print("Enter (1, 2, 0): ");
                int choice5 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice5){
                    case 1:
                        System.out.print("Enter day of the week: ");
                        String day = scanner.nextLine();
                        System.out.print("Enter Routine: ");
                        String routine = scanner.nextLine();
                        member.createRoutine(currentMemberId, day, routine);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 2:
                        member.displayRoutines(currentMemberId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleMemberFunctions();
                        break;
                }
                break;
            case 6:
                System.out.println("Select option:");
                System.out.println("1. Join Session");
                System.out.println("2. Display Enrolled Sessions");
                System.out.print("Enter (1, 2, 0): ");
                int choice6 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice6){
                    case 1:
                        member.displayAvailableSessions();
                        System.out.print("Enter ID of the session you want to join. Or -1 for none: ");
                        int sessionId = scanner.nextInt();
                        scanner.nextLine();
                        if (sessionId == -1){
                            System.out.println("\n*-------------------------------------------------------------*\n");
                            handleMemberFunctions();
                        }
                        member.bookSession(sessionId, currentMemberId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 2:
                        member.displayEnrolledSessions(currentMemberId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleMemberFunctions();
                        break;
                }
                break;
            case 7:
                System.out.println("Select option:");
                System.out.println("1. Join Class");
                System.out.println("2. Display Enrolled Classes");
                System.out.print("Enter (1, 2, 0): ");
                int choice7 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice7) {
                    case 1:
                        member.displayAvailableClasses();
                        System.out.print("Enter ID of the class you want to join. Or -1 for none: ");
                        int classId = scanner.nextInt();
                        scanner.nextLine();
                        if (classId == -1) {
                            System.out.println("\n*-------------------------------------------------------------*\n");
                            handleMemberFunctions();
                        }
                        member.enrollInClass(classId, currentMemberId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 2:
                        member.displayMemberClasses(currentMemberId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleMemberFunctions();
                        break;
                }
                break;
            case 8:
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();

                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.nextLine();

                System.out.print("Enter email: ");
                String email2 = scanner.nextLine();

                System.out.print("Enter birthday (YYYY-MM-DD): ");
                String birthday = scanner.nextLine();
                java.sql.Date formattedBirthday = convertDate(birthday);

                System.out.print("Enter join date (YYYY-MM-DD): ");
                String joinDate = scanner.nextLine();
                java.sql.Date formattedJointDate = convertDate(joinDate);

                System.out.print("Create password: ");
                String pass2 = scanner.nextLine();

                member.updatePersonalInfo(currentMemberId, firstName, lastName, phoneNumber, email2, formattedBirthday, formattedJointDate, pass2);
                currentMemberId = getMemberId(email2);
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleMemberFunctions();
                break;
            case 9:
                System.out.println("Select option:");
                System.out.println("1. Pay Bill");
                System.out.println("2. Display Bills");
                System.out.print("Enter (1, 2, 0): ");
                int choice9 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice9) {
                    case 1:
                        member.displayUnpaidBills(currentMemberId);
                        System.out.print("Enter ID of the bill you want to pay. Or -1 for none: ");
                        int billId = scanner.nextInt();
                        scanner.nextLine();
                        if (billId == -1) {
                            System.out.println("\n*-------------------------------------------------------------*\n");
                            handleMemberFunctions();
                        }
                        member.payBill(billId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 2:
                        member.displayUnpaidBills(currentMemberId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleMemberFunctions();
                        break;
                }
                break;
            case 10:
                System.out.println("Select option:");
                System.out.println("1. Book Room");
                System.out.println("2. Display Booked Rooms");
                System.out.print("Enter (1, 2, 0): ");
                int choice10 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice10){
                    case 1:
                        member.displayAvailableRooms();
                        System.out.print("Enter ID of the room you want to book. Or -1 for none: ");
                        int roomId = scanner.nextInt();
                        scanner.nextLine();
                        if (roomId == -1){
                            System.out.println("\n*-------------------------------------------------------------*\n");
                            handleMemberFunctions();
                        }
                        member.bookRoom(roomId, currentMemberId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 2:
                        member.displayMemberBookedRooms(currentMemberId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleMemberFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleMemberFunctions();
                        break;
                }

                break;
            default:
                System.out.println("Invalid choice");
                handleMemberFunctions();
                break;
        }
    }

    public void handleTrainerFunctions(){
        System.out.println("Select option:");
        System.out.println("1. Search Member");
        System.out.println("2. Classes");
        System.out.println("3. Sessions");
        System.out.println("4. Rooms");
        System.out.print("Enter (1, 2, 3, 4, 0): ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n*-------------------------------------------------------------*\n");
        switch (choice) {
            case 0:
                System.exit(1);
            case 1:
                System.out.print("Enter members first name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter members last name: ");
                String lastName = scanner.nextLine();

                trainer.displayMemberProfile(firstName, lastName);
                System.out.println("\n*-------------------------------------------------------------*\n");
                handleTrainerFunctions();
                break;
            case 2:
                System.out.println("Select option:");
                System.out.println("1. Remove Class");
                System.out.println("2. Display Classes");
                System.out.print("Enter (1, 2, 0): ");
                int choice2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice2) {
                    case 1:
                        trainer.displayTrainerClasses(currentTrainerId);
                        System.out.print("Enter ID of the class you want to remove. Or -1 for none: ");
                        int classId = scanner.nextInt();
                        scanner.nextLine();
                        if (classId == -1) {
                            System.out.println("\n*-------------------------------------------------------------*\n");
                            handleTrainerFunctions();
                        }
                        trainer.removeClass(classId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleTrainerFunctions();
                        break;
                    case 2:
                        trainer.displayTrainerClasses(currentTrainerId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleTrainerFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleTrainerFunctions();
                        break;
                }
                break;
            case 3:
                System.out.println("Select option:");
                System.out.println("1. Create Session");
                System.out.println("2. Remove Session");
                System.out.println("3. Display Sessions");
                System.out.print("Enter (1, 2, 3, 0): ");
                int choice3 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice3){
                    case 1:
                        System.out.print("Enter session date (YYYY-MM-DD): ");
                        String sessionDate = scanner.nextLine();
                        java.sql.Date formattedSessionDate = convertDate(sessionDate);

                        System.out.print("Enter start time (HH:mm:ss): ");
                        String startTime = scanner.nextLine();
                        java.sql.Time formattedStartTime = convertTime(startTime);

                        System.out.print("Enter end time (HH:mm:ss): ");
                        String endTime = scanner.nextLine();
                        java.sql.Time formattedEndTime = convertTime(endTime);

                        trainer.createSession(currentTrainerId, formattedSessionDate, formattedStartTime, formattedEndTime);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleTrainerFunctions();
                        break;
                    case 2:
                        trainer.displayTrainerSessions(currentTrainerId);
                        System.out.print("Enter the id of the session to remove: ");
                        int sessionId = scanner.nextInt();
                        scanner.nextLine();
                        trainer.removeSession(sessionId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleTrainerFunctions();
                        break;
                    case 3:
                        trainer.displayTrainerSessions(currentTrainerId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleTrainerFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleTrainerFunctions();
                        break;
                }
                break;
            case 4:
                System.out.println("Select option:");
                System.out.println("1. Book Room");
                System.out.println("2. Display Booked Rooms");
                System.out.print("Enter (1, 2, 0): ");
                int choice4 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice4){
                    case 1:
                        trainer.displayAvailableRooms();
                        System.out.print("Enter ID of the room you want to book. Or -1 for none: ");
                        int roomId = scanner.nextInt();
                        scanner.nextLine();
                        if (roomId == -1){
                            System.out.println("\n*-------------------------------------------------------------*\n");
                            handleTrainerFunctions();
                        }
                        trainer.bookRoom(roomId, currentTrainerId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleTrainerFunctions();
                        break;
                    case 2:
                        trainer.displayTrainerBookedRooms(currentTrainerId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleTrainerFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleTrainerFunctions();
                        break;
                }
                break;
            default:
                System.out.println("Invalid choice");
                handleTrainerFunctions();
                break;
        }
    }

    public void handleStaffFunctions(){
        System.out.println("Select option:");
        System.out.println("1. Rooms");
        System.out.println("2. Equipment");
        System.out.println("3. Classes");
        System.out.println("4. Billing");
        System.out.print("Enter (1, 2, 3, 4, 0): ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n*-------------------------------------------------------------*\n");
        switch (choice) {
            case 0:
                System.exit(1);
                break;
            case 1:
                System.out.println("Select option:");
                System.out.println("1. Create Room Booking");
                System.out.println("2. Remove Room Booking");
                System.out.println("3. View Room Bookings");
                System.out.print("Enter (1, 2, 3, 0): ");
                int choice1 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice1){
                    case 1:
                        System.out.print("Enter start time (HH:mm:ss): ");
                        String startTime = scanner.nextLine();
                        java.sql.Time formattedStartTime = convertTime(startTime);

                        System.out.print("Enter end time (HH:mm:ss): ");
                        String endTime = scanner.nextLine();
                        java.sql.Time formattedEndTime = convertTime(endTime);

                        staff.createRoomBooking(formattedStartTime, formattedEndTime);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 2:
                        staff.displayRoomBookings();
                        System.out.print("Enter the id of the booking to remove: ");
                        int roomId = scanner.nextInt();
                        scanner.nextLine();
                        staff.removeRoomBooking(roomId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 3:
                        staff.displayRoomBookings();
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleStaffFunctions();
                        break;
                }
                break;
            case 2:
                System.out.println("Select option:");
                System.out.println("1. Add Equipment");
                System.out.println("2. Remove Equipment");
                System.out.println("3. Display Equipment");
                System.out.println("4. Update Equipment Maintenance");
                System.out.print("Enter (1, 2, 3, 4, 0): ");
                int choice2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice2) {
                    case 1:
                        System.out.print("Enter equipment type: ");
                        String equipmentType = scanner.nextLine();

                        System.out.print("Enter last maintenance date (YYYY-MM-DD): ");
                        String maintenanceDate = scanner.nextLine();
                        java.sql.Date formattedMaintenanceDate = convertDate(maintenanceDate);

                        staff.addEquipment(equipmentType, formattedMaintenanceDate);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 2:
                        staff.displayAllEquipment();
                        System.out.print("Enter ID of the equipment to remove. Or -1 for none: ");
                        int equipmentId = scanner.nextInt();
                        scanner.nextLine();
                        if (equipmentId == -1){
                            System.out.println("\n*-------------------------------------------------------------*\n");
                            handleStaffFunctions();
                        }
                        staff.removeEquipment(equipmentId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 3:
                        staff.displayAllEquipment();
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 4:
                        staff.displayAllEquipment();
                        System.out.print("Enter ID of the equipment to update. Or -1 for none: ");
                        int equipmentId2 = scanner.nextInt();
                        scanner.nextLine();
                        if (equipmentId2 == -1){
                            System.out.println("\n*-------------------------------------------------------------*\n");
                            handleStaffFunctions();
                        }

                        System.out.print("Enter new maintenance date (YYYY-MM-DD): ");
                        String newMaintenanceDate = scanner.nextLine();
                        java.sql.Date formattedNewMaintenanceDate = convertDate(newMaintenanceDate);

                        staff.updateEquipmentMaintenance(equipmentId2, formattedNewMaintenanceDate);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                }
                break;
            case 3:
                System.out.println("Select option:");
                System.out.println("1. Create Class");
                System.out.println("2. Remove Class");
                System.out.println("3. Display Classes");
                System.out.println("4. Update Class");

                System.out.print("Enter (1, 2, 3, 4, 0): ");
                int choice3 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice3) {
                    case 1:
                        System.out.print("Enter trainers first name: ");
                        String firstName = scanner.nextLine();

                        System.out.print("Enter trainers last name: ");
                        String lastName = scanner.nextLine();

                        int trainerId = staff.getTrainerId(firstName, lastName);

                        System.out.print("Enter class date (YYYY-MM-DD): ");
                        String classDate = scanner.nextLine();
                        java.sql.Date formattedClassDate = convertDate(classDate);

                        System.out.print("Enter start time (HH:mm:ss): ");
                        String startTime = scanner.nextLine();
                        java.sql.Time formattedStartTime = convertTime(startTime);

                        System.out.print("Enter end time (HH:mm:ss): ");
                        String endTime = scanner.nextLine();
                        java.sql.Time formattedEndTime = convertTime(endTime);

                        staff.createClass(trainerId, formattedClassDate,formattedStartTime,formattedEndTime);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 2:
                        staff.displayClasses();
                        System.out.print("Enter the id of the class to remove, or -1 for none: ");
                        int classId = scanner.nextInt();
                        scanner.nextLine();

                        if (classId == -1){
                            System.out.println("\n*-------------------------------------------------------------*\n");
                            handleStaffFunctions();
                        }

                        staff.removeClass(classId);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 3:
                        staff.displayClasses();
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 4:
                        staff.displayClasses();
                        System.out.print("Enter the id of the class to update, or -1 for none: ");
                        int classId2 = scanner.nextInt();
                        scanner.nextLine();

                        if (classId2 == -1){
                            System.out.println("\n*-------------------------------------------------------------*\n");
                            handleStaffFunctions();
                        }

                        System.out.print("Enter new class date (YYYY-MM-DD): ");
                        String classDate2 = scanner.nextLine();
                        java.sql.Date formattedClassDate2 = convertDate(classDate2);

                        System.out.print("Enter new start time (HH:mm:ss): ");
                        String startTime2 = scanner.nextLine();
                        java.sql.Time formattedStartTime2 = convertTime(startTime2);

                        System.out.print("Enter new end time (HH:mm:ss): ");
                        String endTime2 = scanner.nextLine();
                        java.sql.Time formattedEndTime2 = convertTime(endTime2);

                        staff.updateClassTime(classId2, formattedClassDate2,formattedStartTime2, formattedEndTime2);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleStaffFunctions();
                        break;
                }
                break;
            case 4:
                System.out.println("Select option:");
                System.out.println("1. Create Bill");
                System.out.println("2. Display Bills");
                System.out.println("3. Process Bill");

                System.out.print("Enter (1, 2, 3, 0): ");
                int choice4 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("\n*-------------------------------------------------------------*\n");
                switch(choice4) {
                    case 1:
                        System.out.print("Enter members first name: ");
                        String firstName = scanner.nextLine();

                        System.out.print("Enter members last name: ");
                        String lastName = scanner.nextLine();

                        int memberId = trainer.getMemberId(firstName, lastName);

                        System.out.print("Enter bill date (YYYY-MM-DD): ");
                        String billDate = scanner.nextLine();
                        java.sql.Date formattedBillDate = convertDate(billDate);

                        System.out.print("Enter dollar amount: ");
                        int dollarAmount = scanner.nextInt();
                        scanner.nextLine();

                        staff.createBill(memberId, currentStaffId, formattedBillDate, dollarAmount);
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 2:
                        staff.displayBills();
                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;
                    case 3:
                        staff.displayUnprocessedBills();

                        System.out.print("Enter the id of the bill to process, or -1 for none: ");
                        int billId = scanner.nextInt();
                        scanner.nextLine();

                        if (billId == -1){
                            System.out.println("\n*-------------------------------------------------------------*\n");
                            handleStaffFunctions();
                        }

                        staff.processBill(billId);

                        System.out.println("\n*-------------------------------------------------------------*\n");
                        handleStaffFunctions();
                        break;

                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Invalid choice");
                        handleStaffFunctions();
                        break;
                }
                break;

            default:
                System.out.println("Invalid choice");
                handleMemberFunctions();
                break;
        }
    }

    public java.sql.Date convertDate(String input){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedDate = dateFormat.parse(input);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            return sqlDate;

        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
        }
        return null;
    }

    public java.sql.Time convertTime(String input){

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            java.util.Date parsedDate = dateFormat.parse(input);
            Time time = new Time(parsedDate.getTime());

            return time;
        } catch (ParseException e) {
            System.out.println("Invalid time format. Please enter time in HH:mm:ss format.");
        }
        return null;
    }

    public int getMemberId(String email){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select member_id from members where email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("member_id");
            } else {
                return -1; // Member not found
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getTrainerId(String email){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select trainer_id from trainers where email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("trainer_id");
            } else {
                return -1; // Member not found
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public int getStaffId(String email){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select staff_id from staff where email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("staff_id");
            } else {
                return -1; // Member not found
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }



}
