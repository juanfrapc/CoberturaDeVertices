package Modelo;

import java.util.Iterator;

public final class ConjuntoAristas implements Iterable<Arista>, Cloneable {

    private final Arista[] aristas;
    private int k = 0;
    /**
     *  Constructor que crea un ConjuntoAristas vacío.
     * @param n - Tamaño del conjunto
     */
    public ConjuntoAristas(int n) {
        this.aristas = new Arista[n];
    }
    /**
     *  Constructor por matriz de adyacencia, se considera que existe una arista
     *  entre dos nodos si el peso es mayor que 0.
     * @param mat - Matriz de adyacencia
     * @param nodos - ConjuntoNodos donde se encuentran los nodos, es necesario 
     *  para modificar sus grados a medida que se añaden aristas.
     */
    ConjuntoAristas(int[][] mat, ConjuntoNodos nodos) {
        this(mat.length * mat.length);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] > 0) {
                    this.añade(new Arista(nodos.getNodo(i + 1), nodos.getNodo(j + 1)));
                    nodos.getNodo(i + 1).cambiaGrado(Nodo.INCREMENTA);
                    nodos.getNodo(j + 1).cambiaGrado(Nodo.INCREMENTA);
                }
            }
        }
    }
    /**
     *  Método que añade una arista del contenedor.
     * @param a - Arista a añadir
     * @return - Boolean indicativo de si la operación de realizó correctamente 
     *  o no.
     */
    public boolean añade(Arista a) {
        if (k < aristas.length) {
            aristas[k++] = a;
            return true;
        }
        return false;
    }

    /**
     *  Método que devuelve el númeor de aristas del contenedor.
     * @return int representate del número actual de aristas contenidas por la 
     *  instancia.
     */
    public int nAristas() {
        return k;
    }

    /**
     *  Método que elimina una arista del contenedor.
     * @param a - Arista a eliminar
     * @return - Boolean indicativo de si la operación de realizó correctamente 
     *  o no.
     */
    public boolean eliminar(Arista a) {
        int i;
        boolean flag = false;
        for (i = 0; i < k; i++) {
            if (aristas[i].equals(a)) {
                k--;
                flag = true;
                break;
            }
        }
        for (int j = i; j < k; j++) {
            aristas[j] = aristas[j + 1];
        }
        return flag;
    }
    /**
     *  Método que crea un objeto ConjuntoAristas con clones de las aristas de 
     *  la instancia.
     * @return - objeto ConjuntoAristas.
     */
    @Override
    public ConjuntoAristas clone() throws CloneNotSupportedException {
        ConjuntoAristas res = new ConjuntoAristas(this.nAristas());
        for (int i = 0; i < k; i++) {
            res.añade(aristas[i].clone());
        }
        System.out.print("");
        return res;
    }

    @Override
    public Iterator<Arista> iterator() {
        return new Iterator<Arista>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < k;
            }

            @Override
            public Arista next() {
                return aristas[i++];
            }

            @Override
            public void remove() {
                eliminar(aristas[--i]);
            }

        };
    }
    /**
     *  Método que determina si el contenedor está vacío.
     * @return Boolean indicativo de si el contenedor está vacío o no.
     */
    public boolean esVacio() {
        return k == 0;
    }
    /**
     *  Método que devuelve una arista aleatoria.
     * @return Arista aleatoria.
     */
    public Arista getRandom() {
        int i = (int) Math.round((k) * Math.random() - 0.5);
        return aristas[i];
    }
    /**
     *  Método que determina si un objeto Arista está contenido en el conte-
     *  nedor.
     * @param arista - objeto Arista a buscar.
     * @return Boolean indicativo de si la arista está contenida.
     */
    public boolean contiene(Arista arista) {
        for (int i = 0; i < k; i++) {
            if (aristas[i].equals(arista)) {
                return true;
            }
        }
        return false;
    }

}
