import java.util.*;
import java.math.*;

public class Held_Karp_0_1{
    private boolean udeBug = false;
    private BitMap bitMap;
    private Digraph diGraph;
    // Pair <Father, size>
    private int node_Vertex = 0;
    private int [][] father;
    private int [][] weight;
    
    public Held_Karp_0_1(Digraph diGraph){
	this.diGraph = diGraph;
	bitMap = new BitMap();
	createMatrix();
    }

    public void printPath(){//(Pair<Integer,Integer> inicialNode){
	for(int i = 0; i < father.length; i++){
	    for(int j = 0; j < father.length; j++){
		System.out.print(father[i][j] + "," + weight[i][j] +
				 " | ");
	    }
	    System.out.println("");
	}

    }
    
    public  int makeHeld_Karp(){
	int size = diGraph.size() -1;
	boolean subSet [] = new boolean[size];
	
	for(int i = 0; i < size; i++)
	    subSet[i] = true;
	
	Pair<Integer,Integer> result = seLeTiene(node_Vertex,subSet);
	if(udeBug)
	    printPath();
	return result.second;
    }
    //                                    Index
    //El pair final a retornar es el de [0,{1,2,3}];
    private Pair<Integer,Integer> seLeTiene(int index,boolean [] subGroup)
    {


	/*
	 * Sera que este valor ya fue calculado
	 * y estara esperando en la tabla?
	 */
	if(getPair(index,bitMap.getNumber(subGroup)) != null){
	    /*
	     * Si seño! ya estaba calculado
	     * Que hermosura no?,retorne  ese retoño
	     */
	    return getPair(index, bitMap.getNumber(subGroup));
		
	}

	/*
	 * Mijo nos toco meternos en el fango
	 * a ver, veamos primero si sale
	 * de una y dejamos de pendejaiar con
	 * ese.
	 */

	if(bitMap.getLength(subGroup) == 0){
	    /* vean pues, salio con que es
	     * de la forma [1,0], que maravilla
	     * estamos en el fondo! jajajaj
	     */

	    //Estara  ya guardado?

	    if(getPair(index,node_Vertex) != null){
		// mas hermoso no puede ser.
		return getPair(index,node_Vertex);
	    }
	    /*
	     * Patron.. no esta calculado..
	     * ¿ Que no que?! Calculen a ese hp YA MISMO.
	     */
	    
	    
	    int distance = 0;
	    //Veamos desde el nodo Vertice a este cuanto es

	    distance += diGraph.getWeight(node_Vertex,index);

	    /* 
	     * Listo patron.
	     * Y entonces? se va a guardar solo?
	     * CUADREME ESO YA MISMO!.
	     */

	    Pair<Integer,Integer> newBaby =
		new Pair<Integer,Integer>(node_Vertex, distance);

	    setPair(index,node_Vertex,newBaby);
	    return newBaby;
		 
	}
	
	/*
	 * Ahhh no esta calculado, comencemos
	 * a descomponerlo.
	 */
	    
	ArrayList<Pair<Integer,Integer>> results =
	    new ArrayList<Pair<Integer,Integer>>();
	
	for(int i = 0; i < subGroup.length; i++){
	    if(!subGroup[i]) continue;

	    subGroup[i] = false;
	    int newIndex = i + 1;
	    //FUCK
	    /*
	     * Y este hp que? ya estara o todavia nada?
	     */

	    if(getPair(newIndex,bitMap.getNumber(subGroup)) != null){
		/*
		 * Eh que maravilla ome, ya esta calculado
		 * agregemoslo para despues ver si es la
		 * mejor Opcion
		 */



		Pair <Integer,Integer> newPair = getPair(newIndex,
							 bitMap.getNumber(subGroup));
		    
		 
		int distance = diGraph.getWeight(newIndex, index) +
		    newPair.second;
		
		
		results.add(new Pair<Integer,Integer>(newIndex, distance));
						      
	    }else{
		/*
		 * Que no que?
		 * Despejeme eso ya Mismmo.
		 */
		Pair <Integer,Integer> newPair =
		    seLeTiene(newIndex,subGroup);
		/*
		 * Jefe, despejado..
		 * Listo, consruyan el nuevo posible
		 * Vertice, y guardelo como Opcion..
		 * No se les olvide sumar Distancias..
		 */
		int distance = diGraph.getWeight(newIndex, index) +
		    newPair.second;
		
		
		results.add(new Pair<Integer,Integer>(newIndex, distance));
				
	    }

	    

	    subGroup[i] = true;


	}

	setPair(index,bitMap.getNumber(subGroup),getMinimun(results));
	return getPair(index,bitMap.getNumber(subGroup));

    }

    private Pair<Integer,Integer> getMinimun(ArrayList<Pair<Integer,Integer>> array){
	
	int min = Integer.MAX_VALUE;
	Pair<Integer,Integer> minPair = null;;
	int distance = 0;
	
	for(Pair<Integer,Integer> pairs : array){

	    distance = pairs.second;
	    
	    if(distance < min){
		minPair = pairs;
		min = distance;
	    }
	}
	

	return minPair;
	
	
    }
    
    /*
     * This method create our 
     * matrix; wich one, we are
     * going to use through the
     * whole program.
     *
     * Rows = diGraph size!
     * Columns = 2 ^ (digraphSize - 1)
     * This is because we don't need to
     * make combinations with the node
     * vertex (in other words, we are't including
     * the vertex node.)
     */
    private void createMatrix(){
	int m_Rows, m_Columns;

	m_Rows = diGraph.size();
	m_Columns =(int) Math.pow(2,m_Rows - 1 );

	father = new int[m_Rows][m_Columns];
	weight = new int[m_Rows][m_Columns];
	
    }

    private Pair<Integer, Integer> getPair(int row, int column ){
	int fatherValue = father[row][column];
	int weightValue = weight[row][column];

	if(weightValue == 0)
	    return null;
	
	return new Pair<Integer,Integer>(fatherValue, weightValue);

    }

    private void setPair(int row, int column, Pair<Integer,Integer> pair){
	father[row][column] = pair.first;
	weight[row][column] = pair.second;

    }
}
