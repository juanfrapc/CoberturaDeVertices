package Control;

import Modelo.Arista;
import Modelo.ConjuntoAristas;
import Modelo.ConjuntoNodos;
import Modelo.Grafo;
import Modelo.Nodo;
import java.util.Iterator;

public class AproximacionCoberturaVertices {

    public static final int METODOALEATORIO = 1;
    public static final int METODOGRADO = 2;

    public String nombre() {
        return "Algoritmos de aproximación para la cobertura de vértices";
    }

    public ConjuntoNodos resuelve(Grafo grafo, int metodo) throws CloneNotSupportedException {
        ConjuntoNodos solucion = new ConjuntoNodos(grafo.dimensiones()[0], true);
        ConjuntoAristas elegibles = grafo.cloneAristas();
        switch (metodo) {
            case METODOGRADO:
                ConjuntoNodos candidatos = grafo.cloneNodos();
                new RadixSort().ordena(candidatos); 
                while (!elegibles.esVacio()) {
                    Arista a = elegir(elegibles, candidatos);
                    candidatos.eliminar(a.getOrigen());
                    candidatos.eliminar(a.getDestino());
                    añadeCandidatos(a, solucion);
                    eliminaIncidentes(a, elegibles);
                }
            default:
                while (!elegibles.esVacio()) {
                    Arista a = elegir(elegibles);
                    añadeCandidatos(a, solucion);
                    eliminaIncidentes(a, elegibles);
                }
                break;
        };
        return solucion;
    }

    private Arista elegir(ConjuntoAristas conjunto) {
        return conjunto.getRandom();
    }

    private Arista elegir(ConjuntoAristas aristas, ConjuntoNodos nodos) {
        Nodo nodoMax = nodos.getMaxNodo();
        for (Arista arista : aristas) {
            if (arista.getOrigen().equals(nodoMax) || arista.getDestino().equals(nodoMax)) {
                return arista;
            }
        }
        return null;
    }

    private void eliminaIncidentes(Arista a, ConjuntoAristas elegibles) {
        Nodo origen = a.getOrigen();
        Nodo destino = a.getDestino();
        Iterator<Arista> it = elegibles.iterator();
        while (it.hasNext()) {
            Arista arista = it.next();
            if (arista.getOrigen().equals(origen) || arista.getDestino().equals(origen)) {
                it.remove();
            } else if (arista.getOrigen().equals(destino) || arista.getDestino().equals(destino)) {
                it.remove();
            }
        }
    }

    private void añadeCandidatos(Arista a, ConjuntoNodos solucion) {
        if (a.getOrigen().getGrado() > 1) {
            solucion.añade(a.getOrigen());
            if (a.getDestino().getGrado() > 1) {
                solucion.añade(a.getDestino());
            }
        } else {
            solucion.añade(a.getDestino());
        }
    }

}
