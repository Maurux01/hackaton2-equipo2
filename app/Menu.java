package app;

import servicio.DomiService;
import java.util.Scanner;

public class Menu {
    private DomiService service;
    private Scanner scanner;

    public Menu(DomiService service, Scanner scanner) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        // Para validar el do/While y finalizar el servicio
        int opcion = -1;

        // Para que no se cierre repentina mente
        do {

            try {
                System.out.println("================DOMI ° OPERADOR==============");
                // System.out.println("----------------CLIENTE---------------");
                System.out.println(" CLIENTE    " + " 1. Registrar      " + " 2. Listar   ");
                System.out.println("            " + " 3. Editar         " + " 4. Eliminar ");
                System.out.println("                                                    ");

                // Repartidor
                System.out.println(" REPARTIDOR " + " 5. Registrar      " + " 6. Listar   ");
                System.out.println("            " + " 7. Cambiar Disponibilidad      ");
                System.out.println("                                                    ");
                // Pedido
                System.out.println(" PEDIDOS    " + " 8. Crear pedido (Cotiza y asigna) ");
                System.out.println("            " + " 9. Entregar        " + " 10. Cancelar ");
                System.out.println("            " + " 11. Listar pedidos ");
                System.out.println("                                                    ");
                // Operación
                System.out.println(" OPERACIÓN  " + " 12. Activar / desactivar lluvia");
                System.out.println("            " + " 13. Cierre del día ");
                System.out.println("                                                    ");

                // Salir del sistema
                System.out.println(" PARA SALIR DEL MENU OPRIMA 0:");
                System.out.println(" 0. Salir");

                System.out.println("===============================================");
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Debe ingresar un número valido");
            } catch (IllegalArgumentException e) {
                // Validaciones del servicio (Capturadas en pedido o en Domi)
                System.out.println("[ERROR DE VALIDACIÓN] : " + e.getMessage());
            } catch (Exception e) {
                // Validaciones del servicio (Capturadas en pedido o en Domi)
                System.out.println("[ERROR DE INESPERADO] : " + e.getMessage());
            }
        } while (opcion != 0);
    }
}
