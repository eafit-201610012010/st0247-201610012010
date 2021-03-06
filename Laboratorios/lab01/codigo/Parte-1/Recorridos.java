import java.util.*;
//import java.Collections.*;
/**
 * Clase que contiene los recorridos en profundidad (DFS) y anchura (BFS) para
 * la clase Digraph
 * 
 * @author Mateo Agudelo
 */
public class Recorridos {
    
    public static void testBFS(){
        DigraphAL biGraphAL = new DigraphAL(5);
         biGraphAL.addArc(0,1,10);
         biGraphAL.addArc(0,2,3);
         biGraphAL.addArc(1,2,1);
         biGraphAL.addArc(1,3,2);
         biGraphAL.addArc(2,1,4);
         biGraphAL.addArc(2,3,8);
         biGraphAL.addArc(2,4,2);
         biGraphAL.addArc(3,4,7);
         biGraphAL.addArc(4,3,9);
        
        ArrayList<Integer> result = bfs(biGraphAL,0); 
        
        for(int number: result){
            System.out.print(number + " ");
        }
        
        
    }
    
    public static void testDFS(){
        Graph g = new DigraphAM(8);
        
        //g.addArc(int source, int destination, int weight);
        
        g.addArc(0,3,0);
        g.addArc(0,1,0);
             
        g.addArc(1,0,0);
        g.addArc(1,2,0);
        g.addArc(1,5,0);
        
        g.addArc(2,1,0);
        g.addArc(2,5,0);
        g.addArc(2,4,0);
        g.addArc(2,0,0);
        
        //g.addArc(3,1,0);
        
        g.addArc(4,7,0);
        
        //g.addArc(5,3,0);
        
        g.addArc(6,5,0);
        
        //g.addArc(7,6,0);

        
        
        ArrayList<Integer> result = dfs(g,3); 
        
        for(int number: result){
            System.out.print(number + " ");
        }
        
        
    }
    
    
    public static ArrayList<Integer> dfs(Graph g, int start) {
        // complete...
        // recuerde: null para nodos aislados!
        boolean[] visitados = new boolean[g.size()];
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        visitados[start] = true;
        list.add(start);
        
        dfs(g,start,visitados,list);
        
        if(list.size()> 1)        
            return list;
        
        return null;
        
    }
    

    
    // recomendado
    private static void dfs(Graph g, int nodo, boolean[] visitados, ArrayList<Integer> list) {
        // complete...
        
        ArrayList<Integer> successors = g.getSuccessors(nodo);
        if (successors == null) return;
        for(int successor: successors){
            if(!visitados[successor]){
                visitados[successor] = true;
                list.add(successor);
                dfs(g,successor,visitados,list);
                
            }
        }
    }
    
    
    

    public static ArrayList<Integer> bfs(Graph g, int start) {
        // complete...
        // recuerde: null para nodos aislados!
        
        ArrayList<Integer> results = new ArrayList<Integer>();
        Queue<Integer> BFSQueue = new LinkedList<Integer>();
        boolean visited [] = new boolean[g.size()];
        
        BFSQueue.add(start);
        
        while(BFSQueue.size() > 0){
            int actual = BFSQueue.poll();
            
            ArrayList<Integer> successors = g.getSuccessors(actual);
            
            if(successors != null){
                for(int node: successors){
                    if(!visited[node]){
                        BFSQueue.add(node);
                        visited[node] = true;
                    }
                }
            }
            
            results.add(actual);
            
        }
        if(results.size()> 1)        
            return results;
        
        return null;
    }

    public static boolean hayCaminoDFS(Graph g, int i, int j) {
        ArrayList<Integer> resultDFS = bfs(g,i);
        if(resultDFS == null)return false;
        for (int number: resultDFS){
            if(number == j) return true;
        } 
        return false;
        
    }
    

    public static boolean hayCaminoBFS(Graph g, int i, int j) {
        // complete...
        
        ArrayList<Integer> resultBFS = dfs(g,i);
        if(resultBFS == null)return false;
        for (int number: resultBFS){
            if(number == j) return true;
        } 
        return false;
    }

}
