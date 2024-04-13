import java.sql.*;

public class Trainer {
    private static final String url = "jdbc:postgresql://localhost:5432/Project V2";
    private static final String user = "postgres";
    private static final String password = "admin";

    private Member member = new Member();

    public Trainer(){
    }

    /**
     * This method creates a trainer.
     * @param firstName
     * @param lastName
     * @param phone
     * @param email
     * @param pass
     */
    public void createTrainer(String firstName, String lastName, String phone, String email, String pass) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "insert into trainers (first_name, last_name, phone, email, password) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phone);
            statement.setString(4, email);
            statement.setString(5, pass);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Trainer created successfully.");
            } else {
                System.out.println("Failed to create trainer.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns true if trainer logs in successfully and false if not.
     * @param email
     * @param pass
     * @return
     */
    public boolean trainerLogin(String email, String pass) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select 1 from trainers where email = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, pass);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method displays available rooms.
     */
    public void displayAvailableRooms(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * from rooms where is_booked = false";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                int memberId = resultSet.getInt("member_id");
                int trainerId = resultSet.getInt("trainer_id");
                java.sql.Time startTime = resultSet.getTime("start_time");
                java.sql.Time endTime = resultSet.getTime("end_time");
                boolean isBooked = resultSet.getBoolean("is_booked");

                System.out.println("Room ID: " + roomId);
                System.out.println("Member ID: " + memberId);
                System.out.println("Trainer ID: " + trainerId);
                System.out.println("Start Time: " + startTime);
                System.out.println("End Time: " + endTime);
                System.out.println("Is Booked: " + isBooked + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method books a room for a trainer.
     * @param roomId
     * @param trainerId
     */
    public void bookRoom(int roomId, int trainerId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "update rooms set trainer_id = ?, is_booked = true where room_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, trainerId);
            statement.setInt(2, roomId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Room booked successfully.");
            } else {
                System.out.println("Failed to book room.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method allows the trainer to create a session when they are available.
     * @param trainerId
     * @param sessionDate
     * @param startTime
     * @param endTime
     */
    public void createSession(int trainerId, Date sessionDate, Time startTime, Time endTime){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "insert into sessions (trainer_id, start_time, end_time, is_booked, session_date) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, trainerId);
            statement.setTime(2, startTime);
            statement.setTime(3, endTime);
            statement.setBoolean(4, false);
            statement.setDate(5, sessionDate);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Session created successfully.");
            } else {
                System.out.println("Failed to create session.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method removes a session.
     * @param sessionId
     */
    public void removeSession(int sessionId) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "delete from sessions where session_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, sessionId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Session removed successfully.");
            } else {
                System.out.println("Failed to remove session. Session ID might not exist.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays trainers sessions.
     * @param trainerId
     */
    public void displayTrainerSessions(int trainerId) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * from sessions where trainer_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, trainerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int sessionId = resultSet.getInt("session_id");
                int memberId = resultSet.getInt("member_id");
                Time startTime = resultSet.getTime("start_time");
                Time endTime = resultSet.getTime("end_time");
                boolean isBooked = resultSet.getBoolean("is_booked");

                System.out.println("Session ID: " + sessionId);
                System.out.println("Trainer ID: " + trainerId); // Use the provided trainerId
                System.out.println("Member ID: " + memberId);
                System.out.println("Start Time: " + startTime);
                System.out.println("End Time: " + endTime);
                System.out.println("Is Booked: " + isBooked + "\n");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays a members profile.
     * @param firstName
     * @param lastName
     */
    public void displayMemberProfile(String firstName, String lastName) {

            // Retrieve member ID based on first and last name
            int memberId = getMemberId(firstName, lastName);
            if (memberId == -1) {
                System.out.println("Member not found.");
                return;
            }

            // Display health metrics
            member.displayHealthMetrics(memberId);

            // Display goals
            member.displayGoals(memberId);

            // Display achievements
            member.displayAchievements(memberId);
    }

    /**
     * This method returns a members id from first and last name.
     * @param firstName
     * @param lastName
     * @return
     */
    private int getMemberId(String firstName, String lastName) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select member_id from members where first_name = ? and last_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
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

    /**
     * This method displays a trainers classes.
     * @param trainerId
     */
    public void displayTrainerClasses(int trainerId) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * from classes where trainer_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, trainerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int classId = resultSet.getInt("class_id");
                int trainerIdResult = resultSet.getInt("trainer_id");
                String classDate = resultSet.getString("class_date");
                System.out.println("Class ID: " + classId + ", Trainer ID: " + trainerIdResult + ", Class Date: " + classDate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method removes a class.
     * @param classId
     */
    public void removeClass(int classId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "delete from classes where class_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, classId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Class removed successfully.");
            } else {
                System.out.println("No class found with the specified ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This trainer displays the rooms a member has booked.
     * @param trainerId
     */
    public void displayTrainerBookedRooms(int trainerId) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * from rooms where trainer_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, trainerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                Time startTime = resultSet.getTime("start_time");
                Time endTime = resultSet.getTime("end_time");
                System.out.println("Room ID: " + roomId + ", Start Time: " + startTime + ", End Time: " + endTime);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays unProcessed bills.
     */
    public void displayUnprocessedBills() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * from bills where is_processed = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, false); // Filter for unprocessed bills
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt("bill_id");
                int memberId = resultSet.getInt("member_id");
                int staffId = resultSet.getInt("staff_id");
                String date = resultSet.getString("date");
                int dollarAmount = resultSet.getInt("dollar_amount");
                boolean isPaid = resultSet.getBoolean("is_paid");

                System.out.println("Bill ID: " + billId);
                System.out.println("Member ID: " + memberId);
                System.out.println("Staff ID: " + staffId);
                System.out.println("Date: " + date);
                System.out.println("Amount: " + dollarAmount);
                System.out.println("Is Paid: " + isPaid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method processes a bill.
     * @param billId
     */
    public void processBill(int billId) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "update bills set is_processed = ? where bill_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, true); // Set is_processed to TRUE
            statement.setInt(2, billId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Bill with ID " + billId + " has been processed.");
            } else {
                System.out.println("No bill found with ID " + billId + ".");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
