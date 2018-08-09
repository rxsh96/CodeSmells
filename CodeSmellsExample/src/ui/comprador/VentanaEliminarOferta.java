/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.comprador;

import com.sun.prism.impl.Disposer.Record;
import entities.Oferta;
import io.IOFile;
import java.util.ArrayList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tempo.TempoData;
import utils.Constants;

/**
 *
 * @author rxsh96
 */
public class VentanaEliminarOferta {
    
    private Stage ventanaEliminarOferta;
    private Stage secundaryStage;
    
    private BorderPane root;
    private HBox contenedor;
    
    private TableView tOfertas;
    
    private Button bOfertar;
    private Button bAtras;
    
    private TempoData data;
    private ObservableList<Oferta> datos;
 
    public VentanaEliminarOferta(Stage ventanaEliminarOferta){
        this.ventanaEliminarOferta = ventanaEliminarOferta;
        data = new TempoData();
        data.setOfertasDisponibles((ArrayList<Oferta>) IOFile.loadOfertas());
        datos = FXCollections.observableArrayList();
        initElements();
        initButtons();
        setUpElements();
        backgroundSetup();
    }

    private void initElements(){
        root = new BorderPane();
        root.setId("rootI");
        contenedor = new HBox();
        tOfertas = new TableView();
        bOfertar = new Button("Ofertar");
        bOfertar.setId("subButtonC");
        bAtras = new Button("Volver al Menu");
        bAtras.setId("subButtonC");
    }
    
    private void setUpElements(){
        setUpTable();
        root.setPadding(new Insets(50));
        root.setCenter(tOfertas);
        root.setBottom(contenedor);
        root.setPadding(new Insets(10));
        contenedor.getChildren().addAll(bAtras, bOfertar);
        contenedor.setPadding(new Insets(10, 0, 0, 0));
        contenedor.setSpacing(200);
        contenedor.setAlignment(Pos.CENTER);
    }
    
    private void setUpTable(){
        datos = FXCollections.observableList(data.getOfertasDisponibles());
        tOfertas.setItems(datos);
        TableColumn<Oferta, String> vehiculo = new TableColumn("Vehiculo");
        vehiculo.setResizable(false);
        vehiculo.setMinWidth(100);
        vehiculo.setCellValueFactory(new PropertyValueFactory<>("placa"));
        TableColumn<Oferta, String> correo = new TableColumn("Correo");
        correo.setResizable(false);
        correo.setMinWidth(150);
        correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        TableColumn<Oferta, String> precio = new TableColumn("Valor Ofertado");
        precio.setResizable(false);
        precio.setMinWidth(100);
        precio.setCellValueFactory(new PropertyValueFactory<>("precioOfertado"));
        TableColumn delete = new TableColumn<>("Eliminar Oferta");
        delete.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>(){
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        delete.setCellFactory(new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>(){
            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();  
            }
        });
        vehiculo.prefWidthProperty().bind(tOfertas.widthProperty().divide(4));
        correo.prefWidthProperty().bind(tOfertas.widthProperty().divide(4));
        precio.prefWidthProperty().bind(tOfertas.widthProperty().divide(4));
        delete.prefWidthProperty().bind(tOfertas.widthProperty().divide(4));
        tOfertas.getColumns().addAll(vehiculo, correo, precio, delete);
    }
    

    
    private void backgroundSetup() {
        root.getStylesheets().add("style/Style.css");
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    private void initButtons(){
        setActionMenu();
        setActionOfertar();
    }
    
    private void setActionMenu(){
        bAtras.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuComprador(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
            secundaryStage.setTitle("Menu del Comprador");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.ventanaEliminarOferta.close();
            secundaryStage.show();
        });   
    }
    
    private void setActionOfertar(){
        bOfertar.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuOferta(secundaryStage).getRoot(), 700, 550);
            secundaryStage.setTitle("Ofertas");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.ventanaEliminarOferta.close();
            secundaryStage.show();
        });
    }
    
    private class ButtonCell extends TableCell<Record, Boolean> {
        Button bDelete = new Button("Delete");
        private ButtonCell(){
            bDelete.setId("subButtonE");
            bDelete.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                	Oferta ofertaActual = (Oferta) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	datos.remove(ofertaActual);
                        data.getOfertasDisponibles().remove(ofertaActual);
                        IOFile.editOfertaFile(ofertaActual.toString());
                }
            });
        }
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(bDelete);
            }
            else{
                setGraphic(null);
            }
        }
    }
    
}
