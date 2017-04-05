package Aplicacion;

import Modelo.ConjuntoNodos;
import Modelo.Grafo;
import Modelo.Nodo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Vista.GrafoReader;
import java.io.File;

public class FileGrafoReader implements GrafoReader {

    private String path = "";

    /**
     * Constructor con la ruta del fichero
     *
     * @param path Ruta del fichero
     */
    public FileGrafoReader(String path) {
        this.path = path;
    }

    @Override
    public Grafo read() throws Exception {

        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(new File(path)));

            int nNodos = Integer.parseInt(reader.readLine().split("=")[1].trim());
            int[][] matriz = new int[nNodos][nNodos];// matriz destino
            String fila;
            int i = 0, j = 0;
            while ((fila = reader.readLine()) != null && i < nNodos) {
                String[] parametros = fila.split("\\s+");
                for (String parametro : parametros) {
                    matriz[i][j++] = Integer.parseInt(parametro);
                }
                j = 0;
                i++;
            }
            reader.close();
            return new Grafo(matriz);
        } catch (IOException ex) {
            // Ruta no válida
            throw new Exception("Fichero no válido", ex);
        }
    }

}
