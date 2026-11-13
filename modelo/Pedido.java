package modelo;

public class Pedido {
    private static int contadorPedidos = 1;

    private int numero;
    private Cliente cliente;
    private String descripcion;
    private boolean esGrande;
    private double km;
    private Repartidor repartidorAsignado;
    private double costo;
    private double tiempo;
    private EstadoPedido estado; // EN_CAMINO, ENTREGADO, CANCELADO

    public Pedido(Cliente cliente, String descripcion, boolean esGrande, double km, Repartidor repartidorAsignado, double costo, double tiempo) {
        this.numero = contadorPedidos++;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.esGrande = esGrande;
        this.km = km;
        this.repartidorAsignado = repartidorAsignado;
        this.costo = costo;
        this.tiempo = tiempo;
        this.estado = EstadoPedido.EN_CAMINO;
    }

    public Pedido(int numero, Cliente cliente, String descripcion, boolean esGrande, double km, Repartidor repartidorAsignado, double costo, double tiempo, EstadoPedido estado) {
        this.numero = numero;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.esGrande = esGrande;
        this.km = km;
        this.repartidorAsignado = repartidorAsignado;
        this.costo = costo;
        this.tiempo = tiempo;
        this.estado = (estado != null) ? estado : EstadoPedido.EN_CAMINO;
        if (numero >= contadorPedidos) {
            contadorPedidos = numero + 1;
        }
    }

    public Pedido(int numero, Cliente cliente, String descripcion, boolean esGrande, double km, Repartidor repartidorAsignado, double costo, double tiempo, String estadoStr) {
        this.numero = numero;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.esGrande = esGrande;
        this.km = km;
        this.repartidorAsignado = repartidorAsignado;
        this.costo = costo;
        this.tiempo = tiempo;
        if (estadoStr == null || estadoStr.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        try {
            this.estado = EstadoPedido.valueOf(estadoStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado no valido. Use EN_CAMINO, ENTREGADO o CANCELADO.");
        }
        if (numero >= contadorPedidos) {
            contadorPedidos = numero + 1;
        }
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isEsGrande() {
        return esGrande;
    }

    public double getKm() {
        return km;
    }

    public Repartidor getRepartidorAsignado() {
        return repartidorAsignado;
    }

    // Alias para compatibilidad con DomiService
    public Repartidor getRepartidor() {
        return repartidorAsignado;
    }

    public double getCosto() {
        return costo;
    }

    public double getTiempo() {
        return tiempo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    // Setter para validacion por seguridad
    public void setEstado(EstadoPedido estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        this.estado = estado;
    }

    public void setEstado(String estadoStr) {
        if (estadoStr == null || estadoStr.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        this.estado = EstadoPedido.valueOf(estadoStr.trim().toUpperCase());
    }

    @Override
    public String toString() {
        return "Pedido #" + numero + " [" + estado + "] - Cliente: " + (cliente != null ? cliente.getNombre() : "") +
                " (" + (cliente != null ? cliente.getBarrio() : "") + ") - Repartidor: " +
                (repartidorAsignado != null ? repartidorAsignado.getNombre() : "N/A") +
                " - Costo: $" + Math.round(costo) + " - Tiempo: " + ((tiempo == Math.round(tiempo)) ? (Math.round(tiempo) + " min") : String.format(java.util.Locale.US, "%.1f min", tiempo));
    }
}
