package src;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Clase que gestiona una lista de tareas, permitiendo operaciones como añadir, modificar,
 * eliminar, mostrar, guardar en fichero, cargar desde fichero y sincronizar con una base de datos.
 */
public class GestorTareas {
    // Lista de tareas en memoria
    private ArrayList<Tarea> tareas = new ArrayList<>();

    /**
     * Añade una nueva tarea a la lista.
     *
     * @param titulo      Título de la tarea.
     * @param descripcion Descripción de la tarea.
     */
    public void añadirTarea(String titulo, String descripcion) {
        tareas.add(new Tarea(titulo, descripcion));
    }

    /**
     * Modifica una tarea existente de la lista si el índice es válido.
     *
     * @param numeroTarea        Número de la tarea a modificar (empezando desde 1).
     * @param nuevoTitulo      Nuevo título de la tarea.
     * @param nuevaDescripcion Nueva descripción de la tarea.
     */
    public void modificarTarea(int numeroTarea, String nuevoTitulo, String nuevaDescripcion) {
        int index = numeroTarea - 1;
        if (index >= 0 && index < tareas.size()) {
            Tarea tarea = tareas.get(index);
            tarea.setTitulo(nuevoTitulo);
            tarea.setDescripcion(nuevaDescripcion);
        } else {
            System.out.println("Índice no válido.");
        }
    }

    /**
     * Elimina una tarea de la lista si el índice es válido.
     *
     * @param numeroTarea Número de la tarea a eliminar (empezando desde 1).
     */
    public void eliminarTarea(int numeroTarea) {
        int index = numeroTarea - 1;
        if (index >= 0 && index < tareas.size()) {
            tareas.remove(index);
        } else {
            System.out.println("Índice no válido.");
        }
    }

    /**
     * Muestra todas las tareas almacenadas en la lista.
     * Si la lista está vacía, lo indica por consola.
     */
    public void mostrarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
        } else {
            for (int i = 0; i < tareas.size(); i++) {
                System.out.println("Tarea " + (i+1) + ":");
                System.out.println(tareas.get(i));
                System.out.println("--------------------");
            }
        }
    }

    /**
     * Guarda las tareas actuales en un archivo de texto.
     *
     * @param nombreFichero Nombre del fichero donde se guardarán las tareas.
     */
    public void guardarTareasEnFichero(String nombreFichero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero))) {
            for (Tarea tarea : tareas) {
                writer.write(tarea.getTitulo() + ";" + tarea.getDescripcion());
                writer.newLine();
            }
            System.out.println("Tareas guardadas en " + nombreFichero);
        } catch (IOException e) {
            System.out.println("Error al guardar las tareas: " + e.getMessage());
        }
    }

    /**
     * Carga tareas desde un archivo de texto.
     *
     * @param nombreFichero Nombre del fichero desde el cual se cargarán las tareas.
     */
    public void cargarTareasDesdeFichero(String nombreFichero) {
        tareas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    tareas.add(new Tarea(partes[0], partes[1]));
                }
            }
            System.out.println("Tareas cargadas desde " + nombreFichero);
        } catch (IOException e) {
            System.out.println("Error al cargar las tareas: " + e.getMessage());
        }
    }

    /**
     * Guarda las tareas actuales en la base de datos.
     * Si ya existe una tarea con el mismo título, se actualiza su descripción.
     *
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void guardarEnBD() throws SQLException {
        try (Connection conn = ConexionBD.getConnection()) {
            String sql = """
                INSERT INTO listaTareas (titulo, descripcion)
                VALUES (?, ?)
                ON CONFLICT (titulo)
                DO UPDATE SET descripcion = EXCLUDED.descripcion
            """;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Tarea t : tareas) {
                    ps.setString(1, t.getTitulo());
                    ps.setString(2, t.getDescripcion());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }
}
