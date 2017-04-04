package Modelo;

public class Arista {
    
    private final Nodo origen;
    private final Nodo destino;
    private final int id;
    private static int idGenerator=1;

    public Arista(Nodo origen, Nodo destino) {
        this.destino = destino;
        this.origen = origen;
        this.id=idGenerator++;
    }
    
    private Arista(Nodo origen, Nodo destino, int id){
        this.destino = destino;
        this.origen = origen;
        this.id=id;
    }
    
    public Nodo getDestino() {
        return destino;
    }

    public Nodo getOrigen() {
        return origen;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Arista)) {
            return false;
        }
        final Arista other = (Arista) obj;
        return this.id == other.id;
    } 

    @Override
    public Arista clone() throws CloneNotSupportedException {
        Arista a = new Arista(origen, destino, id);
        return a;
    }

    @Override
    public String toString() {
        return "{"+ origen + "\t" + destino + '}';
    }
    
    
}
