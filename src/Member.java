import java.sql.*;
public class Member {

    private static final String url = "jdbc:postgresql://localhost:5432/Project V2";
    private static final String user = "postgres";
    private static final String password = "admin";

    public Member(){

    }

    /**
     * This method add a member to the table.
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param email
     * @param birthday
     * @param joinDate
     * @param pass
     */
    public void createMember(String firstName, String lastName, String phoneNumber, String email, Date birthday, Date joinDate, String pass) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "insert into members (first_name, last_name, phone_number, email, birthday, join_date, password) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phoneNumber);
            statement.setString(4, email);
            statement.setDate(5, birthday);
            statement.setDate(6, joinDate);
            statement.setString(7, pass);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Member added successfully.");
            } else {
                System.out.println("Failed to add member.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns true if member logs in successfully and false if not.
     * @param email
     * @param pass
     * @return
     */
    public boolean memberLogin(String email, String pass) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select 1 from members where email = ? and password = ?";
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
     * This method updates a members personal info given a member ID.
     * @param memberId
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param email
     * @param birthday
     * @param joinDate
     * @param pass
     */
    public void updatePersonalInfo(int memberId, String firstName, String lastName, String phoneNumber, String email, Date birthday, Date joinDate, String pass){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "update members set first_name = ?, last_name = ?, phone_number = ?, email = ?, birthday = ?, join_date = ?, password = ? where member_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phoneNumber);
            statement.setString(4, email);
            statement.setDate(5, birthday);
            statement.setDate(6, joinDate);
            statement.setString(7, pass);
            statement.setInt(8, memberId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Member information updated successfully.");
            } else {
                System.out.println("No member found with the provided ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method lets members input their health metrics.
     * @param memberId
     * @param weightKg
     * @param heightCm
     * @param restHeartBpm
     * @param bmi
     */
    public void inputHealthMetrics(int memberId, int weightKg, int heightCm, int restHeartBpm, int bmi){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "insert into health_metrics (member_id, weight_kg, height_cm, rest_heart_bpm, bmi) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, memberId);
            statement.setInt(2, weightKg);
            statement.setInt(3, heightCm);
            statement.setInt(4, restHeartBpm);
            statement.setInt(5, bmi);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Health metrics added successfully for member with ID " + memberId);
            } else {
                System.out.println("Failed to add health metrics.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method lets members update their health metrics.
     * @param memberId
     * @param weightKg
     * @param heightCm
     * @param restHeartBpm
     * @param bmi
     */
    public void updateHealthMetrics(int memberId, int weightKg, int heightCm, int restHeartBpm, int bmi){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "update health_metrics set weight_kg = ?, height_cm = ?, rest_heart_bpm = ?, bmi = ? where member_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, weightKg);
            statement.setInt(2, heightCm);
            statement.setInt(3, restHeartBpm);
            statement.setInt(4, bmi);
            statement.setInt(5, memberId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Health metrics updated successfully for member with ID " + memberId);
            } else {
                System.out.println("No health metrics found for the provided member ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays a members health metrics.
     * @param memberId
     */
    public void displayHealthMetrics(int memberId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * from health_metrics where member_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, memberId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int weightKg = resultSet.getInt("weight_kg");
                int heightCm = resultSet.getInt("height_cm");
                int restHeartBpm = resultSet.getInt("rest_heart_bpm");
                int bmi = resultSet.getInt("bmi");

                System.out.println("Health Metrics for Member ID: " + memberId);
                System.out.println("Weight (kg): " + weightKg);
                System.out.println("Height (cm): " + heightCm);
                System.out.println("Resting Heart Rate (bpm): " + restHeartBpm);
                System.out.println("BMI: " + bmi);
            } else {
                System.out.println("No health metrics found for member with ID " + memberId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method allows memebers to add fitness goals
     * @param memberId
     * @param goalText
     */
    public void addGoal(int memberId, String goalText){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "insert into goals (member_id, goal) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, memberId);
            statement.setString(2, goalText);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Goal added successfully for member with ID " + memberId);
            } else {
                System.out.println("Failed to add goal.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays the goals of a member.
     * @param memberId
     */
    public void displayGoals(int memberId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select goal from goals where member_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, memberId);

            ResultSet resultSet = statement.executeQuery();
            System.out.println("Goals for Member ID: " + memberId);
            while (resultSet.next()) {
                String goal = resultSet.getString("goal");
                int goalId = resultSet.getInt("goal_id");
                System.out.println(goalId + ". " + goal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method removes a members goal after it is completed.
     * @param goalId
     */
    public void removeGoal(int goalId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "delete from goals where goal_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, goalId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Goal with ID " + goalId + " removed successfully.");
            } else {
                System.out.println("No goal found with ID " + goalId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method allows a member to add an achievement.
     * @param memberId
     * @param achievementText
     */
    public void addAchievement(int memberId, String achievementText){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "insert into Achievements (member_id, achievement) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, memberId);
            statement.setString(2, achievementText);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Achievement added successfully for member with ID " + memberId);
            } else {
                System.out.println("Failed to add achievement.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays a members achievements
     * @param memberId
     */
    public void displayAchievements(int memberId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT achievement FROM Achievements WHERE member_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, memberId);

            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Achievements for Member ID: " + memberId);
                while (resultSet.next()) {
                    String achievement = resultSet.getString("achievement");
                    System.out.println("- " + achievement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method allows members to create a routine for each day of the week.
     * @param memberId
     * @param dayOfWeek
     * @param routineText
     */
    public void createRoutine(int memberId, String dayOfWeek, String routineText){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "insert into routines (member_id, day_of_the_week, routine) values (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, memberId);
            statement.setString(2, dayOfWeek);
            statement.setString(3, routineText);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Routine created successfully for member with ID " + memberId + " on " + dayOfWeek);
            } else {
                System.out.println("Failed to create routine.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays a members routines.
     * @param memberId
     */
    public void displayRoutines(int memberId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select day_of_the_week, routine from routines where member_id = ? order by day_of_the_week";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, memberId);

            ResultSet resultSet = statement.executeQuery();
            System.out.println("Routines for Member ID: " + memberId);
            while (resultSet.next()) {
                String dayOfWeek = resultSet.getString("day_of_the_week");
                String routine = resultSet.getString("routine");
                System.out.println(dayOfWeek + ": " + routine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays all available classes for a member to sign up for.
     */
    public void displayAvailableClasses(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * from classes where is_full = false";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int classId = resultSet.getInt("class_id");
                int trainerId = resultSet.getInt("trainer_id");
                Date classDate = resultSet.getDate("class_date");
                Time startTime = resultSet.getTime("start_time");
                Time endTime = resultSet.getTime("end_time");
                boolean isFull = resultSet.getBoolean("is_full");

                System.out.println("Class ID: " + classId);
                System.out.println("Trainer ID: " + trainerId);
                System.out.println("Class Date: " + classDate);
                System.out.println("Start Time: " + startTime);
                System.out.println("End Time: " + endTime);
                System.out.println("Is Full: " + isFull);
                System.out.println();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method enrolls a member into a class.
     * @param classId
     * @param memberId
     */
    public void enrollInClass(int classId, int memberId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String querySelect = "select member1_id, member2_id, member3_id, member4_id from classes where class_id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(querySelect);
            selectStatement.setInt(1, classId);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                // Pick the first null member ID and update it
                int member1Id = resultSet.getInt("member1_id");
                int member2Id = resultSet.getInt("member2_id");
                int member3Id = resultSet.getInt("member3_id");
                int member4Id = resultSet.getInt("member4_id");

                int firstNullMemberId = 0;
                if (member1Id == 0) {
                    firstNullMemberId = 1;
                } else if (member2Id == 0) {
                    firstNullMemberId = 2;
                } else if (member3Id == 0) {
                    firstNullMemberId = 3;
                } else if (member4Id == 0) {
                    firstNullMemberId = 4;
                }

                // Update the first null member ID with the given member ID
                String updateMemberId = "member" + firstNullMemberId + "_id";
                String queryUpdate = "update classes set " + updateMemberId + " = ? where class_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(queryUpdate);
                updateStatement.setInt(1, memberId);
                updateStatement.setInt(2, classId);
                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Member enrolled in class successfully.");
                } else {
                    System.out.println("Failed to enroll member in class.");
                }

                if(firstNullMemberId == 4){
                   setClassFull(classId);
                }

            } else {
                System.out.println("Class not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets a class to full when all spots are taken up.
     * @param classId
     */
    public void setClassFull(int classId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "update classes set is_full = true where class_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, classId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Class set to full successfully.");
            } else {
                System.out.println("Failed to set class to full.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays all available training sessions.
     */
    public void displayAvailableSessions(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * from sessions where is_booked = false";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int sessionId = resultSet.getInt("session_id");
                int trainerId = resultSet.getInt("trainer_id");
                int memberId = resultSet.getInt("member_id");
                Date sessionDate = resultSet.getDate("session_date");
                Timestamp startTime = resultSet.getTimestamp("start_time");
                Timestamp endTime = resultSet.getTimestamp("end_time");
                boolean isBooked = resultSet.getBoolean("is_booked");

                System.out.println("Session ID: " + sessionId);
                System.out.println("Trainer ID: " + trainerId);
                System.out.println("Member ID: " + memberId);
                System.out.println("Session Date: " + sessionDate);
                System.out.println("Start Time: " + startTime);
                System.out.println("End Time: " + endTime);
                System.out.println("Is Booked: " + isBooked);
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method books a session for a member.
     * @param sessionId
     * @param memberId
     */
    public void bookSession(int sessionId, int memberId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "update sessions set is_booked = true, member_id = ? where session_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, memberId);
            statement.setInt(2, sessionId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Session booked successfully.");
            } else {
                System.out.println("Failed to book session.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
     * This method books a room for a member.
     * @param roomId
     * @param memberId
     */
    public void bookRoom(int roomId, int memberId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "update rooms set member_id = ?, is_booked = true where room_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, memberId);
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
}


