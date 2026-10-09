    package main.java.com.reclutaraspirantes.jm.controller;

    import javafx.fxml.FXML;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.PasswordField;
    import javafx.scene.control.TextField;
    import main.java.com.reclutaraspirantes.jm.dto.RegistroDTO;
    import main.java.com.reclutaraspirantes.jm.dto.RolDTO;
    import main.java.com.reclutaraspirantes.jm.exception.RepositorioException;
    import main.java.com.reclutaraspirantes.jm.exception.ValidacionException;
    import main.java.com.reclutaraspirantes.jm.util.Alertas;
    import main.java.com.reclutaraspirantes.jm.util.sceneManager.SceneManager;
    import main.java.com.reclutaraspirantes.jm.service.AuthService;
    public class RegistroController {

        private final AuthService authService;
        private final SceneManager sceneManager;

        @FXML private TextField txtUsuario;
        @FXML private PasswordField txtContrasena;
        @FXML private PasswordField txtConfirmacion;
        @FXML private ComboBox<RolDTO> cmbRol;

        public RegistroController(AuthService authService, SceneManager sceneManager) {
            this.authService = authService;
            this.sceneManager = sceneManager;
        }

        @FXML
        private void initialize() {
            try {
                cmbRol.getItems().setAll(authService.listarRoles());
            } catch (RepositorioException e) {
                Alertas.error(e.getMessage());
            }
        }

        @FXML
        private void onRegistrar() {
            try {
                authService.registrar(new RegistroDTO(txtUsuario.getText(), txtContrasena.getText(),
                        txtConfirmacion.getText(), cmbRol.getValue()));
                Alertas.info("Usuario registrado correctamente.");
                sceneManager.cambiarVista("Login.fxml", "Iniciar sesión");
            } catch (ValidacionException | RepositorioException e) {
                Alertas.error(e.getMessage());
            }
        }

        @FXML
        private void onVolver() {
            sceneManager.cambiarVista("Login.fxml", "Iniciar sesión");
        }
    }
