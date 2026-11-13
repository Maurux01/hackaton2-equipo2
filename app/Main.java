package app;

import servicio.DomiService;
import modelo.*;

public class Main {
    public static void main(String[] args) {
        DomiService servicio = new DomiService();

        System.out.println("=== DOMI - Datos de arranque ===");
        System.out.println("Barrios con cobertura: " + servicio.getBarrios().size());
        for (String b : servicio.getBarrios()) {
            System.out.println("  " + b + " -> " + servicio.distanciaDe(b) + " km");
        }

        System.out.println("Repartidores:");
        for (Repartidor r : servicio.getRepartidores()) {
            System.out.println("  " + r);
        }

        System.out.println("Clientes:");
        for (Cliente c : servicio.getClientes().values()) {
            System.out.println("  " + c);
        }
    }
}