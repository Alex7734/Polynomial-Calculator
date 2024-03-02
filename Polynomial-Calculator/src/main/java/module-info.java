module com.polynomial.calculator.polynomialcalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.polynomial.calculator.polynomialcalculator to javafx.fxml;
    exports com.polynomial.calculator.polynomialcalculator;
    exports com.polynomial.calculator.polynomialcalculator.controlers;
    opens com.polynomial.calculator.polynomialcalculator.controlers to javafx.fxml;
}