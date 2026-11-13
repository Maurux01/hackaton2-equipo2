package servicio;

import modelo.*;
import java.util.*;

public class DomiService {
    private HashMap<String, Cliente> clientes = new HashMap<>();
    private HashSet<String> barrios = new HashSet<>();
    private HashMap<String, Double> distancias = new HashMap<>();
    private ArrayList<Repartidor> repartidores = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    private int siguienteNumeroPedido = 1;
    private boolean modoLluvia = false;

    public DomiService() {
        cargarBarrios();
        cargarRepartidores();
        cargarClientes();
    }

    private void cargarBarrios() {
        agregarBarrio("Chapinero", 1.5);
        agregarBarrio("Teusaquillo", 3.0);
        agregarBarrio("La Candelaria", 4.5);
        agregarBarrio("Usaquén", 6.0);
        agregarBarrio("Cedritos", 8.0);
        agregarBarrio("Fontibón", 11.0);
        agregarBarrio("Suba", 12.0);
        agregarBarrio("Kennedy", 14.0);
        agregarBarrio("Bosa", 18.0);
    }

    private void agregarBarrio(String nombre, double km) {
        barrios.add(nombre);
        distancias.put(nombre, km);
    }

    private void cargarRepartidores() {
        repartidores.add(new Bici("R-01", "Camila Torres", true));
        repartidores.add(new Moto("R-02", "Andrés Mejía", true));
        repartidores.add(new Carro("R-03", "Jorge Pardo", true));
    }

    private void cargarClientes() {
        clientes.put("1020304050", new Cliente("1020304050", "Laura Gómez", "3001234567", "Teusaquillo", "Cra 1 #1"));
        clientes.put("1030405060", new Cliente("1030405060", "Mateo Rincón", "3109876543", "Usaquén", "Cra 2 #2"));
    }

    public boolean tieneCobertura(String barrio) { return barrios.contains(barrio); }
    public double distanciaDe(String barrio) { return distancias.get(barrio); }
    public Cliente buscarCliente(String cedula) { return clientes.get(cedula); }
    public boolean existeCliente(String cedula) { return clientes.containsKey(cedula); }
    public ArrayList<Repartidor> getRepartidores() { return repartidores; }
    public ArrayList<Pedido> getPedidos() { return pedidos; }
    public boolean isModoLluvia() { return modoLluvia; }
    public void setModoLluvia(boolean v) { this.modoLluvia = v; }
    public int siguienteNumeroPedido() { return siguienteNumeroPedido++; }
    public HashSet<String> getBarrios() { return barrios; }
    public HashMap<String, Double> getDistancias() { return distancias; }
    public HashMap<String, Cliente> getClientes() { return clientes; }
}