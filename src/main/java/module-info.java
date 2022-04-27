module com.example.lab8javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lab8javafx to javafx.fxml;
    exports com.example.lab8javafx;
    exports com.example.lab8javafx.Entities;
    opens com.example.lab8javafx.Entities to javafx.fxml;
    exports com.example.lab8javafx.DAO;
    opens com.example.lab8javafx.DAO to javafx.fxml;
    exports com.example.lab8javafx.DataBase;
    opens com.example.lab8javafx.DataBase to javafx.fxml;
    exports com.example.lab8javafx.ToDeleteLater;
    opens com.example.lab8javafx.ToDeleteLater to javafx.fxml;
}