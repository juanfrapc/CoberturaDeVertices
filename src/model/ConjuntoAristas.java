package model;

import java.util.Iterator;

public final class ConjuntoAristas implements Iterable<Arista> {

    private final Arista[] aristas;
    private int k = 0;

    ConjuntoAristas(int n) {
        this.aristas = new Arista[n];
    }

    ConjuntoAristas(int[][] mat, ConjuntoNodos nodos) {
        this(mat.length*mat.length);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j]==1) {
                    this.añade(new Arista(nodos.getNodo(i), nodos.getNodo(j)));
                }
            }
        }
    }

    public boolean añade(Arista a) {
        if (k < aristas.length) {
            aristas[k++] = a;
            return true;
        }
        return false;
    }

    public int nAristas() {
        return k;
    }

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

    @Override
    public ConjuntoAristas clone() throws CloneNotSupportedException {
        ConjuntoAristas res = new ConjuntoAristas(this.nAristas());
        for (Arista arista : aristas) {
            res.añade(arista.clone());
        }
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

}
