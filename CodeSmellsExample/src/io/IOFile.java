/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import entities.Oferta;
import entities.Vehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tda.CircularDoublyLinkedList;


/**
 *
 * @author rxsh96
 */


/*
    Ejemplo de Long Class y de Comments
*/
public class IOFile {
    
    private static final String FILEAUTOS = "files/autos.txt";
    private static final String FILEOFERTAS = "files/ofertas.txt";

    private IOFile(){}
    
    public static void ioFileVehiculo(Vehiculo vehiculo){
        if(!placaVerifier(vehiculo)){
            writer(vehiculo);
        }
        else{
            modifier(vehiculo);
        }
    }
    
    //Metodo que verifica que la placa exista dentro del archivo de los autos
    public static boolean placaVerifier(String placa){
        File file = new File(FILEAUTOS);
        if(file.exists() && placa != null && !"".equals(placa)){
            try (Scanner lector = new Scanner(file)){
                while(lector.hasNextLine()){
                    String info = lector.nextLine();
                    String infoPlaca = info.split("\\|")[0];
                    if(placa.equalsIgnoreCase(infoPlaca)){
                        return true;
                    }
                }
                lector.close();
            } 
            catch (FileNotFoundException ex){
                Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    
    public static boolean placaVerifier(Vehiculo vehiculo){
        File file = new File(FILEAUTOS);
        if(file.exists()){
            try (Scanner lector = new Scanner(file)){
                while(lector.hasNextLine()){
                    String info = lector.nextLine();
                    String infoPlaca = info.split("\\|")[0];
                    if(vehiculo.getPlaca().equalsIgnoreCase(infoPlaca)){
                        return true;
                    }
                }
                lector.close();
            } 
            catch (FileNotFoundException ex){
                Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
      
    //METODO QUE VERIFICA QUE LA OFERTA EXISTA DENTRO DEL ARCHIVO DE LAS OFERTAS
    public static boolean ofertaExists(String placa){
        File file = new File(FILEOFERTAS);
        if(file.exists() && placa != null && !"".equals(placa)){
            try (Scanner lector = new Scanner(file)){
                while(lector.hasNextLine()){
                    String info = lector.nextLine();
                    String infoPlaca = info.split("\\|")[0];
                    if(placa.equalsIgnoreCase(infoPlaca)){
                        return true;
                    }
                }
                lector.close();
            } 
            catch (FileNotFoundException ex){
                Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    //ESTE METODO ESCRIBE LA INFORMACION DE UN VEHICULO EN EL ARCHIVO DE VEHICULOS
    private static void writer(Vehiculo vehiculo){
        File file = new File(FILEAUTOS);
        try {
            FileWriter writer;
            if (file.exists()){
                writer = new FileWriter(file, true);
            } 
            else{
                writer = new FileWriter(file);
            }
            try(PrintWriter printW = new PrintWriter(writer)){
                printW.append(vehiculo.toString()+"\n");
                writer.close();
            }
        } 
        catch(IOException ex){
            Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    
    /*Ejemplo de Long Method*/
    //ESTE METODO MODIFICA LA INFORMACION DE UN VEHICULO EN EL ARCHIVO DE LOS VEHICULOS
    private static void modifier(Vehiculo vehiculo){
        File file = new File(FILEAUTOS);
        try {
            FileInputStream fInputStream = new FileInputStream(file);
            try (BufferedReader bReader = new BufferedReader(new InputStreamReader(fInputStream))) {
                StringBuilder sBuilder = new StringBuilder();
                String actualInfo;
                while((actualInfo = bReader.readLine()) != null){
                    if(actualInfo.split("\\|")[0].equalsIgnoreCase(vehiculo.getPlaca())){
                        String newLine = updater(actualInfo, vehiculo);
                        sBuilder.append(newLine).append("\n");
                    }
                    else{
                        sBuilder.append(actualInfo).append("\n");
                    }
                }
                FileWriter fstreamWrite = new FileWriter(file);
                try (BufferedWriter bWriter = new BufferedWriter(fstreamWrite)) {
                    bWriter.write(sBuilder.toString());
                }
            }
        } 
        catch (IOException e) {
            Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
    
    /* EJEMPLO DE SWITCH STATEMENTS */
    // ESTE METODO ACTUALIZA LA INFORMACION DE LOS VEHICULOS
    private static String updater(String actualInfo, Vehiculo vehiculo){
        String[] actualInfoElements = actualInfo.split("\\|");
        String[] newInfoElements = vehiculo.toString().split("\\|");
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0 ; i < newInfoElements.length ; i++) {
            if("null".equals(newInfoElements[i]) || "".equalsIgnoreCase(newInfoElements[i])){
                strBuilder.append(newInfoElements[i].replaceAll(newInfoElements[i], actualInfoElements[i]));
                if(i != newInfoElements.length-1){
                    strBuilder.append("|");
                }
            }           
            else{
                strBuilder.append(actualInfoElements[i].replaceAll(actualInfoElements[i], newInfoElements[i]));
                if(i != newInfoElements.length-1){
                    strBuilder.append("|");
                }
            }
        }
        return strBuilder.toString();
    }
    
    public static CircularDoublyLinkedList<Vehiculo> loadVehiculos(){
        CircularDoublyLinkedList<Vehiculo> listaVehiculos = new CircularDoublyLinkedList<>();
        File file = new File(FILEAUTOS);
        try {
            FileReader fileReader = new FileReader(file);
            try (BufferedReader bReader = new BufferedReader(fileReader)) {
                String actualInfo;
                while((actualInfo = bReader.readLine()) != null){
                    listaVehiculos.addFirst(vehiculoSetter(actualInfo));
                }
            }
        } 
        catch (IOException e) {
            Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, e);
        }
        return listaVehiculos;
    }
        
    // METODO QUE INICIALIZA LA INFORMACION DE LOS VEHICULOS CON LA ENCONTRADA EN EL ARCHIVO Y
    // SETEA LA NUEVA INFORMACION EN LOS CAMPOS VACIOS
    private static Vehiculo vehiculoSetter(String actualInfo){
        Vehiculo vehiculo = new Vehiculo();
        String[] vehiculoData = actualInfo.split("\\|");
        vehiculo.setPlaca(vehiculoData[0]);
        vehiculo.setMarca(vehiculoData[1]);
        vehiculo.setModelo(vehiculoData[2]);
        vehiculo.setTipoMotor(vehiculoData[3]);
        vehiculo.setYear(vehiculoData[4]);
        vehiculo.setTipo(vehiculoData[5]);
        vehiculo.setRecorrido(vehiculoData[6]);
        vehiculo.setColor(vehiculoData[7]);
        vehiculo.setTipoCombustible(vehiculoData[8]);
        vehiculo.setVidrios(vehiculoData[9]);
        vehiculo.setTransmision(vehiculoData[10]);
        vehiculo.setPrecio(vehiculoData[11]);
        vehiculo.setImageURL(vehiculoData[12]);
        return vehiculo;
    }
    
    
    //METODO QUE ESCRIBE UNA OFERTA EN EL ARCHIVO DE OFERTAS
    public static void writer(Oferta oferta){
        File file = new File(FILEOFERTAS);
        try {
            FileWriter writer;
            if(file.exists()){
                writer = new FileWriter(file, true);
            } 
            else{
                writer = new FileWriter(file);
            }
            try(PrintWriter printW = new PrintWriter(writer)){
                printW.append(oferta.toString()+"\n");
                writer.close();
            }
        } 
        catch(IOException ex) {
            Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    
    //METODO QUE ME CARGA TODA LA INFORMACION DEL ARCHIVO DE LAS OFERTAS EN UNA LISTA
    public static List<Oferta> loadOfertas(){
        List<Oferta> listaOfertas = new ArrayList<>();
        File file = new File(FILEOFERTAS);
        try {
            FileReader fileReader = new FileReader(file);
            try (BufferedReader bReader = new BufferedReader(fileReader)) {
                String actualInfo;
                while((actualInfo = bReader.readLine()) != null){
                    listaOfertas.add(ofertaSetter(actualInfo));
                }
            }
        } 
        catch (IOException e) {
            Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, e);
        }
        return listaOfertas;
    }

    public static List<Oferta> loadVehiculosOfertados(String placa){
        List<Oferta> listaOfertas = new ArrayList<>();
        File file = new File(FILEOFERTAS);
        try {
            FileReader fileReader = new FileReader(file);
            try (BufferedReader bReader = new BufferedReader(fileReader)) {
                String actualInfo;
                while((actualInfo = bReader.readLine()) != null){
                    if(actualInfo.split("\\|")[0].equalsIgnoreCase(placa)){
                        listaOfertas.add(ofertaSetter(actualInfo));
                    }
                }
            }
        } 
        catch (IOException e) {
            Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, e);
        }
        return listaOfertas;
    }
    
    public static void writer(Oferta oferta, Vehiculo vehiculo){
        File file = new File(FILEOFERTAS);
        try {
            FileWriter writer;
            if(file.exists()){
                writer = new FileWriter(file, true);
            } 
            else{
                writer = new FileWriter(file);
            }
            try(PrintWriter printW = new PrintWriter(writer)){
                printW.append(oferta.toString()+"\n");
                vehiculo.setOfertas(oferta);
                writer.close();
            }
        } 
        catch(IOException ex) {
            Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public static void editOfertaFile(String lineContent){
        File file = new File(FILEOFERTAS);
        if (file.exists()) {
            try{
                try (Stream<String> stream = Files.lines(file.toPath())) {
                    List<String> out = stream.filter(line -> !line.contains(lineContent)).collect(Collectors.toList());
                    Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
                }
            } 
            catch (IOException ex) {
                Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void editVehiculoFile(String lineContent){
        File file = new File(FILEAUTOS);
        if (file.exists()) {
            try{
                try (Stream<String> stream = Files.lines(file.toPath())) {
                    List<String> out = stream.filter(line -> !line.contains(lineContent)).collect(Collectors.toList());
                    Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
                }
            } 
            catch (IOException ex) {
                Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static Oferta ofertaSetter(String actualInfo) {
        Oferta oferta = new Oferta();
        String[] ofertaData = actualInfo.split("\\|");
        oferta.setPlaca(ofertaData[0]);
        oferta.setCorreo(ofertaData[1]);
        oferta.setPrecioOfertado(ofertaData[2]);
        return oferta;
    }
    
    public static Vehiculo obtenerVehiculo(String placa){
        File file = new File(FILEAUTOS);
        Vehiculo vehiculo = new Vehiculo();
        if(file.exists() && placa != null && !"".equals(placa)){
            try (Scanner lector = new Scanner(file)){
                while(lector.hasNextLine()){
                    String info = lector.nextLine();
                    String infoPlaca = info.split("\\|")[0];
                    if(placa.equalsIgnoreCase(infoPlaca)){
                        vehiculo = IOFile.vehiculoSetter(info);
                        return vehiculo;
                    }
                }
                lector.close();
            } 
            catch (FileNotFoundException ex){
                Logger.getLogger(IOFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return vehiculo;
    }
}
