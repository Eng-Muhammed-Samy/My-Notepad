module com.example.usingfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.usingfx to javafx.fxml;
    exports com.example.usingfx;
}