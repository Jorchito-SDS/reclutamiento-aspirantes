package main.java.com.reclutaraspirantes.jm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.reclutaraspirantes.jm.config.ConnectionDb;
import main.java.com.reclutaraspirantes.jm.exception.RepositorioException;
import main.java.com.reclutaraspirantes.jm.model.Rol;

public class RolRepository {

    private final ConnectionDb conexion;

 
    public RolRepository(ConnectionDb conexion) {
        this.conexion = conexion;
    }

    public List<Rol> listar() {
        String sql = "SELECT id_rol, nombre_rol FROM rol ORDER BY nombre_rol";
        try (Connection c = conexion.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Rol> roles = new ArrayList<>();
            while (rs.next()) {
                roles.add(new Rol(rs.getInt("id_rol"), rs.getString("nombre_rol")));
            }
            return roles;
        } catch (SQLException e) {
            throw new RepositorioException("Error al listar los roles.", e);
        }
    }
}