module com.example.datahealthv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.datahealthv2.controllers to javafx.fxml,javafx.base;
    opens com.example.datahealthv2.model to javafx.base;
    exports com.example.datahealthv2;


}