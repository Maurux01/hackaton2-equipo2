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
        this.numero = contadorPedidos++; // Asigna el consecutivo y avanza el contador
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.esGrande = esGrande;
        this.km = km;
        this.repartidorAsignado = repartidorAsignado;
        this.costo = costo;
        this.tiempo = tiempo;
        this.estado = EstadoPedido.EN_CAMINO; // nace por defecto EN_CAMINO
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
}