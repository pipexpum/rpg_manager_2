package model;

public abstract class Personaje {
    private int id;
    private String nombre;
    private int nivel;
    private int vida;
    private int ataque;
    private String habilidadEspecial;

    public Personaje(String nombre, int nivel, int vida, int ataque, String habilidadEspecial) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.ataque = ataque;
        this.habilidadEspecial = habilidadEspecial;
    }

    // Método polimórfico — cada subclase lo sobreescribe
    public abstract String usarHabilidadEspecial();

    // Getters y Setters con validación
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        this.nombre = nombre;
    }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) {
        if (nivel < 1 || nivel > 100)
            throw new IllegalArgumentException("El nivel debe estar entre 1 y 100");
        this.nivel = nivel;
    }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }

    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }

    public String getHabilidadEspecial() { return habilidadEspecial; }
    public void setHabilidadEspecial(String h) { this.habilidadEspecial = h; }

    @Override
    public String toString() {
        return String.format("[%d] %s | Tipo: %s | Nivel: %d | Vida: %d | Ataque: %d | Habilidad: %s",
            id, nombre, getClass().getSimpleName(), nivel, vida, ataque, habilidadEspecial);
    }
}