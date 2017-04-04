package Modelo;

import java.util.Iterator;

public final class ConjuntoAristas implements Iterable<Arista>, Cloneable{

    private final Arista[] aristas;
    private int k = 0;

    public ConjuntoAristas(int n) {
        this.aristas = new Arista[n];
    }

    ConjuntoAristas(int[][] mat, ConjuntoNodos nodos) {
        this(mat.length*mat.length);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j]>0) {
                    this.añade(new Arista(nodos.getNodo(i+1), nodos.getNodo(j+1)));
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
    public ConjuntoAristas clone() throws CloneNotSupportedException  {
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

    public boolean esVacio() {
        return k==0;
    }

    public Arista getRandom() {
        int i = (int) Math.round((k+1)*Math.random() - 0.5);
        return aristas[i];
    }

    public boolean contiene(Arista arista) {
        for (Arista aristita : aristas) {
            if (aristita.equals(arista)) {
                return true;
            }
        }
        return false;
    }
    
}
