import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * AUTOR: VÍCTOR HERNÁNDEZ PÉREZ
 * ASIGNATURA: DISEÑO Y ANÁLISIS DE ALGORITMOS
 * E-MAIL: alu0100697032@ull.edu.es
 * 14/04/2015
 * Main.java
 * AlgoritmosConstructivos
 */

/**
 * @author Victor
 *
 */
public class Main {
	private static Grafo grafo;
	/**
	 * @param args
	 * @throws IOException 
	 */

	public static void main (String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/*variables para guardar el contenido del fichero para luego construir el objeto grafo*/
		int numeroVertices; 
		String aux;
		ArrayList<Integer> afinidades = new ArrayList<Integer>();
		
		/*Se necesita para la lectura del fichero*/
	    FileReader f = new FileReader("src/instancias/4.txt");
	    BufferedReader b = new BufferedReader(f);
	    
	    numeroVertices = Integer.parseInt(b.readLine());//guardamos el numero de vertices
	    
	    /*guardamos las afinidades en un array*/
	    while((aux = b.readLine())!=null) {
	        afinidades.add(Integer.parseInt(aux));
	    }
	    /*construimos el objeto grafo de la clase grafo*/
	    grafo = new Grafo(numeroVertices, afinidades);
	    b.close();
	    
	    //grafo.mostrarGrafo();
	    //System.out.println();
	    System.out.println(grafo.algoritmoVoraz());
	}

}
