module com.example.listing_33_4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.listing_33_4 to javafx.fxml;
    exports com.example.listing_33_4;
}