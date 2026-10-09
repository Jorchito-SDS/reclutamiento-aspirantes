package main.java.com.reclutaraspirantes.jm;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.com.reclutaraspirantes.jm.config.ConnectionDb;
import main.java.com.reclutaraspirantes.jm.controller.InventarioController;
import main.java.com.reclutaraspirantes.jm.controller.LoginController;
import main.java.com.reclutaraspirantes.jm.controller.RegistroController;
import main.java.com.reclutaraspirantes.jm.repository.AspiranteRepository;
import main.java.com.reclutaraspirantes.jm.repository.RolRepository;
import main.java.com.reclutaraspirantes.jm.repository.UsuarioRepository;
import main.java.com.reclutaraspirantes.jm.service.AspiranteService;
import main.java.com.reclutaraspirantes.jm.service.AuthService;
import main.java.com.reclutaraspirantes.jm.util.sceneManager.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ConnectionDb conexion = new ConnectionDb();
        AuthService authService = new AuthService(new UsuarioRepository(conexion), new RolRepository(conexion));
        AspiranteService aspiranteService = new AspiranteService(new AspiranteRepository(conexion));

        SceneManager sceneManager = new SceneManager(stage);
        sceneManager.setFabricaControladores(tipo -> {
            if (tipo == LoginController.class) {
                return new LoginController(authService, sceneManager);
            }
            if (tipo == RegistroController.class) {
                return new RegistroController(authService, sceneManager);
            }
            if (tipo == InventarioController.class) {
                return new InventarioController(aspiranteService, sceneManager);
            }
            throw new IllegalArgumentException("Controlador desconocido: " + tipo);
        });

        sceneManager.cambiarVista("Login.fxml", "Iniciar sesión");
    }

    public static void main(String[] args) {
        launch(args);
    }
}