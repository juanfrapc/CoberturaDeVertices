package Modelo;

import java.util.Iterator;

public class Grafo {

    private final ConjuntoAristas aristas;
    private final ConjuntoNodos nodos;
    /**
     *  Constructor que crea un grafo vacío de un tamaño n, se crea con un con-
     *  junto de aristas de tamaño n^2 y un conjunto de nodos de tmaño n.
     * @param tamaño - Tamaño del grafo
     */
    public Grafo(int tamaño) {
        this.aristas = new ConjuntoAristas(tamaño * tamaño);
        this.nodos = new ConjuntoNodos(tamaño, true);
    }
    /**
     *  Constructor por matriz de adyacencia, se considera que existe una arista
     *  entre dos nodos si el peso es mayor que 0.
     * @param mat - Matriz de adyacencia
     */
    public Grafo(int[][] mat) {
        Nodo.restartIdGen();
        this.nodos = new ConjuntoNodos(mat.length, false);
        this.aristas = new ConjuntoAristas(mat, nodos);
    }

    /**
     *  Método que añade una arista a del grafo, se modifican los grados de los 
     *  nodos que se encuentren en sus extremos.
     * @param a - Arista a añadir
     * @return - Boolean indicativo del éxito o fracaso de la operación.
     */
    public boolean añadeArista(Arista a) {
        if (this.aristas.añade(a)) {
            a.getOrigen().cambiaGrado(Nodo.INCREMENTA);
            a.getDestino().cambiaGrado(Nodo.INCREMENTA);
            nodos.marcarDesordenado();
            return true;
        }
        return false;
    }

    /**
     *  Método que elimina una arista a del grafo
     * @param a - Arista a eliminar
     * @return - Boolean indicativo del éxito o fracaso de la operación.
     */
    public boolean quitarArista(Arista a) {
        if (this.aristas.eliminar(a)) {
            a.getOrigen().cambiaGrado(Nodo.DECREMENTA);
            a.getDestino().cambiaGrado(Nodo.DECREMENTA);
            return true;
        }
        return false;
    }

    /**
     *  Método que añade un nodo al grafo.
     * @param n - Nodo a añadir
     * @return - Boolean indicativo del éxito o fracaso de la operación.
     */
    public boolean añadeNodo(Nodo n) {
        return this.nodos.añade(n);
    }

    /**
     *  Método que elimina del grafo un nodo junto con todas las aristas que lo 
     *  tengan en alguno de sus extremos.
     * @param n - Nodo a eliminar
     * @return - Boolean indicativo del éxito o fracaso de la operación.
     */
    public boolean quitarNodo(Nodo n) {
        if (this.nodos.eliminar(n)) {
            Iterator<Arista> it = aristas.iterator();
            while (it.hasNext()) {
                Arista arista = it.next();
                if (arista.getOrigen().equals(n) || arista.getDestino().equals(n)) {
                    it.remove();
                }
            }
            return true;
        }
        return false;
    }

    /**
     *  Método que devuelve las dimensiones del grafo.
     * @return Array de 2 elementos, el primero se corresponde con el número de 
     *  nodos del grafo, y el segundo con el número de aristas.
     */
    public int[] dimensiones() {
        return new int[]{this.nodos.nNodos(), this.aristas.nAristas()};
    }
    /**
     *  Método que devuelve un iterador sobre el conjunto de aristas interno
     * @return - Iterator de Arista sobre del ConjuntoAristas interno.
     */
    public Iterator<Arista> iteradorAristas() {
        return aristas.iterator();
    }
    /**
     *  Método que devuelve una copia del conjunto de aristas interno
     * @return - ConjuntoAristas clon del ConjuntoAristas interno.
     * @throws CloneNotSupportedException
     */
    public ConjuntoAristas cloneAristas() throws CloneNotSupportedException {
        return aristas.clone();
    }

    /**
     *  Método que devuelve una copia del conjunto de nodos interno
     * @return - ConjuntoNodos clon del ConjuntoNodos interno.
     * @throws CloneNotSupportedException
     */
    public ConjuntoNodos cloneNodos() throws CloneNotSupportedException {
        return nodos.clone();
    }
    
    /**
     *  Método que restaura los grados de los nodos del conjunto de nodos 
     *  interno a su valor original.
     */
    public void restaurarGrados() {
        for (Nodo nodo : nodos) {
            while (nodo.getGrado() != 0) {
                nodo.cambiaGrado(Nodo.DECREMENTA);
            }
        }
        for (Arista arista : aristas) {
            arista.getOrigen().cambiaGrado(Nodo.INCREMENTA);
            arista.getDestino().cambiaGrado(Nodo.INCREMENTA);
        }
    }
}
