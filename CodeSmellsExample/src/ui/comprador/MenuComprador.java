/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.comprador;

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
public class MenuComprador {
    
    private final Stage menuCompradorStage;
    private Stage secundaryStage;
    
    private StackPane root;
    private VBox contenedor;
    
    private Button bOfertar;
    private Button bEliminarOferta;
    private Button bAtras;
    
    public MenuComprador(Stage stage){
        this.menuCompradorStage = stage;
        contSetUp();
        backgroundSetup();
        setUpButtons();
    }
    
    private void initElements(){
        root = new StackPane();
        root.setId("rootPrincipal");
        contenedor = new VBox();
        bOfertar = new Button("Ofertar por un Vehiculo");
        bEliminarOferta = new Button("Eliminar Oferta");
        bAtras = new Button("Menu Principal");
    }
    
    private void contSetUp(){
        initElements();
        root.getChildren().add(contenedor);
        contenedor.getChildren().addAll(bOfertar, bEliminarOferta, bAtras);
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
        setActionRegresar();
        setActionOfertar();
        setActionEliminarOferta();
    }
    
    private void setActionOfertar(){
        bOfertar.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuOferta(secundaryStage).getRoot(), Constants.MENU_OFERTAS_WIDTH, Constants.MENU_OFERTAS__LENGTH);
            secundaryStage.setTitle("Ofertas");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.menuCompradorStage.close();
            secundaryStage.show();
        });
    }
    
    private void setActionEliminarOferta(){
        bEliminarOferta.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new VentanaEliminarOferta(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
            secundaryStage.setTitle("Eliminar Oferta");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.menuCompradorStage.close();
            secundaryStage.show();
        });
    }
    
    private void setActionRegresar(){
        bAtras.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuPrincipal(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
            secundaryStage.setTitle("Compra de Autos");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.menuCompradorStage.close();
            secundaryStage.show();
        });
    }    
}
