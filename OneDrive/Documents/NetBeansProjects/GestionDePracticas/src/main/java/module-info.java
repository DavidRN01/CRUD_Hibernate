module com.mycompany.gestiondepracticas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.gestiondepracticas to javafx.fxml;
    exports com.mycompany.gestiondepracticas;
}
