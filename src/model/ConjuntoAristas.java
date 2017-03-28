package model;

public class ConjuntoAristas {

    private final Arista[] aristas;
    private int k = 0;

    public ConjuntoAristas(int n) {
        this.aristas = new Arista[n];
    }

    public boolean añade(Arista a) {
        if (k < aristas.length) {
            aristas[k++] = a;
            return true;
        }
        return false;
    }
    
    public int nAristas(){
        return k;
    }

    public boolean eliminar(Arista a){
        int i;
        boolean flag=false;
        for (i = 0; i < k; i++) {
            if(aristas[i].equals(a)){
                k--;
                flag=true;
                break;
            }
        }
        for (int j = i; j < k; j++) {
            aristas[j]=aristas[j+1];
        }
        return flag;
    }
}
