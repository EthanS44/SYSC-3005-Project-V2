import java.sql.*;

public class Staff {

    private static final String url = "jdbc:postgresql://localhost:5432/Project V2";
    private static final String user = "postgres";
    private static final String password = "admin";


    public Staff(){
    }

    /**
     * This method create a staff member.
     * @param firstName
     * @param lastName
     * @param phone
     * @param email
     * @param pass
     */
    public void createStaff(String firstName, String lastName, String phone, String email, String pass) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "INSERT INTO Staff (first_name, last_name, phone, email, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phone);
            statement.setString(4, email);
            statement.setString(5, pass);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Staff member created successfully.");
            } else {
                System.out.println("Failed to create staff member.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns true if staff logs in successfully and false if not.
     * @param email
     * @param pass
     * @return
     */
    public boolean staffLogin(String email, String pass) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select 1 from staff where email = ? and password = ?";
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
     * This method adds equipment to the equipment table.
     * @param equipmentType
     * @param lastMaintained
     */
    public void addEquipment(String equipmentType, Date lastMaintained){
        String insertQuery = "insert into equipment (equipment_type, last_maintained) values (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, equipmentType);
            pstmt.setDate(2, lastMaintained);

            pstmt.executeUpdate();
            System.out.println("Equipment added successfully.");

        } catch (SQLException e) {
            System.out.println("Error while adding equipment: " + e.getMessage());
        }
    }

    /**
     * This method displays all the equipment in the equipment table.
     */
    public void displayAllEquipment(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "select * from equipment";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            //System.out.println("Equipment ID\tEquipment Type\tLast Maintained");

                while (resultSet.next()) {
                    int equipmentId = resultSet.getInt("equipment_id");
                    String equipmentType = resultSet.getString("equipment_type");
                    String lastMaintained = resultSet.getString("last_maintained");

                    System.out.println("Equipment ID = " + equipmentId + "\t\t" + " Equipment Type = " + equipmentType
                            + "\t\t" + "Last Maintained = " + lastMaintained);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while displaying Equipment: " + e.getMessage());
        }

    }

    /**
     * This method removes the equipment with the specified equipment id from the table.
     * @param id
     */
    public void removeEquipment(int id){
        try{
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "delete from equipment where equipment_id =" + id;
            PreparedStatement statement = connection.prepareStatement(query);
            int rowsUpdated = statement.executeUpdate();

            if(rowsUpdated > 0) {
                System.out.println("Equipment successfully deleted");
            }
            else{
                System.out.println("No equipment with that id");
            }

        } catch (SQLException e) {
        e.printStackTrace();
            System.out.println("Error while removing equipment: " + e.getMessage());
        }
    }


    /**
     * This method updates the equipments last maintained date after it's been serviced.
     */
    public void updateEquipmentMaintenance(int equipmentId, Date serviceDate){
        try{
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "update equipment set last_maintained = ? where equipment_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(2, equipmentId);
            statement.setDate(1, serviceDate);

            int rowsUpdated = statement.executeUpdate();

            if(rowsUpdated > 0) {
                System.out.println("Equipment successfully updated");
            }
            else{
                System.out.println("No equipment with that id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while updating equipment: " + e.getMessage());
        }

    }

    /**
     * This method creates a bill for a member to pay.
     * @param memberId
     * @param staffId
     * @param date
     * @param dollarAmount
     */
    public void createBill(int memberId, int staffId, Date date, int dollarAmount){
        String query = "insert into bills (member_id, staff_id, date, dollar_amount, is_paid) values (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, memberId);
            pstmt.setInt(2, staffId);
            pstmt.setDate(3, date);
            pstmt.setInt(4,dollarAmount);
            pstmt.setBoolean(5, false);

            pstmt.executeUpdate();
            System.out.println("Bill added successfully.");

        } catch (SQLException e) {
            System.out.println("Error while adding bill: " + e.getMessage());
        }
    }

    /**
     * This method displays all paid and unpaid bills.
     */
    public void displayBills(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "select * from bills";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            //System.out.println("Bill ID\tMember ID\tStaff ID\tDate\tDollar Total\tIs Paid?");

            while (resultSet.next()) {
                int billId = resultSet.getInt("bill_id");
                int memberId = resultSet.getInt("member_id");
                int staff_id = resultSet.getInt("staff_id");
                int dollarAmount = resultSet.getInt("dollar_amount");
                boolean isPaid = resultSet.getBoolean("is_paid");

                System.out.println("Bill ID = " + billId + "\t\t" + "Member ID = " + memberId +
                        "\t\t" + "Staff ID = " + staff_id + "\t\t" + "Dollar Amount = " + dollarAmount +
                        "\t\t" + "Is Paid? = " + isPaid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while displaying bills: " + e.getMessage());
        }
    }

    /**
     * This method allows the staff to create a class
     * @param trainerId
     * @param classDate
     * @param startTime
     * @param endTime
     */
    public void createClass(int trainerId, Date classDate, Time startTime, Time endTime){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "insert into classes (trainer_id, class_date, start_time, end_time, is_full) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, trainerId);
            statement.setDate(2, classDate);
            statement.setTime(3, startTime);
            statement.setTime(4, endTime);
            statement.setBoolean(5, false);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Class created successfully.");
            } else {
                System.out.println("Failed to create class.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method displays all classes.
     */
    public void displayClasses(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * from classes";
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
     * This method allows the staff to update the date and time of a class.
     * @param classId
     * @param newClassDate
     * @param newStartTime
     * @param newEndTime
     */
    public void updateClassTime(int classId, Date newClassDate, Time newStartTime, Time newEndTime){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "update classes set class_date = ?, start_time = ?, end_time = ? where class_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, newClassDate);
            statement.setTime(2, newStartTime);
            statement.setTime(3, newEndTime);
            statement.setInt(4, classId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Class date and time updated successfully.");
            } else {
                System.out.println("No class found with the specified ID.");
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
     * This method creates a room booking.
     * @param startTime
     * @param endTime
     */
    public void createRoomBooking(Time startTime, Time endTime){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "insert into rooms (start_time, end_time) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setTime(1, startTime); // Example start time
            statement.setTime(2, endTime); // Example end time

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Room created successfully.");
            } else {
                System.out.println("Failed to create room.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays all room bookings.
     */
    public void displayRoomBookings(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * from rooms";
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
     * This method removes a room booking.
     * @param roomId
     */
    public void removeRoomBooking(int roomId){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM Rooms WHERE room_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Room removed successfully.");
            } else {
                System.out.println("Failed to remove room. Room ID might not exist.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
