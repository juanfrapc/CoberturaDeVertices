package Control;

import Modelo.Arista;
import Modelo.ConjuntoAristas;
import Modelo.ConjuntoNodos;
import Modelo.Grafo;
import Modelo.Nodo;
import java.util.Iterator;

public class AproximacionCoberturaVertices {

    public String nombre() {
        return "Algoritmos de aproximación para la cobertura de vértices";
    }

    public ConjuntoNodos resuelve(Grafo grafo) throws CloneNotSupportedException {
        ConjuntoNodos solucion = new ConjuntoNodos(grafo.dimensiones()[0], true);
        ConjuntoAristas elegibles = grafo.cloneAristas();
        while (!elegibles.esVacio()) {
            System.out.println("tamaño = "+ elegibles.nAristas());
            Arista a = elegir(elegibles);
            System.out.println("arista random = " + a);
            solucion.añade(a.getOrigen());
            System.out.println(a.getOrigen());
            solucion.añade(a.getDestino());
            System.out.println(a.getDestino());
            eliminaIncidentes(a, elegibles);
        }
        return solucion;
    }

    private Arista elegir(ConjuntoAristas conjunto) {
        return conjunto.getRandom();
    }

    private void eliminaIncidentes(Arista a, ConjuntoAristas elegibles) {
        Nodo origen = a.getOrigen();
        Nodo destino = a.getDestino();
        Iterator<Arista> it = elegibles.iterator();
        while (it.hasNext()) {
            Arista arista = it.next();
            if (arista.getOrigen().equals(origen) || arista.getDestino().equals(origen)) {
                System.out.println("1 " + arista);
                it.remove();
            } else if (arista.getOrigen().equals(destino) || arista.getDestino().equals(destino)) {
                System.out.println("2 " + arista);
                it.remove();
            }
        }
    }

}
