# ejercicio-24-ordenamiento-casi-ordenado
Contiene un arreglo inicialmente ordenado con pocas modificaciones aleatorias, aplicar Bubble Sort optimizado, Insertion Sort y Quick Sort sobre copias idénticas, registrar comparaciones, movimientos y tiempos, y determinar experimentalmente cuál se adapta mejor.


**Asignatura:** Estructuras de Datos — Unidad 2 (Taller Práctico)  
**Programa:** Ingeniería de Software — Universidad de Cartagena  
**Estudiante:** Jorge Taján Acevedo  
**Tutor:** John Carlos Arrieta Arrieta

## Descripción
Implementación manual en Java de tres algoritmos de ordenamiento ejecutados sobre copias idénticas de arreglos casi ordenados (`N = 100`, `1.000`, `10.000` con 5% de perturbación aleatoria), contabilizando comparaciones, movimientos (intercambios o desplazamientos) y tiempo de ejecución en nanosegundos:
- **Bubble Sort Optimizado** ($O(n)$ en el mejor caso / $O(n^2)$ promedio)
- **Insertion Sort** ($O(n)$ en datos casi ordenados / $O(n^2)$ promedio)
- **Quick Sort** con pivote central ($O(n \log n)$ promedio)

## Instrucciones de ejecución
1. Clonar el repositorio público desde GitHub.
2. Abrir la carpeta del proyecto en **IntelliJ IDEA** (JDK 17 o superior).
3. Ejecutar el método `main` ubicado en `src/Main.java`.