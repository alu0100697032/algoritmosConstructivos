import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

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
	private int[][] matrizAfinidades;

	public Grafo(int numeroVertices, ArrayList<Integer> afinidades) {
		//inicializar atributos
		setNumeroVertices(numeroVertices);
		setAfinidades(afinidades);
		//inicializar matriz
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
	
	/*************************
	 **** GREEDY ALGORITHM ***
	 ************************/
	
	public HashMap<Integer, Integer> algoritmoVoraz(){
		//Hash que contiene los vertices de la lista
		HashMap<Integer, Integer> S = new HashMap<>(); 
		HashMap<Integer, Integer> S_ = new HashMap<>(); 
		//variables usadas para guardar informacion necesaria para almacenar los nodos en el hash
		int clave;
		float valorFuncionObjetivo;
		float valorFuncionObjetivoAux;
		int valorSumaAfinidades;
		int valorSumaAfinidadesAux;
		int numeroInsertarHash;
		//auxiliares para saber en que posición está el maximo
		int auxi = 0; 
		int auxj = 0;
		//maxima afinidad
		int max = 0;
		//RECORRER LA MATRIZ DE AFINIDADES PARA ENCONTRAR EL MAXIMO
		for(int i = 0; i < numeroVertices; i++){
			for(int j = 0; j < numeroVertices; j++){
				if(matrizAfinidades[i][j] > max){
					max = matrizAfinidades[i][j];
					auxi = i;
					auxj = j;
				}
			}
		}
		//SE AÑADEN LOS VERTICES AL HASH
		S.put(auxi, auxi);
		S.put(auxj, auxj);
		valorSumaAfinidades = max;
		valorSumaAfinidadesAux = valorSumaAfinidades;
		valorFuncionObjetivo = valorSumaAfinidades/S.size();
		valorFuncionObjetivoAux = valorFuncionObjetivo;
		numeroInsertarHash = auxi;	
		//REPETIR HASTA QUE S SEA IGUAL A S_
		while(S_.size() != S.size()){
			S_ = (HashMap<Integer, Integer>) S.clone(); //se iguala los hash
			Iterator it = S_.entrySet().iterator();
			//recorremos el hash para ver si las afinidades mejoran el valor objetivo
			while(it.hasNext()){
			  Map.Entry e = (Map.Entry)it.next();
			  clave = (int) e.getKey();
			  //para cada nodo del hash recorremos las afinidades(aristas)
			  for(int i = 0 ; i < numeroVertices; i++){
				  if(S_.containsKey(i)) //si el nodo ya está en el hash, no se duplica
					  continue;
				  else{//si no esta en el hash, se comprueba si mejora el valor objetivo, si mejora, se cambia (se trabaja sobre los auxiliares)
					  if(((matrizAfinidades[clave][i] + valorSumaAfinidades)/S.size()) > valorFuncionObjetivoAux){
						  valorSumaAfinidadesAux = matrizAfinidades[clave][i] + valorSumaAfinidades;
						  numeroInsertarHash = i;
						  valorFuncionObjetivoAux = (float)((matrizAfinidades[clave][i] + valorSumaAfinidades)/(S.size()+1));
					  }
				  }//END IF ELSE
			  }//END FOR
			}//END SECOND WHILE
			if(S.containsKey(numeroInsertarHash))//si el nodo que se va a insertar ya esta en el hash no se duplica
				continue;
			else{//en el caso contrario se mente en el hash
				S.put(numeroInsertarHash, numeroInsertarHash);
				valorFuncionObjetivo = valorFuncionObjetivoAux;
				valorSumaAfinidades = valorSumaAfinidadesAux;
			}
		}//END FRIST WHILE
		return S;
	}
	
	/*****************************
	 ******* GRASP ALGORITHM *****
	 *****************************/
	
	public HashMap<Integer, Integer> algoritmoGRASP(int lrc){
		//Hash que contiene los vertices de la lista
		HashMap<Integer, Integer> S = new HashMap<>(); 
		HashMap<Integer, Integer> S_ = new HashMap<>(); 
		HashMap<Integer, Integer> LRC = new HashMap<>(); 
		//variables usadas para guardar informacion necesaria para almacenar los nodos en el hash
		int clave;
		float valorFuncionObjetivo;
		float valorFuncionObjetivoAux;
		int valorSumaAfinidades;
		int valorSumaAfinidadesAux;
		int numeroInsertarHash;
		//auxiliares para saber en que posición está el maximo
		int auxi = 0; 
		int auxj = 0;
		//maxima afinidad
		int max = 0;
		//RECORRER LA MATRIZ DE AFINIDADES PARA ENCONTRAR EL MAXIMO
		for(int i = 0; i < numeroVertices; i++){
			for(int j = 0; j < numeroVertices; j++){
				if(matrizAfinidades[i][j] > max){
					max = matrizAfinidades[i][j];
					auxi = i;
					auxj = j;
				}
			}
		}
		//SE AÑADEN LOS VERTICES AL HASH
		S.put(auxi, max);
		S.put(auxj, max);
		valorSumaAfinidades = max;
		valorSumaAfinidadesAux = valorSumaAfinidades;
		valorFuncionObjetivo = valorSumaAfinidades/S.size();
		valorFuncionObjetivoAux = valorFuncionObjetivo;
		numeroInsertarHash = auxi;	
		//REPETIR HASTA QUE S SEA IGUAL A S_
		while(S_.size() != S.size()){
			S_ = (HashMap<Integer, Integer>) S.clone(); //se iguala los hash
			LRC.clear();
			Iterator it = S_.entrySet().iterator();
			//recorremos el hash para ver si las afinidades mejoran el valor objetivo
			while(it.hasNext()){
			  Map.Entry e = (Map.Entry)it.next();
			  clave = (int) e.getKey();
			  //para cada nodo del hash recorremos las afinidades(aristas)
			  for(int i = 0 ; i < numeroVertices; i++){
				  if(S_.containsKey(i)) //si el nodo ya está en el hash, no se duplica
					  continue;
				  else{//si no esta en el hash, se comprueba si mejora el valor objetivo, si mejora, se cambia (se trabaja sobre los auxiliares)
					  if(((matrizAfinidades[clave][i] + valorSumaAfinidades)/S.size()) > valorFuncionObjetivoAux){
						  LRC.put(i, valorSumaAfinidadesAux);
						  valorSumaAfinidadesAux = matrizAfinidades[clave][i] + valorSumaAfinidades;
						  valorFuncionObjetivoAux = (float)((matrizAfinidades[clave][i] + valorSumaAfinidades)/(S.size()+1));
					  }
				  }//END IF ELSE
			  }//END FOR
			}//END SECOND WHILE
			int auxnumber = 0;
			if(LRC.size() <= lrc){
				for(int i = 0; i < LRC.size(); i++){
					if(LRC.containsKey(i)){
						if(max < LRC.get(i)){
							max = LRC.get(i);
							auxnumber = i;
						}
					}
				}
			}else{
				max = -99999999;
				for(int i = 0; i < lrc; i++){
					Random rnd = new Random();
					int number = (int)(rnd.nextDouble() * lrc + 1);
					if(LRC.containsKey(number)){
						if(max < LRC.get(number)){
							max = LRC.get(number);
							auxnumber = number;
						}
					}
				}
			}
			if(valorFuncionObjetivo < (max/S.size()+1)){
				valorFuncionObjetivo = (max/S.size()+1);
				S.put(auxnumber, auxnumber);
				valorSumaAfinidades = max;
				System.out.println(S.size());
			}
		}//END FRIST WHILE
		return S;
	}
	
	/*
	 * Metodo para mostrar la información del grafo
	 */
	
	public void mostrarGrafo () {
		System.out.println("Número de vértices: " + getNumeroVertices());
		System.out.println("Matriz de afinidades: ");
		for(int i = 0; i < numeroVertices; i++){
			for(int j = 0; j < numeroVertices; j++){
				System.out.print(matrizAfinidades[i][j] + " ");
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
