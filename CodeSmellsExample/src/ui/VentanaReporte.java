/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.Oferta;
import io.IOFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tempo.TempoData;
import utils.Constants;

/**
 *
 * @author rxsh96
 * Sustentation class
 */
public class VentanaReporte {
    
    private Stage primaryStage;
    private Stage secundaryStage;
    
    private VBox root;
    
    private Button bSalir;
    private TempoData data;
    HashMap<String, Integer> map;
    
    public VentanaReporte(Stage ventanaReporte){
        primaryStage = ventanaReporte;
        initElements();
        contadorOfertas();
        fillVbox();
        setActionRegresar();
        root.getChildren().add(bSalir);
        backgroundSetup();
    }

    private void initElements(){
        initData();
        root = new VBox();
        map = new HashMap<>();
        bSalir = new Button("Regresar al Menu");
    }
    
    private void initData(){
        data = new TempoData();
        data.setOfertasDisponibles((ArrayList<Oferta>) IOFile.loadOfertas());
    }
    
    private void contadorOfertas(){
        for (int i = 0; i < data.getOfertasDisponibles().size(); i++) {
        int cont = 0;
            for (int j = 0; j < data.getOfertasDisponibles().size(); j++) {
                if (data.getOfertasDisponibles().get(j).getCorreo().equalsIgnoreCase(data.getOfertasDisponibles().get(i).getCorreo())) {
                    cont++;
                }
            }
            map.put(data.getOfertasDisponibles().get(i).getCorreo(), cont);
        }
    }
    
    private void fillVbox(){
        Set<String> keySet = map.keySet();
        for (String string : keySet) {
             root.getChildren().add(new Label("Usuario: "+string+ "\nOfertas: "+map.get(string)));
        }
    }
    
    public VBox getRoot() {
        return root;
    }
    
    private void setActionRegresar(){
        bSalir.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuPrincipal(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
            secundaryStage.setTitle("Compra de Autos");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.primaryStage.close();
            secundaryStage.show();
        });
    }    
    
    private void backgroundSetup() {
        root.getStylesheets().add("style/Style.css");
    }
}
