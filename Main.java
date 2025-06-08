import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorTareas gestor = new GestorTareas();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("----- Gestor de Tareas -----");
            System.out.println("1. Añadir tarea");
            System.out.println("2. Modificar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Mostrar tareas");
            System.out.println("5. Guardar tareas");
            System.out.println("6. Cargar tareas");
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
                case 5 -> gestor.guardarTareasEnFichero("tareas.txt");
                case 6 -> gestor.cargarTareasDesdeFichero("tareas.txt");
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
