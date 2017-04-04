package Modelo;

import java.util.Arrays;
import java.util.Iterator;

public class ConjuntoNodos implements Iterable<Nodo>{

    private final Nodo[] nodos;
    private int k = 0;

    public ConjuntoNodos(int n, boolean vacio) {
        this.nodos = new Nodo[n];
        if (!vacio) {
            for (int i = 0; i < n; i++) {
                this.añade(new Nodo());
            }
        }
    }

    public final boolean añade(Nodo nodo) {
        if (k < nodos.length) {
            nodos[k++] = nodo;
            return true;
        }
        return false;
    }

    public Nodo getNodo(int id) {
        for (Nodo nodo : nodos) {
            if (nodo.equals(new Nodo(id))) {
                return nodo;
            }
        }
        return null;
    }

    public int nNodos() {
        return k;
    }

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

    @Override
    public ConjuntoNodos clone() throws CloneNotSupportedException {
        ConjuntoNodos res = new ConjuntoNodos(this.nNodos(),false);
        for (Nodo Nodo : nodos) {
            res.añade(Nodo.clone());
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

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

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

    public boolean contiene(Nodo nodo) {
        for (Nodo nodito : nodos) {
            if (nodito.equals(nodo)) {
                return true;
            }
        }
        return false;
    }

    
    
}
