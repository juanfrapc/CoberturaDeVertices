package Aplicacion;

import Modelo.Arista;
import Modelo.ConjuntoNodos;
import Modelo.Grafo;
import Modelo.Nodo;
import Vista.GrafoDisplay;
import java.util.Iterator;

/**
 * Clase Singleton que muestra un grafo por la consola.
 *
 * @author Juan Francisco Pérez Caballero && Gabriel García Buey
 */
public class GrafoPrinter implements GrafoDisplay {

    private static GrafoPrinter printer = null;

    private GrafoPrinter() {
    }

    /**
     * @return Instancia de la Clase (Singleton)
     */
    public static GrafoPrinter getInstance() {
        if (printer == null) {
            printer = new GrafoPrinter();
        }
        return printer;
    }

    @Override
    public void print(Grafo grafo) {

        Iterator<Arista> it = grafo.iteradorAristas();

        System.out.println("NodoA\tNodoB");
        while(it.hasNext()){
            Arista a = it.next();
            System.out.println(a.toString());
        }

    }
    
    public void print(ConjuntoNodos conjunto){
        for (Nodo nodo : conjunto) {
            System.out.print(nodo + "\t");
        }
        System.out.println("");
    }

}