module com.example.laba22 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.laba22 to javafx.fxml;
    exports com.example.laba22;
}