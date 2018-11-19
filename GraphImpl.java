import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Filename:   GraphImpl.java
 * Project:    p4
 * Course:     cs400 
 * Authors:    Devin Porter, Arjun Sachar
 * Due Date:   11/19/18
 * 
 * T is the label of a vertex, and List<T> is a list of
 * adjacent vertices for that vertex.
 *
 * Additional credits: Java8 Documentation of HashMap, List, Set
 *
 * Bugs or other notes:
 *
 * @param <T> type of a vertex
 */
public class GraphImpl<T> implements GraphADT<T> {

    // YOU MAY ADD ADDITIONAL private members
    // YOU MAY NOT ADD ADDITIONAL public members
    
    /**
     * The size and order of the graph
     */
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
    }
    /**
     *  Will make a vertex if given non-null and non duplicate vertex key
     *  @param vertex: the vertex to be added
     */
    public void addVertex(T vertex) {
        if(vertex != null && !hasVertex(vertex)){
            verticesMap.put(vertex, new ArrayList<T>());           
            ++order;
        }
    }
    /**
     * Removes the given vertex and any edges that involve it
     * @param vertex: the vertex to be removed
     */
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
    /**
     * Makes an edge between the two vertexes pointing from 1 to 2 
     * @param vertex1: the vertex to direct from
     * @param vertex2: the vertex to direct to
     */
    public void addEdge(T vertex1, T vertex2) {
        // Will add an edge if given non-null values and edge that isn't already there
        if(vertex1 != null && vertex2 != null && !verticesMap.get(vertex1).contains(vertex2) 
                        && hasVertex(vertex1) && hasVertex(vertex2)){
            verticesMap.get(vertex1).add(vertex2);
            ++size;
        }
    }
    /**
     * Removes an edge between the two vertexes pointing from 1 to 2 
     * @param vertex1: the vertex to direct from
     * @param vertex2: the vertex to direct to
     */
    public void removeEdge(T vertex1, T vertex2) {
        // Will remove edge with using non-null values and an edge already exists
        if(vertex1 != null && vertex2 != null && verticesMap.get(vertex1).contains(vertex2)
                        && hasVertex(vertex1) && hasVertex(vertex2)){
            verticesMap.get(vertex1).remove(vertex2);
            --size;
        }
    }    
    /**
     * Returns a set of all the keys AKA:of all the vertices
     * @return set of vertices
     */
    public Set<T> getAllVertices() {
        return verticesMap.keySet();
    }
    /**
     * Returns a list of the vertices that are pointed at from the current vertex
     * @param vertex: vertex to get successors of
     * @return list of successor vertices
     */
    public List<T> getAdjacentVerticesOf(T vertex) {
        return verticesMap.get(vertex);
    }
    /**
     * Returns boolean based on if the vertex is already in the graph
     * @param vertex: vertex to check if it exists
     * @return true if vertex is in the graph, else false
     */
    public boolean hasVertex(T vertex) {
        return verticesMap.containsKey(vertex);
    }
    /**
     * Returns the order of the graph (Vertices)
     * @return order of graph
     */
    public int order() {
        return order;
    }
    /**
     * Returns the size of the graph (Edges)
     * @return size of graph
     */
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
