package modelo;

public class PruebaDron {

    public static void main(String[] args) {
        Dron dron = new Dron("R-04", "Dron 1");
        Bici bici = new Bici("R-01", "Camila Torres");
        Moto moto = new Moto("R-02", "Andres Mejia");
        Carro carro = new Carro("R-03", "Jorge Pardo");

        System.out.println("===== PRUEBA DRON =====");

        // Caso 11: pedido normal a Usaquen (6 km)
        System.out.println("\nCaso 11: Usaquen, 6 km");
        System.out.println("Costo dron: " + dron.calcularCosto(6) + " (esperado 7600.0)");
        System.out.println("Tiempo dron: " + dron.calcularTiempo(6) + " (esperado 7.2)");
        System.out.println("Dron puede llevar: " + dron.puedeLlevar(6, false) + " (esperado true)");
        System.out.println("Bici puede llevar: " + bici.puedeLlevar(6, false) + " (esperado false)");
        System.out.println("Costo moto: " + moto.calcularCosto(6) + " (esperado 9000.0)");
        System.out.println("Costo carro: " + carro.calcularCosto(6) + " (esperado 12200.0)");

        // Caso 12: lluvia, pedido a Chapinero (1.5 km)
        System.out.println("\nCaso 12: lluvia, Chapinero 1.5 km");
        System.out.println("Dron soporta lluvia: " + dron.soportaLluvia() + " (esperado false)");
        System.out.println("Moto con +20%: " + (moto.calcularCosto(1.5) * 1.2) + " (esperado 5400.0)");
        System.out.println("Tiempo moto: " + moto.calcularTiempo(1.5) + " (esperado 3.0)");

        // Restricciones propias del dron
        System.out.println("\nRestricciones del dron");
        System.out.println("Pedido grande: " + dron.puedeLlevar(3, true) + " (esperado false)");
        System.out.println("Mas de 8 km: " + dron.puedeLlevar(9, false) + " (esperado false)");
        System.out.println("Justo 8 km: " + dron.puedeLlevar(8, false) + " (esperado true)");
    }
}