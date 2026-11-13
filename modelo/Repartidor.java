package modelo;

public abstract class Repartidor implements Calificable{
    private String codigo;
    private String nombre;
    private boolean disponible;

    //Calificaciones
    protected  int sumaCalificaciones= 0;
    protected  int cantidadCalificaciones =0 ;

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

    //Interfaz calificable
    @Override
    public void recibirCalificacion(int nota) {
        if (nota < 1 || nota > 5 ){
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
        }
        this.sumaCalificaciones += nota;
        this.cantidadCalificaciones++;
    }

    @Override
    public double getPromedio() {
        if (cantidadCalificaciones == 0){
            return  0.0;
        }
        return (double) sumaCalificaciones / cantidadCalificaciones;
    }


    // Comportamientos abstractos - Se ponene abstractos por los costos tiempo y el llevar varian en el vehiculo, barrio etc.
    public abstract double calcularCosto(double km);
    public abstract  double calcularTiempo(double km);
    public abstract  boolean puedeLlevar(double km, boolean esGrande);

}


