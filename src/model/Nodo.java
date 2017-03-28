package model;

public class Nodo {
    
    private final int id;
    private static int idGenerator=1;
    private int grado =0;


    public Nodo() {
        this.id = idGenerator++;
    }
    
    public void setGrado(int grado) {
        this.grado = grado;
    }
    
}
