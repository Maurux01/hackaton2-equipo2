package modelo;

public class Carro extends Repartidor {
    private static final double TARIFA_BASE = 5000;
    private static final double VALOR_KM = 1200;
    private static final double VELOCIDAD = 25;

    public Carro(String codigo, String nombre, boolean disponible) {
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
        return true;
    }
}