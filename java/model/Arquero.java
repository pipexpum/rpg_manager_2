package model;

public class Arquero extends Personaje {

    public Arquero(String nombre, int nivel) {
        super(nombre, nivel, 110, 35, "Lluvia de Flechas");
    }

    @Override
    public String usarHabilidadEspecial() {
        return getNombre() + " dispara LLUVIA DE FLECHAS causando " + (getAtaque() * 2) + " de daño!";
    }
}