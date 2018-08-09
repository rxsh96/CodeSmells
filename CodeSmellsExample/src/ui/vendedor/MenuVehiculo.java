/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.vendedor;

import entities.Vehiculo;
import io.IOFile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import tempo.TempoData;
import utils.Constants;
import utils.Validar;

/**
 *
 * @author rxsh96
 */
public class MenuVehiculo {
    
    private Stage menuVehiculoStage;
    private Stage secundaryStage;
    
    private StackPane root;
    private GridPane contenedor;
    
    private Label lPlaca;
    private Label lMarca;
    private Label lModelo;
    private Label lTipoMotor;
    private Label lYear;
    private Label lTipoVehiculo;
    private Label lRecorrido;
    private Label lColor;
    private Label lTipoCombustible;
    private Label lVidrios;
    private Label lTransmision;
    private Label lPrecio;
    private Label lTitle;
            
    private TextField tPlaca;
    private TextField tMarca;
    private TextField tModelo;
    private TextField tTipoMotor;
    private TextField tYear;
    private TextField tTipoVehiculo;
    private TextField tRecorrido;
    private TextField tColor;
    private TextField tTipoCombustible;
    private TextField tVidrios;
    private TextField tTransmision;
    private TextField tPrecio;
    private String imageUrl;
    
    private FileChooser fileChooser;
    
    private Button bLoadImage;
    private Button bGuardar;
    private Button bAtras;
    
    private ImageView imgView;

    private TempoData data;
    
    public MenuVehiculo(Stage stage){
        this.menuVehiculoStage = stage;
        data = new TempoData();
        contSetUp();
        backgroundSetup();
        setUpActions();
        initVehiculoLista();
        setImageLogo();
    }
    
    
    private void initElements(){
        root = new StackPane();
        root.setId("rootSecundary");
        contenedor = new GridPane();
        lPlaca = new Label("Placa"); 
        lMarca = new Label("Marca"); 
        lModelo = new Label("Modelo"); 
        lTipoMotor = new Label("Tipo de Motor"); 
        lYear = new Label("Year"); 
        lTipoVehiculo = new Label("Tipo de Vehiculo"); 
        lRecorrido = new Label("Recorrido"); 
        lColor = new Label("Color"); 
        lTipoCombustible = new Label("Tipo de Combustible"); 
        lVidrios = new Label("Vidrios"); 
        lTransmision = new Label("Transmision"); 
        lPrecio = new Label("Precio"); 
        tPlaca = new TextField();
        tPlaca.setPromptText("Ej: GQI0531");
        tMarca = new TextField();  
        tMarca.setPromptText("Ej: Ford, Toyota, etc");
        tModelo = new TextField();  
        tModelo.setPromptText("Modelo");
        tTipoMotor = new TextField();
        tTipoMotor.setPromptText("Tipo de Motor");
        tYear = new TextField();
        tYear.setPromptText("Año de Fabricación");
        tTipoVehiculo = new TextField();    
        tTipoVehiculo.setPromptText("Tipo de Vehiculo");
        tRecorrido = new TextField();
        tRecorrido.setPromptText("Recorrido en km");
        tColor = new TextField();
        tColor.setPromptText("Color");
        tTipoCombustible = new TextField();
        tTipoCombustible.setPromptText("Tipo de combustible");
        tVidrios = new TextField();    
        tVidrios.setPromptText("Vidrios");
        tTransmision = new TextField();
        tTransmision.setPromptText("Transmision");
        tPrecio = new TextField();
        tPrecio.setPromptText("$ 0.00");
        fileChooser = new FileChooser();
        imgView = new ImageView();
        bLoadImage = new Button("Subir Imagen");
        bLoadImage.setId("subButtonC");
        bGuardar = new Button("Guardar");
        bGuardar.setId("subButtonC");
        bAtras = new Button("Atras");
        bAtras.setId("subButtonC");
        lTitle = new Label("Ingreso de Vehiculo:");
        lTitle.setTextFill(Paint.valueOf("brown"));
        lTitle.setFont(Font.font(18));
    }

    
    private void contSetUp(){
        initElements();
        root.getChildren().add(contenedor);
        
        contenedor.add(lTitle, 0, 0);
        
        contenedor.add(lPlaca, 0, 2);
        contenedor.add(lMarca, 0, 3);
        contenedor.add(lModelo, 0, 4);
        contenedor.add(lTipoMotor, 0, 5);
        contenedor.add(lYear, 0, 6);
        contenedor.add(lTipoVehiculo, 0, 7);
        
        contenedor.add(lRecorrido, 2, 2);
        contenedor.add(lColor, 2, 3);
        contenedor.add(lTipoCombustible, 2, 4);
        contenedor.add(lVidrios, 2, 5);
        contenedor.add(lTransmision, 2, 6);
        contenedor.add(lPrecio, 2, 7);
        
        contenedor.add(bLoadImage, 0, 12, 2, 1);
        contenedor.add(bAtras, 0, 19, 2, 1);
        
        contenedor.add(tPlaca, 1, 2);
        contenedor.add(tMarca, 1, 3);
        contenedor.add(tModelo, 1, 4);
        contenedor.add(tTipoMotor, 1, 5);
        contenedor.add(tYear, 1, 6);
        contenedor.add(tTipoVehiculo, 1, 7);
        
        contenedor.add(tRecorrido, 3, 2);
        contenedor.add(tColor, 3, 3);
        contenedor.add(tTipoCombustible, 3, 4);
        contenedor.add(tVidrios, 3, 5);
        contenedor.add(tTransmision, 3, 6);
        contenedor.add(tPrecio, 3, 7);
        contenedor.add(imgView, 3, 12, 2, 1);
        contenedor.add(bGuardar, 3, 19, 2, 1);
        
        contenedor.setVgap(10);
        contenedor.setHgap(10);
        contenedor.setAlignment(Pos.TOP_CENTER);
        contenedor.setPadding(new Insets(30, 0, 0, 0));
    }
    
    public void vehicleMaker(){
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(tPlaca.getText().toUpperCase());
        vehiculo.setMarca(tMarca.getText());
        vehiculo.setModelo(tModelo.getText());
        vehiculo.setTipoMotor(tTipoMotor.getText());
        vehiculo.setYear(tYear.getText());
        vehiculo.setTipo(tTipoVehiculo.getText());
        vehiculo.setRecorrido(tRecorrido.getText());
        vehiculo.setColor(tColor.getText());
        vehiculo.setTipoCombustible(tTipoCombustible.getText());
        vehiculo.setVidrios(tVidrios.getText());
        vehiculo.setTransmision(tTransmision.getText());
        vehiculo.setPrecio(tPrecio.getText());
        vehiculo.setImageURL(imageUrl);
        IOFile.ioFileVehiculo(vehiculo);

    }

    private void initVehiculoLista(){
        data.setVehiculosDisponibles(IOFile.loadVehiculos());
    }

    private void setUpActions(){
        setActionLoadImage();
        setActionAtras();
        setActionGuardar();
    }
    
    private void setActionLoadImage(){
        bLoadImage.setOnMouseClicked((MouseEvent e) -> {
            File file = fileChooser.showOpenDialog(this.menuVehiculoStage);
            if(file != null){
                String url = file.getPath();
                imageUrl = url.replaceAll("\\\\", "/");
                BufferedImage bufferedImage;
                try {
                    bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    imgView.setImage(image);
                    imgView.setFitHeight(Constants.IMAGE_FIT_HEIGHT);
                    imgView.setFitWidth(Constants.IMAGE_FIT_WIDTH);
                } catch (IOException ex) {
                    Logger.getLogger(MenuVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void setImageLogo(){
        imgView.setImage(new Image("images/carLogo.jpg"));
        imgView.setFitHeight(120);
        imgView.setFitWidth(150);
    }
    
    private void setActionAtras(){
        bAtras.setOnMouseClicked((MouseEvent e) -> {
            goBack();
        });
    }
    
    private void goBack(){
        secundaryStage = new Stage();
        Scene scene = new Scene(new MenuVendedor(secundaryStage).getRoot(), Constants.MENU_DEFAULT_WIDTH, Constants.MENU_DEFAULT_LENGTH);
        secundaryStage.setTitle("Compra de Autos");
        secundaryStage.setResizable(false);
        secundaryStage.setScene(scene);
        this.menuVehiculoStage.close();
        secundaryStage.show();
    }
    
    private void setActionGuardar(){
        bGuardar.setOnMouseClicked((MouseEvent e) -> {
            if (IOFile.placaVerifier(tPlaca.getText())) {
                if (Validar.validarPrincipales(true, tPlaca, tMarca, tModelo, tTipoMotor, tYear, tTipoVehiculo, tRecorrido, tColor, tTipoCombustible, tVidrios, tTransmision, tPrecio)) {
                    vehicleMaker();
                    data.setVehiculosDisponibles(IOFile.loadVehiculos());
                    Validar.notification("Ingreso de Carros", "Operacion exitosa!", "Se ha modificado la informacion correctamente!");
                    goBack();
                }
            }
            if (Validar.validarPrincipales(false, tPlaca, tMarca, tModelo, tTipoMotor, tYear, tTipoVehiculo, tRecorrido, tColor, tTipoCombustible, tVidrios, tTransmision, tPrecio)) {
                vehicleMaker();
                data.setVehiculosDisponibles(IOFile.loadVehiculos());
                Validar.notification("Ingreso de Carros", "Operacion exitosa!", "Se ha guardado la informacion correctamente!");
                goBack();
            }
        });
    }    
    
    private void backgroundSetup() {
        root.getStylesheets().add("style/Style.css");
    }
    
    public StackPane getRoot() {
        return root;
    }

}

