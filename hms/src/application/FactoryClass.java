package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FactoryClass {

	private Button btn_login; 
    public void setLoginButton(Button btn_login) {
        this.btn_login = btn_login;
    }
	
	 public void openManagerPage(SampleController s) {
	        try {
	        	
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("hostel.fxml"));
	            Parent root = loader.load();
	            Stage managerStage = new Stage();
	            managerStage.setTitle("Manager Hostel");
	            managerStage.setScene(new Scene(root));
	            HostelController hostelController = loader.getController();
	            hostelController.setSampleController(s);

	            managerStage.show();
	            
	            Stage loginStage = (Stage) btn_login.getScene().getWindow();
	            loginStage.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            showAlert("Error", "Error opening Manager Page");
	        }
	    }
	 
	 public void openCustomerPage(String s) {
		    try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("customer.fxml"));
		        Parent root = loader.load();
		        
		        // Get the controller instance from the FXMLLoader
		        CustomerController c = loader.getController();
		        
		        // Set customer ID using a method in the controller
		        c.setcutomerid(s);

		        Stage customerStage = new Stage();
		        customerStage.setTitle("Customer Page");
		        customerStage.setScene(new Scene(root));
		        customerStage.show();

		        Stage loginStage = (Stage) btn_login.getScene().getWindow();
		        loginStage.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		        showAlert("Error", "Error opening Customer Page");
		    }
		}

	 private void showAlert(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }

}
