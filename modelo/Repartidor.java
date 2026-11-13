package modelo;

public abstract class Repartidor {
    private String codigo;
    private String nombre;
    private boolean disponible;

    // el constructor
    public Repartidor(String codigo, String nombre) {
        setCodigo(codigo);
        setNombre(nombre);
        this.disponible = true;
    }


    //los getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    //los setters con validaciones
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
        return "Repartidor [Código: " + codigo + ", Nombre: " + nombre + ", Disponible: " + (disponible ? "Sí" : "No") + "]";
    }


}


