/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author allisonbarrezueta
 */
public class Validar {
    
    private static boolean isNumeric(String c){
	try {
		return Integer.parseInt(c) >= 1;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    private static boolean isNumericD(String c){
	try {
		return Double.parseDouble(c) >= 0.00;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    private static boolean isYear(String c){
        if (isNumeric(c)) {
            if (Integer.parseInt(c) > Calendar.getInstance().get(Calendar.YEAR) || Integer.parseInt(c) < 0) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isStr(String c){
	for (int i = 0; i < c.length(); i++) {
            if (Validar.isNumeric(String.valueOf(c.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isPlaca(String c){
        if (c.length() <6)
            return false;
        
        for (int i = 0; i < 3; i++) {
            if (isNumeric(String.valueOf(c.charAt(i)))) {
                return false;
            }
        }
        for (int i = 3; i < c.length(); i++) {
            if (c.charAt(i) != '0') {
                System.out.println(c.charAt(i));
                if (!isNumeric(String.valueOf(c.charAt(i)))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isCorreo(String correo) {
        return (correo.equals("") || correo.equals(" ") || !correo.matches(Constants.PATRONES_EMAIL));
    }
    
    public static boolean validarPrincipales(boolean modify, TextField placa, TextField marca, TextField modelo, TextField tipoMotor, TextField year, TextField tipoVehiculo, TextField recorrido, TextField color, TextField TipoCombustible, TextField Vidrios, TextField Transmision, TextField precio){
        if (modify) {
            if (!marca.getText().isEmpty()) {
                if (!isStr(marca.getText())){
                    marca.setStyle("-fx-control-inner-background: red");
                    alert("Ingreso de Carros","ERROR","ERROR! Campo: Marca");
                    return false;
                }
            } else {
                marca.setStyle("-fx-control-inner-background: white");
            }
            if (!tipoMotor.getText().isEmpty()) {
                if (!isStr(tipoMotor.getText())){
                    tipoMotor.setStyle("-fx-control-inner-background: red");
                    alert("Ingreso de Carros","ERROR","ERROR! Campo: Tipo de Motor");
                    return false;
                }
            }
            if (!year.getText().isEmpty()) {
                if (!isYear(year.getText())){
                    year.setStyle("-fx-control-inner-background: red");
                    alert("Ingreso de Carros","ERROR","ERROR! Año mayor al actual o año menor a 0!");
                    return false;
                }
            }
            if (!color.getText().isEmpty()) {
                if (!isStr(color.getText())){
                    color.setStyle("-fx-control-inner-background: red");
                    alert("Ingreso de Carros","ERROR","Campo: Color");
                    return false;
                }
            }
            if (!tipoVehiculo.getText().isEmpty()) {
                if (!isNumeric(tipoVehiculo.getText())){
                    tipoVehiculo.setStyle("-fx-control-inner-background: red");
                    alert("Ingreso de Carros","ERROR","Campo: Tipo de Vehiculo");
                    return false;
                }
            }
            
            if (!TipoCombustible.getText().isEmpty()) {
                if (!isStr(TipoCombustible.getText())){
                    TipoCombustible.setStyle("-fx-control-inner-background: red");
                    alert("Ingreso de Carros","ERROR","Campo: Tipo de combustible");
                    return false;
                }
            }
            if (!Vidrios.getText().isEmpty()) {
                if (!isStr(Vidrios.getText())){
                    Vidrios.setStyle("-fx-control-inner-background: red");
                    alert("Ingreso de Carros","ERROR","Campo: Vidrios");
                    return false;
                }
            }
            if (!Transmision.getText().isEmpty()) {
                if (!isStr(Transmision.getText())){
                    Transmision.setStyle("-fx-control-inner-background: red");
                    alert("Ingreso de Carros","ERROR","Campo: Transmision");
                    return false;
                }
            }
            if (!recorrido.getText().isEmpty()) {
                if (!isNumeric(recorrido.getText())){
                    recorrido.setStyle("-fx-control-inner-background: red");
                    alert("Ingreso de Carros","ERROR","ERROR! Recorrido menor a 0!");
                    return false;
                }
            }
            if (!precio.getText().isEmpty()) {
                if (!Validar.isNumericD(precio.getText())) {
                    precio.setStyle("-fx-control-inner-background: red");
                    alert("Ingreso de Carros","ERROR","ERROR! Precio menor a 0!");
                    return false;
                }
            }
            return true;
        } else {
            if (!placa.getText().isEmpty() && !marca.getText().isEmpty() && !modelo.getText().isEmpty() && !tipoMotor.getText().isEmpty() && !year.getText().isEmpty() && !tipoVehiculo.getText().isEmpty() && !recorrido.getText().isEmpty() && !color.getText().isEmpty() && !TipoCombustible.getText().isEmpty() && !Vidrios.getText().isEmpty() && !Transmision.getText().isEmpty() && !precio.getText().isEmpty()){
                if (isPlaca(placa.getText()) && isStr(marca.getText()) && isNumericD(tipoMotor.getText()) && isYear(year.getText()) && isStr(tipoVehiculo.getText()) && isNumeric(recorrido.getText()) && isStr(color.getText()) && isStr(TipoCombustible.getText()) && isStr(Vidrios.getText()) && isStr(Transmision.getText()) && isNumericD(precio.getText())) {
                    return true;
                }
                if (!Validar.isPlaca(placa.getText()))
                    alert("Ingreso de Carros","ERROR","ERROR! Formato de placa: ABC0000");
                if (!Validar.isStr(marca.getText()))
                    alert("Ingreso de Carros","ERROR","ERROR! Las Marcas no contienen numeros");
                if (!isNumericD(tipoMotor.getText()))
                    alert("Ingreso de Carros","ERROR","ERROR! Tipo de motor no contienen letras");
                if (!Validar.isYear(year.getText()))
                    alert("Ingreso de Carros","ERROR","ERROR! Año mayor al actual o año menor a 0!");

                if (!Validar.isNumeric(recorrido.getText())) 
                    alert("Ingreso de Carros","ERROR","ERROR! Recorrido menor a 0!");

                if (!Validar.isNumericD(precio.getText())) 
                    alert("Ingreso de Carros","ERROR","ERROR! Precio menor a 0!");
                return false;
            } else if (placa.getText().isEmpty() && marca.getText().isEmpty() && modelo.getText().isEmpty() && tipoMotor.getText().isEmpty() && year.getText().isEmpty() && tipoVehiculo.getText().isEmpty() && recorrido.getText().isEmpty() && color.getText().isEmpty() && TipoCombustible.getText().isEmpty() && Vidrios.getText().isEmpty() && Transmision.getText().isEmpty() && precio.getText().isEmpty()) {
                    alert("Ingreso de Carros","ERROR","Debe llenar todos los campos para el ingreso del nuevo vehiculo!");
            }
            return false;
        }
    }
    
    public static void notification(String titulo, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public static void alert(String titulo, String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public static boolean ventanaOferta(TextField correo, TextField precio){
        if (isNumericD(precio.getText()) && isCorreo(correo.getText())) {
            return true;
        } else if (!isNumericD(precio.getText()) && !isCorreo(correo.getText())) {
            precio.setStyle("-fx-control-inner-background: red");
            correo.setStyle("-fx-control-inner-background: red");
            alert("Realizar Oferta", "ERROR", "Modificar Campo:\n - Correo\n - Precio");
        } else if (!isNumericD(precio.getText())) {
            precio.setStyle("-fx-control-inner-background: red");
            alert("Realizar Oferta", "ERROR", "Modificar Campo: Precio");
        } else if (!isCorreo(correo.getText())) {
            correo.setStyle("-fx-control-inner-background: red");
            alert("Realizar Oferta", "ERROR", "Modificar Campo: Correo");
        }
        return false;
    }
    
    private static boolean compareNumber(TextField n1, TextField n2, boolean integer){
        if (integer) {
            try {
                int m1,m2;
                m1 = Integer.valueOf(n1.getText());
                m2 = Integer.valueOf(n2.getText());
                if (Integer.compare(m1, m2) == -1 || Integer.compare(m1, m2) == 0) {
                    return true;
                }
            } catch (NumberFormatException e) {
                Logger.getLogger(Validar.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            try {
                double m1,m2;
                m1 = Double.valueOf(n1.getText());
                m2 = Double.valueOf(n2.getText());
                if (Double.compare(m1, m2) == -1 || Double.compare(m1, m2) == 0) {
                    return true;
                }
            } catch (NumberFormatException e) {
                Logger.getLogger(Validar.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return false;
    }
    
    private static boolean compareEmptyFields(TextField n1, TextField n2) {
        if (!n1.getText().isEmpty() && !n2.getText().isEmpty()) {
            return true;
        } else if ((!n1.getText().isEmpty() && n2.getText().isEmpty())) {
            n2.setStyle("-fx-control-inner-background: red");
            return false;
        } else if (n1.getText().isEmpty() && !n2.getText().isEmpty()) {
            n1.setStyle("-fx-control-inner-background: red");
            return false;
        }
        alert("Validacion de Campos", "ERROR", "Debe llenar campos Desde y Hasta");
        return false;
    }
    
}
