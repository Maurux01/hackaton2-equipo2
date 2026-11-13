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
// Crear pedido (opción 8)
    private void cargarClientes() {
        clientes.put("1020304050", new Cliente("1020304050", "Laura Gómez", "3001234567", "Teusaquillo", "Cra 1 #1"));
        clientes.put("1030405060", new Cliente("1030405060", "Mateo Rincón", "3109876543", "Usaquén", "Cra 2 #2"));
    }

    public Pedido crearPedido(String cedula, String descripcion, boolean grande) {
        Cliente cliente = clientes.get(cedula);
        if (cliente == null) {
            throw new IllegalArgumentException("No existe un cliente con esa cedula.");
        }
        double km = distanciaDe(cliente.getBarrio());

        Repartidor mejor = null;
        double mejorCosto = 0;
        double mejorTiempo = 0;
        boolean primero = true;

        for (Repartidor r : repartidores) {
            if (!r.isDisponible()) continue;
            if (!r.puedeLlevar(km, grande)) continue;

            double costo = r.calcularCosto(km);
            double tiempo = r.calcularTiempo(km);

            // Aplicar lluvia si está activa (nivel operación): +20% costo y bicis no disponibles
            if (modoLluvia) {
                if (r instanceof Bici) continue; // bici no vuela con lluvia
                costo = costo * 1.20;
            }

            if (primero || costo < mejorCosto
                    || (costo == mejorCosto && tiempo < mejorTiempo)) {
                mejor = r;
                mejorCosto = costo;
                mejorTiempo = tiempo;
                primero = false;
            }
        }

        if (mejor == null) {
            return null; // ningún repartidor puede llevarlo
        }

        mejor.setDisponible(false);
        int numero = siguienteNumeroPedido++;
        Pedido pedido = new Pedido(numero, cliente, descripcion, grande, km,
                mejor, mejorCosto, mejorTiempo, "EN_CAMINO");
        pedidos.add(pedido);
        return pedido;
    }

    // ===== PASO 4 (Valentina): entregar, cancelar y regla de eliminar cliente =====
    private Pedido buscarPedido(int numero) {
        for (Pedido p : pedidos) {
            if (p.getNumero() == numero) {
                return p;
            }
        }
        throw new IllegalArgumentException("No existe un pedido con el número " + numero);
    }

    public void entregarPedido(int numero) {
        Pedido pedido = buscarPedido(numero);
        if (pedido.getEstado() != EstadoPedido.EN_CAMINO) {
            throw new IllegalArgumentException(
                    "No se puede entregar: el pedido está " + pedido.getEstado());
        }
        pedido.setEstado(EstadoPedido.ENTREGADO);
        pedido.getRepartidor().setDisponible(true);
    }

    public void cancelarPedido(int numero) {
        Pedido pedido = buscarPedido(numero);
        if (pedido.getEstado() != EstadoPedido.EN_CAMINO) {
            throw new IllegalArgumentException(
                    "No se puede cancelar: el pedido está " + pedido.getEstado());
        }
        pedido.setEstado(EstadoPedido.CANCELADO);
        pedido.getRepartidor().setDisponible(true);
    }

    public boolean clienteTienePedidoEnCamino(String cedula) {
        for (Pedido p : pedidos) {
            if (p.getCliente().getCedula().equals(cedula)
                    && p.getEstado() == EstadoPedido.EN_CAMINO) {
                return true;
            }
        }
        return false;
    }
    public void eliminarCliente(String cedula) {
        if (!clientes.containsKey(cedula)) {
            throw new IllegalArgumentException("No existe un cliente con esa cédula.");
        }
        if (clienteTienePedidoEnCamino(cedula)) {
            throw new IllegalArgumentException(
                    "No se puede eliminar: el cliente tiene un pedido EN_CAMINO.");
        }
        clientes.remove(cedula);
    }
    // ===== FIN PASO 4 =====

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