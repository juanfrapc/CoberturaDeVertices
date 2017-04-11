package Modelo;

public class Arista {
    
    private final Nodo origen;
    private final Nodo destino;
    private final int id;
    private static int idGenerator=1;
    /**
     *  Constructor que crea una arista con origen y destino pasados por parame-
     *  tro.
     * @param id - id deseado
     * @param origen - origen deseado
     * @param destino - destino deseado
     */
    public Arista(Nodo origen, Nodo destino) {
        this.destino = destino;
        this.origen = origen;
        this.id=idGenerator++;
    }
    /**
     *  Constructor que crea una arista con id, origen y destino pasados 
     *  por parametro.
     * @param id - id deseado
     * @param origen - origen deseado
     * @param destino - destino deseado
     */
    private Arista(Nodo origen, Nodo destino, int id){
        this.destino = destino;
        this.origen = origen;
        this.id=id;
    }
    /**
     *  Método que obtiene el nodo destino del objeto.
     * @return int representante del id.
     */
    public Nodo getDestino() {
        return destino;
    }
    /**
     *  Método que obtiene el nodo origen del objeto.
     * @return int representante del id.
     */
    public Nodo getOrigen() {
        return origen;
    }
    /**
     *  Método que compara un objeto Object con la propia instancia, devolverá 
     *  True si el objeto es una instancia de Arista y ambos tienen el mismo id 
     *  y referencian a los mismos nodos de origen y destino.
     * @param obj - Object con el que comparar.
     * @return - Boolean indicativo de si ambos objetos son iguales o no.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Arista)) {
            return false;
        }
        final Arista other = (Arista) obj;
        if (this.id == other.id && this.origen == other.origen 
                && this.destino == other.destino) return true;
        return false;
    } 
    /**
     *  Método que crea un objeto Arista con id, origen y destino idénticos a 
     *  los de la propia instancia.
     * @return - objeto Arista.
     */
    @Override
    public Arista clone() throws CloneNotSupportedException {
        Arista a = new Arista(origen, destino, id);
        return a;
    }
    /**
     *  Método que devuelve una String representante del objeto.
     * @return - String representativa de la instancia.
     */
    @Override
    public String toString() {
        return "{"+ origen + "\t" + destino + '}';
    } 
}
