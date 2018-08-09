/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.vendedor;

import entities.Oferta;
import entities.Vehiculo;
import tempo.TempoData;
import io.IOFile;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utils.Constants;

/**
 *
 * @author allisonbarrezueta
 */
public class MenuIngresarPlaca {
    
    private Stage menuIngresarPlaca;
    private Stage secundaryStage;
    
    private GridPane root;
    
    private VBox notification;
    private ScrollPane sp; 

    private Label lTitle;
    private Label lPlaca;
    
    private TextField tPlaca;
    
    private Button bSeleccionar;
    private Button bAtras;
    
    private TempoData data;
    
    public MenuIngresarPlaca(Stage menuIngresarPlaca){
        this.menuIngresarPlaca = menuIngresarPlaca;
        initElements();
        initListas();
        setNotificacion();
        setContenedor();
        backgroundSetup();
        setUpButtons();
        new hiloNotificacion(data.getOfertasDisponibles()).start();
    }

    public GridPane getRoot() {
        return root;
    }
    
    private void initElements() {
        notification = new VBox();
        notification.setId("vboxSc");
        sp = new ScrollPane();
        root = new GridPane();
        data = new TempoData();
        root.setId("rootI");
        lPlaca = new Label("Placa: ");
        lTitle = new Label("Ingreso de Placa:");
        tPlaca = new TextField();
        bSeleccionar = new Button("Seleccionar Placa");
        bSeleccionar.setId("subButtonC");
        lTitle.setFont(Font.font(16));
        lTitle.setId("lblIP");
        lPlaca.setId("lblIP");
        bAtras = new Button("Atras");
        bAtras.setId("subButtonC");
    }
    
    private void setNotificacion(){
        sp.setPrefSize(200, 500);
    }

    private void setContenedor() {
        root.add(lTitle, 1, 0);
        root.add(lPlaca, 1, 2);
        root.add(tPlaca, 3, 2);
        root.add(bSeleccionar, 3, 3);
        root.add(bAtras, 1, 3);
        root.add(sp, 7, 2);
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(20);
    }

    private void backgroundSetup() {
        root.getStylesheets().add("style/Style.css");
    }

    private void setUpButtons() {
        setActionOfertar();
        setActionAtras();
    }
    
    private void initListas(){
        data.setVehiculosDisponibles(IOFile.loadVehiculos());
        data.setOfertasDisponibles((ArrayList<Oferta>) IOFile.loadOfertas());
    }

    private void setActionOfertar() {
        bSeleccionar.setOnMouseClicked((MouseEvent e) -> {
            if(IOFile.ofertaExists(tPlaca.getText())){
                Vehiculo vehiculo = IOFile.obtenerVehiculo(tPlaca.getText());
                secundaryStage = new Stage();
                Scene scene = new Scene(new MenuAceptarOferta(secundaryStage, vehiculo).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
                secundaryStage.setTitle("Aceptar Oferta");
                secundaryStage.setResizable(false);
                secundaryStage.setScene(scene);
                MenuIngresarPlaca.this.menuIngresarPlaca.close();
                secundaryStage.show();
            }
        });
    }

    private void setActionAtras() {
        bAtras.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuVendedor(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
            secundaryStage.setTitle("Compra de Autos");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.menuIngresarPlaca.close();
            secundaryStage.show();
        });
    }

    
    private class hiloNotificacion extends Thread{
        private  ArrayList<Oferta> oferta;
        hiloNotificacion(ArrayList<Oferta> oferta){
            this.oferta = oferta;
        }
        
        @Override
        public void run() {
            for(int  i = oferta.size()-1; i >=0 ; i--){
                String texto = "Vehiculo:  "+oferta.get(i).getPlaca() ;
                Platform.runLater(()->actualizar(texto));
            }
        }
        
        private void actualizar(String linea){
            Label  notif = new Label();
            notif.setFont(Font.font(12));
            notif.setId("lblIP2");
            notif.setText(linea);
            notification.getChildren().add(notif);
            sp.setContent(notification);
        }
    }

}
