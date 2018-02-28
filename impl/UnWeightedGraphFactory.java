package impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import adt.Graph;

/**
 * GraphFactory
 * 
 * File format (based on files provided by Sedgwick):
 * number of vertices
 * number of edges
 * each edge: vertex vertex (weight)
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * June 18, 2015
 */
public class UnWeightedGraphFactory {    
    public static Graph directedALGraphFromFile(String filename) {
        try {
            Scanner file = new Scanner(new File(filename));
            AdjListGraph.ALGBuilder builder = 
                    new AdjListGraph.ALGBuilder(Integer.parseInt(file.nextLine()));
            int numEdges = Integer.parseInt(file.nextLine());
            for (int i = 0; i < numEdges; i++) {
                StringTokenizer tokey = new StringTokenizer(file.nextLine());
                builder.connect(Integer.parseInt(tokey.nextToken()), 
                        Integer.parseInt(tokey.nextToken()));
            }
            file.close();
            return builder.getGraph(); 
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return null;
        }
    }

    
    public static Graph directedAMGraphFromFile(String filename) {
        try {
            Scanner file = new Scanner(new File(filename));

            AdjMatrixGraph.AMGBuilder builder = 
                    new AdjMatrixGraph.AMGBuilder(Integer.parseInt(file.nextLine()));
            int numEdges = Integer.parseInt(file.nextLine());
            for (int i = 0; i < numEdges; i++) {
                StringTokenizer tokey = new StringTokenizer(file.nextLine());
                builder.connect(Integer.parseInt(tokey.nextToken()), 
                        Integer.parseInt(tokey.nextToken()));
            }
            file.close();
            return builder.getGraph();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return null;
        }

    }
    
}
