package model;

public class Arista {
    
    private final Nodo origen;
    private final Nodo destino;
    private final int id;
    private static int idGenerator=1;

    public Arista(Nodo destino, Nodo origen) {
        this.destino = destino;
        this.origen = origen;
        this.id=idGenerator++;
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
        if (obj instanceof Arista) {
            return false;
        }
        final Arista other = (Arista) obj;
        return this.id == other.id;
    } 

    
    @Override
    public Arista clone() throws CloneNotSupportedException {
        return (Arista) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
