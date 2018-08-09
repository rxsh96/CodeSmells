/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ui.comprador;

import entities.Vehiculo;
import io.IOFile;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tda.CircularDoublyLinkedList;
import tempo.TempoData;
import utils.Constants;

/**
 *
 * @author rxsh96
 */
public class MenuOferta {

    private Stage ofertaStage;
    private Stage secundaryStage;

    private BorderPane root;
    private GridPane contenedorSuperior;
    private StackPane contenedorImagen;
    private VBox contenedorTablas;
    private VBox contenedorBotones;
    private HBox contenedorBotonesInfo;
    private HBox contenedorBotonesMenu;

    private Label lTipoVehiculo;
    private Label lRecorrido;
    private Label lYear;
    private Label lPrecio;

    private Button bBuscar;
    private Button bSiguiente;
    private Button bAnterior;
    private Button bAtras;
    private Button bOfertar;

    private TextField tTipoVehiculo;
    private TextField tRecorridoDesde;
    private TextField tRecorridoHasta;
    private TextField tYearDesde;
    private TextField tYearHasta;
    private TextField tPrecioDesde;
    private TextField tPrecioHasta;

    private TableView table1;
    private TableView table2;
    private TableView table3;

    private ImageView image;

    private TempoData data;
    private CircularDoublyLinkedList<Vehiculo> filteredList;

    public MenuOferta(Stage menuOfertaStage){
        this.ofertaStage = menuOfertaStage;
        data = new TempoData();
        filteredList = new CircularDoublyLinkedList<>();
        elementsSetUp();
        initButtons();
        backgroundSetup();
        setImageLogo();
    }

    private void initElements(){
        root = new BorderPane();
        root.setId("rootSecundary");
        
        contenedorSuperior = new GridPane();
        contenedorTablas = new VBox();
        contenedorBotones = new VBox();
        contenedorBotonesInfo = new HBox();
        contenedorBotonesMenu = new HBox();
        contenedorImagen = new StackPane();
        
        lTipoVehiculo = new Label("Tipo de Vehiculo");
        lRecorrido = new Label("Recorrido");
        lYear = new Label("Year");
        lPrecio = new Label("Precio");
        
        bBuscar = new Button("Buscar");
        bSiguiente = new Button("Siguiente");
        bSiguiente.setDisable(true);
        bSiguiente.setId("subButtonC");
        bAnterior = new Button("Anterior");
        bAnterior.setDisable(true);
        bAtras = new Button("Volver al Menu");
        bOfertar = new Button("Ofertar");
        bOfertar.setDisable(true);
        bAnterior.setId("subButtonC");
        bBuscar.setId("subButtonC");
        bAtras.setId("subButtonC");
        bOfertar.setId("subButtonC");
        
        tTipoVehiculo = new TextField();
        tTipoVehiculo.setPromptText("Tipo de vehiculo");
        tRecorridoDesde = new TextField();
        tRecorridoDesde.setPromptText("Desde");
        tRecorridoHasta = new TextField();
        tRecorridoHasta.setPromptText("Hasta");
        tYearDesde = new TextField();
        tYearDesde.setPromptText("Desde");
        tYearHasta = new TextField();
        tYearHasta.setPromptText("Hasta");
        tPrecioDesde = new TextField();
        tPrecioDesde.setPromptText("Desde");
        tPrecioHasta = new TextField();
        tPrecioHasta.setPromptText("Hasta");
        
        image = new ImageView();
        
        table1 = new TableView();
        table2 = new TableView();
        table3 = new TableView();
    }

    private void elementsSetUp(){
        initElements();
        contenedorSuperiorSetUp();
        tableViewSetUp();
        root.setTop(contenedorSuperior);
        root.setRight(contenedorImagen);
        root.setCenter(contenedorTablas);
        root.setBottom(contenedorBotones);
        contenedorBotones.getChildren().addAll(contenedorBotonesInfo, contenedorBotonesMenu);
        contenedorBotones.setAlignment(Pos.CENTER);
        contenedorBotones.setSpacing(20);
        contenedorBotonesInfo.getChildren().addAll(bAnterior,bSiguiente);
        contenedorBotonesMenu.getChildren().addAll(bAtras, bOfertar);
        contenedorTablas.getChildren().addAll(table1, table2, table3);
        contenedorTablas.setPadding(new Insets(0, 50, 0, 50));
        contenedorBotonesInfo.setSpacing(350);
        contenedorBotonesInfo.setPadding(new Insets(15, 0, 10, 75));
        contenedorBotonesMenu.setSpacing(320);
        contenedorBotonesMenu.setPadding(new Insets(0, 0, 15, 75));
        contenedorImagen.setPadding(new Insets(10, 20, 10, 10));
    }

    private void contenedorSuperiorSetUp(){
        contenedorSuperior.add(lTipoVehiculo, 0, 0);
        contenedorSuperior.add(tTipoVehiculo, 1, 0);

        contenedorSuperior.add(lRecorrido, 0, 1);
        contenedorSuperior.add(tRecorridoDesde, 1, 1);
        contenedorSuperior.add(tRecorridoHasta, 2, 1);

        contenedorSuperior.add(lYear, 0, 2);
        contenedorSuperior.add(tYearDesde, 1, 2);
        contenedorSuperior.add(tYearHasta, 2, 2);

        contenedorSuperior.add(lPrecio, 0, 3);
        contenedorSuperior.add(tPrecioDesde, 1, 3);
        contenedorSuperior.add(tPrecioHasta, 2, 3);

        contenedorSuperior.add(bBuscar, 4, 4);

        contenedorSuperior.setPadding(new Insets(30, 0, 20, 0));
        contenedorSuperior.setAlignment(Pos.CENTER);
        contenedorSuperior.setHgap(5);
        contenedorSuperior.setVgap(5);
    }

    private void setImageLogo(){
        if(contenedorImagen.getChildren().isEmpty()){
            image = new ImageView("images/carLogo.jpg");
            image.setFitHeight(120);
            image.setFitWidth(150);
            contenedorImagen.getChildren().add(image);
        }
        else{
            contenedorImagen.getChildren().remove(0);
            setImageLogo();
        }
    }

    private void tableViewSetUp(){
        table1();
        table2();
        table3();
    }

    private void table1(){
        table1.setEditable(true);
        TableColumn<Vehiculo, String> placa = new TableColumn("Placa");
        placa.setResizable(false);
        placa.setCellValueFactory(new PropertyValueFactory<>("placa"));
        TableColumn<Vehiculo, String> marca = new TableColumn("Marca");
        marca.setResizable(false);
        marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        TableColumn<Vehiculo, String> modelo = new TableColumn("Modelo");
        modelo.setResizable(false);
        modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        TableColumn<Vehiculo, String> tipoMotor = new TableColumn("Tipo Motor");
        tipoMotor.setResizable(false);
        tipoMotor.setCellValueFactory(new PropertyValueFactory<>("tipoMotor"));
        table1.getColumns().addAll(placa, marca, modelo, tipoMotor);
        placa.prefWidthProperty().bind(table1.widthProperty().divide(4));
        marca.prefWidthProperty().bind(table1.widthProperty().divide(4));
        modelo.prefWidthProperty().bind(table1.widthProperty().divide(4));
        tipoMotor.prefWidthProperty().bind(table1.widthProperty().divide(4));
    }

    private void table2(){
        table2.setEditable(true);
        TableColumn year = new TableColumn("Año");
        year.setResizable(false);
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        TableColumn tipo = new TableColumn("Tipo");
        tipo.setResizable(false);
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        TableColumn recorrido = new TableColumn("Recorrido");
        recorrido.setResizable(false);
        recorrido.setCellValueFactory(new PropertyValueFactory<>("recorrido"));
        TableColumn color = new TableColumn("Color");
        color.setResizable(false);
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        table2.getColumns().addAll(year, tipo, recorrido, color);
        year.prefWidthProperty().bind(table2.widthProperty().divide(4));
        tipo.prefWidthProperty().bind(table2.widthProperty().divide(4));
        recorrido.prefWidthProperty().bind(table2.widthProperty().divide(4));
        color.prefWidthProperty().bind(table2.widthProperty().divide(4));
    }

    private void table3(){
        table3.setEditable(true);
        TableColumn tipoCombustible = new TableColumn("Combustible");
        tipoCombustible.setResizable(false);
        tipoCombustible.setCellValueFactory(new PropertyValueFactory<>("tipoCombustible"));
        TableColumn vidrios = new TableColumn("Vidrios");
        vidrios.setResizable(false);
        vidrios.setCellValueFactory(new PropertyValueFactory<>("vidrios"));
        TableColumn transmision = new TableColumn("Transmision");
        transmision.setResizable(false);
        transmision.setCellValueFactory(new PropertyValueFactory<>("transmision"));
        TableColumn precio = new TableColumn("Precio");
        precio.setResizable(false);
        precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        table3.getColumns().addAll(tipoCombustible, vidrios, transmision, precio);
        tipoCombustible.prefWidthProperty().bind(table3.widthProperty().divide(4));
        vidrios.prefWidthProperty().bind(table3.widthProperty().divide(4));
        transmision.prefWidthProperty().bind(table3.widthProperty().divide(4));
        precio.prefWidthProperty().bind(table3.widthProperty().divide(4));
    }

    private void filter(){
        filteredList = typeFilter(recorridoFilter(yearFilter(precioFilter(data.getVehiculosDisponibles()))));
    }


    private CircularDoublyLinkedList<Vehiculo> typeFilter(CircularDoublyLinkedList<Vehiculo> lista){
        CircularDoublyLinkedList<Vehiculo> newList = new CircularDoublyLinkedList<>();
        if("".equalsIgnoreCase(tTipoVehiculo.getText().trim())){
            return lista;
        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getTipo().trim().equalsIgnoreCase(tTipoVehiculo.getText().trim())) {
                    newList.addFirst(lista.get(i));
                }
            }
            return newList;
        }
    }

    private CircularDoublyLinkedList<Vehiculo> recorridoFilter(CircularDoublyLinkedList<Vehiculo> lista){
        CircularDoublyLinkedList<Vehiculo> newList = new CircularDoublyLinkedList<>();
        if("".equalsIgnoreCase(tRecorridoDesde.getText().trim()) || "".equalsIgnoreCase(tRecorridoHasta.getText().trim())){
            return lista;
        } else {
            double desde = Double.parseDouble(tRecorridoDesde.getText().trim());
            double hasta = Double.parseDouble(tRecorridoHasta.getText().trim());
            for (int i = 0; i < lista.size(); i++) {
                double recorrido = Double.parseDouble(lista.get(i).getRecorrido().trim());
                if (recorrido >= desde && recorrido <= hasta) {
                    newList.addFirst(lista.get(i));
                }
            }
            return newList;
        }
    }

    private CircularDoublyLinkedList<Vehiculo> yearFilter(CircularDoublyLinkedList<Vehiculo> lista){
        CircularDoublyLinkedList<Vehiculo> newList = new CircularDoublyLinkedList<>();
        if("".equalsIgnoreCase(tYearDesde.getText().trim()) || "".equalsIgnoreCase(tYearHasta.getText().trim())){
            return lista;
        }
        else{
            double desde = Double.parseDouble(tYearDesde.getText().trim());
            double hasta = Double.parseDouble(tYearHasta.getText().trim());
            for (int i = 0; i < lista.size(); i++) {
                double year = Double.parseDouble(lista.get(i).getYear().trim());
                if (year >= desde && year <= hasta) {
                    newList.addFirst(lista.get(i));
                }
            }
            return newList;
        }
    }

    private CircularDoublyLinkedList<Vehiculo> precioFilter(CircularDoublyLinkedList<Vehiculo> lista){
        CircularDoublyLinkedList<Vehiculo> newList = new CircularDoublyLinkedList<>();
        if("".equalsIgnoreCase(tPrecioDesde.getText().trim()) || "".equalsIgnoreCase(tPrecioHasta.getText().trim())){
            return lista;
        }
        else{
            double desde = Double.parseDouble(tPrecioDesde.getText().trim());
            double hasta = Double.parseDouble(tPrecioHasta.getText().trim());
            for (int i = 0; i < lista.size(); i++) {
                double precio = Double.parseDouble(lista.get(i).getPrecio().trim());
                if (precio >= desde && precio <= hasta) {
                    newList.addFirst(lista.get(i));
                }
            }
            return newList;
        }
    }

    private void backgroundSetup() {
        root.getStylesheets().add("style/Style.css");
    }

    public BorderPane getRoot() {
        return root;
    }


    private void showDataInTable(){
        if(!filteredList.isEmpty()){
            ObservableList<Vehiculo> datos = FXCollections.observableArrayList(filteredList.get(0));
            table1.setItems(datos);
            table2.setItems(datos);
            table3.setItems(datos);
            showImage();
        }
        else{
            setImageLogo();
            table1.getItems().clear();
            table2.getItems().clear();
            table3.getItems().clear();
        }
    }

    private void showImage(){
        if(!filteredList.isEmpty()){
            contenedorImagen.getChildren().remove(0);
            Vehiculo vehiculo = (Vehiculo)table1.getItems().get(0);
            if (vehiculo.getImageURL() == null) {
                showImage();
            }
            else{
                File imageFile = new File(vehiculo.getImageURL());
                String fileLocation = imageFile.toURI().toString();
                System.out.println();
                System.out.println(fileLocation);
                Image imagen = new Image(fileLocation); 
                image.setImage(imagen);
                image.setFitHeight(Constants.IMAGE_FIT_HEIGHT);
                image.setFitWidth(Constants.IMAGE_FIT_WIDTH);
                contenedorImagen.getChildren().add(image); 
            }
        }
    }

    private void initButtons(){
        setActionBuscar();
        setActionOfertar();
        setActionAtras();
        setActionSiguiente();
        setActionAnterior();
    }

    private void buttonValidator(){
        if(!filteredList.isEmpty()){
            bAnterior.setDisable(false);
            bSiguiente.setDisable(false);
            bOfertar.setDisable(false);
        }
        else{
            bAnterior.setDisable(true);
            bSiguiente.setDisable(true);
            bOfertar.setDisable(true);
        }
    }


    private void setActionBuscar(){
        bBuscar.setOnMouseClicked((MouseEvent e) -> {
            data.setVehiculosDisponibles(IOFile.loadVehiculos());
            filter();
            buttonValidator();
            System.out.println(filteredList);
            showDataInTable();
        });
    }


    private void setActionSiguiente(){
        bSiguiente.setOnMouseClicked((MouseEvent e) -> {
            ObservableList<Vehiculo> datos = FXCollections.observableArrayList(filteredList.getNext());
            table1.setItems(datos);
            table2.setItems(datos);
            table3.setItems(datos); 
            showImage();
        });
    }

    private void setActionAnterior(){
        bAnterior.setOnMouseClicked((MouseEvent e) -> {
            ObservableList<Vehiculo> datos = FXCollections.observableArrayList(filteredList.getPrev());
            table1.setItems(datos);
            table2.setItems(datos);
            table3.setItems(datos); 
            showImage();
        });
    }

    private void setActionAtras(){
        bAtras.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Scene scene = new Scene(new MenuComprador(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
            secundaryStage.setTitle("Menu del Comprador");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.ofertaStage.close();
            secundaryStage.show();
        }); 
    }

    private void setActionOfertar(){
        bOfertar.setOnMouseClicked((MouseEvent e) -> {
            secundaryStage = new Stage();
            Vehiculo vehiculo = (Vehiculo)table1.getItems().get(0);
            Scene scene = new Scene(new VentanaOferta(secundaryStage, vehiculo).getRoot(), Constants.MENU_OFERTA_WINDOW_WIDTH, Constants.MENU_OFERTAS_WINDOW_LENGTH);
            secundaryStage.setTitle("Realizar Oferta");
            secundaryStage.setResizable(false);
            secundaryStage.setScene(scene);
            this.ofertaStage.close();
            secundaryStage.show();
        });
    }

}