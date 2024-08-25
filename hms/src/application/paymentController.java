package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class paymentController {
	
	int guest;
	public void set_guests(int yes)
	{
		guest=yes;
	}
	   @FXML
	    private Button btn_paymentdone;

	    @FXML
	    private ComboBox<String> choose_paymentmethod;

	    @FXML
	    private AnchorPane nichywala;

	    @FXML
	    private TextField set_totalamount;

	    @FXML
	    private AnchorPane uperwalapane;
	    
	    @FXML
	    private Button btn_logout;

	    
	    RoomController rc = new RoomController();
	     public void initialize() {
	    	
	    	 choose_paymentmethod.setItems(FXCollections.observableArrayList("Debit Card", "Credit Card",
	    			 "Sada Pay","Pay Pal"));
	    	 
		    int	total=15000;
		    	 String totalAsString = String.valueOf(total);
		    	set_totalamount.setText(totalAsString);
	     }

	     @FXML
	     private void paymentDone()
	     {
	    	 uperwalapane.setVisible(true);
	    	 nichywala.setVisible(true);
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
