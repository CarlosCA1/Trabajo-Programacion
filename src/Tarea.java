package src;

/**
 * Clase que representa una tarea con un título y una descripción.
 */
public class Tarea {
    private String titulo;
    private String descripcion;

    /**
     * Constructor que inicializa una tarea con título y descripción.
     *
     * @param titulo      Título de la tarea.
     * @param descripcion Descripción de la tarea.
     */
    public Tarea(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el título de la tarea.
     *
     * @return El título de la tarea.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece un nuevo título para la tarea.
     *
     * @param titulo El nuevo título de la tarea.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la descripción de la tarea.
     *
     * @return La descripción de la tarea.
     */
    public String getDescripcion() {
        return descripcion;
    }


    /**
     * Establece una nueva descripción para la tarea.
     *
     * @param descripcion La nueva descripción de la tarea.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve una representación en texto de la tarea, útil para mostrar por consola.
     *
     * @return Cadena con el título y la descripción de la tarea.
     */
    public String toString() {
        return "Título: " + titulo + "\nDescripción: " + descripcion;
    }
}
