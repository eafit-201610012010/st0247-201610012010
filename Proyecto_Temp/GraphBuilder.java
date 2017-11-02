import java.util.*;
import java.nio.*;
import java.io.*;

/*
 * Leer un archivo por paso de
 * Parametros y construir su grafo.
 *
 * Autor: Juan Gonzalo Quiroz Cadavid
*/

public class GraphBuilder{

    private static MedellinGraph medellinGraph;
    private static MedellinGraphTest medellinGraphTest;
    
    public static void main(String [ ] args){
	//Creat Graph.

	medellinGraph = new MedellinGraph();
	medellinGraphTest = new MedellinGraphTest();
	Held_Karp_0_1 hel_kapr;
	

	
	//Read the File.
	if(args.length >= 1){
	    readFile(args[0]);
	    if(args.length == 2){

		held_kapr =
		    new Held_Karp_0_1(get_A(getNodesURL()));
		
	    }else{
		System.err.println("Ingrese la URL");
	    }
	}else{
	    System.err.println("Ingrese el nombre del fichero!");
	}

    }

    public DigraphAM  get_A (ArrayList<NodeContainer> nodesCont){
	DigraphAM digraph = new DigraphAM(nodesCont.size());
	int size = nodesCont.size();
	for(int i = 0; i < size; i ++){
	    for(int j = 0; j < size; j ++){
		if(j == i ) continue;
		
	    }
	}
	
    }


    public static ArrayList<NodeContainer> [] getNodesURL(String URL){
	ArrayList<NodeContainer> result = new ArrayList<NodeContainer>();

	String extra_data = "https://www.google.es/maps/dir/";
	String first_state = URL.substring(extra_data, URL.length);

	System.out.println(first_state);

	String [] pair_cordenadas = first_state.Split("/");
	String [] tuple;
	int xC,yC, intPos = 1;
	for(String pairC : pair_cordenadas){
	     tuple = pairC.split[","];

	     if(tuple[0].charAt(0) == '@')
		 break;

	     xC = Integer.parseInt(tuple[0]);
	     yC = Integer.parseInt(tuple[1]);
	     //FALTA
	     result.add(intPos,medellinGraph.foundPerCordenadas(xC, yC));
	     intPos++;
	     
	}
	tuple = pairC.split[","];
	
	xC = Integer.parseInt(tuple[0]);
	yC = Integer.parseInt(tuple[1]);
	
	result.add(0,medellinGraph.foundPerCordenadas(xC, yC));

	return result;
	

    }
    

    
    public static void buildNode(String line){
	//Lets split the line.
	String []Test = line.split(" ");

	
	double idNode = Double.parseDouble(Test[0]);
	double cordenadaY = Double.parseDouble(Test[1]);
	double cordenadaX = Double.parseDouble(Test[2]);
	String name = "NoName";
	if(Test.length >= 4){
	    name = "";
	    for(int i = 3; i < Test.length; i++)
		name += Test[i] + " ";
	}

	medellinGraph.addNodeToContainerNode(idNode, cordenadaY,
					     cordenadaX,name);
    }
        public static void connect2Contaniers(String line){
	//Lets split the line.
	String []Test = line.split(" ");

	
	double idContainerFrom = Double.parseDouble(Test[0]);
	double idContainerTo = Double.parseDouble(Test[1]);
	double length = Double.parseDouble(Test[2]);
	String name = "UnKnown";
	
	if(Test.length > 4){
	    name = "";
	    for(int i = 3; i < Test.length; i++)
		name += Test[i] + " ";
	}

	medellinGraph.connectContainer(idContainerFrom, idContainerTo,
				       length,name);
    }
    

    public static void readFile(String fileDirection){

        String fileName = fileDirection;
        String line = null;
        try {
	    
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
	    /*
	     * A centinel, when the moment to read the conections
	     * apper, this Centinel swicht the Operations.
	     */
	    boolean cambio = false;
            while((line = bufferedReader.readLine()) != null) {
		
		if(!cambio){
		    if(line.equals("[BREAK]")){
			cambio = true;
			continue;
		    }
		    
		    buildNode(line);
		}else{
		    
		    connect2Contaniers(line);

		}
                //System.out.println(line);
            }
	    medellinGraphTest.setMedellinGraph(medellinGraph);
	    medellinGraphTest.testConnections(1397149003);
	    bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                   
            // ex.printStackTrace();
        }
    }


    


}
