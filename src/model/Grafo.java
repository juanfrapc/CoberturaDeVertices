package model;

import java.util.Iterator;

public class Grafo {

    private final ConjuntoAristas aristas;
    private final ConjuntoNodos nodos;

    public Grafo(int tamaño) {
        this.aristas = new ConjuntoAristas(tamaño * tamaño);
        this.nodos = new ConjuntoNodos(tamaño);
    }
    
    public Grafo(int[][] mat){
        this.nodos = new ConjuntoNodos(mat.length);
        this.aristas=new ConjuntoAristas(mat, nodos);
    }

    public boolean añadeArista(Arista a) {
        if (this.aristas.añade(a)) {
            a.getOrigen().cambiaGrado(true);
            a.getDestino().cambiaGrado(true);
            return true;
        }
        return false;
    }

    public boolean quitarArista(Arista a) {
        if (this.aristas.eliminar(a)) {
            a.getOrigen().cambiaGrado(false);
            a.getDestino().cambiaGrado(false);
            return true;
        }
        return false;
    }

    public boolean añadeNodo(Nodo n) {
        return this.nodos.añade(n);
    }

    public boolean quitarNodo(Nodo n) {
        if (this.nodos.eliminar(n)){
            Iterator<Arista> it = aristas.iterator();
            while(it.hasNext()){
                Arista arista = it.next();
                if (arista.getOrigen().equals(n) || arista.getDestino().equals(n)) {
                    it.remove();
                }
            }
            return true;
        }
        return false;
    }

}
