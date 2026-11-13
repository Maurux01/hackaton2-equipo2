package modelo;

public class Dron extends Repartidor {

    private static final double TARIFA_BASE = 4000;
    private static final double VALOR_KM = 600;
    private static final double VELOCIDAD = 50;      // km/h
    private static final double DISTANCIA_MAX = 8;   // km

    public Dron(String codigo, String nombre) {
        super(codigo, nombre);
    }

    @Override
    public double calcularCosto(double km) {
        return TARIFA_BASE + km * VALOR_KM;
    }

    @Override
    public double calcularTiempo(double km) {
        return km / VELOCIDAD * 60;
    }

    @Override
    public boolean puedeLlevar(double km, boolean esGrande) {
        return !esGrande && km <= DISTANCIA_MAX;
    }

    @Override
    public boolean soportaLluvia() {
        return false;
    }
}
