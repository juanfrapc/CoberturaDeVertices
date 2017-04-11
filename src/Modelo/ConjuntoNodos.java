package Modelo;

import java.util.Iterator;

public class ConjuntoNodos implements Iterable<Nodo>{

    private final Nodo[] nodos;
    private int k = 0;
    private boolean ordenado = false;
    /**
     *  Constructor que crea un ConjuntoNodos de tamaño n, el contenedor se 
     *  creará vacío o no en función del segundo parámetro.
     * @param n - int representativo del tamaño del ConjuntoNodos.
     * @param vacio - Boolean que indica si el contenedor se creará vacío (True)
     *  o no (False).
     */
    public ConjuntoNodos(int n, boolean vacio) {
        this.nodos = new Nodo[n];
        if (!vacio) {
            for (int i = 0; i < n; i++) {
                this.añade(new Nodo());
            }
        }
    }

    /**
     *  Método que añade un nodo al contenedor. Si el contenedor está ordenado, 
     *  se marcará como desordenado si el grado del nodo a añadir es mayor que 0
     * @param nodo -  Nodo a añadir
     * @return - Boolean indicativo del éxito de la operación.
     */
    public final boolean añade(Nodo nodo) {
        if (k < nodos.length) {
            nodos[k++] = nodo;
            if (ordenado && nodo.getGrado() > 0) marcarDesordenado();
            return true;
        }
        return false;
    }

    /**
     *  Método que obtiene el nodo cuyo id sea el pasado por parámetro.
     * @param id - id del Nodo buscado.
     * @return - Nodo buscado o null si no se encuentra.
     */
    public Nodo getNodo(int id) {
        Nodo comp = new Nodo(id);
        for (int i = 0; i < k; i++) {
            if (nodos[i].equals(comp)) {
                return nodos[i];
            }
        }
        return null;
    }
    
    /**
     * Método que devuelve el nodo de mayor grado en caso de que el contenedor 
     * esté ordenado.
     * @return Nodo de mayor grado o null en caso de que el contenedor 
     *  esté desordenado o vacío.
     */
    public Nodo getMaxNodo() {
        return ordenado? nodos[0]:null;
    }
    
    /**
     *  Método que devuelve un array con los elementos del contenedor. 
     *  AVISO: es fundamental recorrer el array con el valor proporcionado por 
     *  la función nNodos().
     * @return - Array de Nodos con los elementos.
     */
    public Nodo[] getNodos() {
        return nodos;
    }

    /**
     *  Método que obtiene el número de nodos contenido actualmente.
     * @return int representativo del número nodos contenidos.
     */
    public int nNodos() {
        return k;
    }
    
    /**
     *  Método que marca el contenedor como ordenado según el grado de sus 
     *  nodos.
     */
    public void marcarOrdenado() {
        ordenado = true;
    }
    
    /**
     *  Método que marca el contenedor como desordenado
     */
    public void marcarDesordenado() {
        ordenado = false;
    }
    /**
     *  Método que elimina un nodo del contenedor.
     * @param nodo - Nodo a eliminar
     * @return - Boolean indicativo de si la operación de realizó correctamente 
     *  o no.
     */
    public boolean eliminar(Nodo nodo) {
        int i;
        boolean flag = false;
        for (i = 0; i < k; i++) {
            if (nodos[i].equals(nodo)) {
                k--;
                flag = true;
                break;
            }
        }
        for (int j = i; j < k; j++) {
            nodos[j] = nodos[j + 1];
        }
        return flag;
    }
    /**
     *  Método que crea un objeto ConjuntoNodos con clones de los nodos de 
     *  la instancia.
     * @return - objeto ConjuntoAristas.
     */
    @Override
    public ConjuntoNodos clone() throws CloneNotSupportedException {
        ConjuntoNodos res = new ConjuntoNodos(this.nNodos(),true);
        for (int i = 0; i < k; i++) {
            res.añade(nodos[i].clone());
        }
        return res;
    }

    @Override
    public Iterator<Nodo> iterator() {
        return new Iterator<Nodo>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < k;
            }

            @Override
            public Nodo next() {
                return nodos[i++];
            }

            @Override
            public void remove() {
                eliminar(nodos[--i]);
            }
        };
    }
    /**
     *  Método que compara un objeto Object con la propia instancia, devolverá 
     *  True si el objeto es una instancia de ConjuntoNodos, si ambos tienen el 
     *  mismo número de elementos y si contienen los mismo nodos.
     * @param obj - Object con el que comparar.
     * @return - Boolean indicativo de si ambos objetos son iguales o no.
     */
    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof ConjuntoNodos)) {
            return false;
        }
        final ConjuntoNodos other = (ConjuntoNodos) obj;
        if (this.k != other.k) {
            return false;
        }
        for (Nodo nodo : this) {
            boolean flag = false;
            for (Nodo nodito : other) {
                if (nodo.equals(nodito)) {
                    flag = true;
                    break;
                }
            }
            if (!flag){
                return false;
            }
        }
        return true;
    }
    /**
     *  Método que determina si un objeto Nodo está contenido en el conte-
     *  nedor.
     * @param nodo - objeto Nodo a buscar.
     * @return Boolean indicativo de si la arista está contenida.
     */
    public boolean contiene(Nodo nodo) {
        for (int i = 0; i < k; i++) {
            if (nodos[i].equals(nodo)) {
                return true;
            }
        }
        return false;
    }
}
