package modelo;

public class Moto extends Repartidor{
    public Moto (String codigo, String nombre){
        super(codigo, nombre);
    }

    //Abstractos
    @Override
    public double calcularCosto(double km) {
        return 3000 + (km * 1000);
    }

    @Override
    public double calcularTiempo(double km) {
        return (km / 30 ) * 60;
    }

    @Override
    public boolean puedeLlevar(double km, boolean esGrande) {
        //si son 15km o menos Y no lleva pedidos grandes
        return km <= 15 && !esGrande ;
    }
}
