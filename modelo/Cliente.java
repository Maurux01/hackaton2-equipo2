package modelo;

public class Cliente {
    private String cedula;
    private String nombre;
    private String telefono;
    private String barrio;
    private String direccion;

    public Cliente(String cedula, String nombre, String telefono, String barrio, String direccion) {
        setCedula(cedula);
        setNombre(nombre);
        setTelefono(telefono);
        setBarrio(barrio);
        setDireccion(direccion);
    }

    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getBarrio() { return barrio; }
    public String getDireccion() { return direccion; }

    public void setCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cedula no puede estar vacia.");
        }
        this.cedula = cedula.trim();
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre.trim();
    }

    public void setTelefono(String telefono) {
        if (telefono == null) throw new IllegalArgumentException("El telefono no puede ser nulo.");
        String t = telefono.trim();
        if (t.length() < 7 || t.length() > 10) {
            throw new IllegalArgumentException("El telefono debe tener entre 7 y 10 digitos.");
        }
        for (int i = 0; i < t.length(); i++) {
            if (!Character.isDigit(t.charAt(i))) {
                throw new IllegalArgumentException("El telefono solo puede contener numeros.");
            }
        }
        this.telefono = t;
    }

    public void setBarrio(String barrio) {
        if (barrio == null || barrio.trim().isEmpty()) {
            throw new IllegalArgumentException("El barrio no puede estar vacio.");
        }
        this.barrio = barrio.trim();
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La direccion no puede estar vacia.");
        }
        this.direccion = direccion.trim();
    }

    @Override
    public String toString() {
        return cedula + " - " + nombre + " - " + telefono + " - " + barrio + " - " + direccion;
    }
}