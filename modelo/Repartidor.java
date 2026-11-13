package modelo;

import java.util.ArrayList;

public abstract class Repartidor implements Calificable {
    private String codigo;
    private String nombre;
    private boolean disponible;
    private ArrayList<Integer> calificaciones = new ArrayList<>();

    public Repartidor(String codigo, String nombre, boolean disponible) {
        setCodigo(codigo);
        setNombre(nombre);
        setDisponible(disponible);
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public boolean isDisponible() { return disponible; }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio.");
        }
        this.codigo = codigo.trim();
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre.trim();
    }

    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public abstract double calcularCosto(double km);
    public abstract double calcularTiempo(double km);
    public abstract boolean puedeLlevar(double km, boolean grande);

    public String getTipo() { return this.getClass().getSimpleName(); }

    @Override
    public void recibirCalificacion(int calificacion) {
        if (calificacion < 1 || calificacion > 5) {
            throw new IllegalArgumentException("La calificacion debe estar entre 1 y 5.");
        }
        calificaciones.add(calificacion);
    }

    @Override
    public double obtenerPromedio() {
        if (calificaciones.isEmpty()) return 0.0;
        int suma = 0;
        for (int c : calificaciones) suma += c;
        return (double) suma / calificaciones.size();
    }

    @Override
    public String toString() {
        return codigo + " " + nombre + " (" + getTipo() + ") - disponible: " + disponible
                + " - promedio: " + obtenerPromedio();
    }
}