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
        try {
            System.out.println("================DOMI ° OPERADOR==============");
            System.out.println(" CLIENTE    " + " 1. Registrar      " + " 2. Listar   ");
            System.out.println("            " + " 3. Editar         " + " 4. Eliminar ");
            System.out.println(" REPARTIDOR " + " 5. Registrar      " + " 6. Listar   ");
            System.out.println("            " + " 7. Cambiar Disponibilidad      ");
            System.out.println(" PEDIDOS    " + " 8. Crear pedido (Cotiza y asigna) ");
            System.out.println("            " + " 9. Entregar        " + " 10. Cancelar ");
            System.out.println("            " + " 11. Listar pedidos ");
            System.out.println(" OPERACIÓN  " + " 12. Activar / desactivar lluvia");
            System.out.println("            " + " 13. Cierre del día ");
            System.out.println(" 0. Salir");
            System.out.println("===============================================");
        } catch (Exception e) {
            System.out.println("[ERROR INESPERADO] : " + e.getMessage());
        }
    }
}
