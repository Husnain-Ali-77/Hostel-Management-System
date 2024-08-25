package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import cummnication.notification;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RoomController {

	  @FXML
	    private Button btn_query;

	  @FXML
	    private Button btn_logout2;

	@FXML
    private Button btn_clicktoform;

    @FXML
    private Button btn_reserveseatBACK;
    
    @FXML
    private ComboBox<String> choose_bedavailability;

    @FXML
    private ComboBox<Integer> choose_bednumber;

    @FXML
    private ComboBox<Integer> choosefloorno;

    @FXML
    private ComboBox<Integer> chooseroomnumber;

    @FXML
    private ComboBox<String> chooseroomtype;

    @FXML
    private AnchorPane reserveseatpane;

    @FXML
    private AnchorPane seatdetailpane;

    @FXML
    private Label set_availrooms;

    @FXML
    private Label set_availseats;

    @FXML
    private Label set_totalrooms;

    @FXML
    private Label set_vacantrooms;

    @FXML
    private TextField type_noofguests;
    
    private String hostelname; 

    public void setHostelName(String hn) {
        this.hostelname = hn;
    }
    private String Customerid; 

    public void setcutomerid(String ci) {
        this.Customerid = ci;
    }
	    
	    @FXML
       private void initialize()
       {
    	   choosefloorno.setItems(FXCollections.observableArrayList(1,2,3,4,5));
    	   chooseroomtype.setItems(FXCollections.observableArrayList("Airy", "Closed"));
    	   choose_bedavailability.setItems(FXCollections.observableArrayList("Yes", "No"));
    	   choose_bednumber.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        }
	    int solution;
	  

	    @FXML
	    private void reserveseatBACK()
	    {
	    	seatdetailpane.setVisible(true);
	    	reserveseatpane.setVisible(false);
	    }
	   
	    public void setRoomDetails(int totalRooms, int vacantRooms) {
	        set_totalrooms.setText(Integer.toString(totalRooms));
	        set_vacantrooms.setText(Integer.toString(vacantRooms));
	        solution   = totalRooms-vacantRooms;
	        int totalseats = 4*solution;
	        set_availrooms.setText(Integer.toString(solution));
	        set_availseats.setText(Integer.toString(totalseats));
	        
	        
	    }
	    
	    @FXML
		   private void clickform()
		   {
		    	seatdetailpane.setVisible(false);
		    	reserveseatpane.setVisible(true);
		    	datafillingofroom();
		   }
	    
	    private void datafillingofroom()
	    {
	    	
	    	chooseroomnumber.getItems().clear();

	        for (int i = 1; i <= solution; i++) {
	            chooseroomnumber.getItems().add(i);
	        }
	    }
	    
	    @FXML
	    private void merireservation()
	    {
	    	seat(solution);
	    }
	 
	    private void seat(int solution) {
	        int noOfGuests = Integer.parseInt(type_noofguests.getText());
	        paymentController pc = new paymentController();
	        pc.set_guests(noOfGuests);
	        int floorNumber = choosefloorno.getValue();
	        String roomType = chooseroomtype.getValue();
	        int roomNumber = chooseroomnumber.getValue(); 
	        String hostelName = hostelname; 
	        String customerid=Customerid;
	        int bedNum = choose_bednumber.getValue();
	        String isAvailable = choose_bedavailability.getValue();
	        String message = "Room Booked";
	        Date date = new Date(System.currentTimeMillis()); // Using the current date
	        String status="yes";
	        reservation selectedReservation = new reservation(date,status);
	        notification selectedNotification = new notification( message, date);
	        bed selectedBed = new bed(bedNum, isAvailable, roomNumber, hostelName);
	        room selectedRoom = new room(noOfGuests, floorNumber, roomType, roomNumber, hostelName,customerid);

	        insertRoomData(selectedRoom, selectedBed, selectedNotification,selectedReservation);
	    }


	    public void insertRoomData(room selectedRoom, bed selectedBed, notification selectedNotification, reservation selectedReservation) {
	        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/project";
	        String dbUsername = "root";
	        String dbPassword = "Country@7755";

	        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
	            String insertRoomQuery = "INSERT INTO room (no_of_guests, floor_number, room_type, room_number, hostel_name,customer_id) VALUES (?,?, ?, ?, ?, ?)";
	            String insertBedQuery = "INSERT INTO bed (bed_num, isavailable, room_number, hostel_name) VALUES (?, ?, ?, ?)";
	            String insertNotificationQuery = "INSERT INTO notification ( message, date) VALUES ( ?, ?)";
	            String insertReservationQuery = "INSERT INTO reservation (date, status) VALUES (?, ?)";

	            try (PreparedStatement roomStatement = connection.prepareStatement(insertRoomQuery);
	                 PreparedStatement bedStatement = connection.prepareStatement(insertBedQuery);
	                 PreparedStatement notificationStatement = connection.prepareStatement(insertNotificationQuery);
	                 PreparedStatement reservationStatement = connection.prepareStatement(insertReservationQuery)) {

	                // Insert into room table
	                roomStatement.setInt(1, selectedRoom.get_noofguests());
	                roomStatement.setInt(2, selectedRoom.get_floornumber());
	                roomStatement.setString(3, selectedRoom.get_roomtype());
	                roomStatement.setInt(4, selectedRoom.get_roomnumber());
	                roomStatement.setString(5, selectedRoom.get_hostelname());
	                roomStatement.setString(6, selectedRoom.get_customerid());

	                int roomRowsAffected = roomStatement.executeUpdate();

	                // Insert into bed table
	                bedStatement.setInt(1, selectedBed.get_bednum());
	                bedStatement.setString(2, selectedBed.get_isavailable());
	                bedStatement.setInt(3, selectedBed.get_roomnumber());
	                bedStatement.setString(4, selectedBed.get_hostelname());

	                int bedRowsAffected = bedStatement.executeUpdate();

	                // Insert into notification table
	              //  notificationStatement.setInt(1, selectedNotification.getNotiId());
	                notificationStatement.setString(1, selectedNotification.getMessage());
	                notificationStatement.setDate(2, new java.sql.Date(selectedNotification.getDate().getTime()));

	                int notificationRowsAffected = notificationStatement.executeUpdate();

	                // Insert into reservation table
	                reservationStatement.setDate(1, new java.sql.Date(selectedReservation.getDate().getTime()));
	                reservationStatement.setString(2, selectedReservation.getStatus());

	                int reservationRowsAffected = reservationStatement.executeUpdate(); // Execute the reservation statement

	                if (roomRowsAffected > 0 && bedRowsAffected > 0 && notificationRowsAffected > 0 && reservationRowsAffected > 0) {
	                    showAlert("Message", "Seat Reserved successfully.");
	                    System.out.println("Data inserted successfully into the room, bed, notification, and reservation tables.");
	                } else {
	                    showAlert("Message", "Failed to Reserved Seat ");
	                    System.out.println("Failed to insert data into the room, bed, notification, and reservation tables.");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Log or handle the exception appropriately
	            showAlert("Error", "Failed to insert data. Please check your input.");
	        }
	    }

	    private void seat2(int solution) {
	        int noOfGuests = Integer.parseInt(type_noofguests.getText());
	        paymentController pc = new paymentController();
	        pc.set_guests(noOfGuests);
	        int floorNumber = choosefloorno.getValue();
	        String roomType = chooseroomtype.getValue();
	        int roomNumber = chooseroomnumber.getValue(); 
	        String hostelName = hostelname; 
	        String customerid=Customerid;
	        int bedNum = choose_bednumber.getValue();
	        String isAvailable = choose_bedavailability.getValue();
	        int notiId = 1; 
	        String message = "Room Booked";
	        Date date = new Date(System.currentTimeMillis()); // Using the current date
	        String status="yes";
	        reservation selectedReservation = new reservation(date,status);
	        notification selectedNotification = new notification( message, date);
	        bed selectedBed = new bed(bedNum, isAvailable, roomNumber, hostelName);
	        room selectedRoom = new room(noOfGuests, floorNumber, roomType, roomNumber, hostelName,customerid);

	        insertRoomData2(selectedRoom, selectedBed, selectedNotification,selectedReservation);
	    }

	    
	    public void insertRoomData2(room selectedRoom, bed selectedBed, notification selectedNotification, reservation selectedReservation) {
	        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/project";
	        String dbUsername = "root";
	        String dbPassword = "Country@7755";

	        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
	            String insertRoomQuery = "INSERT INTO room (no_of_guests, floor_number, room_type, room_number, hostel_name,customer_id) VALUES (?,?, ?, ?, ?, ?)";
	            String insertBedQuery = "INSERT INTO bed (bed_num, isavailable, room_number, hostel_name) VALUES (?, ?, ?, ?)";
	            String insertNotificationQuery = "INSERT INTO notification ( message, date) VALUES ( ?, ?)";
	            String insertReservationQuery = "INSERT INTO reservation (date, status) VALUES (?, ?)";

	            try (PreparedStatement roomStatement = connection.prepareStatement(insertRoomQuery);
	                 PreparedStatement bedStatement = connection.prepareStatement(insertBedQuery);
	                 PreparedStatement notificationStatement = connection.prepareStatement(insertNotificationQuery);
	                 PreparedStatement reservationStatement = connection.prepareStatement(insertReservationQuery)) {

	                // Insert into room table
	                roomStatement.setInt(1, selectedRoom.get_noofguests());
	                roomStatement.setInt(2, selectedRoom.get_floornumber());
	                roomStatement.setString(3, selectedRoom.get_roomtype());
	                roomStatement.setInt(4, selectedRoom.get_roomnumber());
	                roomStatement.setString(5, selectedRoom.get_hostelname());
	                roomStatement.setString(6, selectedRoom.get_customerid());

	                int roomRowsAffected = roomStatement.executeUpdate();

	                // Insert into bed table
	                bedStatement.setInt(1, selectedBed.get_bednum());
	                bedStatement.setString(2, selectedBed.get_isavailable());
	                bedStatement.setInt(3, selectedBed.get_roomnumber());
	                bedStatement.setString(4, selectedBed.get_hostelname());

	                int bedRowsAffected = bedStatement.executeUpdate();

	                // Insert into notification table
	               
	                notificationStatement.setString(1, selectedNotification.getMessage());
	                notificationStatement.setDate(2, new java.sql.Date(selectedNotification.getDate().getTime()));

	                int notificationRowsAffected = notificationStatement.executeUpdate();

	                // Insert into reservation table
	                reservationStatement.setDate(1, new java.sql.Date(selectedReservation.getDate().getTime()));
	                reservationStatement.setString(2, selectedReservation.getStatus());

	                int reservationRowsAffected = reservationStatement.executeUpdate(); // Execute the reservation statement

	                if (roomRowsAffected > 0 && bedRowsAffected > 0 && notificationRowsAffected > 0 && reservationRowsAffected > 0) {
	                   // showAlert("Message", "Seat Reserved successfully.");
	                    System.out.println("Data inserted successfully into the room, bed, notification, and reservation tables.");
	                } else {
	                  //  showAlert("Message", "Failed to Reserved Seat ");
	                    System.out.println("Failed to insert data into the room, bed, notification, and reservation tables.");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Log or handle the exception appropriately
	          //  showAlert("Error", "Failed to insert data. Please check your input.");
	        }
	    }

	    
	    private void showAlert(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Message");
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
    
	    @FXML
	    private void payment() {
	    	
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("payment.fxml"));
	            Parent root = loader.load();
	            Scene scene = new Scene(root);
	            
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	        }
	      seat2(solution);
	    }
	    @FXML
	  private void  queryFun()
	  {
		  showAlert("Message", "Your Question has reached, we will keep in touch you soon");
	  }
	    
	    @FXML
	     private void logoutfun()
	     {
	    	 try {
	    		    FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
	    		    Parent root = loader.load();
	    		    Scene scene = new Scene(root);
	    		    Stage stage = new Stage();
	    		    stage.setScene(scene);
	    		    stage.show();
	    		} catch (IOException e) {
	    		    e.printStackTrace();
	    		}

	     }
	   
}

