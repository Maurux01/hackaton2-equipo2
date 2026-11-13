package modelo;

public abstract class Repartidor implements Calificable {
    private String codigo;
    private String nombre;
    private boolean disponible;

    // Calificaciones
    protected int sumaCalificaciones = 0;
    protected int cantidadCalificaciones = 0;

    public Repartidor(String codigo, String nombre) {
        setCodigo(codigo);
        setNombre(nombre);
        this.disponible = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        this.codigo = codigo.trim();
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        String promStr = (cantidadCalificaciones == 0) 
                ? "Sin calificaciones" 
                : String.format(java.util.Locale.US, "%.1f ⭐ (%d calif.)", getPromedio(), cantidadCalificaciones);
        return "Repartidor [Código: " + codigo + ", Nombre: " + nombre + ", Disponible: " + (disponible ? "Sí" : "No") + ", Promedio: " + promStr + "]";
    }

    // =========================================================================
    // IMPLEMENTACIÓN DE INTERFAZ CALIFICABLE (Checkpoint 3)
    // =========================================================================
    @Override
    public void recibirCalificacion(int calificacion) {
        if (calificacion < 1 || calificacion > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
        }
        this.sumaCalificaciones += calificacion;
        this.cantidadCalificaciones++;
    }

    @Override
    public double obtenerPromedio() {
        if (cantidadCalificaciones == 0) {
            return 0.0;
        }
        return (double) sumaCalificaciones / cantidadCalificaciones;
    }

    @Override
    public double getPromedio() {
        return obtenerPromedio();
    }

    public int getCantidadCalificaciones() {
        return cantidadCalificaciones;
    }

    // =========================================================================
    // MÉTODOS POLIMÓRFICOS
    // =========================================================================
    public abstract double calcularCosto(double km);
    public abstract double calcularTiempo(double km);
    public abstract boolean puedeLlevar(double km, boolean esGrande);

    public boolean soportaLluvia() {
        return true;
    }
}
