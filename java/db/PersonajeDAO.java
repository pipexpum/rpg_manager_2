package db;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonajeDAO {

    // CREATE — Insertar personaje en BD
    public void insertar(Personaje p) {
        String sql = "INSERT INTO personaje (nombre, tipo, nivel, vida, ataque, habilidad_especial) VALUES (?,?,?,?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getClass().getSimpleName());
            ps.setInt(3, p.getNivel());
            ps.setInt(4, p.getVida());
            ps.setInt(5, p.getAtaque());
            ps.setString(6, p.getHabilidadEspecial());
            ps.executeUpdate();
            System.out.println("Personaje guardado en BD.");

        } catch (SQLException e) {
            System.out.println(" Error al insertar: " + e.getMessage());
        }
    }

    // READ — Listar todos los personajes
    public List<Personaje> listarTodos() {
        List<Personaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM personaje";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Personaje p = crearPersonaje(rs);
                if (p != null) lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // READ — Buscar por nombre
    public List<Personaje> buscarPorNombre(String nombre) {
        List<Personaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM personaje WHERE nombre LIKE ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Personaje p = crearPersonaje(rs);
                if (p != null) lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return lista;
    }

    // UPDATE — Actualizar nivel
    public void actualizarNivel(int id, int nuevoNivel) {
        String sql = "UPDATE personaje SET nivel = ? WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nuevoNivel);
            ps.setInt(2, id);
            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Nivel actualizado correctamente.");
            else
                System.out.println("No se encontró personaje con ID " + id);

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    // DELETE — Eliminar por ID
    public void eliminar(int id) {
        String sql = "DELETE FROM personaje WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println(" Personaje eliminado.");
            else
                System.out.println(" No se encontró personaje con ID " + id);

        } catch (SQLException e) {
            System.out.println(" Error al eliminar: " + e.getMessage());
        }
    }

    // Método auxiliar — convierte ResultSet a objeto Personaje
    private Personaje crearPersonaje(ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipo");
        String nombre = rs.getString("nombre");
        int nivel = rs.getInt("nivel");
        Personaje p;

        switch (tipo) {
            case "Guerrero": p = new Guerrero(nombre, nivel); break;
            case "Mago":     p = new Mago(nombre, nivel);     break;
            case "Arquero":  p = new Arquero(nombre, nivel);  break;
            default: return null;
        }
        p.setId(rs.getInt("id"));
        p.setVida(rs.getInt("vida"));
        p.setAtaque(rs.getInt("ataque"));
        p.setHabilidadEspecial(rs.getString("habilidad_especial"));
        return p;
    }
}