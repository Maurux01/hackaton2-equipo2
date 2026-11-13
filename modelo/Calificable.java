package modelo;

public interface Calificable {
    void recibirCalificacion(int calificacion);
    double obtenerPromedio();

    default double getPromedio() {
        return obtenerPromedio();
    }
}
