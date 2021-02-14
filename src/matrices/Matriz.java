/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matrices;

import java.awt.Dimension;
import java.util.Random;

/**
 *
 * @author galvez
 */
public class Matriz {
    private int[][]datos;
    private Random rnd = new Random();
    
    public Matriz(int filas, int columnas, boolean inicializarAleatorio){
        datos = new int[columnas][];
        for(int i=0; i<columnas; i++){
            datos[i] = new int[filas];
            if (inicializarAleatorio)
                for(int j=0; j<filas; j++)
                    datos[i][j] = rnd.nextInt(100);
        }
    }
    public Matriz(Dimension d, boolean inicializarAleatorio){
        this(d.height, d.width, inicializarAleatorio);
    }
    
    public Dimension getDimension(){
        return new Dimension(datos.length, datos[0].length);
    }
    
    public static Matriz sumarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles { 
        if(! a.getDimension().equals(b.getDimension())) throw new DimensionesIncompatibles("La suma de matrices requiere matrices de las mismas dimensiones");        
        int i, j, filasA, columnasA; 
        filasA = a.getDimension().height; 
        columnasA = a.getDimension().width; 
        Matriz matrizResultante = new Matriz(filasA, columnasA, false);
        for (j = 0; j < filasA; j++) { 
            for (i = 0; i < columnasA; i++) { 
                matrizResultante.datos[i][j] += a.datos[i][j] + b.datos[i][j]; 
            } 
        } 
        return matrizResultante; 
    } 
    
    /**
     * multiplicarDosMatrices
     * @param a Matriz de dimensiones NxM
     * @param  b Matriz de dimensiones MxK
     * @return Matriz de dimensiones NxK que resulta la multiplicación vectorial
     * @throws DimensionesIncompatibles si a y b no cumplen con las dimensiones adecuadas
     */
    public static Matriz multiplicarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles { 
        int n, m,k, filasA,filasB, columnasB,columnasA ; 
        filasA = (int) a.getDimension().getHeight(); 
        columnasA = (int) a.getDimension().getWidth(); 
        filasB = (int)b.getDimension().getHeight(); 
        columnasB = (int) b.getDimension().getWidth(); 
        if (!( columnasA == filasB )) throw new DimensionesIncompatibles("La multiplicación (producto punto) de matrices require que al menos las matrices sean NxM y MxK");

        Matriz matrizResultante = new Matriz(filasA, columnasB, false);
        for (n=0; n<filasA; n++)
            for (k=0; k < columnasB; k++)  {
              matrizResultante.datos[k][n] = 0;
               for (m=0; m < columnasA; m++)
                   matrizResultante.datos[k][n] += a.datos[m][n] * b.datos[k][m];                  
            } 
        return matrizResultante;
    }

    @Override
    public String toString(){
        String ret = "";
        ret += "[\n";
        for (int i = 0; i < getDimension().width; i++) {
            ret += "(";
            for (int j = 0; j < getDimension().height; j++) {  
                ret += String.format("%3d", datos[i][j]); 
                if (j != getDimension().height - 1) ret += ", ";
            } 
            ret += ")";
            if (i != getDimension().width - 1) ret += ",";
            ret += "\n";
        } 
        ret += "]\n";
        return ret;
    }
}
