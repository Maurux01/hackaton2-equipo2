package modelo;

public class Bici extends Repartidor {
    private static final double TARIFA_BASE = 2000;
    private static final double VALOR_KM = 800;
    private static final double VELOCIDAD = 15;
    private static final double DISTANCIA_MAX = 5;

    public Bici(String codigo, String nombre, boolean disponible) {
        super(codigo, nombre, disponible);
    }

    @Override
    public double calcularCosto(double km) {
        return TARIFA_BASE + (km * VALOR_KM);
    }

    @Override
    public double calcularTiempo(double km) {
        return km / VELOCIDAD * 60;
    }

    @Override
    public boolean puedeLlevar(double km, boolean grande) {
        if (grande) return false;
        return km <= DISTANCIA_MAX;
    }
}