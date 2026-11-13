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
        repartidores.add(new Bici("R-01", "Camila Torres"));
        repartidores.add(new Moto("R-02", "Andrés Mejía"));
        repartidores.add(new Carro("R-03", "Jorge Pardo"));
    }

    private void cargarClientes() {
        clientes.put("1020304050", new Cliente("1020304050", "Laura Gómez", "3001234567", "Teusaquillo", "Cra 1 #1"));
        clientes.put("1030405060", new Cliente("1030405060", "Mateo Rincón", "3109876543", "Usaquén", "Cra 2 #2"));
    }

    // =========================================================================
    // CREAR PEDIDO (Cotiza y asigna 100% polimórfico - Cero instanceof)
    // =========================================================================
    public Pedido crearPedido(String cedula, String descripcion, boolean grande) {
        Cliente cliente = clientes.get(cedula);
        if (cliente == null) {
            throw new IllegalArgumentException("No existe un cliente con esa cédula.");
        }
        double km = distanciaDe(cliente.getBarrio());

        Repartidor mejor = null;
        double mejorCosto = 0;
        double mejorTiempo = 0;
        boolean primero = true;

        for (Repartidor r : repartidores) {
            if (!r.isDisponible()) continue;
            if (modoLluvia && !r.soportaLluvia()) continue; // Polimórfico (cero instanceof)
            if (!r.puedeLlevar(km, grande)) continue;       // Polimórfico

            double costo = r.calcularCosto(km);
            double tiempo = r.calcularTiempo(km);

            // Aplicar lluvia: +20% costo y bicis descartadas arriba
            if (modoLluvia) {
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

    // =========================================================================
    // ENTREGAR, CANCELAR Y CALIFICAR (Checkpoint 2 y 3)
    // =========================================================================
    private Pedido buscarPedido(int numero) {
        for (Pedido p : pedidos) {
            if (p.getNumero() == numero) {
                return p;
            }
        }
        throw new IllegalArgumentException("No existe un pedido con el número " + numero);
    }

    public void entregarPedido(int numero, int calificacion) {
        Pedido pedido = buscarPedido(numero);
        if (pedido.getEstado() != EstadoPedido.EN_CAMINO) {
            throw new IllegalArgumentException(
                    "No se puede entregar: el pedido #" + numero + " ya está " + pedido.getEstado());
        }
        if (pedido.getRepartidor() != null) {
            pedido.getRepartidor().recibirCalificacion(calificacion); // Valida que esté entre 1 y 5
            pedido.getRepartidor().setDisponible(true);
        }
        pedido.setEstado(EstadoPedido.ENTREGADO);
    }

    public void entregarPedido(int numero) {
        Pedido pedido = buscarPedido(numero);
        if (pedido.getEstado() != EstadoPedido.EN_CAMINO) {
            throw new IllegalArgumentException(
                    "No se puede entregar: el pedido #" + numero + " ya está " + pedido.getEstado());
        }
        if (pedido.getRepartidor() != null) {
            pedido.getRepartidor().setDisponible(true);
        }
        pedido.setEstado(EstadoPedido.ENTREGADO);
    }

    public void cancelarPedido(int numero) {
        Pedido pedido = buscarPedido(numero);
        if (pedido.getEstado() != EstadoPedido.EN_CAMINO) {
            throw new IllegalArgumentException(
                    "No se puede cancelar: el pedido #" + numero + " ya está " + pedido.getEstado());
        }
        pedido.setEstado(EstadoPedido.CANCELADO);
        if (pedido.getRepartidor() != null) {
            pedido.getRepartidor().setDisponible(true);
        }
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

    // =========================================================================
    // CIERRE DEL DÍA (Checkpoint 3 - Nivel Operación real)
    // =========================================================================
    public void realizarCierreDelDia() {
        int totalPedidos = pedidos.size();
        int entregados = 0;
        int cancelados = 0;
        int enCamino = 0;
        double ingresosTotales = 0;
        double ingresosBicis = 0;
        double ingresosMotos = 0;
        double ingresosCarros = 0;

        for (Pedido p : pedidos) {
            switch (p.getEstado()) {
                case ENTREGADO:
                    entregados++;
                    ingresosTotales += p.getCosto();

                    if (p.getRepartidor() instanceof Bici) {
                        ingresosBicis += p.getCosto();
                    } else if (p.getRepartidor() instanceof Moto) {
                        ingresosMotos += p.getCosto();
                    } else if (p.getRepartidor() instanceof Carro) {
                        ingresosCarros += p.getCosto();
                    }
                    break;
                case CANCELADO:
                    cancelados++;
                    break;
                case EN_CAMINO:
                    enCamino++;
                    break;
            }
        }

        // Buscar al repartidor estrella (mejor promedio con al menos 1 calificación)
        Repartidor estrella = null;
        double mejorPromedio = -1;

        for (Repartidor r : repartidores) {
            if (r.getCantidadCalificaciones() > 0) {
                double promedio = r.getPromedio();
                if (promedio > mejorPromedio) {
                    mejorPromedio = promedio;
                    estrella = r;
                }
            }
        }

        System.out.println("\n==================================");
        System.out.println("       CIERRE DEL DÍA (REPORTE)   ");
        System.out.println("==================================");
        System.out.println("Total de pedidos: " + totalPedidos);
        System.out.println("- Entregados : " + entregados);
        System.out.println("- Cancelados : " + cancelados);
        System.out.println("- En camino  : " + enCamino);
        System.out.println("----------------------------------");
        System.out.println("Ingresos totales del día : $" + Math.round(ingresosTotales));
        System.out.println("  * Bicis  : $" + Math.round(ingresosBicis));
        System.out.println("  * Motos  : $" + Math.round(ingresosMotos));
        System.out.println("  * Carros : $" + Math.round(ingresosCarros));
        System.out.println("----------------------------------");
        if (estrella != null) {
            System.out.printf(java.util.Locale.US, "Repartidor estrella: %s (Promedio: %.1f ⭐ con %d calificaciones)\n",
                    estrella.getNombre(), mejorPromedio, estrella.getCantidadCalificaciones());
        } else {
            System.out.println("Repartidor estrella: Aún no hay repartidores con calificaciones.");
        }
        System.out.println("==================================");
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
