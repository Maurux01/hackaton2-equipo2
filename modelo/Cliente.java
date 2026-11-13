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

    // ---------- cedula (no vacío) ----------
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula no puede estar vacía.");
        }
        this.cedula = cedula.trim();
    }

    // ---------- nombre (no vacío) ----------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    // ---------- "telefono (solo números, entre 7 y 10 dígitos)" ----------
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        String limpio = telefono.trim();
        if (limpio.length() < 7 || limpio.length() > 10) {
            throw new IllegalArgumentException("El teléfono debe tener entre 7 y 10 dígitos.");
        }
        for (int i = 0; i < limpio.length(); i++) {
            if (!Character.isDigit(limpio.charAt(i))) {
                throw new IllegalArgumentException("El teléfono solo puede tener números.");

            }
        }
        this.telefono = limpio;
    }


    // ---------- barrio (no vacío; la cobertura la valida el servicio) ----------
    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        if (barrio == null || barrio.trim().isEmpty()) {
            throw new IllegalArgumentException("El barrio no puede estar vacío.");
        }
        this.barrio = barrio.trim();
    }

    // ---------- direccion (no vacío) ----------
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        this.direccion = direccion.trim();
    }
}