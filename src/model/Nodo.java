package model;

public class Nodo {
    
    private final int id;
    private static int idGenerator=1;
    private int grado =0;


    public Nodo() {
        this.id = idGenerator++;
    }
    
    Nodo(int id){
        this.id=id;
    }
    
    public int getGrado() {
        return grado;
    }
    
    public int cambiaGrado(boolean aumenta){
        return aumenta?++this.grado:--this.grado;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Nodo) {
            return false;
        }
        final Nodo other = (Nodo) obj;
        return this.id == other.id;
    }
    
    
    
    @Override
    public Nodo clone() throws CloneNotSupportedException {
        return (Nodo) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
