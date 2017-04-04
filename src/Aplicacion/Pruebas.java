package Aplicacion;

import Control.AproximacionCoberturaVertices;
import Modelo.Arista;
import Modelo.ConjuntoNodos;
import Modelo.Grafo;
import java.util.Iterator;
import java.util.Scanner;

public class Pruebas {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        String command = "";

        while (true) { // bucle infinito.
            //Menú
            System.out.println("1. Introducir grafo mediante fichero de texto");
            System.out.println("2. Introducir grafo por consola [Matriz de adyacencia]");
            System.out.println("3. Ejecutar pruebas");
            System.out.println("[Pulse la tecla 'Q' para salir]");
            System.out.print("Comando a ejecutar: ");
            // Menú

            command = userInput.nextLine();
            String path;
            GrafoPrinter printer = GrafoPrinter.getInstance();
            Grafo grafo;
            ConjuntoNodos sol;
            int origen;
            int destino;
            switch (command) {
                case "1": // a través de fichero
                    System.out.print("\nIntroduzca la ruta absoluta del fichero de texto: ");
                    path = userInput.nextLine();
                    grafo = cargarGrafo(path);
                    if (grafo == null) {
                        break;
                    }
                    printer.print(grafo);
                    System.out.println("\n---------------- Resolución ----------------");
                    try {
                        sol = resolverGrafo(grafo);
                        printer.print(sol);
                    } catch (Exception ex) {
                        System.out.println("\n" + ex.getMessage() + "\n");
                    }
                    break;
                case "2": // Matriz por pantalla
                    System.out.print("\nNumero de nodos del grafo: ");
                    int nNodos = userInput.nextInt();
                    System.out.println("\n---------------- Creación de la Matriz de Adyacencia ----------------");
                    int[][] matAdyacencia = new int[nNodos][nNodos];
                    for (int i = 0; i < nNodos; i++) {
                        for (int j = 0; j < nNodos; j++) {
                            System.out.print("Peso arista de nodo " + (i + 1) + " a nodo " + (j + 1) + " : ");
                            matAdyacencia[i][j] = userInput.nextInt();
                        }
                    }
                    System.out.println("\n---------------- Resolución ----------------");
                    grafo = new Grafo(matAdyacencia);
                    try {
                        sol = resolverGrafo(grafo);
                        printer.print(sol);
                    } catch (Exception ex) {
                        System.out.println("\n" + ex.getMessage() + "\n");
                    }

                    break;
                case "3": // Pruebas por defecto
                    System.out.println("\n---------------- Pruebas ----------------");
                    System.out.print("-Prueba 1 - ");
                    path = "EjemploGrafo.txt";
                    grafo = cargarGrafo(path);
                    int solEsperada = 100;
                    try {
                        sol = resolverGrafo(grafo);
                        comprueba(grafo, sol, solEsperada);
                    } catch (Exception ex) {
                        System.out.println("Fallo: excepción no esperada");
                        System.out.println(ex.getClass()+ "\n");
                    }

//                    System.out.print("-Prueba 2 - ");
//                    path = "EjemploGrafo2.txt";
//                    grafo = cargarGrafo(path);
//                    try {
//                        sol = resolverGrafo(grafo);
//                        comprueba(grafo, sol, solEsperada);
//                    } catch (Exception ex) {
//                        System.out.println("Fallo: excepción no esperada");
//                        System.out.println(ex.getMessage() + "\n");
//                    }
                    break;
                case "q":
                    return;
                case "Q":
                    return;
                default:
                    System.out.println("\nError: Comando erróneo\n");
            }
        }
    }

    private static Grafo cargarGrafo(String path) {
        FileGrafoReader reader = new FileGrafoReader(path);
        Grafo grafo = null;
        try {
            grafo = reader.read();
        } catch (Exception ex) {
            System.out.println("\n" + ex.getMessage() + "\n");
        }
        return grafo;
    }

    private static ConjuntoNodos cargarConjuntoNodos(String path) {
        FileGrafoReader reader = new FileGrafoReader(path);
        ConjuntoNodos nodos = null;
        try {
            nodos = reader.getConjuntoNodos();
        } catch (Exception ex) {
            System.out.println("\n" + ex.getMessage() + "\n");
        }
        return nodos;
    }

    private static ConjuntoNodos resolverGrafo(Grafo grafo) throws Exception {
        AproximacionCoberturaVertices resol = new AproximacionCoberturaVertices();
        ConjuntoNodos sol = resol.resuelve(grafo);
        return sol;
    }

    private static void comprueba(Grafo grafo, ConjuntoNodos sol, int optimo) {
        if (sol.nNodos() > 2 * optimo) {
            System.out.println("Fallo: la solución obtenida no se aproxima lo suficiente al óptimo");
        }
        Iterator<Arista> it = grafo.iteradorAristas();
        while (it.hasNext()) {
            Arista arista = it.next();
            if (!sol.contiene(arista.getOrigen()) && !sol.contiene(arista.getDestino())) {
                System.out.println("Fallo: la solución obtenida no es un conjunto cobertura");
            }
        }
        System.out.println("Éxito\n");
    }

}
