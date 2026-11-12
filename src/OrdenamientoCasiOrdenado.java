import java.util.Random;

// Clase que genera arreglos casi ordenados e implementa los 3 algoritmos manuales
public class OrdenamientoCasiOrdenado {

    // Genera un arreglo ordenado ascendentemente y desordena aleatoriamente un pequeño porcentaje
    public static int[] generarArregloCasiOrdenado(int tamano, int porcentajeDesorden, long semilla) {
        int[] arreglo = new int[tamano];
        for (int i = 0; i < tamano; i++) {
            arreglo[i] = i + 1; // Secuencia inicialmente ordenada: 1, 2, 3...
        }

        // Calculamos cuántos intercambios aleatorios haremos (mínimo 1 si tamano > 1)
        int cambios = Math.max(1, (tamano * porcentajeDesorden) / 100);
        Random random = new Random(semilla);

        for (int k = 0; k < cambios; k++) {
            int pos1 = random.nextInt(tamano);
            // Intercambiamos con una posición cercana para mantenerlo "casi ordenado"
            int salto = random.nextInt(5) + 1;
            int pos2 = Math.min(tamano - 1, pos1 + salto);

            int temp = arreglo[pos1];
            arreglo[pos1] = arreglo[pos2];
            arreglo[pos2] = temp;
        }
        return arreglo;
    }

    // 1. BUBBLE SORT OPTIMIZADO: Usa bandera 'intercambiado' para detenerse si ya está ordenado
    public static ResultadoOrdenamiento bubbleSortOptimizado(int[] a) {
        long inicio = System.nanoTime();
        long comparaciones = 0;
        long intercambios = 0;

        if (a == null || a.length < 2) {
            return new ResultadoOrdenamiento(0, 0, System.nanoTime() - inicio);
        }

        boolean intercambiado;
        for (int n = a.length; n > 1; n--) {
            intercambiado = false;
            for (int i = 1; i < n; i++) {
                comparaciones++;
                if (a[i - 1] > a[i]) {
                    int temp = a[i];
                    a[i] = a[i - 1];
                    a[i - 1] = temp;
                    intercambios++;
                    intercambiado = true;
                }
            }
            // Si en toda la pasada no hubo intercambios, el arreglo ya quedó ordenado
            if (!intercambiado) {
                break;
            }
        }

        return new ResultadoOrdenamiento(comparaciones, intercambios, System.nanoTime() - inicio);
    }

    // 2. INSERTION SORT: Toma cada elemento y lo desliza hacia la izquierda hasta su lugar
    public static ResultadoOrdenamiento insertionSort(int[] a) {
        long inicio = System.nanoTime();
        long comparaciones = 0;
        long desplazamientos = 0;

        if (a == null || a.length < 2) {
            return new ResultadoOrdenamiento(0, 0, System.nanoTime() - inicio);
        }

        for (int i = 1; i < a.length; i++) {
            int clave = a[i];
            int j = i - 1;

            // Desplazamos a la derecha los elementos mayores que 'clave'
            while (j >= 0) {
                comparaciones++;
                if (a[j] > clave) {
                    a[j + 1] = a[j];
                    desplazamientos++;
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = clave;
        }

        return new ResultadoOrdenamiento(comparaciones, desplazamientos, System.nanoTime() - inicio);
    }

    // 3. QUICK SORT: Divide y vencerás usando el elemento central como pivote
    public static ResultadoOrdenamiento quickSort(int[] a) {
        long inicio = System.nanoTime();
        long[] contadores = new long[2]; // contadores[0] = comparaciones, contadores[1] = intercambios

        if (a != null && a.length >= 2) {
            quickSortRecursivo(a, 0, a.length - 1, contadores);
        }

        return new ResultadoOrdenamiento(contadores[0], contadores[1], System.nanoTime() - inicio);
    }

    private static void quickSortRecursivo(int[] a, int lo, int hi, long[] contadores) {
        int i = lo;
        int j = hi;
        int pivote = a[lo + ((hi - lo) >>> 1)]; // Pivote en el centro

        while (i <= j) {
            while (a[i] < pivote) {
                contadores[0]++;
                i++;
            }
            contadores[0]++; // Última comparación que detuvo el while

            while (a[j] > pivote) {
                contadores[0]++;
                j--;
            }
            contadores[0]++; // Última comparación que detuvo el while

            if (i <= j) {
                if (i != j) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    contadores[1]++;
                }
                i++;
                j--;
            }
        }

        if (lo < j) quickSortRecursivo(a, lo, j, contadores);
        if (i < hi) quickSortRecursivo(a, i, hi, contadores);
    }

    // Método auxiliar para verificar que el arreglo quedó ordenado ascendentemente
    public static boolean estaOrdenado(int[] a) {
        if (a == null) return true;
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) return false;
        }
        return true;
    }
}