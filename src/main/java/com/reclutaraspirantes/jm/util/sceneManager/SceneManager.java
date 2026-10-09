package main.java.com.reclutaraspirantes.jm.util.sceneManager;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.java.com.reclutaraspirantes.jm.exception.VistaException;

public class SceneManager {

    private final Stage stage;
    private Callback<Class<?>, Object> fabricaControladores;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void setFabricaControladores(Callback<Class<?>, Object> fabrica) {
        this.fabricaControladores = fabrica;
    }

    public void cambiarVista(String archivoFxml, String titulo) {
        try {
         
            String rutaRelativa = "/main/resources/view/" + archivoFxml;
            
            URL resourceUrl = getClass().getResource(rutaRelativa);
            
            if (resourceUrl == null) {

                resourceUrl = getClass().getResource("/" + archivoFxml);
            }
            
            if (resourceUrl == null) {
                throw new IOException("No se pudo encontrar el archivo FXML: " + archivoFxml);
            }

            FXMLLoader loader = new FXMLLoader(resourceUrl);
            loader.setControllerFactory(fabricaControladores);
            Parent raiz = loader.load();
            
            stage.setScene(new Scene(raiz));
            stage.setTitle(titulo);
            stage.show();
            
        } catch (IOException e) {
            throw new VistaException("No se pudo cargar la vista " + archivoFxml, e);
        }
    }
}