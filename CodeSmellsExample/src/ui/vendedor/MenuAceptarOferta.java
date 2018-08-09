/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.vendedor;

import entities.Oferta;
import entities.Vehiculo;
import io.IOFile;
import java.io.File;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tda.CircularDoublyLinkedList;
import tempo.TempoData;
import utils.Constants;
import utils.Validar;

/**
 *
 * @author allisonbarrezueta
 */
public class MenuAceptarOferta {
    
    private Stage menuAceptarOferta;
    private Stage secundaryStage;
    
    private BorderPane root;
    private HBox contenedorSuperior;
    private VBox contenedorBottom;
    private GridPane contenedorCentro;
    private HBox contenedorBotonesMenu;
    private HBox contenedorBotonesInfo;
    private StackPane contenedorImagen;
    
    private Label lTitle;
    private Label lPlaca1;
    private Label lPlaca2;
    private Label lPrecio1;
    private Label lPrecio2;
    private Label lCorreo1;
    private Label lCorreo2;
    
    private Button bNext;
    private Button bPrevious;
    private Button bAceptar;
    private Button bSalir;
    
    private TempoData data;
    private Vehiculo vehiculo;
    private ImageView image;
    
    private CircularDoublyLinkedList<Oferta> ofertasVehiculo;
    private Oferta oferta;
    
    public MenuAceptarOferta(Stage menuAceptarOferta, Vehiculo vehiculo) {
        this.menuAceptarOferta = menuAceptarOferta;
        this.vehiculo = vehiculo;
        
        elementsSetUp();
        backgroundSetup();
        setUpButton();
        initOfertasLista();
        showImage();
    }

    public BorderPane getRoot() {
        return root;
    }

    private void setUpButton() {
        setActionSalir();
        setActionAceptar();
        setActionSiguiente();
        setActionAnterior();
    }

    private void initElements() {
        root = new BorderPane();
        root.setId("rootAOf");
        
        data = new TempoData();
        data.setOfertasDisponibles((ArrayList<Oferta>) IOFile.loadOfertas());
        ofertasVehiculo = CircularDoublyLinkedList.toCircularList((ArrayList) IOFile.loadVehiculosOfertados(vehiculo.getPlaca()));
        oferta = ofertasVehiculo.getFirst(); 
        contenedorSuperior = new HBox();
        contenedorBottom = new VBox();
        contenedorCentro = new GridPane();
        contenedorBotonesInfo = new HBox();
        contenedorBotonesMenu = new HBox();
        contenedorImagen = new StackPane();
        
        lTitle = new Label("Ofertas");
        lTitle.setId("lblOferta");
        
        image = new ImageView();
        
        bPrevious = new Button("Atras");
        bNext = new Button("Siguiente");
        bSalir = new Button("Salir");
        bAceptar = new Button("Aceptar");
        
        bPrevious.setId("subButtonE");
        bNext.setId("subButtonE");
        bSalir.setId("subButtonE");
        bAceptar.setId("subButtonE");
        
        lPlaca1 = new Label("Placa: ");
        lPlaca1.setId("lblAceptarOferta");
        lPlaca2 = new Label(oferta.getPlaca());
        lPlaca2.setId("lblAceptarOferta");
        lPrecio1 = new Label("Precio Ofertado");
        lPrecio1.setId("lblAceptarOferta");
        lPrecio2 = new Label(oferta.getPrecioOfertado());
        lPrecio2.setId("lblAceptarOferta");
        lCorreo1 = new Label("Correo: ");
        lCorreo1.setId("lblAceptarOferta");
        lCorreo2 = new Label(oferta.getCorreo());
        lCorreo2.setId("lblAceptarOferta");
    }
    
    private void showImage(){
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
    
    private void elementsSetUp() {
        initElements();
        contenedorCentroSetUp();
        root.setTop(contenedorSuperior);
        root.setCenter(contenedorImagen);
        root.setLeft(contenedorCentro);
        root.setBottom(contenedorBottom);
        
        contenedorBottom.getChildren().addAll(contenedorBotonesInfo, contenedorBotonesMenu);
        contenedorBottom.setAlignment(Pos.CENTER);
        contenedorBottom.setSpacing(20);
        contenedorBotonesInfo.getChildren().addAll(bPrevious,bNext);
        contenedorBotonesMenu.getChildren().addAll(bSalir, bAceptar);
        contenedorBotonesInfo.setSpacing(270);
        contenedorBotonesInfo.setPadding(new Insets(15, 0, 10, 55));
        contenedorBotonesMenu.setSpacing(280);
        contenedorBotonesMenu.setPadding(new Insets(0, 0, 15, 55));
        contenedorImagen.setAlignment(Pos.CENTER);
        contenedorImagen.setPadding(new Insets(10, 10, 10, 20));
        contenedorSuperior.getChildren().addAll(lTitle);
        contenedorSuperior.setAlignment(Pos.TOP_RIGHT);
    }
    
    
    private void setActionSalir() {
        bSalir.setOnMouseClicked((event) -> {
            goBack();
        });
    }

    private void goBack(){
        secundaryStage = new Stage();
        Scene scene = new Scene(new MenuVendedor(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
        secundaryStage.setTitle("Compra de Autos");
        secundaryStage.setResizable(false);
        secundaryStage.setScene(scene);
        this.menuAceptarOferta.close();
        secundaryStage.show();
    }
    
    private void setActionAceptar() {
        bAceptar.setOnMouseClicked((event) -> {
            IOFile.editOfertaFile(oferta.toString());
            IOFile.editVehiculoFile(vehiculo.toString());
            data.setOfertasDisponibles((ArrayList<Oferta>) IOFile.loadVehiculosOfertados(vehiculo.getPlaca()));
            ofertasVehiculo = CircularDoublyLinkedList.toCircularList((ArrayList) IOFile.loadVehiculosOfertados(vehiculo.getPlaca()));
            Validar.notification("Aceptar Venta", "Venta Exitosa", "Haz vendido tu carro!");
            goBack();
        });
    }

    private void setActionSiguiente() {
        bNext.setOnMouseClicked((event) -> {
            oferta = ofertasVehiculo.getNext();
            lPrecio2.setText(oferta.getPrecioOfertado());
            lCorreo2.setText(oferta.getCorreo());
        });
    }

    private void setActionAnterior() {
        bPrevious.setOnMouseClicked((event) -> {
            oferta = ofertasVehiculo.getPrev();
            lPrecio2.setText(oferta.getPrecioOfertado());
            lCorreo2.setText(oferta.getCorreo());
        });
    }

    private void backgroundSetup() {
        root.getStylesheets().add("style/Style.css");
    }

    private void contenedorCentroSetUp() {        
        contenedorCentro.add(contenedorImagen,0, 0);
        contenedorCentro.add(lPlaca1, 3, 0);
        contenedorCentro.add(lPlaca2, 4, 0);
        contenedorCentro.add(lPrecio1, 3, 1);
        contenedorCentro.add(lPrecio2, 4, 1);
        contenedorCentro.add(lCorreo1, 3, 3);
        contenedorCentro.add(lCorreo2, 4, 3);
        
        contenedorCentro.setPadding(new Insets(20, 5, 10, 5));
        contenedorCentro.setAlignment(Pos.CENTER);
        contenedorCentro.setHgap(5);
        contenedorCentro.setVgap(5);
    }

    private void initOfertasLista() {
        data.setOfertasDisponibles((ArrayList<Oferta>) vehiculo.getOfertas());
    }

    
}