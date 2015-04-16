import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * AUTOR: VÍCTOR HERNÁNDEZ PÉREZ ASIGNATURA: DISEÑO Y ANÁLISIS DE ALGORITMOS
 * E-MAIL: alu0100697032@ull.edu.es 14/04/2015 Grafo.java
 * AlgoritmosConstructivos
 */

/**
 * @author Victor
 * 
 */
public class Grafo {

	private int numeroVertices;
	private ArrayList<Integer> afinidades;
	private ArrayList<Point> points;
	private int[][] matrizAfinidades;

	public Grafo(int numeroVertices, ArrayList<Integer> afinidades) {
		/*
		 * Inicializar atributos
		 */
		 
		setNumeroVertices(numeroVertices);
		setAfinidades(afinidades);
		setPoints(new ArrayList<Point>());
		matrizAfinidades = new int[numeroVertices][numeroVertices];
		for(int i = 0; i < numeroVertices; i++){
			for(int j = 0; j < numeroVertices; j++){
				matrizAfinidades[i][j] = -9999999;
			}
		}
		int aux1 = 0;// sirve para mantener el primer vértice por el que vamos recorriendo
		int aux2 = 1;// sirve para mantener el segundo vértice por el que vamos recorriendo
		int aux3 = aux2; // sirve para recorrer cada afinidad entre los pares de vertices 
		for (int i = 0; i < afinidades.size(); i++) {//recorremos en numero de afinidades que haya
			//points.add(new Point(aux1, aux3));//creamos un nuevo punto y lo añadimos al array 
			matrizAfinidades[aux1][aux3] = afinidades.get(i);
			matrizAfinidades[aux3][aux1] = afinidades.get(i);
			if (aux3 + 1 >= numeroVertices) { //si ya se ha recorrido cada afinidad del un vertice, se pasa al otro
				aux1++;
				aux2++;
				aux3 = aux2;
			} else if ((aux1 == aux3 + 1) && (aux3 + 2 < numeroVertices)) {//se comprueba que no sea del mismo vertice al mismo vertice
				aux3 = aux3 + 2;
			} else //se pasa a la siguiente afinidad
				aux3++;
		}
	}
	/*
	 * Algoritmo voraz
	 */
	public ArrayList<Integer> algoritmoVoraz(){
		ArrayList<Integer> S = new ArrayList<Integer>();//Lista que se va a devolver con la solucion
		ArrayList<Integer> S_ = new ArrayList<Integer>();
		float max = 0;//maxima afinidad
		int aux = 0;//auxiliar para saber en que posición está el maximo
		
		for(int i = 0; i < afinidades.size(); i++){//recorrer lista de afinidades para encontrar el maximo
			if(afinidades.get(i) > max){//si alguno mejora max se cambia
				max = afinidades.get(i);
				aux = i;
			}
		}
		//se añaden los vertices a la lista
		S.add(points.get(aux).x);
		S.add(points.get(aux).y);
		
		while(S_ != S){
			S_ = S;
			
		}
		return S;
	}
	
	/*Metodo para mostrar la información del grafo*/
	
	public void mostrarGrafo () {
		/*System.out.println("Número de vértices: " + getNumeroVertices());
		System.out.println("Lista de afinidades: ");
		for (int i = 0; i < afinidades.size(); i++) {
			System.out.println(i + " " + afinidades.get(i) + " " + points.get(i));
		}*/
		for(int i = 0; i < numeroVertices; i++){
			for(int j = 0; j < numeroVertices; j++){
				System.out.print(matrizAfinidades[i][j]);
			}
			System.out.println();
		}
	}

	/* GETTERS Y SETTERS */
	/**
	 * @return the numeroVertices
	 */
	public int getNumeroVertices () {
		return numeroVertices;
	}

	/**
	 * @param numeroVertices
	 *            the numeroVertices to set
	 */
	public void setNumeroVertices (int numeroVertices) {
		this.numeroVertices = numeroVertices;
	}

	/**
	 * @return the afinidades
	 */
	public ArrayList<Integer> getAfinidades () {
		return afinidades;
	}

	/**
	 * @param afinidades
	 *            the afinidades to set
	 */
	public void setAfinidades (ArrayList<Integer> afinidades) {
		this.afinidades = afinidades;
	}

	/**
	 * @return the points
	 */
	public ArrayList<Point> getPoints () {
		return points;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints (ArrayList<Point> points) {
		this.points = points;
	}
	/**
	 * @return the matrizAfinidades
	 */
	public int[][] getMatrizAfinidades () {
		return matrizAfinidades;
	}
	/**
	 * @param matrizAfinidades the matrizAfinidades to set
	 */
	public void setMatrizAfinidades (int[][] matrizAfinidades) {
		this.matrizAfinidades = matrizAfinidades;
	}

}
