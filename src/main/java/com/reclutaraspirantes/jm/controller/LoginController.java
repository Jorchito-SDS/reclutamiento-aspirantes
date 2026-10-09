package main.java.com.reclutaraspirantes.jm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.com.reclutaraspirantes.jm.dto.UsuarioDTO;
import main.java.com.reclutaraspirantes.jm.exception.AuthException;
import main.java.com.reclutaraspirantes.jm.exception.RepositorioException;
import main.java.com.reclutaraspirantes.jm.exception.ValidacionException;
import main.java.com.reclutaraspirantes.jm.service.AuthService;
import main.java.com.reclutaraspirantes.jm.util.Alertas;
import main.java.com.reclutaraspirantes.jm.util.sceneManager.SceneManager;


public class LoginController {

    private final AuthService authService;
    private final SceneManager sceneManager;

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;

    public LoginController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
    }

    @FXML
    private void onIngresar() {
        try {
            UsuarioDTO usuario = authService.iniciarSesion(txtUsuario.getText(), txtContrasena.getText());
            sceneManager.cambiarVista("Inventario.fxml", "Inventario de aspirantes - " + usuario.nombreUsuario()
                    + " (" + usuario.rol() + ")");
        } catch (AuthException | ValidacionException | RepositorioException e) {
            Alertas.error(e.getMessage());
        }
    }

    @FXML
    private void onIrARegistro() {
        sceneManager.cambiarVista("Registro.fxml", "Registro");
    }
}
