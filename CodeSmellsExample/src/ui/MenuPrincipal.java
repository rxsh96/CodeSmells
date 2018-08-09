/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import ui.vendedor.MenuVendedor;
import ui.comprador.MenuComprador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utils.Constants;

/**
 *
 * @author rxsh96
 */

/* EJEMPLO DE DATA CLUMPS Y DE CODIGO DUPLICADO
    ESPECIFICAMENTE EN TODOS LOS METODOS DE TODAS LAS CLASES DENTRO DEL PAQUETE UI 
    LA ESTRUCTURA DE LOS MISMOS ES MUY SIMILAR
*/
public class MenuPrincipal {

    private final Stage menuPrincipalStage;
    private Stage secundaryStage;
    
    private StackPane root;
    private VBox contenedor;
    
    private Button bVendedor;
    private Button bComprador;
    private Button bReporte;
    private Button bSalir;
    
    public MenuPrincipal(Stage stage){
        this.menuPrincipalStage = stage;
        contSetUp();
        backgroundSetup();
        setUpButtons();
    }
    
    private void initElements(){
        root = new StackPane();
        root.setId("rootPrincipal");
        contenedor = new VBox();
        bVendedor = new Button("Vendedor");
        bComprador = new Button("Comprador");
        bReporte = new Button("Reporte");
        bSalir = new Button("Salir");
    }
    
    private void contSetUp(){
        initElements();
        root.getChildren().add(contenedor);
        contenedor.getChildren().addAll(bVendedor, bComprador, bReporte, bSalir);
        StackPane.setAlignment(contenedor, Pos.CENTER);
        contenedor.setSpacing(30);
        contenedor.setPadding(new Insets(200, 30, 200, 250));
    }

    private void backgroundSetup() {
        root.getStylesheets().add("style/Style.css");
    }
    
    public StackPane getRoot() {
        return root;
    }
    
    private void setUpButtons(){
        setActionComprador();
        setActionVendedor();
        setActionReporte();
        setActionSalir();
    }
    
    private void setActionVendedor(){
        bVendedor.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuVendedor(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
            secundaryStage.setTitle("Menu del Vendedor");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.menuPrincipalStage.close();
            secundaryStage.show();
        });
    }
    
    private void setActionComprador(){
        bComprador.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuComprador(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
            secundaryStage.setTitle("Menu del Comprador");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.menuPrincipalStage.close();
            secundaryStage.show();
        });
    }
    
    private void setActionReporte(){
        bReporte.setOnMouseClicked((MouseEvent e) -> {
        secundaryStage = new Stage();
        Scene scene = new Scene(new VentanaReporte(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
        secundaryStage.setTitle("Reportes");
        secundaryStage.setResizable(false);
        secundaryStage.setScene(scene);
        this.menuPrincipalStage.close();
        secundaryStage.show();
        });
    }
    
    private void setActionSalir(){
        bSalir.setOnMouseClicked((MouseEvent e) -> {
            
        });
    
    }
    
}
