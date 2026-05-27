package model;

public class Guerrero extends Personaje {

    public Guerrero(String nombre, int nivel) {
        super(nombre, nivel, 150, 25, "Golpe Brutal");
    }

    @Override
    public String usarHabilidadEspecial() {
        return getNombre() + " usa GOLPE BRUTAL causando " + (getAtaque() * 2) + " de daño!";
    }
}