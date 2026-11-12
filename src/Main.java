import java.util.Arrays;

// Clase principal para ejecutar las pruebas y comparar el rendimiento de los algoritmos
public class Main {

    public static void main(String[] args) {
        // Probamos con tres tamaños distintos y 5% de perturbación (arreglo casi ordenado)
        int[] tamanosPrueba = {100, 1_000, 10_000};
        int porcentajeDesorden = 5;

        // Calentamiento breve de la JVM para estabilizar la medición de System.nanoTime()
        int[] warmup = OrdenamientoCasiOrdenado.generarArregloCasiOrdenado(500, 5, 42L);
        OrdenamientoCasiOrdenado.bubbleSortOptimizado(Arrays.copyOf(warmup, warmup.length));
        OrdenamientoCasiOrdenado.insertionSort(Arrays.copyOf(warmup, warmup.length));
        OrdenamientoCasiOrdenado.quickSort(Arrays.copyOf(warmup, warmup.length));

        for (int n : tamanosPrueba) {
            // Generamos un arreglo base casi ordenado con semilla fija para reproducibilidad
            int[] base = OrdenamientoCasiOrdenado.generarArregloCasiOrdenado(n, porcentajeDesorden, 123L);

            System.out.println("==========================================================================");
            System.out.printf("PRUEBA CON TAMANO N = %d (Desorden aleatorio: %d%%)%n", n, porcentajeDesorden);
            System.out.println("==========================================================================");

            // Si el arreglo es pequeño (N = 100), mostramos una muestra antes y después
            if (n == 100) {
                System.out.println("Original (primeros 15): " + Arrays.toString(Arrays.copyOf(base, 15)));
            }

            // Creamos copias idénticas para que cada algoritmo ordene exactamente los mismos datos
            int[] copiaBubble = Arrays.copyOf(base, base.length);
            int[] copiaInsertion = Arrays.copyOf(base, base.length);
            int[] copiaQuick = Arrays.copyOf(base, base.length);

            // Ejecutamos los tres algoritmos
            ResultadoOrdenamiento resBubble = OrdenamientoCasiOrdenado.bubbleSortOptimizado(copiaBubble);
            ResultadoOrdenamiento resInsertion = OrdenamientoCasiOrdenado.insertionSort(copiaInsertion);
            ResultadoOrdenamiento resQuick = OrdenamientoCasiOrdenado.quickSort(copiaQuick);

            if (n == 100) {
                System.out.println("Ordenado (primeros 15): " + Arrays.toString(Arrays.copyOf(copiaInsertion, 15)));
            }

            // Imprimimos la tabla comparativa y verificamos que todos quedaron ordenados
            imprimirFila("Bubble Optimizado", resBubble, OrdenamientoCasiOrdenado.estaOrdenado(copiaBubble));
            imprimirFila("Insertion Sort", resInsertion, OrdenamientoCasiOrdenado.estaOrdenado(copiaInsertion));
            imprimirFila("Quick Sort", resQuick, OrdenamientoCasiOrdenado.estaOrdenado(copiaQuick));
            System.out.println();
        }
    }

    // Método auxiliar para imprimir los resultados de forma alineada en consola
    private static void imprimirFila(String nombre, ResultadoOrdenamiento res, boolean ordenado) {
        System.out.printf("  %-18s | Ordenado: %-5s | Comparaciones: %-9d | Movimientos: %-9d | Tiempo: %d ns%n",
                nombre,
                ordenado ? "SI" : "NO",
                res.getComparaciones(),
                res.getMovimientos(),
                res.getTiempoNano());
    }
}