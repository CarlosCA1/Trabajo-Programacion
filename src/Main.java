package src;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Clase principal.
 * Permite al usuario añadir, modificar, eliminar, mostrar tareas y guardar en base de datos.
 */
public class Main {
    /**
     * Método principal
     */
    public static void main(String[] args) {
        GestorTareas gestor = new GestorTareas();
        // Carga inicial de tareas desde fichero al iniciar el programa
        gestor.cargarTareasDesdeFichero("tareas.txt");
        Scanner sc = new Scanner(System.in);
        int opcion;

        // Menú
        do {
            System.out.println("----- Gestor de Tareas -----");
            System.out.println("1. Añadir tarea");
            System.out.println("2. Modificar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Mostrar tareas");
            System.out.println("5. Guardar en base de datos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Descripción: ");
                    String desc = sc.nextLine();
                    gestor.añadirTarea(titulo, desc);
                }
                case 2 -> {
                    gestor.mostrarTareas();
                    System.out.print("Número de tarea a modificar: ");
                    int index = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Nueva descripción: ");
                    String desc = sc.nextLine();
                    gestor.modificarTarea(index, titulo, desc);
                }
                case 3 -> {
                    gestor.mostrarTareas();
                    System.out.print("Número de tarea a eliminar: ");
                    int index = sc.nextInt();
                    gestor.eliminarTarea(index);
                }
                case 4 -> gestor.mostrarTareas();
                case 5 -> {
                    try {
                        gestor.guardarEnBD();
                        System.out.println("Tareas guardadas en la base de datos.");
                    } catch (SQLException e) {
                        System.out.println("Error al guardar: " + e.getMessage());
                    }
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    gestor.guardarTareasEnFichero("tareas.txt");
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
