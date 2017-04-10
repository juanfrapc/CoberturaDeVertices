package Control;

import Modelo.ConjuntoNodos;
import Modelo.Nodo;

/**
 * Clase que implementa el metodo de ordenación "Radix Sort"
 * @author Juan Francisco Pérez Caballero && Gabriel García Buey
 */
public class RadixSort {

    private Nodo[] nodos;
    private int nNodos;
    

    public String nombre() {
        return "Radix Sort";
    }


    public void ordena(ConjuntoNodos conjunto) {
        this.nodos = conjunto.getNodos();
        this.nNodos = conjunto.nNodos();
        int m = maximo();

        for (int exp = 1; m / exp > 0; exp *= 10) {
            // Una iteracón por cada dígito.
            ordenaDígito(exp);
        }
    }

    private int maximo() {
        // devuelve el máximo
        Nodo max = nodos[0];
        for (int i = 1; i < nNodos; i++) {
            if (nodos[i].compareTo(max) > 0) {
                max = nodos[i];
            }
        }
        return max.getGrado();
    }

    private void ordenaDígito(int exp) {
        Nodo[] aux = new Nodo[nNodos];
        int i;
        int[] count = new int[10];
        for (i = 0; i < count.length; i++) {
            count[i] = 0;
        }

        for (i = 0; i < nNodos; i++) {
            count[(nodos[i].getGrado() / exp) % 10]++;
        }

        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (i = nNodos - 1; i >= 0; i--) {
            aux[count[(nodos[i].getGrado() / exp) % 10] - 1] = nodos[i];
            count[(nodos[i].getGrado() / exp) % 10]--;
        }

        for (i = nNodos - 1; i >= 0 ; i--) {
            nodos[nNodos - i - 1] = aux[i];
        }
    }
}