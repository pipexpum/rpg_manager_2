package model;

public class Mago extends Personaje {

    public Mago(String nombre, int nivel) {
        super(nombre, nivel, 90, 40, "Bola de Fuego");
    }

    @Override
    public String usarHabilidadEspecial() {
        return getNombre() + " lanza BOLA DE FUEGO causando " + (getAtaque() * 3) + " de daño mágico!";
    }
}