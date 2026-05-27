package controller;

import db.PersonajeDAO;
import model.*;
import java.util.List;

public class PersonajeController {

    private PersonajeDAO dao = new PersonajeDAO();

    // Crear personaje según tipo
    public void crearPersonaje(String nombre, String tipo, int nivel) {
        Personaje p;
        switch (tipo.toLowerCase()) {
            case "guerrero":
                p = new Guerrero(nombre, nivel);
                break;
            case "mago":
                p = new Mago(nombre, nivel);
                break;
            case "arquero":
                p = new Arquero(nombre, nivel);
                break;
            default:
                System.out.println(" Tipo inválido. Usa: Guerrero, Mago o Arquero");
                return;
        }
        dao.insertar(p);
    }

    // Listar todos
    public void listarPersonajes() {
        List<Personaje> lista = dao.listarTodos();
        if (lista.isEmpty()) {
            System.out.println(" No hay personajes en la BD.");
            return;
        }
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("        LISTA DE PERSONAJES");
        System.out.println("═══════════════════════════════════════");
        for (Personaje p : lista) {
            System.out.println(p);
        }
        System.out.println("═══════════════════════════════════════\n");
    }

    // Buscar por nombre
    public void buscarPorNombre(String nombre) {
        List<Personaje> lista = dao.buscarPorNombre(nombre);
        if (lista.isEmpty()) {
            System.out.println("No se encontró ningún personaje con ese nombre.");
            return;
        }
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("        RESULTADOS DE BÚSQUEDA");
        System.out.println("═══════════════════════════════════════");
        for (Personaje p : lista) {
            System.out.println(p);
        }
        System.out.println("═══════════════════════════════════════\n");
    }

    // Actualizar nivel
    public void actualizarNivel(int id, int nuevoNivel) {
        if (nuevoNivel < 1 || nuevoNivel > 100) {
            System.out.println(" El nivel debe estar entre 1 y 100.");
            return;
        }
        dao.actualizarNivel(id, nuevoNivel);
    }

    // Eliminar personaje
    public void eliminarPersonaje(int id) {
        dao.eliminar(id);
    }
}