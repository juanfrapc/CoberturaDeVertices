package model;

public class ConjuntoNodos {

    private final Nodo[] Nodos;
    private int k = 0;

    public ConjuntoNodos(int n) {
        this.Nodos = new Nodo[n];
    }

    public boolean añade(Nodo nodo) {
        if (k < Nodos.length) {
            Nodos[k++] = nodo;
            return true;
        }
        return false;
    }
    
    public int nNodos(){
        return k;
    }

    public boolean eliminar(Nodo nodo){
        int i;
        boolean flag=false;
        for (i = 0; i < k; i++) {
            if(Nodos[i].equals(nodo)){
                k--;
                flag=true;
                break;
            }
        }
        for (int j = i; j < k; j++) {
            Nodos[j]=Nodos[j+1];
        }
        return flag;
    }
    
    @Override
    public ConjuntoNodos clone() throws CloneNotSupportedException {
        ConjuntoNodos res = new ConjuntoNodos(this.nNodos());
        for (Nodo Nodo : Nodos) {
            res.añade(Nodo.clone());
        }
        return res;
    }
    
}
