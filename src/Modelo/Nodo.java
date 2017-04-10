package Modelo;

public class Nodo implements Comparable<Nodo>{

    private final int id;
    private static int idGenerator = 1;
    private int grado = 0;
    public static final boolean INCREMENTA = true;
    public static final boolean DECREMENTA = false;

    public Nodo() {
        this.id = idGenerator++;
    }

    public Nodo(int id) {
        this.id = id;
    }

    public Nodo(int id, int grado) {
        this.id = id;
        this.grado = grado;
    }

    public static void restartIdGen(){
        idGenerator=1;
    }
    
    public int getGrado() {
        return grado;
    }

    public int getId() {
        return id;
    }

    public int cambiaGrado(boolean accion) {
        return accion ? ++this.grado : --this.grado;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Nodo)) {
            return false;
        }
        final Nodo other = (Nodo) obj;
        return this.id == other.id;
    }

    @Override
    public Nodo clone() throws CloneNotSupportedException {
        return new Nodo(this.id, this.grado);
    }

    @Override
    public String toString() {
        return "" + id;
    }

    @Override
    public int compareTo(Nodo o) {
        return this.grado - o.grado;
    }

}
