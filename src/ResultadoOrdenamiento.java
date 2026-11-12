// Clase modelo que almacena las métricas obtenidas al ordenar un arreglo
public class ResultadoOrdenamiento {

    // Cantidad de comparaciones entre elementos realizadas por el algoritmo
    private final long comparaciones;

    // Cantidad de intercambios o desplazamientos de datos ejecutados
    private final long movimientos;

    // Tiempo total de ejecución medido en nanosegundos
    private final long tiempoNano;

    // Constructor que recibe y guarda las tres métricas al terminar el ordenamiento
    public ResultadoOrdenamiento(long comparaciones, long movimientos, long tiempoNano) {
        this.comparaciones = comparaciones;
        this.movimientos = movimientos;
        this.tiempoNano = tiempoNano;
    }

    // Métodos para consultar cada resultado desde la clase principal
    public long getComparaciones() {
        return comparaciones;
    }

    public long getMovimientos() {
        return movimientos;
    }

    public long getTiempoNano() {
        return tiempoNano;
    }
}
