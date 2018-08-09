/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.vendedor;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.MenuPrincipal;
import utils.Constants;

/**
 *
 * @author rxsh96
 */
public class MenuVendedor {
    
    private final Stage menuVendedorStage;
    private Stage secundaryStage;
    
    private StackPane root;
    private VBox contenedor;
    
    private Button bVehiculo;
    private Button bOferta;
    private Button bAtras;
   
    
    public MenuVendedor(Stage stage){
        this.menuVendedorStage = stage;
        contSetUp();
        backgroundSetup();
        setUpButtons();
    }
    
    private void initElements(){
        root = new StackPane();
        root.setId("rootPrincipal");
        contenedor = new VBox();
        bVehiculo = new Button("Ingresar/Modificar Vehiculo");
        bOferta = new Button("Aceptar Oferta");
        bAtras = new Button("Menu Principal");
    }
    
    private void contSetUp(){
        initElements();
        root.getChildren().add(contenedor);
        contenedor.getChildren().addAll(bVehiculo, bOferta, bAtras);
        contenedor.setSpacing(30);
        contenedor.setPadding(new Insets(200, 30, 200, 200));
    }

    private void backgroundSetup() {
        root.getStylesheets().add("style/Style.css");
    }
    
    public StackPane getRoot() {
        return root;
    }
    
    private void setUpButtons(){
        setActionVehiculo();
        setActionRegresar();
        setActionOferta();
    }
    
    private void setActionVehiculo(){
        bVehiculo.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuVehiculo(secundaryStage).getRoot(), Constants.MENU_VENDEDOR_WIDTH, Constants.MENU_VENDEDOR_LENGTH);
            secundaryStage.setTitle("Ingreso/Modificacion de Vehiculo");
            secundaryStage.setScene(scene);
            secundaryStage.setResizable(false);
            secundaryStage.show();
            this.menuVendedorStage.close();
        });
    }
    
    private void setActionRegresar(){
        bAtras.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuPrincipal(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
            secundaryStage.setTitle("Compra de Autos");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.menuVendedorStage.close();
            secundaryStage.show();
        });
    }

    private void setActionOferta() {
        bOferta.setOnMouseClicked((MouseEvent e)->{
        secundaryStage = new Stage();
        Scene scene = new Scene(new MenuIngresarPlaca(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_INGRESAR_PLACA_LENGTH);
        secundaryStage.setTitle("Aceptar Oferta");
        secundaryStage.setResizable(false);
        secundaryStage.setScene(scene);
        menuVendedorStage.close();
        secundaryStage.show();
        
        });
    }
    
}


