package modelo;

public class Moto extends Repartidor {
    private static final double TARIFA_BASE = 3000;
    private static final double VALOR_KM = 1000;
    private static final double VELOCIDAD = 30;
    private static final double DISTANCIA_MAX = 15;

    public Moto(String codigo, String nombre, boolean disponible) {
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