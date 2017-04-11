package Modelo;

public class Nodo implements Comparable<Nodo>{

    private final int id;
    private static int idGenerator = 1;
    private int grado = 0;
    public static final boolean INCREMENTA = true;
    public static final boolean DECREMENTA = false;

    /**
     *  Constructor vacío, inicializa el campo id según los nodos creados
     *  anteriormente.
     */
    public Nodo() {
        this.id = idGenerator++;
    }

    /**
     *  Constructor que crea un objeto Nodo con el id deseado
     * @param id - id deseado
     */
    public Nodo(int id) {
        this.id = id;
    }

    /**
     *  Constructor que crea un nodo con id y grado establecidos por parametro.
     * @param id - id deseado
     * @param grado - grado deseado
     */
    public Nodo(int id, int grado) {
        this.id = id;
        this.grado = grado;
    }

    /**
     *  Reinicia la variable idGenerator a 1
     */
    public static void restartIdGen(){
        idGenerator=1;
    }
    
    /**
     *  Método que obtiene el grado de un objeto Nodo.
     * @return int representante del grado.
     */
    public int getGrado() {
        return grado;
    }

    /**
     *  Método que obtiene el grado de un objeto Nodo.
     * @return int representante del id.
     */
    public int getId() {
        return id;
    }

    /**
     *  Método que modifica el grado de un nodo. Si el parámetro pasado es True
     *  incrementa, si es False decrementa. Es aconsejable utilizar las varia-
     *  bles estáticas de clase INCREMENTA y DECREMENTA.
     * @param accion - Booleano True para incrementar o False para decrementar.
     * @return - int con el valor actual del grado.
     */
    public int cambiaGrado(boolean accion) {
        return accion ? ++this.grado : --this.grado;
    }
    /**
     *  Método que compara un objeto Object con la propia instancia, devolverá 
     *  True si el objeto es una instancia de Nodo y ambos tienen el mismo id.
     * @param obj - Object con el que comparar.
     * @return - Boolean indicativo de si ambos objetos son iguales o no.
     */
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
    /**
     *  Método que crea un objeto Nodo con el mismo id que la propia instancia.
     * @return - objeto Nodo.
     */
    @Override
    public Nodo clone() throws CloneNotSupportedException {
        return new Nodo(this.id, this.grado);
    }
    /**
     *  Método que devuelve una String representante del objeto.
     * @return - String representativa de la instancia.
     */
    @Override
    public String toString() {
        return "" + id;
    }
    /**
     *  Método que compara los grados de un objeto Nodo y la propia instancia y 
     *  devuelve un int que representa la diferencia entre ambos.  
     * @param o - Nodo con el que comparar la instancia.
     * @return - int positivo si la instancia tiene mayor grado que el Nodo pa-
     *  sado, negativo si es menor o 0 si son iguales.
     */
    @Override
    public int compareTo(Nodo o) {
        return this.grado - o.grado;
    }

}
