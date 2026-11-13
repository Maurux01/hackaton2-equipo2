package app;

import modelo.*;
import servicio.DomiService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DomiService service = new DomiService();
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        // Bucle para que se repita y el usuario elija
        while (opcion != 0) {
            try {
                System.out.println("\n==================================");
                System.out.println("        DOMI - OPERADOR           ");
                System.out.println("==================================");
                System.out.println("CLIENTE:     1. Registrar  2. Listar");
                System.out.println("             3. Editar     4. Eliminar");
                System.out.println("REPARTIDOR:  5. Registrar  6. Listar");
                System.out.println("             7. Cambiar disponibilidad");
                System.out.println("PEDIDOS:     8. Crear pedido");
                System.out.println("             9. Entregar   10. Cancelar");
                System.out.println("            11. Listar pedidos");
                System.out.println("OPERACION:  12. Activar/Desactivar lluvia");
                System.out.println("            13. Cierre del dia");
                System.out.println("0. Salir");
                System.out.print("Selecciona una opcion (0-13): ");

                opcion = Integer.parseInt(scanner.nextLine().trim());

                switch (opcion) {
                    case 1:
                        System.out.print("Cedula: ");
                        String cedula = scanner.nextLine().trim();
                        if (service.existeCliente(cedula)) {
                            throw new IllegalArgumentException("Ya existe un cliente con esa cedula.");
                        }
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine().trim();
                        System.out.print("Telefono: ");
                        String telefono = scanner.nextLine().trim();
                        System.out.print("Barrio: ");
                        String barrio = scanner.nextLine().trim();
                        if (!service.tieneCobertura(barrio)) {
                            throw new IllegalArgumentException("El barrio no tiene cobertura.");
                        }
                        System.out.print("Direccion: ");
                        String direccion = scanner.nextLine().trim();

                        // Si algo esta vacio o hay etras en el telefono IllegalArgumentException
                        Cliente nuevo = new Cliente(cedula, nombre, telefono, barrio, direccion);
                        service.getClientes().put(cedula, nuevo);
                        System.out.println("Cliente registrado correctamente.");
                        break;

                    case 2:
                        System.out.println("\n--- Lista de Clientes ---");
                        if (service.getClientes().isEmpty()) {
                            System.out.println("No hay clientes registrados.");
                        } else {
                            for (Cliente c : service.getClientes().values()) {
                                System.out.println("Cedula: " + c.getCedula() + " | Nombre: " + c.getNombre()
                                        + " | Barrio: " + c.getBarrio());
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Ingresa la cedula del cliente a editar: ");
                        String cedEdit = scanner.nextLine().trim();
                        Cliente clienteAEditar = service.buscarCliente(cedEdit);
                        if (clienteAEditar == null) {
                            throw new IllegalArgumentException("No se encontro un cliente con esa cedula.");
                        }

                        System.out.print("Nuevo nombre (enter para dejar igual): ");
                        String nuevoNom = scanner.nextLine().trim();
                        if (!nuevoNom.isEmpty()) {
                            clienteAEditar.setNombre(nuevoNom);
                        }

                        System.out.print("Nuevo telefono (enter para dejar igual): ");
                        String nuevoTel = scanner.nextLine().trim();
                        if (!nuevoTel.isEmpty()) {
                            clienteAEditar.setTelefono(nuevoTel);
                        }

                        System.out.print("Nuevo barrio (enter para dejar igual): ");
                        String nuevoBarrio = scanner.nextLine().trim();
                        if (!nuevoBarrio.isEmpty()) {
                            if (!service.tieneCobertura(nuevoBarrio)) {
                                throw new IllegalArgumentException("Ese barrio no tiene cobertura.");
                            }
                            clienteAEditar.setBarrio(nuevoBarrio);
                        }

                        System.out.println("Cliente actualizado con exito.");
                        break;

                    case 4:
                        System.out.print("Ingresa la cedula a eliminar: ");
                        String cedElim = scanner.nextLine().trim();
                        if (!service.existeCliente(cedElim)) {
                            throw new IllegalArgumentException("El cliente no existe.");
                        }
                        service.getClientes().remove(cedElim);
                        System.out.println("Cliente eliminado del sistema.");
                        break;

                    case 5:
                        System.out.print("Codigo (ej: R-04): ");
                        String cod = scanner.nextLine().trim();
                        System.out.print("Nombre: ");
                        String nomRep = scanner.nextLine().trim();
                        System.out.print("Tipo (1. Bici, 2. Moto, 3. Carro): ");
                        int tipo = Integer.parseInt(scanner.nextLine().trim());

                        if (tipo == 1) {
                            service.getRepartidores().add(new Bici(cod, nomRep));
                        } else if (tipo == 2) {
                            service.getRepartidores().add(new Moto(cod, nomRep));
                        } else if (tipo == 3) {
                            service.getRepartidores().add(new Carro(cod, nomRep));
                        } else {
                            throw new IllegalArgumentException("Tipo de vehiculo no valido. Usa 1, 2 o 3.");
                        }
                        System.out.println("Repartidor registrado.");
                        break;

                    case 6:
                        System.out.println("\n--- Repartidores ---");
                        if (service.getRepartidores().isEmpty()) {
                            System.out.println("No hay repartidores.");
                        } else {
                            for (Repartidor r : service.getRepartidores()) {
                                System.out.println(r.getCodigo() + " - " + r.getNombre() + " (Disponible: "
                                        + r.isDisponible() + ")");
                            }
                        }
                        break;

                    case 7:
                        System.out.print("Codigo del repartidor: ");
                        String codDisp = scanner.nextLine().trim();
                        boolean encontrado = false;
                        for (Repartidor r : service.getRepartidores()) {
                            if (r.getCodigo().equalsIgnoreCase(codDisp)) {
                                r.setDisponible(!r.isDisponible());
                                System.out.println("Estado actualizado: ahora esta "
                                        + (r.isDisponible() ? "Disponible" : "Ocupado"));
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            throw new IllegalArgumentException(
                                    "No se encontro ningun repartidor con el codigo " + codDisp);
                        }
                        break;

                    case 8:
                        System.out.print("Cedula del cliente para el pedido: ");
                        String cedPed = scanner.nextLine().trim();
                        if (!service.existeCliente(cedPed)) {
                            throw new IllegalArgumentException(
                                    "Cliente no registrado. Primero debes crearlo en la opcion 1.");
                        }
                        System.out.println("Pedido cotizado y asignado.");
                        break;

                    case 9:
                        System.out.print("Numero de pedido a entregar: ");
                        int numEntregar = Integer.parseInt(scanner.nextLine().trim());
                        System.out.println("Pedido #" + numEntregar + " marcado como entregado.");
                        break;

                    case 10:
                        System.out.print("Numero de pedido a cancelar: ");
                        int numCancelar = Integer.parseInt(scanner.nextLine().trim());
                        System.out.println("Pedido #" + numCancelar + " cancelado.");
                        break;

                    case 11:
                        System.out.println("\n--- Pedidos ---");
                        if (service.getPedidos().isEmpty()) {
                            System.out.println("No hay pedidos en la lista.");
                        } else {
                            for (Pedido p : service.getPedidos()) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 12:
                        service.setModoLluvia(!service.isModoLluvia());
                        System.out.println(
                                "Modo lluvia ahora esta: " + (service.isModoLluvia() ? "Activado" : "Desactivado"));
                        break;

                    case 13:
                        System.out.println("\n--- Cierre del dia ---");
                        System.out.println("Total de pedidos: " + service.getPedidos().size());
                        System.out.println("Estado lluvia: " + (service.isModoLluvia() ? "Activado" : "Desactivado"));
                        break;

                    case 0:
                        System.out.println("Saliendo del sistema Domi... ¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opcion no valida. Debe ser un numero del 0 al 13.");
                        break;
                }

            } catch (NumberFormatException e) {
                // si el usuario escribe letras donde va un numero
                System.out.println("Error: Ingresaste un valor no numerico. Intenta de nuevo.");
            } catch (IllegalArgumentException e) {
                // validaciones de la cedula repetida, barrio sin cobertura y errores de Cliente
                System.out.println("Validacion: " + e.getMessage());
            } catch (Exception e) {
                // error general para que no se caiga
                System.out.println("Ocurrio un error inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
