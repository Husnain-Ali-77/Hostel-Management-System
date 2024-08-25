package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.*;

public class SampleController {
	
	DBHandler db = new DBHandler();
	   @FXML
	    private Button btn_login;

	    @FXML
	    private Button btn_signup;

	    @FXML
	    private AnchorPane pane_login;

	    @FXML
	    private AnchorPane pane_signup;

	    @FXML
	    private TextField txt_cnic_up;

	    @FXML
	    private TextField txt_name_up;

	    @FXML
	    private PasswordField txt_password;

	    @FXML
	    private TextField txt_password_up;

	    @FXML
	    private TextField txt_phonenumber_up;

	    @FXML
	    private TextField txt_username;

	    
	    @FXML
	    private ComboBox<String> type_up;

	    @FXML
	    private ComboBox<String> type;
	    
	    @FXML
	    private TextField type_username_up;

	    public void initialize() {
	        type_up.setItems(FXCollections.observableArrayList("Customer", "Manager"));
	        type.setItems(FXCollections.observableArrayList("Customer", "Manager"));
	        fc.setLoginButton(btn_login);
	    }
	    FactoryClass fc = new FactoryClass();
	    @FXML
	    private void Login(ActionEvent event) {
	        String username = txt_username.getText();
	        String password = txt_password.getText();

	        if (authenticateUser(username, password)) {
	            System.out.println("User authenticated successfully!");
	            String userType = type.getValue().toString();

	            if ("Manager".equals(userType)) {
	                // If user type is "Manager," navigate to the manager page
	            fc.openManagerPage(this);
	            } else if ("Customer".equals(userType)) {
	                // If user type is "Customer," navigate to the customer page
	               fc.openCustomerPage(username);
	            } else {
	            	showAlert("Login Successful", "Welcome, " + username + "!");
	                  }
	        } else {
	            System.out.println("Authentication failed. Invalid username or password.");
	            showAlert("Login Failed", "Invalid username or password. Please try again.");
	        }
	    }
	    
	    private boolean authenticateUser(String username, String password) {
	                try (ResultSet resultSet=db.authenticateUser2(username,password) ) {
	                    return resultSet.next(); 
	                }
	         catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    @FXML
	    private void Signup(ActionEvent event) {
	        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/project";
	        String dbUsername = "root";
	        String dbPassword = "Country@7755";
	        String name = txt_name_up.getText();
	        String cnic = txt_cnic_up.getText();
	        String phonenumber = txt_phonenumber_up.getText();
	        String username = type_username_up.getText();
	        String password = txt_password_up.getText();
	        String userType = type_up.getValue().toString();  // Assuming type_up is ComboBox<String>

	        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
	      
	            String checkQuery = "SELECT * FROM users WHERE username = ?";
	            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
	                checkStatement.setString(1, username);
	                try (ResultSet resultSet = checkStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                    	System.out.println("User with the same username already exists.");
	                        showAlert("Username already exists", "Please choose a different username");
	                        return; // Exit the method, no need to proceed with insertion
	                    }
	                }
	            }
	            String insertQuery = "INSERT INTO users (name, cnic, phonenumber, username, password, typee) VALUES (?, ?, ?, ?, ?, ?)";
	            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
	                statement.setString(1, name);
	                statement.setString(2, cnic);
	                statement.setString(3, phonenumber);
	                statement.setString(4, username);
	                statement.setString(5, password);
	                statement.setString(6, userType);

	                int rowsAffected = statement.executeUpdate();

	                if (rowsAffected > 0) {
	                    System.out.println("User inserted successfully!");
	                    showAlert("Message", "User inserted Successfully");
	                } else {
	                    System.out.println("User insertion failed.");
	                    showAlert("Message", "User Not inserted Successfully");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	        }
	    }

	    private void showAlert(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	  
	    public void LoginpaneShow()
	    {
	    	pane_login.setVisible(true);
	    	pane_signup.setVisible(false);
	    	
	    	
	    }
	    public void SignuppaneShow()
	    {
	    	pane_signup.setVisible(true);
	    	pane_login.setVisible(false);
	    }
	    public String getUsername() {
	        return txt_username.getText();
	    }
	  
	    
}
