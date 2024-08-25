package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HostelController {
	
	@FXML
	private SampleController sc;

	public void setSampleController(SampleController sampleController) {
	    this.sc = sampleController;
	}

	 @FXML
	    private Button btn_logout;

	    @FXML
	    private Button btn_logout2;
    @FXML
    private TextField modify_description;
	
	 @FXML
	    private Button add_hostelinfo;

	    @FXML
	    private AnchorPane add_viewpane;

	    @FXML
	    private AnchorPane addhostel_pane;

	    @FXML
	    private Button btn_UPDATEhostelprofile;

	    @FXML
	    private Button btn_addhostel;

	    @FXML
	    private Button btn_addhostelBACK;

	    @FXML
	    private Button btn_hostelprofileBACK;

	    @FXML
	    private Button btn_modifyBACK;

	    @FXML
	    private Button btn_updatehostelprofile;

	    @FXML
	    private AnchorPane hostelprofiledetail;

	    @FXML
	    private TextField modify_hosteladdress;

	    @FXML
	    private TextField modify_hostelname;

	    @FXML
	    private TextField modify_managername;

	    @FXML
	    private TextField modify_price;

	    @FXML
	    private TextField modify_totalrooms;

	    @FXML
	    private TextField modify_vacantroom;

	    @FXML
	    private AnchorPane register_pane;

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
	    private Label set_totalrooms;

	    @FXML
	    private Label set_vacantrooms;

	    @FXML
	    private TextField type_address;

	    @FXML
	    private TextField type_description;

	    @FXML
	    private DatePicker type_establishdate;

	    @FXML
	    private TextField type_hostelid;

	    @FXML
	    private TextField type_hostelname;

	    @FXML
	    private ComboBox<String> type_hosteltype;

	    @FXML
	    private TextField type_managername;

	    @FXML
	    private TextField type_mcontact;

	    @FXML
	    private TextField type_musername;

	    @FXML
	    private TextField type_totalrooms;

	    @FXML
	    private TextField type_vacantrooms;

	    @FXML
	    private Button view_hostel;
	    
	    @FXML
	    private AnchorPane modifypane;


	    public void initialize() {
	        type_hosteltype.setItems(FXCollections.observableArrayList("Male", "Female"));
	       
	    }
	    
	    @FXML
	     private void logoutFun()
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
	  
	    @FXML
	    private void addhosteltoDB() {
	        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/project";
	        String dbUsername = "root";
	        String dbPassword = "Country@7755";

	        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {    String checkQuery = "SELECT * FROM Hostel WHERE manager_username = ?";
	            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
	                checkStatement.setString(1, type_musername.getText());
	                try (ResultSet resultSet = checkStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                    	showAlert("Error", "A hostel already exists for the given manager username. You can not add the new one hostel.");
	                        return;
	                    }
	                }
	            }
	            String insertQuery = "INSERT INTO Hostel (hostel_id, hostel_name, address, manager_name, manager_username, " +
	                    "manager_contact, total_rooms, vacant_rooms, hostel_type, established_date, description) " +
	                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
	                // Validate and set values
	                int hostelId = Integer.parseInt(type_hostelid.getText());
	                String hostelName = type_hostelname.getText();
	                String address = type_address.getText();
	                String managerName = type_managername.getText();
	                String managerUsername = type_musername.getText();
	                String managerContact = type_mcontact.getText();
	                int totalRooms = Integer.parseInt(type_totalrooms.getText());
	                int vacantRooms = Integer.parseInt(type_vacantrooms.getText());
	                String hostelType = type_hosteltype.getValue();
	                java.sql.Date establishDate = java.sql.Date.valueOf(type_establishdate.getValue());
	                String description = type_description.getText();

	                // Set values in the prepared statement
	                statement.setInt(1, hostelId);
	                statement.setString(2, hostelName);
	                statement.setString(3, address);
	                statement.setString(4, managerName);
	                statement.setString(5, managerUsername);
	                statement.setString(6, managerContact);
	                statement.setInt(7, totalRooms);
	                statement.setInt(8, vacantRooms);
	                statement.setString(9, hostelType);
	                statement.setDate(10, establishDate);
	                statement.setString(11, description);

	                int rowsAffected = statement.executeUpdate();

	                if (rowsAffected > 0) {
	                    System.out.println("Hostel inserted successfully!");
	                    showAlert("Message", "Hostel Profile Created Successfully");
	                } else {
	                    System.out.println("Hostel insertion failed.");
	                    showAlert("Message", "Hostel Profile not Created");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();  // Print the stack trace for debugging
	            // Handle the exception appropriately
	            showAlert("Error", "Failed to insert hostel. Please check your input.");
	        } catch (NumberFormatException e) {
	            showAlert("Error", "Invalid number format. Please check numeric fields.");
	        }
	    }


	    @FXML
	    public void viewhostel() {
	    	System.out.println("in the view hostel function ");
	    	register_pane.setVisible(false);
	    	modifypane.setVisible(false);
	    	add_viewpane.setVisible(false);
	    	addhostel_pane.setVisible(false);
	    	hostelprofiledetail.setVisible(true);
	        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/project";
	        String dbUsername = "root";
	        String dbPassword = "Country@7755";

	        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
	        	 String username = sc.getUsername(); 
	        	
	            String query = "SELECT * FROM hostel WHERE manager_username = ?";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	                statement.setString(1, username);
	               
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        // Retrieve data from the result set
	                        int hostelId = resultSet.getInt("hostel_id");
	                        String hostelName = resultSet.getString("hostel_name");
	                        String address = resultSet.getString("address");
	                        String hostelType = resultSet.getString("hostel_type");
	                        String managerName = resultSet.getString("manager_name");
	                        String managerContact = resultSet.getString("manager_contact");
	                        int totalRooms = resultSet.getInt("total_rooms");
	                        int vacantRooms = resultSet.getInt("vacant_rooms");
	                        java.sql.Date establishDate = resultSet.getDate("established_date");
	                        LocalDate localDate = establishDate.toLocalDate();
	                       // String description = resultSet.getString("description");

	                        System.out.println("Hostel ID: " + hostelId);
	                        System.out.println("Hostel Name: " + hostelName);
                            set_hostelname.setText(hostelName);
	                        set_hosteladdress.setText(address);
	                        set_hosteltype.setText(hostelType);	                   
	                        set_managername.setText(managerName);
                            set_managercontact.setText(managerContact);
	                        set_vacantrooms.setText(String.valueOf(vacantRooms));
	                        set_totalrooms.setText(String.valueOf(totalRooms));
                            set_establishdate.setText(localDate.toString());
	                         
	                       
	                    } else {
	                       
	                        System.out.println("No hostel data found for the manager: " + username);
	                        showAlert("Message", "No hostel data found for the logged-in manager.");
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();  
	      
	            showAlert("Error", "Failed to retrieve hostel data. Please try again.");
	        }
	        System.out.println("in the view hostel function ");
	    }
	    
	    @FXML
	    private void updateHostel() {
	    	add_viewpane.setVisible(false);
	    	hostelprofiledetail.setVisible(false);
	    	register_pane.setVisible(false);
	      	addhostel_pane.setVisible(false);
	    	modifypane.setVisible(true);
	    }

	    
	    @FXML
	    private void addHostel() {
	    	add_viewpane.setVisible(false);
	    	hostelprofiledetail.setVisible(false);
	    	register_pane.setVisible(false);
	      	addhostel_pane.setVisible(true);
	      	modifypane.setVisible(false);
	}
      @FXML
	    private void addhostelBack() {
	    	register_pane.setVisible(true);
	    	add_viewpane.setVisible(true);
	    	addhostel_pane.setVisible(false);
	    	hostelprofiledetail.setVisible(false);
	    	modifypane.setVisible(false);
	    }
	    @FXML
	    private void hostelprofileBACK()
	    {
	    	register_pane.setVisible(true);
	    	add_viewpane.setVisible(true);
	    	addhostel_pane.setVisible(false);
	    	hostelprofiledetail.setVisible(false);
	    	modifypane.setVisible(false);
	    }
	    
	    @FXML
	    private void modifyhostelBack()
	    {
	    	viewhostel();
	    }
	    
	    @FXML
	    private void modifyHostelprofile() {
	        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/project";
	        String dbUsername = "root";
	        String dbPassword = "Country@7755";

	        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
	            String selectQuery = "SELECT * FROM Hostel WHERE manager_username = ?";
	            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
	                String username = sc.getUsername();
	                selectStatement.setString(1, username);

	                try (ResultSet resultSet = selectStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                        // Retrieve existing data
	                        String existingHostelName = resultSet.getString("hostel_name");
	                        String existingAddress = resultSet.getString("address");
	                        String existingManagerName = resultSet.getString("manager_name");
	                        String existingManagerContact = resultSet.getString("manager_contact");
	                        int existingTotalRooms = resultSet.getInt("total_rooms");
	                        int existingVacantRooms = resultSet.getInt("vacant_rooms");
	                        String existingHostelType = resultSet.getString("hostel_type");
	                        LocalDate existingEstablishDate = resultSet.getDate("established_date").toLocalDate();
	                        String existingDescription = resultSet.getString("description");

	                        // Check if fields are empty, if yes, keep the existing data
	                        String hostelName = modify_hostelname.getText().isEmpty() ? existingHostelName : modify_hostelname.getText();
	                        String address = modify_hosteladdress.getText().isEmpty() ? existingAddress : modify_hosteladdress.getText();
	                        String managerName = modify_managername.getText().isEmpty() ? existingManagerName : modify_managername.getText();
	                        String managerContact = modify_price.getText().isEmpty() ? existingManagerContact : modify_price.getText();
	                        int totalRooms = modify_totalrooms.getText().isEmpty() ? existingTotalRooms : Integer.parseInt(modify_totalrooms.getText());
	                        int vacantRooms = modify_vacantroom.getText().isEmpty() ? existingVacantRooms : Integer.parseInt(modify_vacantroom.getText());
	                        String hostelType = type_hosteltype.getValue() == null ? existingHostelType : type_hosteltype.getValue();
	                        LocalDate establishDate = type_establishdate.getValue() == null ? existingEstablishDate : type_establishdate.getValue();
	                        String description = type_description.getText().isEmpty() ? existingDescription : modify_description.getText();

	                        String updateQuery = "UPDATE Hostel SET hostel_name = ?, address = ?, manager_name = ?, manager_contact = ?, "
	                                + "total_rooms = ?, vacant_rooms = ?, hostel_type = ?, established_date = ?, description = ? "
	                                + "WHERE manager_username = ?";

	                        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
	                            // Set values in the prepared statement
	                            updateStatement.setString(1, hostelName);
	                            updateStatement.setString(2, address);
	                            updateStatement.setString(3, managerName);
	                            updateStatement.setString(4, managerContact);
	                            updateStatement.setInt(5, totalRooms);
	                            updateStatement.setInt(6, vacantRooms);
	                            updateStatement.setString(7, hostelType);
	                            updateStatement.setDate(8, java.sql.Date.valueOf(establishDate));
	                            updateStatement.setString(9, description);
	                            updateStatement.setString(10, username);

	                            int rowsAffected = updateStatement.executeUpdate();

	                            if (rowsAffected > 0) {
	                                System.out.println("Hostel updated successfully!");
	                                showAlert("Message", "Hostel Profile Updated Successfully");
	                            } else {
	                                System.out.println("Hostel update failed.");
	                                showAlert("Message", "Hostel Profile not Updated");
	                            }
	                        }
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            showAlert("Error", "Failed to update hostel. Please check your input.");
	        } catch (NumberFormatException e) {
	            showAlert("Error", "Invalid number format. Please check numeric fields.");
	        }
	    }

	    private void showAlert(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Message");
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	    
	   
	
}

