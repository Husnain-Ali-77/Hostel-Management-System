package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerController {
	
	@FXML
    private Button btn_back;
	
	   @FXML
	    private AnchorPane bookingpane;
	
	   @FXML
	    private Label booking_bedno;

	    @FXML
	    private Label booking_floorno;

	    @FXML
	    private Label booking_hostelname;

	    @FXML
	    private Label booking_payment;

	    @FXML
	    private Label booking_roomnumber;

	    @FXML
	    private Label booking_roomtype;

	    @FXML
	    private Button btn_Searchhostel;

	    @FXML
	    private Button btn_checkdetail;

	    @FXML
	    private Button btn_hosteldetailBack;

	    @FXML
	    private Button btn_reserveseat;

	    @FXML
	    private AnchorPane findhostelpane;

	    @FXML
	    private AnchorPane hosteldetailpane;

	    @FXML
	    private AnchorPane nearHostelpane;

	    @FXML
	    private Label set_establishdate;

	    @FXML
	    private Label set_hosteladdress;

	    @FXML
	    private Label set_hostelname;

	    @FXML
	    private Label set_hosteltype;

	    @FXML
	    private Label set_managercontact;

	    @FXML
	    private Label set_managername;

	    @FXML
	    private Label set_totalroom;

	    @FXML
	    private Label set_vacantrooms;

	    @FXML
	    private TextField type_nearlocation;


    private String Customerid; 

    public void setcutomerid(String ci) {
        this.Customerid = ci;
    }
    
	    int totalRooms;
	    int vacantRooms;
	    DBHandler db = new DBHandler();
	    @FXML
	    public void showHostels() {
	    	findhostelpane.setVisible(false);
	    	hosteldetailpane.setVisible(true);
	        		String temp=type_nearlocation.getText();
	        		ResultSet resultSet = db.get_hosteldata(temp);
	                try {
	                    if (resultSet.next()) {
	                        String hostelName = resultSet.getString("hostel_name");
	                        String address = resultSet.getString("address");
	                        String hostelType = resultSet.getString("hostel_type");
	                        String managerContact = resultSet.getString("manager_contact");
	                        String managerName = resultSet.getString("manager_name");
	                         totalRooms = resultSet.getInt("total_rooms");
	                         vacantRooms = resultSet.getInt("vacant_rooms");
	                        java.sql.Date establishDate = resultSet.getDate("established_date");
	                        LocalDate localDate = establishDate.toLocalDate();
	                        set_hostelname.setText(hostelName);
	                        set_hosteladdress.setText(address);
	                        set_hosteltype.setText(hostelType);
	                        set_managercontact.setText(managerContact);
	                        set_managername.setText(managerName);
	                        set_vacantrooms.setText(String.valueOf(vacantRooms));
	                        set_totalroom.setText(String.valueOf(totalRooms));
	                        set_establishdate.setText(localDate.toString());
	                    } else {
	                    	 showAlert("Message", "No hostel found for the specified location.");
	                        System.out.println("No hostel found for the specified location.");
	                    }
	                }
	         catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	        }
	    }
	    
	    @FXML
	    private void hosteldetailBack()
	    {
	    	findhostelpane.setVisible(true);
	    	hosteldetailpane.setVisible(false);
	    }
	    
	 
	    private void showAlert(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	    
	    @FXML
	    private void reserveseat() {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("room.fxml"));
	            Parent root = loader.load();
	            Scene scene = new Scene(root);
	            RoomController rc = loader.getController();
	            rc.setHostelName(set_hostelname.getText());
	            rc.setcutomerid(Customerid);
	            rc.setRoomDetails(totalRooms, vacantRooms);
	            Stage stage = (Stage) nearHostelpane.getScene().getWindow();
	            stage.setScene(scene);
	            stage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @FXML 
	    private void checkdetail() {
	        bookingpane.setVisible(true);
	        hosteldetailpane.setVisible(false);
	        findhostelpane.setVisible(false);

	        try {ResultSet resultSet = db.bookingdetail(Customerid);

	            if (resultSet.next()) {
	            	booking_bedno.setText("3");
	                booking_floorno.setText(Integer.toString(resultSet.getInt("floor_number")));
	                booking_hostelname.setText(resultSet.getString("hostel_name"));
	                booking_payment.setText("Done");
	                booking_roomnumber.setText(Integer.toString(resultSet.getInt("room_number")));
	                booking_roomtype.setText(resultSet.getString("room_type"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            showAlert("Error", "Error fetching data from the database");
	        }
	    }

@FXML
private void backfun()
{
	bookingpane.setVisible(false);
    hosteldetailpane.setVisible(false);
    findhostelpane.setVisible(true);
}



}

