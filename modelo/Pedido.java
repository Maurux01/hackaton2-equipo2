package modelo;

public class Pedido {
    private int numero;
    private Cliente cliente;
    private String descripcion;
    private boolean grande;
    private double km;
    private Repartidor repartidor;
    private double costo;
    private double tiempo;
    private String estado;

    private static final java.util.Set<String> ESTADOS_VALIDOS =
            new java.util.HashSet<>(java.util.Arrays.asList("EN_CAMINO", "ENTREGADO", "CANCELADO"));

    public Pedido(int numero, Cliente cliente, String descripcion, boolean grande, double km,
                  Repartidor repartidor, double costo, double tiempo, String estado) {
        this.numero = numero;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.grande = grande;
        this.km = km;
        this.repartidor = repartidor;
        this.costo = costo;
        this.tiempo = tiempo;
        setEstado(estado);
    }

    public int getNumero() { return numero; }
    public Cliente getCliente() { return cliente; }
    public String getDescripcion() { return descripcion; }
    public boolean isGrande() { return grande; }
    public double getKm() { return km; }
    public Repartidor getRepartidor() { return repartidor; }
    public double getCosto() { return costo; }
    public double getTiempo() { return tiempo; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) {
        if (estado == null || !ESTADOS_VALIDOS.contains(estado)) {
            throw new IllegalArgumentException("Estado invalido: " + estado);
        }
        this.estado = estado;
    }

    public void setRepartidor(Repartidor repartidor) { this.repartidor = repartidor; }
    public void setCosto(double costo) { this.costo = costo; }
    public void setTiempo(double tiempo) { this.tiempo = tiempo; }

    @Override
    public String toString() {
        return "#" + numero + " - " + cliente.getNombre() + " - " + (repartidor != null ? repartidor.getNombre() : "sin asignar")
                + " - $" + costo + " - " + tiempo + " min - " + estado;
    }
}