import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Filename:   GraphImpl.java
 * Project:    p4
 * Course:     cs400 
 * Authors:    
 * Due Date:   
 * 
 * T is the label of a vertex, and List<T> is a list of
 * adjacent vertices for that vertex.
 *
 * Additional credits: 
 *
 * Bugs or other notes: 
 *
 * @param <T> type of a vertex
 */
public class GraphImpl<T> implements GraphADT<T> {

    // YOU MAY ADD ADDITIONAL private members
    // YOU MAY NOT ADD ADDITIONAL public members
    private int size, order;

    /**
     * Store the vertices and the vertice's adjacent vertices
     */
    private Map<T, List<T>> verticesMap; 
    
    
    /**
     * Construct and initialize and empty Graph
     */ 
    public GraphImpl() {
        verticesMap = new HashMap<T, List<T>>();
        size = 0;
        order = 0;
        // you may initialize additional data members here
    }

    public void addVertex(T vertex) {
        //Will make a vertex if given non-null and non duplicate vertex key
        if(vertex != null && !hasVertex(vertex)){
            verticesMap.put(vertex, new ArrayList<T>());           
            ++order;
        }
    }

    public void removeVertex(T vertex) {
        if(vertex != null && hasVertex(vertex)){
            // Removes the edges that are prerequisites of vertex
            for(int i = 0; i < verticesMap.get(vertex).size(); ++i){
                removeEdge(vertex, verticesMap.get(vertex).get(i));
            }
            // Removes the edges that have vertex as a prerequisites
            T[] vertArray = (T[])getAllVertices().toArray();
            for(int i = 0; i < vertArray.length; ++i){
                if(verticesMap.get(vertArray[i]).contains(vertex)){
                    removeEdge(vertArray[i], vertex);
                }
            }
            verticesMap.remove(vertex);    
            --order;
        }
    }

    public void addEdge(T vertex1, T vertex2) {
        // Will add an edge if given non-null values and edge that isn't already there
        if(vertex1 != null && vertex2 != null && !verticesMap.get(vertex1).contains(vertex2)){
            verticesMap.get(vertex1).add(vertex2);
            ++size;
        }
    }
    
    public void removeEdge(T vertex1, T vertex2) {
        // Will remove edge with using non-null values and an edge already exists
        if(vertex1 != null && vertex2 != null && verticesMap.get(vertex1).contains(vertex2)){
            verticesMap.get(vertex1).remove(vertex2);
            --size;
        }
    }    
    
    public Set<T> getAllVertices() {
        return verticesMap.keySet();
    }

    public List<T> getAdjacentVerticesOf(T vertex) {
        return verticesMap.get(vertex);
    }
    
    public boolean hasVertex(T vertex) {
        return verticesMap.containsKey(vertex);
    }

    public int order() {
        return order;
    }

    public int size() {
        return size;
    }
    
    
    /**
     * Prints the graph for the reference
     * DO NOT EDIT THIS FUNCTION
     * DO ENSURE THAT YOUR verticesMap is being used 
     * to represent the vertices and edges of this graph.
     */
    public void printGraph() {

        for ( T vertex : verticesMap.keySet() ) {
            if ( verticesMap.get(vertex).size() != 0) {
                for (T edges : verticesMap.get(vertex)) {
                    System.out.println(vertex + " -> " + edges + " ");
                }
            } else {
                System.out.println(vertex + " -> " + " " );
            }
        }
    }
}
