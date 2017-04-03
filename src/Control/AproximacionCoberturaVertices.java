package Control;

import Modelo.ConjuntoAristas;
import Modelo.ConjuntoNodos;
import Modelo.Grafo;

public class AproximacionCoberturaVertices {

    public String nombre(){
        return "Algoritmos de aproximación para la cobertura de vértices";
    }
    
    public ConjuntoNodos resuelve( Grafo grafo) throws CloneNotSupportedException{
        ConjuntoNodos solucion = new ConjuntoNodos(grafo.dimensiones()[0], true);
        ConjuntoAristas elegir = grafo.cloneAristas();
        
        
        
        
        
        
        return null;
    }

    private void elegirArista(ConjuntoAristas elegir) {
        
    }
    
}
