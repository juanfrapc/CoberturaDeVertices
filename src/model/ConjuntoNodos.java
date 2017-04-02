package model;

public class ConjuntoNodos {

    private final Nodo[] nodos;
    private int k = 0;

    public ConjuntoNodos(int n) {
        this.nodos = new Nodo[n];
        for (int i = 0; i < n; i++) {
            this.añade(new Nodo());
        }
    }

    public final boolean añade(Nodo nodo) {
        if (k < nodos.length) {
            nodos[k++] = nodo;
            return true;
        }
        return false;
    }
    
    public Nodo getNodo(int id){
        for (Nodo nodo : nodos) {
            if (nodo.equals(new Nodo(id))) {
                return nodo;
            }
        }
        return null;
    }
    
    public int nNodos(){
        return k;
    }

    public boolean eliminar(Nodo nodo){
        int i;
        boolean flag=false;
        for (i = 0; i < k; i++) {
            if(nodos[i].equals(nodo)){
                k--;
                flag=true;
                break;
            }
        }
        for (int j = i; j < k; j++) {
            nodos[j]=nodos[j+1];
        }
        return flag;
    }
    
    @Override
    public ConjuntoNodos clone() throws CloneNotSupportedException {
        ConjuntoNodos res = new ConjuntoNodos(this.nNodos());
        for (Nodo Nodo : nodos) {
            res.añade(Nodo.clone());
        }
        return res;
    }
    
}
