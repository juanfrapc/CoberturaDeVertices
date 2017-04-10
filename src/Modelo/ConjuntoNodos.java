package Modelo;

import java.util.Iterator;

public class ConjuntoNodos implements Iterable<Nodo>{

    private final Nodo[] nodos;
    private int k = 0;
    private boolean ordenado = false;

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
        Nodo comp = new Nodo(id);
        for (int i = 0; i < k; i++) {
            if (nodos[i].equals(comp)) {
                return nodos[i];
            }
        }
        return null;
    }
    
    public Nodo getMaxNodo() {
        return ordenado? nodos[0]:null;
    }
    
    public Nodo[] getNodos() {
        return nodos;
    }

    public int nNodos() {
        return k;
    }
    
    public void marcarOrdenado() {
        ordenado = true;
    }
    
    public void marcarDesordenado() {
        ordenado = false;
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
        for (int i = 0; i < k; i++) {
            if (nodos[i].equals(nodo)) {
                return true;
            }
        }
        return false;
    }
}
