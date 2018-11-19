
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Filename:   CourseSchedulerUtil.java
 * Project:    p4
 * Authors:    Debra Deppeler
 * 
 * Use this class for implementing Course Planner
 * @param <T> represents type
 */

public class CourseSchedulerUtil<T> {
    
    /**
     * Graph object
     */
    private GraphImpl<T> graphImpl;
    
    
    /**
     * constructor to initialize a graph object
     */
    public CourseSchedulerUtil() {
        this.graphImpl = new GraphImpl<T>();
    }
    
    /**
    * createEntity method is for parsing the input json file 
    * @return array of Entity object which stores information 
    * about a single course including its name and its prerequisites
    * @throws Exception like FileNotFound, JsonParseException
    */
    @SuppressWarnings("rawtypes")
    public Entity[] createEntity(String fileName) throws Exception {
        JSONParser parser = new JSONParser();
        try {     	
                // Sets up the 2 iterators we need for the 2 JSONArrays in the formatting
                // Also sets up the parsing and starting JSONobject and JSONArray
        		Object obj = parser.parse(new FileReader(fileName));
        		JSONObject jsonObject = (JSONObject) obj;		
        		JSONArray courseArray = (JSONArray) jsonObject.get("courses");
        		Iterator iterator1 = courseArray.iterator();
        		Iterator iterator2;
        		//Our entity[] and the counter for entities
        		Entity[] courseInfoArray = new Entity[100];
                int mainCounter = 0;
                
                // Iterates the JSONArray of courses 
        		while (iterator1.hasNext()) {    
        		    //Makes a new entity to store info in
                    Entity<String> newEnt = new Entity<String>();
                    int counter = 0;
                    
                    //Gets the course name
        		    JSONObject currCourse = (JSONObject) iterator1.next();
        		    String courseName = (String) currCourse.get("name");
        		    
        		    //Gets a JSONArray of the prerequisites
        		    JSONArray preReq = (JSONArray) currCourse.get("prerequisites");
        		    iterator2 = preReq.iterator();		    
                    String[] prerequisites = new String[100];
                    
                    //Iterates through the JSONArray of prerequisites and saves it to a normal array
        		    while(iterator2.hasNext()){    		        
        		        prerequisites[counter] = (String) iterator2.next();
                        counter++; 
        		    }
        		    //Condenses the prereq array to only what we need
        		    String[] finalPreReqArray = new String[counter];
                    for(int i = 0; i < counter; ++i){
                        finalPreReqArray[i] = prerequisites[i];
                    }        		    
        		    // Sets the name and prereqs of the entity item and puts the entity into
        		    // the final array
        		    newEnt.setName(courseName);
        		    newEnt.setPrerequisites(finalPreReqArray);
        		    courseInfoArray[mainCounter] = newEnt;
        		    ++mainCounter;
        		}
        		//Condenses the entity array to ones that are filed
                Entity[] finalEntityArray = new Entity[mainCounter];
                for(int i = 0; i < mainCounter; ++i){
                    finalEntityArray[i] = courseInfoArray[i];
                }
        		return courseInfoArray;
        		
        	
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace(); }
        catch (Exception e) {e.printStackTrace();}
        return null;
        
    }
    
    
    /**
     * Construct a directed graph from the created entity object 
     * @param entities: which has information about a single course 
     * including its name and its prerequisites
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void constructGraph(Entity[] entities) {
        //TODO: implement this method
        // Adds all the vertices of entities 
        for(int i = 0; i < entities.length; ++i){
            if(entities[i] != null){
                graphImpl.addVertex((T)entities[i].getName());
            }
        }
        // Adds an edge between the prerequisites for each vertex
        for(int i = 0; i < entities.length; ++i){
            if(entities[i] != null){
                for(int k = 0; k < entities[i].getPrerequisites().length; ++k){
                    graphImpl.addEdge((T)entities[i].getName(), (T)entities[i].getPrerequisites()[k]);
                }
            }
        }
    }
    
    
    /**
     * Returns all the unique available courses
     * @return the sorted list of all available courses
     */
    public Set<T> getAllCourses() {
        
        return null;
    }
    
    
    /**
     * To check whether all given courses can be completed or not
     * @return boolean true if all given courses can be completed,
     * otherwise false
     * @throws Exception
     */
    public boolean canCoursesBeCompleted() throws Exception {
        //TODO: implement this method
        return false;

    }
    
    
    /**
     * The order of courses in which the courses has to be taken
     * @return the list of courses in the order it has to be taken
     * @throws Exception when courses can't be completed in any order
     */
    public List<T> getSubjectOrder() throws Exception {
        //TODO: implement this method
        return null;

    }

        
    /**
     * The minimum course required to be taken for a given course
     * @param courseName 
     * @return the number of minimum courses needed for a given course
     */
    public int getMinimalCourseCompletion(T courseName) throws Exception {
        //TODO: implement this method
        return -1;
        
    }
    
}
