module login {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires mysql.connector.j;
	opens application to javafx.graphics, javafx.fxml;
}
