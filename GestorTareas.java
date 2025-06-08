import java.io.*;
import java.util.ArrayList;

public class GestorTareas {
    private ArrayList<Tarea> tareas = new ArrayList<>();

    public void añadirTarea(String titulo, String descripcion) {
        tareas.add(new Tarea(titulo, descripcion));
    }

    public void modificarTarea(int index, String nuevoTitulo, String nuevaDescripcion) {
        if (index >= 0 && index < tareas.size()) {
            Tarea tarea = tareas.get(index);
            tarea.setTitulo(nuevoTitulo);
            tarea.setDescripcion(nuevaDescripcion);
        } else {
            System.out.println("Índice no válido.");
        }
    }

    public void eliminarTarea(int index) {
        if (index >= 0 && index < tareas.size()) {
            tareas.remove(index);
        } else {
            System.out.println("Índice no válido.");
        }
    }

    public void mostrarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
        } else {
            for (int i = 0; i < tareas.size(); i++) {
                System.out.println("Tarea #" + i);
                System.out.println(tareas.get(i));
                System.out.println("--------------------");
            }
        }
    }

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
}
