module com.absan.kopi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires spotify.web.api.java;


    opens com.absan.kopi to javafx.fxml;
    exports com.absan.kopi;
}