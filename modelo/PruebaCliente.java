public class PruebaCliente {

    public static void main(String[] args) {

        System.out.println("===== PRUEBA CLASE CLIENTE =====");

        // Prueba 1: cliente con datos correctos
        System.out.println("\nPrueba 1: cliente valido");
        try {
            Cliente cliente = new Cliente("1012345678", "Valentina", "3001234567", "Chapinero", "Calle 1 # 2-3");
            System.out.println("Cedula: " + cliente.getCedula());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Telefono: " + cliente.getTelefono());
            System.out.println("Barrio: " + cliente.getBarrio());
            System.out.println("Direccion: " + cliente.getDireccion());
            System.out.println("Resultado: OK, el cliente se creo bien");
        } catch (Exception e) {
            System.out.println("Resultado: FALLO, no deberia dar error -> " + e.getMessage());
        }

        // Prueba 2: cedula vacia
        System.out.println("\nPrueba 2: cedula vacia");
        try {
            Cliente cliente = new Cliente("", "Valentina", "3001234567", "Chapinero", "Calle 1 # 2-3");
            System.out.println("Resultado: FALLO, deberia haber dado error");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado: OK, dio error -> " + e.getMessage());
        }

        // Prueba 3: cedula nula
        System.out.println("\nPrueba 3: cedula nula");
        try {
            Cliente cliente = new Cliente(null, "Valentina", "3001234567", "Chapinero", "Calle 1 # 2-3");
            System.out.println("Resultado: FALLO, deberia haber dado error");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado: OK, dio error -> " + e.getMessage());
        }

        // Prueba 4: nombre vacio
        System.out.println("\nPrueba 4: nombre vacio");
        try {
            Cliente cliente = new Cliente("1012345678", "", "3001234567", "Chapinero", "Calle 1 # 2-3");
            System.out.println("Resultado: FALLO, deberia haber dado error");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado: OK, dio error -> " + e.getMessage());
        }

        // Prueba 5: telefono vacio
        System.out.println("\nPrueba 5: telefono vacio");
        try {
            Cliente cliente = new Cliente("1012345678", "Valentina", "", "Chapinero", "Calle 1 # 2-3");
            System.out.println("Resultado: FALLO, deberia haber dado error");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado: OK, dio error -> " + e.getMessage());
        }

        // Prueba 6: telefono muy corto (menos de 7 digitos)
        System.out.println("\nPrueba 6: telefono con menos de 7 digitos");
        try {
            Cliente cliente = new Cliente("1012345678", "Valentina", "123456", "Chapinero", "Calle 1 # 2-3");
            System.out.println("Resultado: FALLO, deberia haber dado error");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado: OK, dio error -> " + e.getMessage());
        }

        // Prueba 7: telefono muy largo (mas de 10 digitos)
        System.out.println("\nPrueba 7: telefono con mas de 10 digitos");
        try {
            Cliente cliente = new Cliente("1012345678", "Valentina", "30012345678", "Chapinero", "Calle 1 # 2-3");
            System.out.println("Resultado: FALLO, deberia haber dado error");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado: OK, dio error -> " + e.getMessage());
        }

        // Prueba 8: telefono con letras
        System.out.println("\nPrueba 8: telefono con letras");
        try {
            Cliente cliente = new Cliente("1012345678", "Valentina", "300abc4567", "Chapinero", "Calle 1 # 2-3");
            System.out.println("Resultado: FALLO, deberia haber dado error");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado: OK, dio error -> " + e.getMessage());
        }

        // Prueba 9: barrio vacio
        System.out.println("\nPrueba 9: barrio vacio");
        try {
            Cliente cliente = new Cliente("1012345678", "Valentina", "3001234567", "", "Calle 1 # 2-3");
            System.out.println("Resultado: FALLO, deberia haber dado error");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado: OK, dio error -> " + e.getMessage());
        }

        // Prueba 10: direccion vacia
        System.out.println("\nPrueba 10: direccion vacia");
        try {
            Cliente cliente = new Cliente("1012345678", "Valentina", "3001234567", "Chapinero", "");
            System.out.println("Resultado: FALLO, deberia haber dado error");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado: OK, dio error -> " + e.getMessage());
        }

        // Prueba 11: los espacios al inicio y al final se recortan
        System.out.println("\nPrueba 11: se recortan los espacios (trim)");
        try {
            Cliente cliente = new Cliente("  1012345678  ", "  Valentina  ", "  3001234567  ", "  Chapinero  ", "  Calle 1 # 2-3  ");
            if (cliente.getNombre().equals("Valentina") && cliente.getTelefono().equals("3001234567")) {
                System.out.println("Resultado: OK, los espacios se quitaron");
            } else {
                System.out.println("Resultado: FALLO, los espacios no se quitaron");
            }
        } catch (Exception e) {
            System.out.println("Resultado: FALLO, no deberia dar error -> " + e.getMessage());
        }

        System.out.println("\n===== FIN DE LAS PRUEBAS =====");
    }
}