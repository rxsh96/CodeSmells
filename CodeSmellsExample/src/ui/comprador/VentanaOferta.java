/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.comprador;

import entities.Oferta;
import entities.Vehiculo;
import io.IOFile;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tda.CircularDoublyLinkedList;
import utils.Constants;

/**
 *
 * @author rxsh96
 */
public class VentanaOferta {
    
    private Stage primaryStage;
    private Stage secundaryStage;
    
    private GridPane root;
    
    private Vehiculo vehiculo;
    
    private Label lCorreo;
    private Label lPrecio;
    
    private TextField tCorreo;
    private TextField tPrecio;
    
    private Button bOfertar;
    private Button bAtras;
    private Button bMenu;
    
    private CircularDoublyLinkedList<Oferta> ofertas;
    
    
    public VentanaOferta(Stage ventanaOferta, Vehiculo vehiculo){
        this.primaryStage = ventanaOferta;
        this.vehiculo = vehiculo;
        ofertas = new CircularDoublyLinkedList();
        initElements();
        setContenedor();
        backgroundSetup();
        setUpButtons();
    }
 
    private void initElements(){
        root = new GridPane();
        lCorreo = new Label("Correo Electronico");
        lPrecio = new Label("Valor a Pagar");
        tCorreo = new TextField();
        tPrecio = new TextField();
        bOfertar = new Button("Ofertar");
        bOfertar.setId(Constants.ID_BUTTON);
        bOfertar.setDisable(true);
        bAtras = new Button("Atras");
        bAtras.setId(Constants.ID_BUTTON);
        bMenu = new Button("Volver al Menu");
        bMenu.setId(Constants.ID_BUTTON);
    }
    
    private void setContenedor(){
        root.add(lCorreo, 0, 0);
        root.add(tCorreo, 1, 0);
        root.add(lPrecio, 0, 1);
        root.add(tPrecio, 1, 1);
        root.add(bAtras, 0, 3);
        root.add(bOfertar, 1, 3);
        root.add(bMenu, 1, 4);
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(20);
    }

    private void bOfertarActivation(Button bGuardar){
        BooleanBinding bBinding = new BooleanBinding(){
            {super.bind(tCorreo.textProperty(),
                tPrecio.textProperty());
            }
            
            @Override
            protected boolean computeValue(){
                return(tCorreo.getText().isEmpty()
                    || tPrecio.getText().isEmpty());
            }
        };
        bGuardar.disableProperty().bind(bBinding); 
    }
    
    private void createOferta(){
        Oferta oferta = new Oferta(vehiculo.getPlaca(), tCorreo.getText(), tPrecio.getText());
        IOFile.writer(oferta, vehiculo);
        this.ofertas.addLast(oferta);
        
    }
    
    public GridPane getRoot() {
        return root;
    }
    
    private void backgroundSetup() {
        root.getStylesheets().add("style/Style.css");
    }
    
    private void setUpButtons(){
        setActionOfertar();
        setActionAtras();
        setActionMenu();
    }
    
    private void setActionOfertar(){
        bOfertarActivation(bOfertar);
        bOfertar.setOnMouseClicked((MouseEvent e) -> {
            createOferta();
            goBack();
        });
    }
    
    private void setActionAtras(){
        bAtras.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuOferta(secundaryStage).getRoot(), Constants.MENU_OFERTAS_WIDTH, Constants.MENU_OFERTAS_LENGTH);
            secundaryStage.setTitle("Compra de Autos");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.primaryStage.close();
            secundaryStage.show();
        });
    }
    
    private void setActionMenu(){
        bMenu.setOnMouseClicked((MouseEvent e) -> {
            goBack();
        });
    }

    private void goBack() {
        secundaryStage = new Stage();
        Scene scene = new Scene(new MenuComprador(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
        secundaryStage.setTitle("Menu del Comprador");
        secundaryStage.setResizable(false);
        secundaryStage.setScene(scene);
        primaryStage.close();
        secundaryStage.show();
    }
    
}
