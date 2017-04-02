package Vista;

import Modelo.Grafo;

/**
 * Interfaz de persistencia. Lector de Gafo
 * @author Juan Francisco Pérez Caballero && Gabriel García Buey
 */
public interface GrafoReader {

    /**
     * @return Grafo leido.
     * @throws Exception
     */
    public Grafo read() throws Exception;

}