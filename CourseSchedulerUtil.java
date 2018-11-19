
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
    private T[] prerequisites; 
    
    
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
        	
        		Object obj = parser.parse(new FileReader(fileName));
        		JSONObject jsonObject = (JSONObject) obj;
        		
        		JSONArray  courseArray = (JSONArray) jsonObject.get("courses");
        		
        		Iterator<String> iterator = courseArray.iterator();
        		
        		Entity[] courseInfoArray = new Entity[16];
        		
        		int counter = 0;
        		
        		while (iterator.hasNext()) {
        			
        			
        			Entity newEnt = new Entity();
        			
        			String course = (String) jsonObject.get("name");
        			
        			prerequisites = (T[]) jsonObject.get("prerequisites");
        			
        			newEnt.setName(course);
        			
        			newEnt.setPrerequisites(prerequisites);
        			
        			courseInfoArray[counter] = newEnt;
        			
        			counter++;
        			
        			
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
     * @param entities which has information about a single course 
     * including its name and its prerequisites
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void constructGraph(Entity[] entities) {
        //TODO: implement this method

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
