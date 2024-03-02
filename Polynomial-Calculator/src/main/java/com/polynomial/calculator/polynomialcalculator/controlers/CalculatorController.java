package com.polynomial.calculator.polynomialcalculator.controlers;

import com.polynomial.calculator.polynomialcalculator.models.Polynomial;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CalculatorController {
    public VBox vbox;
    @FXML
    private TextField firstPolynomialTextField;
    @FXML
    private TextField secondPolynomialTextField;
    @FXML
    private Text resultText;

    private Polynomial firstPolynomial;
    private Polynomial secondPolynomial;

    @FXML
    public void initialize() {
        setupTextFieldInputRestrictions(firstPolynomialTextField);
        setupTextFieldInputRestrictions(secondPolynomialTextField);

        firstPolynomialTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    firstPolynomial = Polynomial.fromString(firstPolynomialTextField.getText());
                    System.out.println("First Polynomial: " + firstPolynomial.getMonomials());
                }
            }
        });

        secondPolynomialTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    secondPolynomial = Polynomial.fromString(secondPolynomialTextField.getText());
                    System.out.println("Second Polynomial: " + secondPolynomial.getMonomials());
                }
            }
        });
    }

    private void setupTextFieldInputRestrictions(TextField textField) {
        textField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String character = event.getCharacter();
            if (!character.matches("[0-9^+-]|x")) {
                event.consume();
            }
        });
    }

    @FXML
    protected void addPolynomials() {
        firstPolynomial = Polynomial.fromString(firstPolynomialTextField.getText());
        secondPolynomial = Polynomial.fromString(secondPolynomialTextField.getText());
        Polynomial resultPolynomial = firstPolynomial.add(secondPolynomial);
        resultPolynomial.simplify();
        System.out.println("Result Polynomial: " + resultPolynomial.getMonomials());
        resultText.setText(resultPolynomial.toString());
    }

    @FXML
    protected void subtractPolynomials() {
        firstPolynomial = Polynomial.fromString(firstPolynomialTextField.getText());
        secondPolynomial = Polynomial.fromString(secondPolynomialTextField.getText());
        Polynomial resultPolynomial = firstPolynomial.subtract(secondPolynomial);
        resultPolynomial.simplify();
        System.out.println("Result Polynomial: " + resultPolynomial.getMonomials());
        resultText.setText(resultPolynomial.toString());
    }

    @FXML
    protected void multiplyPolynomials() {
        firstPolynomial = Polynomial.fromString(firstPolynomialTextField.getText());
        secondPolynomial = Polynomial.fromString(secondPolynomialTextField.getText());
        Polynomial resultPolynomial = firstPolynomial.multiply(secondPolynomial);
        resultPolynomial.simplify();
        System.out.println("Result Polynomial: " + resultPolynomial.getMonomials());
        resultText.setText(resultPolynomial.toString());
    }

    @FXML
    protected void dividePolynomials() {
        firstPolynomial = Polynomial.fromString(firstPolynomialTextField.getText());
        secondPolynomial = Polynomial.fromString(secondPolynomialTextField.getText());
        try {
            Polynomial resultPolynomial = firstPolynomial.divide(secondPolynomial);
            resultPolynomial.simplify();
            System.out.println("Result Polynomial: " + resultPolynomial.getMonomials());
            resultText.setText(resultPolynomial.toString());
        } catch (UnsupportedOperationException e) {
            resultText.setText("Division error: " + e.getMessage());
        }
    }

    @FXML
    protected void deriveFirstPolynomial() {
        firstPolynomial = Polynomial.fromString(firstPolynomialTextField.getText());
        Polynomial derivedPolynomial = firstPolynomial.derivative();
        resultText.setText(derivedPolynomial.toString());
        System.out.println("Derived First Polynomial: " + derivedPolynomial.getMonomials());
    }

    @FXML
    protected void integrateFirstPolynomial() {
        firstPolynomial = Polynomial.fromString(firstPolynomialTextField.getText());
        Polynomial integratedPolynomial = firstPolynomial.integrate();
        resultText.setText(integratedPolynomial.toString());
        System.out.println("Integrated First Polynomial: " + integratedPolynomial.getMonomials());
    }

    @FXML
    protected void clearFields() {
        firstPolynomialTextField.clear();
        secondPolynomialTextField.clear();
        resultText.setText("");
    }

    @FXML
    protected void exit() {
        System.exit(0);
    }
}
